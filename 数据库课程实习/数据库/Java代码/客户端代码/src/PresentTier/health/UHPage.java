package PresentTier.health;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UHPage extends Health{
	JPanel mButtons = new JPanel();
	public UHPage() {
		super();
		SeeAll = new JButton("查询所有健康信息");
		SeeByNOrID = new JButton("条件查询健康信息");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	 }
}
