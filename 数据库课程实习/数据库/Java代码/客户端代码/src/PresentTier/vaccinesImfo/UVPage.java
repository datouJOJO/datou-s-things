package PresentTier.vaccinesImfo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UVPage extends Vaccines{
	JPanel mButtons = new JPanel();
	public UVPage() {
		super();
		SeeAll = new JButton("��ѯ����������Ϣ");
		SeeByNOrID = new JButton("������ѯ������Ϣ");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
