package PresentTier.shelters;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class USPage extends Shelter{
	JPanel uButtons = new JPanel();
	public USPage() {
		super();
		SeeAll = new JButton("��ѯ������������Ϣ");
		SeeByNOrID = new JButton("������ѯ��������Ϣ");

		uButtons.add(SeeAll);
		uButtons.add(SeeByNOrID);
		
		mainSelect.add(uButtons,BorderLayout.SOUTH);
	}
}
