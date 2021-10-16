import tkinter as tk
import pickle
from tkinter import messagebox
import socket
import time

window1 = tk.Tk()
window1.title('登陆界面')
window1.geometry('350x250')

#欢迎界面
canvas = tk.Canvas(window1)
image_file=tk.PhotoImage(file='welcome.gif')
image = canvas.create_image(100,-10,anchor='nw',image=image_file)
canvas.pack(side='top')
#输入登录名和密码框
in_use_name=tk.StringVar()
name=tk.Entry(window1,textvariable=in_use_name,show=None)
#textvariable是变量的意思
name.place(x=130,y=100)
in_use_ps=tk.StringVar()
in_use_name.set('201*100****')
password = tk.Entry(window1,textvariable=in_use_ps,show='*')
password.place(x=130,y=140)
#标签
use_name = tk.Label(window1,text='用户名',font=('Arial',10))
use_name.place(x=80,y=100)
use_ps = tk.Label(window1,text='密码',font=('Arial',10))
use_ps.place(x=90,y=140)

'''
#存储用户的名字和IP
try:
    with open('hh_ip.pickle', 'rb') as ip_file:
        hh_ip = pickle.load(ip_file)
except FileNotFoundError:
    with open('hh_ip.pickle', 'wb')as ip_file:
        hh_ip = {'admin': '127.0.0.1'}
        pickle.dump(hh_ip, ip_file)
'''

#注册botton
def sign_up():
    def check_imf():
        un=sub_name.get()
        up=sub_password.get()
        rup=re_password.get()
        my_port= in_use_port.get()
        #获取本机名字
        my_name = socket.gethostname()
        # 获取本机ip
        us_ip = socket.gethostbyname(my_name)

        with open('use_imf.pickle','rb') as u_file:
            load_file=pickle.load(u_file)
        #data一个存以用户名为键值，元素值为密码，ip和port的字典

        if up!=rup:
            tk.messagebox.showerror(title='密码错误',message='please input again to make two passage be same')
        elif un in load_file:
            '''
            if load_hh[un]!=us_ip:
                load_hh[un]=us_ip
                with open('hh_ip.pickle', 'wb')as hh_file:
                    pickle.dump(load_hh, hh_file)
            '''
            tk.messagebox.showerror(title='用户名重复',message='this use name is already to sign up ,please input another one')
        elif len(un)==0:
            tk.messagebox.showerror(title='用户名为空',message='please input your name')
        elif len(up)==0:
            tk.messagebox.showerror(title='密码为空',message='please input your password')
        elif len(rup) == 0:
            tk.messagebox.showerror(title='密码为空', message='please input your password')
        else:
            '''
            load_hh[un]=us_ip
            with open('hh_ip.pickle', 'wb')as hh_file:
                pickle.dump(load_hh, hh_file)
            '''

            data=[up,us_ip,my_port]
            load_file[un]=data

            with open('use_imf.pickle','wb') as u_file:
                pickle.dump(load_file,u_file)

            tk.messagebox.showinfo('you sign up successfully!','welcome!')
            window_sign_up.destroy()

    window_sign_up=tk.Toplevel(window1)
    window_sign_up.geometry('300x200')
    window_sign_up.title("注册")
    new_name = tk.StringVar()
    new_ps = tk.StringVar()
    re_new_ps = tk.StringVar()
    us_port = tk.StringVar()
    sub_use_name = tk.Label(window_sign_up, text='用户名', font=('Arial', 10))
    sub_use_name.place(x=40, y=40)
    sub_name = tk.Entry(window_sign_up,textvariable=new_name, show=None)
    sub_name.place(x=100, y=40)
    sub_use_ps = tk.Label(window_sign_up, text='密码', font=('Arial', 10))
    sub_use_ps.place(x=50, y=70)
    sub_password = tk.Entry(window_sign_up,textvariable=new_ps,show='*')
    sub_password.place(x=100, y=70)
    sub_use_ps = tk.Label(window_sign_up, text='密码', font=('Arial', 10))
    sub_use_ps.place(x=50, y=70)
    in_use_port = tk.Entry(window_sign_up,textvariable=us_port,show=None)
    in_use_port.place(x=100, y=130)
    use_port = tk.Label(window_sign_up, text='端口', font=('Arial', 10))
    use_port.place(x=50, y=130)
    re_password = tk.Entry(window_sign_up, textvariable=re_new_ps,show='*')
    re_password.place(x=100, y=100)
    re_us_p = tk.Label(window_sign_up, text='确认密码', font=('Arial', 10))
    re_us_p.place(x=25, y=100)

    submit = tk.Button(window_sign_up, text="submit", command=check_imf)
    submit.place(x=220, y=160)

sign_on = tk.Button(window1,text="sign on",command= sign_up)
sign_on.place(x=230,y=180)

