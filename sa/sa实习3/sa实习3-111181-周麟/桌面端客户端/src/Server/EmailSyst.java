package Server;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import EmailServer.SendEmailServerImpl;
import EmailServer.SendEmailServerImplProxy;
import EmailServer.SendEmailServerImplService;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class EmailSyst {

	protected Shell sendPage;
	public Text inEmail;
	public Text inMsg;
	public Button checkEmail;
	public Button sendEmail;
	public Text inTopic;

	
	JFrame checkPage;
	
	//引入发送邮件服务
	SendEmailServerImplProxy sendEmailServerImplProxy = new SendEmailServerImplProxy();
	SendEmailServerImpl sendEmailServerImpl = sendEmailServerImplProxy.getSendEmailServerImpl();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EmailSyst window = new EmailSyst();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		sendPage.open();
		sendPage.layout();
		while (!sendPage.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension testSize;
		int locationX = 0;
		int locationY = 0;
		
		sendPage = new Shell();
		sendPage.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		sendPage.setSize(350, 600);
		sendPage.setText("邮件");
		
		Label Email = new Label(sendPage, SWT.NONE);
		Email.setBounds(20, 22, 52, 21);
		Email.setText("\u6536\u4EF6\u4EBA");
		
		inEmail = new Text(sendPage, SWT.BORDER);
		inEmail.setBounds(75, 20, 240, 26);
		
		inMsg = new Text(sendPage, SWT.BORDER);
		inMsg.setBounds(15, 115, 300, 368);
		
		checkEmail = new Button(sendPage, SWT.NONE);
		
		//验证邮箱界面
		checkPage = new JFrame();
		checkPage.setLayout(null);
		checkPage.setSize(400, 200);
		testSize = checkPage.getSize(); 
		locationX = (screenSize.width-testSize.width)/2;
		locationY = (screenSize.height-testSize.height)/2;
		checkPage.setLocation(locationX,locationY);
		
		JLabel jl = new JLabel("邮件名");
		jl.setBounds(40, 20, 100, 50);
		JTextField jtf = new JTextField();
		jtf.setBounds(100, 30, 250, 30);
		JButton jb = new JButton("提交验证");
		jb.setBounds(100, 70, 100, 30);
		checkPage.add(jl);
		checkPage.add(jtf);
		checkPage.add(jb);
		checkPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		checkPage.setVisible(true);
		
		//验证邮箱操作
		checkEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				checkPage.setVisible(true);
			}
		});
		
		checkEmail.setText("\u9A8C\u8BC1\u90AE\u4EF6");
		checkEmail.setBounds(191, 497, 111, 30);
		
		sendEmail = new Button(sendPage, SWT.NONE);	
		//发送邮件操作
		sendEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					System.out.println(inEmail.getText());
					boolean flag = sendEmailServerImpl.judge(inTopic.getText(), inEmail.getText(), inMsg.getText());
					if(flag) {
						JOptionPane.showMessageDialog(null, "发送成功","success",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "发送失败","failure",JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					boolean flag = sendEmailServerImpl.checkEmail(jtf.getText());
					if(flag) {
						JOptionPane.showMessageDialog(null, "邮箱有效","success",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "邮箱无效","failure",JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		sendEmail.setBounds(20, 497, 111, 30);
		sendEmail.setText("\u53D1\u9001\u90AE\u4EF6");
		
		Label topic = new Label(sendPage, SWT.NONE);
		topic.setBounds(20, 70, 52, 20);
		topic.setText("\u4E3B\u9898");
		
		inTopic = new Text(sendPage, SWT.BORDER);
		inTopic.setBounds(75, 70, 240, 30);

	}
}
