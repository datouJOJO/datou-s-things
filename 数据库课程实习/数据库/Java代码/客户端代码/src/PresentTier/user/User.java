package PresentTier.user;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import PresentTier.ButtonsAndBackground;

/*
 * �û��Ĵ���
 * ��ҪΪ����ɲ������
 * ����Ҫ��źͲ���ʱ��
 * ֻ��Ҫ�û����ƣ��绰�����䣬���������
 */
public class User extends ButtonsAndBackground{
	JLabel uName = new JLabel("�û�����");
	JLabel uPwd = new JLabel("�û�����");
	JLabel uEmail = new JLabel("�û�����");
	JLabel uTel = new JLabel("�û��绰");
	JLabel uSelterID = new JLabel("���������");
	
	public JTextField uInName = new JTextField();
	public JPasswordField inPwd = new JPasswordField();
	public JTextField uInEmail= new JTextField();
	public JTextField uInTel= new JTextField();
	public JTextField uInShelterID= new JTextField();
	
	public JButton YesAdd = new JButton("ȷ�����");
	public User() {
		// TODO Auto-generated constructor stub
		super();
		String[]type = {"Ա������","Ա������","Ա���绰","���������"};
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
