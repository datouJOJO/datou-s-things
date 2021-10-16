# coding:utf-8
import os

class player(object):
    def __init__(self):
        self.name = None
        self.hp = ''
        self.exp = ''
        self.x = ''
        self.y = ''


    def isExist(self,name):
        # f = open('./user/'+name+'.txt',"w")
        # f.write('haha')
        # f.close()
        filename = name+'.txt'
        files = os.listdir('./user')
        if filename in files:
            return True
        else:
            return False

    def write(self,name,hp,x,y,exp):
        if name is '':
            f = open('./user/'+self.name+'.txt',"w")
            data = str(hp)+' '+str(x)+' '+str(y)+' '+str(exp)
            f.write(data)
            f.close()
        else:
            f = open('./user/' + name + '.txt', "w")
            data = str(hp) + ' ' + str(x) + ' ' + str(y) + ' ' + str(exp)
            f.write(data)
            f.close()

    def loadIfo(self,name):
        if name =='':
            f = open('./user/' + self.name + '.txt', "r")
            data = f.read()
            slip = data.split(' ')
            # print slip
            f.close()
        else:
            f = open('./user/' + name + '.txt', "r")
            data = f.read()
            slip = data.split(' ')
            # print slip
            f.close()
        # print slip
        return slip[0],slip[1],slip[2],slip[3]



