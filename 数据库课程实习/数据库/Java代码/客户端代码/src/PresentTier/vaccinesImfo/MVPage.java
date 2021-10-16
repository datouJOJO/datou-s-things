package PresentTier.vaccinesImfo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MVPage extends Vaccines{
	JPanel mButtons = new JPanel();
	public MVPage(){
		super();
		SeeAll = new JButton("查询所有疫苗信息");
		SeeByNOrID = new JButton("条件查询疫苗信息");
		addImfo = new JButton("增加疫苗记录");
		
		mButtons.add(SeeAll);
		mButtons.add(SeeByNOrID);
		mButtons.add(addImfo);
		
		mainSelect.add(mButtons,BorderLayout.SOUTH);
	}
}
