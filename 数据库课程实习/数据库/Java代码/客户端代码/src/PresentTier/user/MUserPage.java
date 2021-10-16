package PresentTier.user;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MUserPage extends User{
	JPanel mButtons = new JPanel();
	public MUserPage() {
		// TODO Auto-generated constructor stub
		super();
		SeeAll = new JButton("查询所有用户信息");
		SeeByNOrID = new JButton("条件查询用户信息");
		addImfo = new JButton("增加用户记录");
		deleteImfo = new JButton("删除用户记录");
		updateImfo = new JButton("更新用户记录");

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
