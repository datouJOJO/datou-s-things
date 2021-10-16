package PresentTier.vaccinesImfo;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PresentTier.ButtonsAndBackground;

public class Vaccines extends ButtonsAndBackground{
	JLabel aName = new JLabel("动物id");
	JLabel uName = new JLabel("员工id");
	JLabel vType = new JLabel("疫苗名称");
	JLabel more = new JLabel("备注");
	
	public JTextField aInName= new JTextField();
	public JTextField uInName= new JTextField();
	
	String[]vaccinesType = {"卫佳捌","三联疫苗","兔瘟疫苗","新城疫疫苗","鸭肝炎病毒疫苗"};
	public JComboBox<String> vInType;
	public JTextField inmore= new JTextField();
	public JButton YesAdd = new JButton("确认添加");
	
	String[] type = {"动物id","用户id"};
	public JComboBox<String>seeType;
	
	public Vaccines(){
		super();
		
		seeType = new JComboBox<String>(type);
		seeType.setBounds(10, 35, 80, 30);
		selectPage.add(seeType);
		selectWord.setText("");
//		selectPage.setVisible(true);
		vInType = new JComboBox<String>(vaccinesType);
		aName.setBounds(50, 50, 60, 20);
		uName.setBounds(50, 90, 60, 20);
		vType.setBounds(50, 130, 60, 20);
		more.setBounds(50, 170, 80, 20);
		
		aInName.setBounds(120, 50, 200, 30);
		uInName.setBounds(120, 90, 200, 30);
		vInType.setBounds(120, 130, 200, 30);
		inmore.setBounds(120, 170, 200, 30);
		
		YesAdd.setBounds(140, 210, 100, 40);
		insertPage.add(aName);
		insertPage.add(uName);
		insertPage.add(vType);
		insertPage.add(more);
		
		insertPage.add(aInName);
		insertPage.add(uInName);
		insertPage.add(vInType);
		insertPage.add(inmore);
		insertPage.add(YesAdd);
		
		insertPage.setVisible(false);
	}
	
	public static void main(String[] args) {
		Vaccines v = new Vaccines();
	}
}
