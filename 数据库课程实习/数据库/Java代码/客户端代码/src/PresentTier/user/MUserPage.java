package PresentTier.user;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MUserPage extends User{
	JPanel mButtons = new JPanel();
	public MUserPage() {
		// TODO Auto-generated constructor stub
		super();
		SeeAll = new JButton("��ѯ�����û���Ϣ");
		SeeByNOrID = new JButton("������ѯ�û���Ϣ");
		addImfo = new JButton("�����û���¼");
		deleteImfo = new JButton("ɾ���û���¼");
		updateImfo = new JButton("�����û���¼");

		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		mButtons.add(deleteImfo);
		mButtons.add(updateImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
//		mainSelect.setVisible(true);
	}
//	public static void main(String[] args) {
//		ManagerPage mp = new ManagerPage();
//	}
}
