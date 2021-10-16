package PresentTier.shelters;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class USPage extends Shelter{
	JPanel uButtons = new JPanel();
	public USPage() {
		super();
		SeeAll = new JButton("查询所有收容所信息");
		SeeByNOrID = new JButton("条件查询收容所信息");

		uButtons.add(SeeAll);
		uButtons.add(SeeByNOrID);
		
		mainSelect.add(uButtons,BorderLayout.SOUTH);
	}
}
