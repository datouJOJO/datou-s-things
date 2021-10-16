package PresentTier.animals;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UUAnimalPage extends Animal{
	JPanel uButtons = new JPanel();
	public UUAnimalPage() {
		super();
		SeeAll = new JButton("查询所有动物信息");
		SeeByNOrID = new JButton("条件查询动物信息");

		uButtons.add(SeeAll);
		uButtons.add(SeeByNOrID);
		
		mainSelect.add(uButtons,BorderLayout.SOUTH);
	}
}
