# coding:utf-8
from socket import *
from Game import *
from Queue import *
import threading


class Client:
    def __init__(self):
        self.game = None
        self.sendEvent = Queue()
        self.tcpCliSock = socket(AF_INET, SOCK_STREAM)

    def connecion(self, ip, port):
        Addr = (ip, int(port))
        self.tcpCliSock.connect(Addr)

    def run(self):
        self.recvT = threading.Thread(target=self.recv, args=[], name='recv')
        # self.recvT.setDaemon(True)
        self.recvT.start()
        self.sendT = threading.Thread(target=self.send, args=[], name='send')
        self.sendT.start()

    def recv(self):
        while (True):
            data = self.tcpCliSock.recv(1024)
            # print data
            version, lenth, type, payload = self.recvParse(data)
            # login
            if type == 2:
                code, hp, exp, x, y = self.parseLoginPacket(payload)
                self.game.login_reply(code, hp, exp, x, y)
                continue

            if type == 4:
                pName, x, y, hp, exp = self.parseMovePacket(payload)
                self.game.move_notify(pName, x, y, hp, exp)
                continue

            if type == 6:
                attaker, victim, damage, hp = self.parseAttackPacket(payload)
                self.game.attack_notify(attaker, victim, damage, hp)
                continue

            if type == 8:
                player,word = self.parseSpeakPacket(payload)
                self.game.speak_notify(player,word)
                continue

            if type == 0x0a:
                player = self.parseLogoutPacket(payload)
                self.game.logout_notify(player)
                continue
            else:
                print 'Error'
                return
    def send(self):
        while (True):
            argv = self.sendEvent.get()
            # 提取出种类
            Type = argv[0]
            # print Type
            if Type == 1:
                packet = self.makeLoginPacket(argv[1])
                # print packet
                self.tcpCliSock.sendall(packet)
                continue

            if Type == 2:
                packet = self.makeMovePacket(argv[1])
                self.tcpCliSock.sendall(packet)
                continue

            if Type == 3:
                packet = self.makeAttackPacket(argv[1])
                self.tcpCliSock.sendall(packet)
                continue

            if Type == 4:
                packet = self.makeSpeakPacket(argv[1])
                self.tcpCliSock.sendall(packet)
                continue

            if Type == 5:
                packet = self.makeLogoutPacket()
                self.tcpCliSock.sendall(packet)
                continue
    # ------------------------------------------分包------------------------------------
    def sendLoginIfo(self, ifo):
        self.sendEvent.put((1, ifo))

    def sendMoveIfo(self, location):
        self.sendEvent.put((2, location))

    def sendAttackIfo(self, victim):
        self.sendEvent.put((3, victim))

    def sendSpeakIfo(self,word):
        self.sendEvent.put((4,word))

    def sendLogoutIfo(self):
        self.sendEvent.put((5,))

    # -----------------------------------------------打包---------------------------------------------------
    # 给login操作打包
    def makeLoginPacket(self, ifo):
        packet = bytearray()
        # 版本号
        packet.append(0x04)
        # 字节尾数 16位
        packet.append((0x10 >> 8) & 0xFF)
        packet.append(0x10 & 0xFF)
        # 操作类型
        packet.append(0x01)
        # 其他信息
        for temp in str(ifo):
            packet.append(ord(temp))
        # 以\0结尾
        packet = packet + bytearray(12)

        packet = packet[0:16]
        return packet.decode()

    def makeMovePacket(self, ifo):
        print 'move packet'
        packet = bytearray()
        # 版本号
        packet.append(0x04)
        # 字节尾数 16位
        packet.append((0x08 >> 8) & 0xFF)
        packet.append(0x08 & 0xFF)
        # 操作类型
        packet.append(0x03)
        # 其他信息
        ifo = ifo.lower()
        if ifo == 'north':
            ifo = 0x00
        elif ifo == 'south':
            ifo = 0x01
        elif ifo == 'east':
            ifo = 0x02
        elif ifo == 'west':
            ifo = 0x03
        else:
            ifo = 0xff
        packet.append(ifo)
        packet = packet + bytearray(12)
        packet = packet[0:0x08]
        return packet.decode()

    def makeAttackPacket(self,ifo):
        packet = bytearray()
        packet.append(0x04)
        packet.append((0x10>>8)&0xFF)
        packet.append(0x10&0xFF)
        packet.append(0x05)
        for temp in str(ifo):
            packet.append(temp)
        packet += bytearray(16)
        packet = packet[0:0x10]
        return packet.decode()

    def makeSpeakPacket(self,ifo):
        len = 0
        bIfo = bytearray()
        for temp in str(ifo):
            bIfo.append(ord(temp))
            len+=1

        lenth = (4+len+3)/4*4
        if (4+len+3)%4!=0:
            lenth = (((4 + len + 3) / 4)+1) * 4
        else:
            lenth = (4+len+3)/4*4
        packet = bytearray()
        packet.append(0x04)
        packet.append((lenth>>8)&0xFF)
        packet.append(lenth&0xFF)
        packet.append(0x07)
        packet+=bIfo
        packet+=bytearray(4)
        packet = packet[0:lenth]
        return packet.decode()

    def makeLogoutPacket(self):
        # print 'makeLogoutPacket'
        packet = bytearray()
        packet.append(0x04)
        packet.append((0x0004>>8)&0xFF)
        packet.append(0x0004&0xFF)
        packet.append(0x09)
        packet = packet[0:0x0004]
        return packet.decode()

    # ---------------------------------------------------拆包------------------------------------------------
    # 拆login包
    def parseLoginPacket(self, payload):
        code = payload[0]
        hp = self.parse32bit(payload[1:5])
        exp = self.parse32bit(payload[5:9])
        x = payload[9]
        y = payload[10]
        # print 'code:' + str(code)
        # print 'hp:' + str(hp)
        # print 'exp:' + str(exp)
        # print 'x:' + str(x)
        # print 'y:' + str(y)
        return code, hp, exp, x, y

    def parseMovePacket(self, location):
        # pName = self.toString(location[0:10])
        pName = location[0:10].decode('utf-8')
        x = location[10]
        y = location[11]
        hp = self.parse32bit(location[12:16])
        exp = self.parse32bit(location[16:20])

        # print 'name:'+pName
        # print 'x:'+str(x)
        # print 'y:'+str(y)
        # print 'hp:'+str(hp)
        # print 'exp:'+str(exp)
        return pName, x, y, hp, exp

    def parseAttackPacket(self, payload):
        attaker = payload[0:10].decode('utf-8')
        victim = payload[10:20].decode('utf-8')
        damage = payload[20]
        hp = self.parse32bit(payload[21:25])

        return attaker,victim,damage,hp

    def parseSpeakPacket(self, payload):
        player = payload[0:10].decode('utf-8')
        word = payload[10:].decode('utf-8')
        # print 'player:'+player
        # print 'word:'+word
        return player,word

    def parseLogoutPacket(self, payload):
        player = payload[0:10].decode('utf-8')
        # print 'player:'+player
        return player

    def parse32bit(self, temp):
        n = 0
        n = n * 255 + temp[0]
        n = n * 255 + temp[1]
        n = n * 255 + temp[2]
        n = n * 255 + temp[3]
        return n

    # ----------------------------------对接收到的消息拆分---------------------------------------
    def recvParse(self, data):
        # print '---->'+data
        if data =='':
            print 'The gate to the tiny world of warcraft has disappeared.'
            # self.tcpCliSock.close()
            return '','','',''
        temp = bytearray(data)
        version = temp[0]
        lenth = temp[2]
        type = temp[3]
        # code = temp[4]
        payload = temp[4:lenth]

        return version, lenth, type, payload