def change_up():
    def change_p():
        data1=ps_p.get()
        data2=re_p.get()
        u_n=c_us.get()

        with open('use_imf.pickle','rb') as us_file:
            us_data=pickle.load(us_file)

        if u_n in us_data:
            temp=us_data[u_n]
            if temp[0]!=data1:
                tk.messagebox.showerror(message="you input an uncorrect password , please input again!")
            else:
                temp[0]=data2
                with open('use_imf.pickle', 'wb') as us_file:
                    us_data[u_n]=temp
                    pickle.dump(us_data,us_file)
                    tk.messagebox.showinfo(message='you changed password successfully!')

    change_usp =tk.Toplevel(window1)
    change_usp.geometry('300x150')
    ps_p = tk.StringVar()
    re_p = tk.StringVar()
    c_us = tk.StringVar()
    us_name = tk.Label(change_usp, text='用户名', font=('Arial', 10))
    us_name.place(x=40, y=0)
    sub_use_name = tk.Label(change_usp, text='旧密码', font=('Arial', 10))
    sub_use_name.place(x=40, y=40)
    sub_use_name = tk.Label(change_usp, text='新密码', font=('Arial', 10))
    sub_use_name.place(x=40, y=80)

    in_re_p = tk.Entry(change_usp, textvariable=c_us, show=None)
    in_re_p.place(x=90, y=0)
    in_ps_p = tk.Entry(change_usp,textvariable=ps_p, show=None)
    in_ps_p.place(x=90,y=40)
    in_re_p = tk.Entry(change_usp,textvariable=re_p, show=None)
    in_re_p.place(x=90,y=80)
    submit = tk.Button(change_usp, text="submit", command=change_p)
    submit.place(x=200, y=105)



#登录button
def log_in():
    use_name=in_use_name.get()
    use_ps=in_use_ps.get()

    try :
        with open('use_imf.pickle','rb') as use_file:
            use_imf = pickle.load(use_file)
    except FileNotFoundError:
        with open ('use_imf.pickle','wb')as use_file:
            use_imf = {'admin':['admin','127.0.0.1','8080']}
            pickle.dump(use_imf,use_file)

    if use_name in use_imf:
        u_data = use_imf[use_name]

        # 获取本机名字
        my_name = socket.gethostname()
        # 获取本机ip
        me_ip = socket.gethostbyname(my_name)
        u_data[1] = me_ip

        # 更新一下ip位置

        use_imf[use_name] = u_data
        if use_name!='admin':
            with open('use_imf.pickle', 'wb')as use_file:
                pickle.dump(use_imf,use_file)

        if use_ps == u_data[0]:
            tk.messagebox.showinfo(title='Welcome',message='how are you!')

            window1.destroy()
            window1.quit()
            root = tk.Tk()
            app = MyApp(root)
            app.mainloop()
        else :
            tk.messagebox.showerror(message ='maybe you should try again to make the password right!')
    else:
        is_sign_on=tk.messagebox.askyesno('welcome','you have to sign on,would you?')
        if is_sign_on:
            sign_up()
log_in = tk.Button(window1,text="log in",command=log_in)
change = tk.Button(window1,text="change",command=change_up)
change.place(x=150,y=180)
log_in.place(x=75,y=180)

with open('use_imf.pickle','rb') as file:
    data=pickle.load(file)
    print(data)

