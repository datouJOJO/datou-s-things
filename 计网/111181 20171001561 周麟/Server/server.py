# coding:utf-8
import socket
from Queue import *
from packetMaker import *
from packetParser import *
import threading
from player import *
import random
import time

class server:
    def __init__(self):
        self.event = Queue()
        self.name = set()
        self.tcpCliSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        #地址
        self.addr = ''
        self.port = ''
        self.packetMaker = packetMaker()
        self.packetParser = packetPaser()
        self.player = player()
        self.sendSocket = None


    def run(self, port):
        self.port = port
        self.tcpCliSock.bind(('', port))
        self.tcpCliSock.listen(5)
        print '* Listening socket is ready.'
        self.sendSocket, self.addr = self.tcpCliSock.accept()
        self.recvT = threading.Thread(target=self.recv,args=[],name = 'recv')
        self.recvT.start()
        self.loopT = threading.Thread(target=self.loop,args=[],name = 'loop')
        self.loopT.start()
        # self.addT = threading.Thread(target=self.addHp,args=[],name = 'add')
        # self.addT.start()

    def recv(self):
        while(True):
            data=self.sendSocket.recv(1024)
            if data=='':
                continue
            version,lenth,type,payload = self.parstData(data)
            #login
            if type==1:
                self.event.put((2,payload))
            elif type==3:
                self.event.put((4, payload))
            elif type==5:
                self.event.put((6, payload))
            elif type==7:
                self.event.put((8, payload))
            elif type==9:
                self.event.put((0x0a, payload))
            else:
                print '--->'+data

    def loop(self):
        while(True):
            ifo = self.event.get()
            type = ifo[0]
            payload = ifo[1]
            # print 'payload:'+payload
            print 'type:'+str(type)
            #处理事件+发送回包
            if type == 2:
                code = ''
                hp = ''
                x = ''
                y = ''
                exp = ''
                #解码成名字
                data = self.packetParser.parseToString(payload)
                self.player.name = data
                if data in self.name:
                    code = 1
                    packet = self.packetMaker.makeLoginReply(data,0,0,0,0,code)

                    self.sendSocket.sendall(packet)
                    continue
                else:
                    code = 0
                    self.name.add(data)
                    print 'add player..'
                #如果这个玩家存在，读取它上次上线的信息
                if self.player.isExist(data):
                    hp,x,y,exp = self.player.loadIfo('')
                    self.player.hp = hp
                    self.player.x = x
                    self.player.y = y
                    self.player.exp = exp

                #如果不存在
                #随机生成生命值和坐标经验值是0
                else:
                    hp = random.randint(100,120)
                    x = random.randint(0,100)
                    y = random.randint(0, 100)
                    exp = 0
                    self.player.hp = hp
                    self.player.x = x
                    self.player.y = y
                    self.player.exp = exp
                    self.player.write('',hp,x,y,exp)
                # print 'code:'+str(code)
                packet = self.packetMaker.makeLoginReply(data,hp,x,y,exp,code)
                # print 'packet:'+packet

                self.sendSocket.sendall(packet)
            #move
            if type == 4:
                location = self.packetParser.parseMove(payload)
                hp,x,y,exp = self.player.loadIfo('')
                x = int(x)
                y = int(y)
                #北north
                if location == 0:
                    y+= 3
                #南south
                if location == 1:
                    y-=3
                #东east
                if location == 2:
                    x+=3
                #西west
                if location == 3:
                    x-=3
                #写回文件
                self.player.write('',hp,x,y,exp)
                #打包
                packet = self.packetMaker.makeMoveNotify(self.player.name,x,y,hp,exp)
                self.sendSocket.sendall(packet)
            #attack
            if type == 6:
                #受害者
                victim = self.packetParser.parseAttack(payload)
                if self.player.name == victim:
                    continue
                if self.player.isExist(victim) == False:
                    continue
                if victim not in self.name:
                    continue

                hp1, x1, y1, exp1 = self.player.loadIfo(victim)
                hp2, x2, y2, exp2 = self.player.loadIfo('')
                #随机伤害
                hurt = random.randint(10,20)
                if int(hurt) >= int(hp1):
                    hp1 = 0
                    exp2 = int(hurt)
                else:
                    hp1 -=int(hurt)
                    exp2 = int(hurt)
                self.player.write(victim,hp1, x1, y1, exp1)
                self.player.write('',hp2, x2, y2, exp2)

                packet = self.packetMaker.makeAttackNotify(self.player.name,victim,hurt,hp1)
                self.sendSocket.sendall(packet)
                print 'sending....'

            #speak
            if type == 8:
                word = self.packetParser.parseSpeak(payload)
                packet = self.packetMaker.makeSpeakPacket(self.player.name,word)
                self.sendSocket.sendall(packet)
                print self.player.name+':'+word
            #logout
            if type == 0x0a:
                packet = self.packetMaker.makeLogout(self.player.name)
                self.sendSocket.sendall(packet)
                #退出的用户将名字去掉
                self.name.remove(self.player.name)
        self.addHp()

    def addHp(self):
        print 'len:'+str(len(self.name))
        if len(self.name)>0:
            for player in self.name:
                hp,x,y,exp = self.player.loadIfo(player)
                self.player.write(player,int(hp)+1,x,y,exp)
                print 'addHp...'
        time.sleep(5)

    #解析出接收到客户端发来的包的信息
    def parstData(self,data):
        temp = bytearray(data)
        print 'temp:'+temp
        version = temp[0]
        lenth = temp[2]
        type = temp[3]
        # code = temp[4]
        payload = temp[4:lenth]
        return version,lenth,type,payload

        # print 'version:'+str(version)
        # print 'lenth:'+str(lenth)
        # print 'type:'+str(type)
        # print 'payload:'+str(payload)

