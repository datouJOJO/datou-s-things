package PresentTier.animals;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MUAnimalPage extends Animal{
	JPanel mButtons = new JPanel();
	public MUAnimalPage() {
		super();
		SeeAll = new JButton("��ѯ���ж�����Ϣ");
		SeeByNOrID = new JButton("������ѯ������Ϣ");
		addImfo = new JButton("���Ӷ����¼");
		deleteImfo = new JButton("ɾ�������¼");
		updateImfo = new JButton("���¶����¼");

		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		mButtons.add(deleteImfo);
		mButtons.add(updateImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
