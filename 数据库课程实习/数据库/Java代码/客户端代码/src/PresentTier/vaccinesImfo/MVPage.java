package PresentTier.vaccinesImfo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MVPage extends Vaccines{
	JPanel mButtons = new JPanel();
	public MVPage(){
		super();
		SeeAll = new JButton("��ѯ����������Ϣ");
		SeeByNOrID = new JButton("������ѯ������Ϣ");
		addImfo = new JButton("���������¼");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
