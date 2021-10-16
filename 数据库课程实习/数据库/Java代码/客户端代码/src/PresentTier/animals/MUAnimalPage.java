package PresentTier.animals;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MUAnimalPage extends Animal{
	JPanel mButtons = new JPanel();
	public MUAnimalPage() {
		super();
		SeeAll = new JButton("查询所有动物信息");
		SeeByNOrID = new JButton("条件查询动物信息");
		addImfo = new JButton("增加动物记录");
		deleteImfo = new JButton("删除动物记录");
		updateImfo = new JButton("更新动物记录");

		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		mButtons.add(deleteImfo);
		mButtons.add(updateImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
