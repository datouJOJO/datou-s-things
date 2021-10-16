# coding:utf-8
from Queue import *
from input import *
from Client import *
from Player import *


class Game:
    def __init__(self, ip, port):
        self.ip = ip
        self.port = port
        self.input = Input()
        self.input.game = self
        self.client = Client()
        self.client.game = self
        self.event = Queue()
        self.player = player()
        self.input.player = self.player

    def run(self):
        self.input.run()
        self.client.connecion(self.ip, self.port)
        self.client.run()
        self.judge()

    # 判断是什么类型的包，并进行发送
    def judge(self):
        while (True):
            ifo = self.event.get()
            # print ifo
            eType = ifo[0]
            # print eType
            if (eType == 1):
                self.client.sendLoginIfo(ifo[1])
                continue

            if (eType == 2):
                self.client.sendMoveIfo(ifo[1])
                continue

            if (eType == 3):
                self.client.sendAttackIfo(ifo[1])
                continue

            if (eType == 4):
                self.client.sendSpeakIfo(ifo[1])
                continue

            if (eType == 5):
                self.client.sendLogoutIfo()
                continue

            if (eType == 6):
                code = ifo[1]
                self.player.hp = ifo[2]
                self.player.exp = ifo[3]
                self.player.x = ifo[4]
                self.player.y = ifo[5]

                if code == 1:
                    print 'A play with the same name is already in the game.'
                else:
                    print 'Welcome to the tiny world of warcraft.'
                    print self.player.name + ': location=(' + str(ifo[4]) + ',' + str(ifo[5]) + '), HP=' \
                          + str(ifo[2]) + ', EXP=' + str(
                        ifo[3])
                continue

            if (eType == 7):
                x = ifo[2]
                y = ifo[3]
                hp = ifo[4]
                exp = ifo[5]
                if (self.player.name == ifo[1]):
                    self.player.x = x
                    self.player.y = y
                    self.player.hp = hp
                    self.player.exp = exp

                if (self.player.isVisible(x, y)):
                    print str(ifo[1]) + ': location=(' + str(x) + ',' + str(y) + '), HP=' + str(hp) + ', EXP=' + str(
                        exp)
                continue

            if (eType == 8):
                attaker = ifo[1]
                victim = ifo[2]
                damage = ifo[3]
                hp = ifo[4]

                if hp == 0:
                    print attaker + ' killed '+ victim
                else:
                    print attaker + ' damaged '+ victim + ' by '+damage+'.'+victim+'\'s is now '+hp

            if (eType == 9):
                player = ifo[1]
                word = ifo[2]
                print player+':'+word

            if (eType == 10):
                player = ifo[1]
                print 'Player '+player+' has left the tiny world of wacraft.'
                sys.exit()

    # 1.login <name>
    # 2.move <方向>
    # 3.attack <name>
    # 4.speak <ifo>
    # 5.logout 退出

    def login(self, name):
        self.event.put((1, name))
        self.player.name = name

    def move(self, location):
        self.event.put((2, location))

    def attack(self, victim):
        self.event.put((3, victim))

    def speak(self, word):
        self.event.put((4, word))

    def logout(self):
        self.event.put((5,))

    def login_reply(self, code, hp, exp, x, y):
        self.event.put((6, code, hp, exp, x, y))

    def move_notify(self, pName, x, y, hp, exp):
        self.event.put((7, pName, x, y, hp, exp))

    def attack_notify(self,attaker,victim,damage,hp):
        self.event.put((8, attaker,victim,damage,hp))

    def speak_notify(self, player, word):
        self.event.put((9, player, word))

    def logout_notify(self,name):
        self.event.put((10,name))