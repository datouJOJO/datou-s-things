package PresentTier.user;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UUserPage extends User{
	JPanel uButtons = new JPanel();
	public UUserPage() {
		// TODO Auto-generated constructor stub
		super();
		SeeAll = new JButton("��ѯ�����û���Ϣ");
		SeeByNOrID = new JButton("������ѯ�û���Ϣ");

		uButtons.add(SeeAll);
		uButtons.add(SeeByNOrID);
		
		mainSelect.add(uButtons,BorderLayout.SOUTH);
//		mainSelect.setVisible(true);
	}
//	public static void main(String[] args) {
//		UserPage up = new UserPage();
//	}
}
