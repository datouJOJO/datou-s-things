package PresentTier.shelters;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PresentTier.ButtonsAndBackground;

public class Shelter extends ButtonsAndBackground{
	JLabel sName = new JLabel("����");
	JLabel sAdrr = new JLabel("��ַ");
	JLabel sPost = new JLabel("�ʱ�");
	JLabel sRoom = new JLabel("������");
	
	public JTextField sInName = new JTextField();
	public JTextField sInAdrr= new JTextField();
	public JTextField sInPost= new JTextField();
	public JTextField sInRoom= new JTextField();
	
	public JButton YesAdd = new JButton("ȷ�����");
	
	public Shelter() {
		super();
		String[]type = {"����","��ַ","�ʱ�","�ܷ���"};
		updateType = new JComboBox<String>(type);
		updateType.setBounds(30, 30, 100, 30);
		UpdatePage.add(updateType);
		
		sName.setBounds(50, 50, 60, 20);
		sAdrr.setBounds(50, 90, 60, 20);
		sPost.setBounds(50, 130, 60, 20);
		sRoom.setBounds(50, 170, 80, 20);
		
		sInName.setBounds(120, 50, 200, 30);
		sInAdrr.setBounds(120, 90, 200, 30);
		sInPost.setBounds(120, 130, 200, 30);
		sInRoom.setBounds(120, 170, 200, 30);
		
		YesAdd.setBounds(140, 210, 100, 40);
		
		insertPage.add(sName);
		insertPage.add(sAdrr);
		insertPage.add(sPost);
		insertPage.add(sRoom);
		
		insertPage.add(sInName);
		insertPage.add(sInAdrr);
		insertPage.add(sInPost);
		insertPage.add(sInRoom);
		insertPage.add(YesAdd);
		
		insertPage.setVisible(false);
	}
	
	public static void main(String[] args) {
		Shelter s = new Shelter();
	}
}