class MyApp(tk.Frame):

    #Constructor for our app.  The master parameter represents the root object
    #that will hold out application.  It is basically a window, with our application
    #existing in a frame inside that window.
    def __init__(self, master):
        self.s= socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
        #Call the constructor of the Frame superclass.  The pad options create padding
        #between the edge of the Frame and the Widgits inside.
        tk.Frame.__init__(self, master)
        #Give our window a title by calling the .title() method on the master object.
        master.title(in_use_name.get()+"已连接")
        #Set a minimum size through the master object, just to make our UI a little
        #nicer to look at.
        master.geometry('250x400')
        #.pack() is a necessary method to get our app ready to be displayed.  Packing
        #objects puts them in a simple column.  For a more complex way to arrange your
        #widgets, see .grid():
        #http://effbot.org/tkinterbook/grid.htm
        self.pack()
        #Now let's make some buttons using the Tkinter class Button.  The "text" parameter
        #indicates the text to be displayed in the button and the "command" parameter
        #specifies a procedure to execute if the button is clicked.  In this app, we will
        #have buttons that increase and decrease a variable.
        #Lastly, a quit button, which will call the .create_quit_window() method defined below,
        #which displays a new window asking whether the user want's to quit.

        self.quitButton = tk.Button(master, text="Quit",width=15,height=0, command=self.create_quit_window)
        self.quitButton.pack(side='bottom')
        self.select=tk.Label(master,text='选择好友:')
        self.select.place(x=10,y=10)
        self.f_name =tk.StringVar()
        self.friend=tk.Listbox(master,listvariable=self.f_name,width=20,height=15)
        self.friend.place(x=50,y=45)
        self.add_f = tk.Button(master,text='添加好友',command=self.add_friend)
        self.add_f.place(x=40,y=323)
        self.add_f = tk.Button(master,text='开始聊天',command=self.start_chat)
        self.add_f.place(x=140,y=323)

        #好友名字
        self.f_n = tk.StringVar()


    #Methods that will be called when the up and down buttons are pressed.
    def increment(self):
        self.value += 1
        #When we reset the value of the StringVar, the text on valueLabel will change!
        self.value_str.set(str(self.value))

    def decrement(self):
        self.value -= 1
        self.value_str.set(str(self.value))

    def add_friend(self):
        a_f = tk.Toplevel(self.master)
        a_f.geometry('220x100')
        a_f.title("添加好友")
        in_f_n=tk.Entry(a_f,textvariable=self.f_n)
        in_f_n.place(x=25,y=25)
        i_n=tk.Label(a_f,text='添加的好友名字：')
        i_n.place(x=0,y=0)
        add_f = tk.Button(a_f,text='确认',command=self.add_f_n)
        add_f.place(x=20,y=50)
        close_a_f = tk.Button(a_f, text='退出', command=a_f.destroy)
        close_a_f.place(x=140, y=50)


    def add_f_n(self):
        self.friend.insert(tk.END,self.f_n.get())

    def start_chat(self):
        def sendmessage():
            # 在聊天内容上方加一行 显示发送人及发送时间
            msgcontent = ('我:'.encode("utf-8").decode("utf-8")) + time.strftime("%Y-%m-%d %H:%M:%S",
                                                                                time.localtime()) + '\n '
            mag = text_msg.get('0.0', tk.END)
            text_msglist.insert(tk.END, msgcontent, 'green')
            text_msglist.insert(tk.END, text_msg.get('0.0', tk.END))
            text_msg.delete('0.0', tk.END)
            self.s.sendto(mag.encode('gbk'), (f_ip, int(f_port)))
            rev_data = self.s.recvfrom(1024)

            if not rev_data:
                text_msglist.insert(tk.END, '对方已离线')
                print('对方已下线')
            else:
                msgcontent = (value) +":"+ time.strftime("%Y-%m-%d %H:%M:%S",
                                                                                time.localtime()) + '\n '
                text_msglist.insert(tk.END, msgcontent, 'green')
                text_msglist.insert(tk.END,rev_data[0].decode('gbk'))
                msgcontent='\n '


        value = self.friend.get(self.friend.curselection())
        char_window=tk.Toplevel(self.master)
        char_window.title("正在和" + value + "聊天")

        with open('use_imf.pickle','rb') as u_ip:
            data=pickle.load(u_ip)
            f_imf=data[value]
            my_imf=data[in_use_name.get()]

            print(f_imf)
            print(my_imf)

            my_ip=my_imf[1]
            my_port=my_imf[2]

            f_ip=f_imf[1]
            f_port=f_imf[2]

        self.s= socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
        self.s.bind(('',int(my_port)))
        frame_left_top=tk.Frame(char_window,width=380, height=270, bg='white')
        frame_left_center=tk.Frame(char_window,width=380, height=100, bg='white')
        frame_left_bottom = tk.Frame(char_window,width=380, height=20)
        frame_right = tk.Frame(char_window,width=170, height=400, bg='white')
        # 创建几个frame作为容器
        ##创建需要的几个元素
        text_msglist = tk.Text(frame_left_top)
        text_msg = tk.Text(frame_left_center);
        button_sendmsg = tk.Button(frame_left_bottom, text=('发送'.encode("utf-8").decode("utf-8")), command=sendmessage)
        # 创建一个绿色的tag
        text_msglist.tag_config('green', foreground='#008B00')
        # 使用grid设置各个容器位置
        frame_left_top.grid(row=0, column=0, padx=2, pady=5)
        frame_left_center.grid(row=1, column=0, padx=2, pady=5)
        frame_left_bottom.grid(row=2, column=0)
        frame_right.grid(row=0, column=1, rowspan=3, padx=4, pady=5)
        frame_left_top.grid_propagate(0)
        frame_left_center.grid_propagate(0)
        frame_left_bottom.grid_propagate(0)
        # 把元素填充进frame
        text_msglist.grid()
        text_msg.grid()
        button_sendmsg.grid(sticky=tk.E)


        #This method creates a new window (which will be a child of the master of our frame,
    #not of our frame itself).  The quit window will ask the user if they really want to quit.
    #If the user clicks yes, the application will close.  If they say no, the quit window
    #will close.
    def create_quit_window(self):
        #The Toplevel class makes a window.  It's simpler than the Frame class.  We will make
        #it a child of our application's master object, but since it is a Toplevel object, it
        #will create a whole new window rather than one that is part of the application window.
        quit_window = tk.Toplevel(self.master)
        #Give our quit window a title and minimum size.
        quit_window.title("Quit?")
        quit_window.minsize(width=150, height=50)
        #Display a message to the user asking if they want to quit.
        quit_label = tk.Label(quit_window, text="Are you sure you want to quit?")
        quit_label.pack()
        #We give our window a yes and no button.  One quits the application and one quits
        #the window.
        yes_button = tk.Button(quit_window, text="Yes", command=self.quit)
        yes_button.pack()
        no_button = tk.Button(quit_window, text="No", command=quit_window.destroy)
        no_button.pack()
        self.s.close()

window1.mainloop()
