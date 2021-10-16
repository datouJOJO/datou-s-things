import sys
import getopt
import Checksum
import BasicSender
import threading
import time

packet_size = 512
sleep_time = 0.05
time_out = 500
window_size = 5

index =0
mutex = threading.Lock()


class Timer(object):
    time_stop = -1
    def __init__(self,time):
        self.time_start = self.time_stop
        self.time = time;

    def start(self):
        if self.time_start == self.time_stop:
            self.time_start = time.time()
            #start time

    def stop(self):
        if self.time_start != self.time_stop:
            self.time_start = self.time_stop
            #stop time

    def running(self):
        return self.time_start != self.time_stop

    def timeout(self):
        if not self.running():
            return False
        else:
            return time.time() - self.time_start >= self.time

send_timer = Timer(time_out)

'''
This is a skeleton sender class. Create a fantastic transport protocol here.
'''
class Sender(BasicSender.BasicSender):
    def __init__(self, dest, port, filename, debug=False):
        super(Sender, self).__init__(dest, port, filename, debug)
        if filename==None:
            filename = raw_input("please input the filename:")
        self.base = 0
        try:
            self.file = open(filename,'rb')
        except IOError:
            print("fail to open file or not existence this file")
            return


    # Main sending loop.
    def start(self):
        msg_type = None
        packets = []
        seq_num = 0
        while True:
            data = self.file.read(packet_size)
            if not data:
                break
            if seq_num == 0:
                msg_type = "start"
            else:
                msg_type = "data"

            packets.append(self.make_packet(msg_type,seq_num,data))
            seq_num += 1
            #pack

        num_packets = len(packets)
        print "the number of the packets is " + str(num_packets+1)
        n = 0
        # for n in range(num_packets):
        #     print packets[n]
        window_size = self.set_window_size(num_packets)
        next_ack = 0

        t = threading.Thread(target=Sender.rece,args = (self,))
        flag = 1
        #new a receiver thread
        while self.base <= num_packets:
            mutex.acquire()
            #locked
            if flag ==1 :
                t.start()
                flag = 0
            while next_ack < self.base + window_size:
                print "send packet" + str(next_ack+1)
                self.send(packets[next_ack])
                next_ack+=1
                #send all the packet in the window

            if not send_timer.running():
                print "start timer"
                send_timer.start()

            while send_timer.running() and not send_timer.timeout():
                mutex.release()
                print "sleep"
                time.sleep(sleep_time)
                mutex.acquire()

            if send_timer.timeout():
                print "timeout"
                send_timer.stop()
                next_ack = self.base
                #send again
            else:
                print "shifting window"
                window_size = self.set_window_size(num_packets)
            mutex.release()

        msg_type = "end"
        packet = self.make_packet(msg_type, seq_num, "")
        self.send(packet)
        response = self.receive()
        print response
        self.file.close()

    def rece(self):
        while True:
            response = self.receive()
            msg_type, seqno, data, checksum = self.split_packet(response)
            print "got ack " + seqno

            if(seqno > self.base):
                mutex.acquire();
                self.base = int(seqno) + 1
                print "ack updated " + str(self.base)
                send_timer.stop()
                mutex.release()
            # print msg_type + " " + seqno +" " + checksum
            # self.handle_new_ack()

    def handle_timeout(self):
        pass

    def handle_new_ack(self, ack):
        self.base+1

    def handle_dup_ack(self, ack):
        pass

    def log(self, msg):
        if self.debug:
            print msg

    def set_window_size(self,num_packet):
        return min(window_size,num_packet-self.base)
'''
This will be run if you run this script from the command line. You should not
change any of this; the grader may rely on the behavior here to test your
submission.
'''
if __name__ == "__main__":
    def usage():
        print "BEARS-TP Sender"
        print "-f FILE | --file=FILE The file to transfer; if empty reads from STDIN"
        print "-p PORT | --port=PORT The destination port, defaults to 33122"
        print "-a ADDRESS | --address=ADDRESS The receiver address or hostname, defaults to localhost"
        print "-d | --debug Print debug messages"
        print "-h | --help Print this usage message"

    try:
        opts, args = getopt.getopt(sys.argv[1:],
                               "f:p:a:d", ["file=", "port=", "address=", "debug="])
    except:
        usage()
        exit()

    port = 33122
    dest = "localhost"
    filename = None
    debug = False

    for o,a in opts:
        if o in ("-f", "--file="):
            filename = a
        elif o in ("-p", "--port="):
            port = int(a)
        elif o in ("-a", "--address="):
            dest = a
        elif o in ("-d", "--debug="):
            debug = True

    s = Sender(dest,port,filename,debug)
    try:
        s.start()
    except (KeyboardInterrupt, SystemExit):
        exit()
