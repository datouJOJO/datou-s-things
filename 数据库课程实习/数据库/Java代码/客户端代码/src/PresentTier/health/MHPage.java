package PresentTier.health;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MHPage extends Health{
	JPanel mButtons = new JPanel();
	public MHPage() {
		super();
		SeeAll = new JButton("��ѯ���н�����Ϣ");
		SeeByNOrID = new JButton("������ѯ������Ϣ");
		addImfo = new JButton("���ӽ�����¼");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
