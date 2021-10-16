from server import *
import sys

def inputP():
    port = ""
    if(len(sys.argv)==3 and sys.argv[1]=="-p"):
        port = sys.argv[2]
        return port
    else:
        print "python2 xxx.py -p <your port>"
        return ''
# port = 2000
port = inputP()
server().run(int(port))