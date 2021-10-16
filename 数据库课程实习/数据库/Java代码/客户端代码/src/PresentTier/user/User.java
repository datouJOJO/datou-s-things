package PresentTier.user;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import PresentTier.ButtonsAndBackground;

/*
 * 用户的大类
 * 主要为了完成插入操作
 * 不需要编号和插入时间
 * 只需要用户名称，电话，邮箱，收容所编号
 */
public class User extends ButtonsAndBackground{
	JLabel uName = new JLabel("用户名称");
	JLabel uPwd = new JLabel("用户密码");
	JLabel uEmail = new JLabel("用户邮箱");
	JLabel uTel = new JLabel("用户电话");
	JLabel uSelterID = new JLabel("收容所编号");
	
	public JTextField uInName = new JTextField();
	public JPasswordField inPwd = new JPasswordField();
	public JTextField uInEmail= new JTextField();
	public JTextField uInTel= new JTextField();
	public JTextField uInShelterID= new JTextField();
	
	public JButton YesAdd = new JButton("确认添加");
	public User() {
		// TODO Auto-generated constructor stub
		super();
		String[]type = {"员工姓名","员工邮箱","员工电话","收容所编号"};
		updateType = new JComboBox<String>(type);
		updateType.setBounds(30, 30, 100, 30);
		UpdatePage.add(updateType);
		
		uName.setBounds(50, 10, 60, 20);
		uPwd.setBounds(50, 50, 60, 20);
		uTel.setBounds(50, 90, 60, 20);
		uEmail.setBounds(50, 130, 60, 20);
		uSelterID.setBounds(50, 170, 80, 20);
		
		uInName.setBounds(120, 10, 200, 30);
		inPwd.setBounds(120, 50, 200, 30);
		uInTel.setBounds(120, 90, 200, 30);
		uInEmail.setBounds(120, 130, 200, 30);
		uInShelterID.setBounds(120, 170, 200, 30);
		
		YesAdd.setBounds(140, 210, 100, 40);
		insertPage.add(uName);
		insertPage.add(uPwd);
		insertPage.add(uEmail);
		insertPage.add(uTel);
		insertPage.add(uSelterID);
		
		insertPage.add(uInName);
		insertPage.add(inPwd);
		insertPage.add(uInTel);
		insertPage.add(uInEmail);
		insertPage.add(uInShelterID);
		insertPage.add(YesAdd);
		insertPage.setVisible(false);
	}
	
	public static void main(String[] args) {
		User u = new User();
	}
}
