from server import *

class packetMaker:
    def makeHeader(self,len,type):
        packet = bytearray()
        packet.append(0x04)
        packet.append((len>>8)&0xFF)
        packet.append(len&0xFF)
        packet.append(type)
        return packet

    def packetName(self,data):
        packet = bytearray()
        count = 0
        for temp in data:
            packet.append(ord(temp))
            count = count+1
        packet = packet+bytearray(10)
        packet = packet[0:10]
        return packet

    def packString(self,string):
        packet = bytearray()
        for temp in string:
            packet.append(ord(temp))
        packet = packet+bytearray(0x00)
        return packet

    def make8(self,n):
        packet = bytearray()
        packet.append(int(n))
        return packet

    def make32(self,n):
        packet = bytearray()
        packet.append((int(n)>>24)&0xFF)
        packet.append((int(n)>>16)&0xFF)
        packet.append((int(n)>>8)&0xFF)
        packet.append(int(n)&0xFF)
        return packet

    def makePadding(self,n):
        return bytearray(4)[0:n]

    def makeLoginReply(self,name,hp,x,y,exp,code):
        packet = self.makeHeader(0x10,0x02)+self.make8(code)+self.make32(hp)+self.make32(exp)\
                 +self.make8(x)+self.make8(y)\
                 +self.makePadding(1)
        return packet.decode()

    def makeMoveNotify(self,name,x,y,hp,exp):
        packet = self.makeHeader(0x18,0x04)+self.packetName(name)+self.make8(x)+self.make8(y)\
                 +self.make32(hp)+self.make32(exp)
        return packet.decode()

    def makeAttackNotify(self,Attacker,victim,Damage,hp):
        packet = self.makeHeader(0x20,0x06)+self.packetName(Attacker)+self.packetName(victim)\
                 +self.make8(Damage)+self.make32(hp)+self.makePadding(3)
        return packet.decode()

    def makeSpeakPacket(self,player,word):
        packet = self.makeHeader(0x001c,0x08)+self.packetName(player)+self.packString(word)+self.makePadding(3)
        return packet.decode()


    def makeLogout(self,player):
        packet = self.makeHeader(0x0008,0x0a)+self.packetName(player)+self.makePadding(2)
        return packet.decode()

