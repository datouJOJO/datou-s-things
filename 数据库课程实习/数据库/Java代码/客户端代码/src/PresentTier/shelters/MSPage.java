package PresentTier.shelters;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MSPage extends Shelter{
	JPanel mButtons = new JPanel();
	public MSPage() {
		super();
		SeeAll = new JButton("��ѯ������������Ϣ");
		SeeByNOrID = new JButton("������ѯ��������Ϣ");
		addImfo = new JButton("������������¼");
		updateImfo = new JButton("������������¼");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		mButtons.add(updateImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
		
	}
}
