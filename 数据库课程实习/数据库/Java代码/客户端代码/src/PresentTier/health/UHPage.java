package PresentTier.health;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UHPage extends Health{
	JPanel mButtons = new JPanel();
	public UHPage() {
		super();
		SeeAll = new JButton("��ѯ���н�����Ϣ");
		SeeByNOrID = new JButton("������ѯ������Ϣ");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	 }
}
