# coding:utf-8
import sys
import threading
from Game import *


class Input:
    def __init__(self):
        self.game = None
        self.player = None

    def run(self):
        self.writeT = threading.Thread(target=self.write, name='writeT')
        self.writeT.setDaemon(True)
        self.writeT.start()

    def write(self):
        while (True):
            str = raw_input("command>")
            # print 'player name:' + self.player.name
            # 分割取出操作种类
            Type = str.split(' ', 1)
            if Type[0] == 'login':
                self.game.login(Type[1])
                continue
            elif self.player.name == '':
                print 'you must login first!'
                continue
            else:
                if Type[0] == 'move':
                    self.game.move(Type[1])
                    continue
                elif Type[0] == 'attack':
                    self.game.attack(Type[1])
                    continue
                elif Type[0] == 'speak':
                    self.game.speak(Type[1])
                    continue

                elif Type[0] == 'logout':
                    self.game.logout()
                    continue

                else:
                    print 'please input login|move|attack|speak|logout...'
                    continue
