# coding:utf-8

class player:
    def __init__(self):
        self.name = ''
        self.hp = ''
        self.x = ''
        self.y = ''
        self.exp = ''

    def isVisible(self, x, y):
        # 左
        if int(self.x) - 5 <= 0:
            left = 0
        else:
            left = int(self.x) - 5

        # 右
        if int(self.x) + 5 >= 100:
            right = 100
        else:
            right = int(self.x) + 5

        # 上
        if int(self.y) - 5 <= 0:
            up = 0
        else:
            up = int(self.y) - 5

        # 下
        if int(self.y) + 5 >= 100:
            down = 100
        else:
            down = int(self.y) + 5
        # print 'x:' + str(x)
        # print 'y:' + str(y)
        # print 'left:' + str(left) + ' ' + 'right:' + str(right) + ' ' + 'up:' + str(up) + ' ' + 'down:' + str(down)
        if left <= x <= right:
            if up <= y <= down:
                return True
            else:
                return False
        else:
            return False
