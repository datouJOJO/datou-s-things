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
		loginPage.setTitle("��¼");
		loginPage.setSize(400,300);
		loginPage.setLayout(null);
		//��¼��ť
		login = new JButton("��¼");
		login.setBounds(130, 180, 140, 40);
		loginPage.add(login);
		//�û�������
		inName = new JTextField();
		inName.setBounds(100, 80, 250, 30);
		loginPage.add(inName);
		//��������
		inPwd = new JPasswordField();
		inPwd.setBounds(100, 130, 250, 30);
		loginPage.add(inPwd);
		//��ǩ
		userName = new JLabel("�û���");
		userPwd = new JLabel("����");
		BigWord = new JLabel("������������Ϣ����ϵͳ");
		BigWord.setFont(new Font(null,Font.BOLD,20));
		userName.setBounds(50, 80, 40, 30);
		userPwd.setBounds(50, 130, 40, 30);
		BigWord.setBounds(70, 0, 250, 100);
		loginPage.add(userName);
		loginPage.add(userPwd);
		loginPage.add(BigWord);
		//�رմ��ڼ���
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
