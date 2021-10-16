package PresentTier.health;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MHPage extends Health{
	JPanel mButtons = new JPanel();
	public MHPage() {
		super();
		SeeAll = new JButton("查询所有健康信息");
		SeeByNOrID = new JButton("条件查询健康信息");
		addImfo = new JButton("增加健康记录");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
