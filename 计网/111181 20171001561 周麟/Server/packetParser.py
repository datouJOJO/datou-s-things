class packetPaser:
    def parseToString(self,data):
        temp = ''
        count = 0
        while(data[count]!=0):
            temp = temp+chr(data[count])
            count = count +1
        return temp

    def parseMove(self,payload):
        temp = payload[0:1]
        location = temp[0]
        print str(location)
        return location

    def parseAttack(self,payload):
        victim = self.parseToString(payload)
        print 'victim:'+victim
        return victim

    def parseSpeak(self,payload):
        word = self.parseToString(payload)
        print 'word:'+word
        return word