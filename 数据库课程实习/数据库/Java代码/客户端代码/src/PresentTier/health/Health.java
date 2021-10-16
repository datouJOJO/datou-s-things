package PresentTier.health;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PresentTier.ButtonsAndBackground;

public class Health extends ButtonsAndBackground{
	JLabel aName = new JLabel("动物id");
	JLabel uName = new JLabel("员工id");
	JLabel hType = new JLabel("健康状况");
	JLabel more = new JLabel("备注");
	
	public JTextField aInName= new JTextField();
	public JTextField uInName= new JTextField();
	public JTextField hInType= new JTextField();
	public JTextField inmore= new JTextField();
	public JButton YesAdd = new JButton("确认添加");
	
	String[] type = {"动物id","用户id"};
	public JComboBox<String>seeType;
	
	public Health() {
		super();
		
		seeType = new JComboBox<String>(type);
		seeType.setBounds(10, 35, 80, 30);
		selectPage.add(seeType);
		selectWord.setText("");
//		selectPage.setVisible(true);
		
		aName.setBounds(50, 50, 60, 20);
		uName.setBounds(50, 90, 60, 20);
		hType.setBounds(50, 130, 60, 20);
		more.setBounds(50, 170, 80, 20);
		
		aInName.setBounds(120, 50, 200, 30);
		uInName.setBounds(120, 90, 200, 30);
		hInType.setBounds(120, 130, 200, 30);
		inmore.setBounds(120, 170, 200, 30);
		
		YesAdd.setBounds(140, 210, 100, 40);
		insertPage.add(aName);
		insertPage.add(uName);
		insertPage.add(hType);
		insertPage.add(more);
		
		insertPage.add(aInName);
		insertPage.add(uInName);
		insertPage.add(hInType);
		insertPage.add(inmore);
		insertPage.add(YesAdd);
	}
	public static void main(String[] args) {
		Health h = new Health();
	}
}
