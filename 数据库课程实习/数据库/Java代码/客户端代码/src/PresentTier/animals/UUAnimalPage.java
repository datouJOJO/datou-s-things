package PresentTier.animals;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UUAnimalPage extends Animal{
	JPanel uButtons = new JPanel();
	public UUAnimalPage() {
		super();
		SeeAll = new JButton("��ѯ���ж�����Ϣ");
		SeeByNOrID = new JButton("������ѯ������Ϣ");

		uButtons.add(SeeAll);
		uButtons.add(SeeByNOrID);
		
		mainSelect.add(uButtons,BorderLayout.SOUTH);
	}
}
