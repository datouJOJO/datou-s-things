package PresentTier.vaccinesImfo;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PresentTier.ButtonsAndBackground;

public class Vaccines extends ButtonsAndBackground{
	JLabel aName = new JLabel("����id");
	JLabel uName = new JLabel("Ա��id");
	JLabel vType = new JLabel("��������");
	JLabel more = new JLabel("��ע");
	
	public JTextField aInName= new JTextField();
	public JTextField uInName= new JTextField();
	
	String[]vaccinesType = {"���Ѱ�","��������","��������","�³�������","Ѽ���ײ�������"};
	public JComboBox<String> vInType;
	public JTextField inmore= new JTextField();
	public JButton YesAdd = new JButton("ȷ�����");
	
	String[] type = {"����id","�û�id"};
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
