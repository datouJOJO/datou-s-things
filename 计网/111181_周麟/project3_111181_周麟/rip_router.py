from sim.api import *
from sim.basics import *

'''
Create your RIP router in this file.
'''

'''
1.create router table
2.judge different situations
'''

router_table = {}

def add(router_table,src, cur):
    for k in list(router_table[src]):
        try:
            router_table[k]
        except KeyError:
            continue
        for temp in router_table[k]:
            if temp == cur:
                router_table[src][cur] = router_table[k][temp] + router_table[src][k]
                try:
                    router_table[cur][src]
                except KeyError:
                    router_table[cur][src] = router_table[src][cur]
                return
    router_table[src][cur] = 1

class RIPRouter (Entity):
    def __init__(self):
    # Add your code here!
        self.max_path_numbers = 100  #biggest path_numbers
        self.ports = {}

    def handle_rx (self, packet, port):
        if isinstance(packet,DiscoveryPacket):
            cur_src = packet.src
            # trace = ','.join((s.name for s in packet.trace))
            # print str(cur_src) + str(trace) + " and new is " + str(self)
            if packet.is_link_up:
                self.ports[cur_src] = port
                router_table[self] = {}
                router_table[self][cur_src] = 1
                temp = RoutingUpdate()
                temp.add_destination(cur_src,1)
                self.send(temp,port,flood = True)
            else:
                del self.ports[cur_src]
                return
            # flag = 1
            # for path in packet.trace:
            #     try:
            #         router_table[cur_src]
            #     except KeyError:
            #         router_table[cur_src] = {}
            #         router_table[cur_src][path] = 1
            #         try:
            #             router_table[path]
            #         except KeyError:
            #             router_table[path] = {}
            #             router_table[path][cur_src] = 1
            #         flag = 0
            #     if flag == 1:
            #         try:
            #             router_table[cur_src][path]
            #         except KeyError:
            #             add(router_table,cur_src,path)
            #             flag = 0
            #         if flag == 1:
            #             packet = RoutingUpdate()
        elif isinstance(packet,RoutingUpdate):
            # print "work! the packet from: " + str(packet.src)
            # print packet.paths
            # if dest in router_table[self]:
            #     if router_table[self][dest] > packet.get_distance(dest) + 1:
            # router_table[self][dest] = packet.get_distance(dest) + 1
            # self.ports = port
            # temp = RoutingUpdate()
            # temp.add_destination(dest,router_table[self][dest])
            # self.send(temp,port,flood=True)
            for path in packet.paths:
                if packet.paths[path] >= self.max_path_numbers:
                    del packet.paths[path]
            temp = 0
            for k in packet.paths.keys():
                temp = k
            if temp is not self:
                try:
                    router_table[self][packet.src]
                except KeyError:
                    try:
                        router_table[self][temp]
                    except KeyError:
                        router_table[self][packet.src] = packet.paths[temp] + 1
                        self.ports = port
                        new_m = RoutingUpdate()
                        new_m.add_destination(temp, router_table[self][packet.src])
                        self.send(new_m, port, flood=True)
                        return
                    router_table[self][packet.src] = packet.paths[temp] + 1
                    self.ports = port
                    new_m = RoutingUpdate()
                    new_m.add_destination(temp, router_table[self][packet.src])
                    self.send(new_m, port, flood=True)
                    return
                # try:
                #     router_table[self][temp]
                # except KeyError:
                #     router_table[self][temp] = packet.paths[temp] + 1
                #     self.ports = port
                #     new_m = RoutingUpdate()
                #     new_m.add_destination(temp, router_table[self][temp])
                #     self.send(new_m, port, flood=True)
                #     return
                if router_table[self][packet.src] > packet.paths[temp] + 1:
                    router_table[self][packet.src] = packet.paths[temp] + 1
                    self.ports = port
                    new_m = RoutingUpdate()
                    new_m.add_destination(temp, router_table[self][packet.src])
                    self.send(new_m, port, flood=True)
        else:
            port = self.ports.get(packet.dst)
            self.send(packet, port, flood=False)
        print "the router table is: " + str(router_table)
        print "the ports table is: " + str(self.ports)
        # for key in router_table.keys():
        #     print str(key) + " " + str(router_table[key])

        # trace = ','.join((s.name for s in packet.trace))
        # print trace
        # print str(packet) + trace
        # print self.ports
        # print self.ports
        # Add your code here!
        # if isinstance(packet, DiscoveryPacket):
        #     ports = topoOf(packet.src).get_ports()
        #     for node in ports:
        #         if node[2] is packet.src:
        #             pass
        #         else:
        #             lenth = len(ports)
        #             ports.append((self,port,packet.src,lenth))
        #             break
        #     '''
        #     if not have this ip just save
        #     '''
        # elif isinstance(packet,RoutingUpdate):
        #     ports = topoOf(packet.src).get_ports()
        #     for node in ports:
        #         if node[2] is packet.src:
        #             des1 = get_distance(self,packet.src)
        #             des2 = get_distance(self,node[2])
        #
        #             if des1<des2:
        #                 break
        #         else:
        #             lenth = len(ports)
        #             ports.append((self,port,packet.src,lenth))
        #             break
        #
        #     '''
        #     if not have this ip just save
        #     compare with distance
        #     shorter update
        #     '''
        # else:
        #     if not packet.dst is NullAddress:
        #         if not packet.dst is self:
        #             self.send(packet, port, flood=False)
        #         else:
        #             pass
        #     else:
        #         topoOf(packet.src).get_ports()
        #         packet.src = self
        #         packet.dst = NullAddress
        #         self.send(packet, port, flood=True)

