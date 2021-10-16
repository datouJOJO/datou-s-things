package PresentTier;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class LoginPage {
	public JFrame loginPage;
	public JButton login;
	public JLabel userName;
	public JLabel userPwd;
	public JLabel BigWord;
	public JTextField inName;
	public JPasswordField inPwd;
	
	public LoginPage() {
		// TODO Auto-generated constructor stub
		setUp();
	}
	
	public void setUp() {
		loginPage = new JFrame();
		loginPage.setTitle("登录");
		loginPage.setSize(400,300);
		loginPage.setLayout(null);
		//登录按钮
		login = new JButton("登录");
		login.setBounds(130, 180, 140, 40);
		loginPage.add(login);
		//用户名输入
		inName = new JTextField();
		inName.setBounds(100, 80, 250, 30);
		loginPage.add(inName);
		//密码输入
		inPwd = new JPasswordField();
		inPwd.setBounds(100, 130, 250, 30);
		loginPage.add(inPwd);
		//标签
		userName = new JLabel("用户名");
		userPwd = new JLabel("密码");
		BigWord = new JLabel("动物收容所信息管理系统");
		BigWord.setFont(new Font(null,Font.BOLD,20));
		userName.setBounds(50, 80, 40, 30);
		userPwd.setBounds(50, 130, 40, 30);
		BigWord.setBounds(70, 0, 250, 100);
		loginPage.add(userName);
		loginPage.add(userPwd);
		loginPage.add(BigWord);
		//关闭窗口监听
		loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension testSize = loginPage.getSize(); 
		loginPage.setLocation((screenSize.width-testSize.width)/2,(screenSize.height-testSize.height)/2);
//		loginPage.setVisible(true);
	}
//	public static void main(String[] args) {
//		LoginPage login = new LoginPage();
//	}
}
