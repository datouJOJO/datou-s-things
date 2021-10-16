package PresentTier.shelters;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MSPage extends Shelter{
	JPanel mButtons = new JPanel();
	public MSPage() {
		super();
		SeeAll = new JButton("查询所有收容所信息");
		SeeByNOrID = new JButton("条件查询收容所信息");
		addImfo = new JButton("增加收容所记录");
		updateImfo = new JButton("更新收容所记录");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		mButtons.add(updateImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
		
	}
}
