package PresentTier.animals;

import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import PresentTier.ButtonsAndBackground;

public class Animal extends ButtonsAndBackground{
	JLabel aName = new JLabel("动物昵称");
	JLabel aType = new JLabel("动物种类");
	JLabel aGender= new JLabel("动物性别");
	JLabel aAge = new JLabel("动物年龄");
	JLabel aSelterID = new JLabel("收容所编号");
	//文件路径
	JTextField imgFile = new JTextField();
	
	public JTextField aInName = new JTextField();
	public JTextField aInType = new JTextField();
	String[]genders = {"公","母"};
	public JComboBox<String>aGChoose = new JComboBox<String>(genders);
	public JTextField aInAge= new JTextField();
	public JTextField aInShelterID= new JTextField();
	public JTextField fileImfo = new JTextField();
	
	public JButton yesAdd = new JButton("确认添加");
	public JButton yesFile = new JButton("浏览");
	
	//保存动物图片路径
	public FileInputStream fs;
	public String filePath;
	
	
	public Animal() {
		// TODO Auto-generated constructor stub
		super();
		
		String[]type = {"动物昵称","收容所id"};
		updateType = new JComboBox<String>(type);
		updateType.setBounds(30, 30, 100, 30);
		UpdatePage.add(updateType);
		
		dbImfo.setRowHeight(60);
		
		aName.setBounds(20, 10, 60, 20);
		aType.setBounds(20, 50, 60, 20);
		aGender.setBounds(20, 90, 60, 20);
		aAge.setBounds(20, 130, 60, 20);
		aSelterID.setBounds(10, 170, 80, 20);
		
		
		aInName.setBounds(80, 10, 200, 30);
		aInType.setBounds(80, 50, 200, 30);
		aGChoose.setBounds(80, 90, 200, 30);
		aInAge.setBounds(80, 130, 200, 30);
		aInShelterID.setBounds(80, 170, 200, 30);
		fileImfo.setBounds(90, 210, 140, 30);
		
		yesAdd.setBounds(290, 210, 100, 40);
		yesFile.setBounds(0, 210, 80, 29);
		
		insertPage.add(aName);
		insertPage.add(aType);
		insertPage.add(aGender);
		insertPage.add(aAge);
		insertPage.add(aSelterID);
		
		insertPage.add(aInName);
		insertPage.add(aInType);
		insertPage.add(aGChoose);
		insertPage.add(aInAge);
		insertPage.add(aInShelterID);
		insertPage.add(yesAdd);
		insertPage.add(yesFile);
		insertPage.add(fileImfo);
		insertPage.setVisible(false);
		
	}
//	public static void main(String[] args) {
//		Animal a = new Animal();
//	}
}
