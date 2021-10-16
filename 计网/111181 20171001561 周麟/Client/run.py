#coding:utf-8
import sys
from Game import *

#输入相应的ip和端口号
def inputIpnP():
    ip = ""
    port = ""
    if(len(sys.argv)==5 and sys.argv[1]=="-s"):
        ip = sys.argv[2]
        if(sys.argv[3]=="-p"):
            port = sys.argv[4]
            return ip,port
        else:
            print "python2 xxx.py -s <your ip> -p <your port>"
            return '',''
    else:
        print "python2 xxx.py -s <your ip> -p <your port>"
        return '',''

if(__name__=='__main__'):
    ip,port = inputIpnP()
    # print ip+" "+port
    # ip = '127.0.0.1'
    # port = 2000
    Game(ip,int(port)).run()