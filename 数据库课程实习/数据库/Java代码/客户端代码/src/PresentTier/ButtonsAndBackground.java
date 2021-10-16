package PresentTier;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ButtonsAndBackground {
	//这是一个专门保存各种按钮还有查询基本窗口的类
	//每个表都应该具备这几个按钮
	public JButton SeeAll;
	public JButton SeeByNOrID;
	public JButton addImfo;
	public JButton deleteImfo;
	public JButton updateImfo;
	//并且每个表都应该有这几个页面
	//主查询界面
	public JFrame mainSelect;
	public JPanel panel;
	//表格
	public JTable dbImfo;
	//增加记录界面
	//每个表的记录都不同 所以按照具体表的排列情况进行修改布局
	public JFrame insertPage;
	
	//查询条件界面
	public JFrame selectPage;
	public JLabel selectWord;
	public JTextField inSelect;
	public JButton selectB;
	//更新条件界面
	public JFrame UpdatePage;
	public JComboBox<String>updateType;
	public JTextField inUpdate;
	public JTextField inUpdateID;
	public JLabel inUpdateWord;
	public JButton updateB;
	//删除条件界面
	public JFrame deletePage;
	public JLabel deleteWord;
	public JTextField indelete;
	public JButton deleteB;
	public ButtonsAndBackground() {
		setUp();
	}
	
	public void setUp() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension testSize;
		int locationX = 0;
		int locationY = 0;
		//主查询界面
		mainSelect = new JFrame();
		mainSelect.setSize(800, 500);
		panel = new JPanel(new BorderLayout());
		dbImfo = new JTable();
		panel.add(dbImfo.getTableHeader(),BorderLayout.NORTH);
		panel.add(dbImfo,BorderLayout.CENTER);
		mainSelect.add(new JScrollPane(panel));
		mainSelect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		testSize = mainSelect.getSize(); 
		locationX = (screenSize.width-testSize.width)/2;
		locationY = (screenSize.height-testSize.height)/2;
		mainSelect.setLocation(locationX,locationY);
		mainSelect.setVisible(false);
		//查询条件界面
		selectPage = new JFrame("查询条件");
		selectPage.setSize(400, 200);
		selectPage.setLayout(null);
		testSize = selectPage.getSize(); 
		locationX = (screenSize.width-testSize.width)/2;
		locationY = (screenSize.height-testSize.height)/2;
		selectPage.setLocation(locationX,locationY);
		selectWord = new JLabel("text");
		selectWord.setBounds(30, 5, 80, 100);
		inSelect = new JTextField();
		inSelect.setBounds(100, 38, 250, 30);
		selectB = new JButton("确认查询");
		selectB.setBounds(140, 80, 100, 40);
		selectPage.add(selectB);
		selectPage.add(inSelect);
		selectPage.add(selectWord);
		selectPage.setVisible(false);
		//插入条件界面
		insertPage = new JFrame("插入条件");
		insertPage.setLayout(null);
		insertPage.setSize(400, 300);
		testSize = insertPage.getSize(); 
		locationX = (screenSize.width-testSize.width)/2;
		locationY = (screenSize.height-testSize.height)/2;
		insertPage.setLocation(locationX,locationY);
		insertPage.setVisible(false);
		//更新条件界面
		UpdatePage = new JFrame("更新条件");
		UpdatePage.setSize(400, 200);
		UpdatePage.setLayout(null);
		testSize = UpdatePage.getSize(); 
		locationX = (screenSize.width-testSize.width)/2;
		locationY = (screenSize.height-testSize.height)/2;
		UpdatePage.setLocation(locationX,locationY);
		inUpdate = new JTextField();
		inUpdate.setBounds(140, 30, 200, 30);
		updateB = new JButton("确认更新");
		updateB.setBounds(250, 70, 100, 40);
		inUpdateID = new JTextField();
		inUpdateID.setBounds(140, 70, 100, 30);
		inUpdateWord = new JLabel("text");
		inUpdateWord.setBounds(80, 30, 200, 100);
		UpdatePage.add(updateB);
		UpdatePage.add(inUpdate);
		UpdatePage.add(inUpdateID);
		UpdatePage.add(inUpdateWord);
		UpdatePage.setVisible(false);
		//删除条件界面
		deletePage = new JFrame("删除条件");
		deletePage.setSize(400, 200);
		deletePage.setLayout(null);
		testSize = deletePage.getSize(); 
		locationX = (screenSize.width-testSize.width)/2;
		locationY = (screenSize.height-testSize.height)/2;
		deletePage.setLocation(locationX,locationY);
		deleteWord = new JLabel("text");
		deleteWord.setBounds(30, 5, 80, 100);
		indelete = new JTextField();
		indelete.setBounds(100, 38, 250, 30);
		deleteB = new JButton("确认删除");
		deleteB.setBounds(140, 80, 100, 40);
		deletePage.add(deleteB);
		deletePage.add(indelete);
		deletePage.add(deleteWord);
		deletePage.setVisible(false);
	}
	
//	public static void main(String[] args) {
//		ButtonsAndBackground bab = new ButtonsAndBackground();
//	}
}
