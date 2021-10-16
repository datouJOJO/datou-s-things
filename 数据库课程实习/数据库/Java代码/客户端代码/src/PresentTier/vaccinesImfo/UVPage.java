package PresentTier.vaccinesImfo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UVPage extends Vaccines{
	JPanel mButtons = new JPanel();
	public UVPage() {
		super();
		SeeAll = new JButton("查询所有疫苗信息");
		SeeByNOrID = new JButton("条件查询疫苗信息");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
