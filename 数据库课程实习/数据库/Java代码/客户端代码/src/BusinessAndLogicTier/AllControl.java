package BusinessAndLogicTier;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import PresentTier.MainPage;
import dbTier.OracleConnection;

public class AllControl implements ActionListener{
	private MainPage mainPage;
	private HashMap<String, String>userNameAndPwd;
	private HashMap<String, String>managerNameAndPwd;
	private OracleConnection oc;
	//判断是管理者登录或者是普通用户登录
	private int flag = 0;
	public AllControl(String oraclName,String user,String pwd) {
		// TODO Auto-generated constructor stub
		//加载用户名和密码表
		System.out.println("正在加载用户信息...");
		oc = new OracleConnection(oraclName,user,pwd);
		userNameAndPwd = new HashMap<String, String>();
		managerNameAndPwd = new HashMap<String, String>();
		loadUserImfo();
		System.out.println("加载完成...");
		//界面初始化
		mainPage = new MainPage();
		mainPage.loginpage.login.addActionListener(this);
		//给选择窗口的按钮添加监听
		mainPage.choosePage.userimfo.addActionListener(this);
		mainPage.choosePage.animalimfo.addActionListener(this);
		mainPage.choosePage.shelterImfo.addActionListener(this);
		mainPage.choosePage.healthImfo.addActionListener(this);
		mainPage.choosePage.VaccinesImfo.addActionListener(this);
		mainPage.MUP.SeeAll.addActionListener(this);
		mainPage.MUP.SeeByNOrID.addActionListener(this);
		mainPage.MUP.addImfo.addActionListener(this);
		mainPage.MUP.updateImfo.addActionListener(this);
		mainPage.MUP.deleteImfo.addActionListener(this);
		mainPage.MUP.selectB.addActionListener(this);
		mainPage.MUP.updateB.addActionListener(this);
		mainPage.MUP.deleteB.addActionListener(this);
		mainPage.MUP.YesAdd.addActionListener(this);
		
		mainPage.UUP.SeeAll.addActionListener(this);
		mainPage.UUP.SeeByNOrID.addActionListener(this);
		mainPage.UUP.selectB.addActionListener(this);
		
		mainPage.MAP.SeeAll.addActionListener(this);
		mainPage.MAP.selectB.addActionListener(this);
		mainPage.MAP.SeeByNOrID.addActionListener(this);
		mainPage.MAP.addImfo.addActionListener(this);
		mainPage.MAP.yesFile.addActionListener(this);
		mainPage.MAP.yesAdd.addActionListener(this);
		mainPage.MAP.updateImfo.addActionListener(this);
		mainPage.MAP.updateB.addActionListener(this);
		mainPage.MAP.deleteImfo.addActionListener(this);
		mainPage.MAP.deleteB.addActionListener(this);
		
		mainPage.UAP.SeeAll.addActionListener(this);
		mainPage.UAP.SeeByNOrID.addActionListener(this);
		mainPage.UAP.selectB.addActionListener(this);
		
		mainPage.MSP.SeeAll.addActionListener(this);
		mainPage.MSP.selectB.addActionListener(this);
		mainPage.MSP.SeeByNOrID.addActionListener(this);
		mainPage.MSP.addImfo.addActionListener(this);
		mainPage.MSP.YesAdd.addActionListener(this);
		mainPage.MSP.updateImfo.addActionListener(this);
		mainPage.MSP.updateB.addActionListener(this);
		mainPage.MSP.deleteB.addActionListener(this);
		
		mainPage.USP.SeeAll.addActionListener(this);
		mainPage.USP.SeeByNOrID.addActionListener(this);
		mainPage.USP.selectB.addActionListener(this);
		
		mainPage.MHP.SeeAll.addActionListener(this);
		mainPage.MHP.SeeByNOrID.addActionListener(this);
		mainPage.MHP.selectB.addActionListener(this);
		mainPage.MHP.addImfo.addActionListener(this);
		mainPage.MHP.YesAdd.addActionListener(this);
		
		mainPage.UHP.SeeAll.addActionListener(this);
		mainPage.UHP.SeeByNOrID.addActionListener(this);
		mainPage.UHP.selectB.addActionListener(this);
		
		mainPage.MVP.SeeAll.addActionListener(this);
		mainPage.MVP.SeeByNOrID.addActionListener(this);
		mainPage.MVP.selectB.addActionListener(this);
		mainPage.MVP.addImfo.addActionListener(this);
		mainPage.MVP.YesAdd.addActionListener(this);
		
		mainPage.UVP.SeeAll.addActionListener(this);
		mainPage.UVP.SeeByNOrID.addActionListener(this);
		mainPage.UVP.selectB.addActionListener(this);
	}
	
	public void loadUserImfo() {
		//先保存用户的账户密码
		try {
			while(oc.userImfo.next()) {
				userNameAndPwd.put(oc.userImfo.getString(1),oc.userImfo.getString(2));
			}
			//再保存管理员的账户密码
			while(oc.managerImfo.next()) {
				managerNameAndPwd.put(oc.managerImfo.getString(1),oc.managerImfo.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==mainPage.loginpage.login) {
			String name = mainPage.loginpage.inName.getText();
			String pwd = new String(mainPage.loginpage.inPwd.getPassword());
			//0代表普通用户 1代表管理者
		if(userNameAndPwd.containsKey(name)) {
			if(userNameAndPwd.get(name).equals(pwd)) {
				flag = 0;
				//然后加载用户页面
				mainPage.choosePage.chooseP.setVisible(true);
				mainPage.loginpage.loginPage.dispose();
			}else {
				//弹出错误信息
				JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码", "输入错误", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(managerNameAndPwd.containsKey(name)) {
			if(managerNameAndPwd.get(name).equals(pwd)) {
				flag = 1;
				//然后加载管理页面
				mainPage.choosePage.chooseP.setVisible(true);
				mainPage.loginpage.loginPage.dispose();
		}
		else {
			//弹出错误信息
			JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码", "输入错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		
//----------------------------------------用户信息查询--------------------------------
		else if(e.getSource()==mainPage.choosePage.userimfo) {
			if(flag==1) {
				mainPage.MUP.mainSelect.setVisible(true);
			}else {
				mainPage.UUP.mainSelect.setVisible(true);
			}
//-----------------------------------------动物信息查询-------------------------------
		}else if(e.getSource()==mainPage.choosePage.animalimfo) {
			if(flag==1) {
				mainPage.MAP.mainSelect.setVisible(true);
			}else {
				mainPage.UAP.mainSelect.setVisible(true);
			}
//---------------------------------------收容所信息查询-------------------------------
		}else if(e.getSource()==mainPage.choosePage.shelterImfo) {
			if(flag==1) {
				mainPage.MSP.mainSelect.setVisible(true);
			}else {
				mainPage.USP.mainSelect.setVisible(true);
			}
//---------------------------------------健康信息查询---------------------------------
		}else if(e.getSource()==mainPage.choosePage.healthImfo) {
			if(flag==1) {
				mainPage.MHP.mainSelect.setVisible(true);
			}else {
				mainPage.UHP.mainSelect.setVisible(true);
			}
//---------------------------------------疫苗信息查询---------------------------------
		}else if(e.getSource()==mainPage.choosePage.VaccinesImfo) {
			if(flag==1) {
				mainPage.MVP.mainSelect.setVisible(true);
			}else {
				mainPage.UVP.mainSelect.setVisible(true);
			}
		}
//---------------------------------------查询所有用户信息-----------------------------
		else{
			if(e.getSource()==mainPage.MUP.SeeAll) {
				ResultSet rs = oc.seeAllUser();
				Object[] columnNames = {"用户编号","用户姓名","邮箱","电话","收容所名称"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MUP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
//--------------------------------------关键字查询用户信息界面----------------------------			
			else if(e.getSource()==mainPage.MUP.SeeByNOrID) {
				mainPage.MUP.selectWord.setText("用户名或id");
				mainPage.MUP.selectPage.setVisible(true);
			}
//---------------------------------------确认查询用户信息按钮-------------------------			
			else if(e.getSource()==mainPage.MUP.selectB) {
				String input = mainPage.MUP.inSelect.getText();
				boolean temp = oc.judgeUser(input);
				if(temp) {
					mainPage.MUP.selectPage.dispose();
					ResultSet rs = oc.seeUserByNameOrID(input);
					Object[] columnNames = {"用户编号","用户姓名","邮箱","电话","收容所名称"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MUP.dbImfo.getModel();
					dtm.getDataVector().clear();
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "用户id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------增加用户记录界面---------------------------------
			else if(e.getSource()==mainPage.MUP.addImfo) {
				mainPage.MUP.insertPage.setVisible(true);
			}
//---------------------------------------确认增加用户信息按钮----------------------------------			
			else if(e.getSource()==mainPage.MUP.YesAdd) {
				String name = mainPage.MUP.uInName.getText();
				String pwd = new String(mainPage.MUP.inPwd.getPassword());
				String email = mainPage.MUP.uInEmail.getText();
				String tel = mainPage.MUP.uInTel.getText();
				String shelterID = mainPage.MUP.uInShelterID.getText();
				boolean temp = oc.judgeShelter(shelterID);
				String[]data = {name,pwd,email,tel,shelterID};
				if(temp) {
					boolean added = oc.addUser(data);
					if(added) {
						mainPage.MUP.insertPage.dispose();
						JOptionPane.showMessageDialog(null, "插入数据成功", "successful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "插入数据失败", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "收容所id或不正确不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------更新用户信息界面-------------------------------
			else if(e.getSource()==mainPage.MUP.updateImfo) {
				mainPage.MUP.UpdatePage.setVisible(true);
				mainPage.MUP.inUpdateWord.setText("更新的员工id");
			}
//----------------------------------确认更新---------------------------------------
			else if(e.getSource()== mainPage.MUP.updateB) {
				String id = mainPage.MUP.inUpdateID.getText();
				boolean user = oc.judgeUser(id);
				if(user) {
					int index = mainPage.MUP.updateType.getSelectedIndex();
					String data = mainPage.MUP.inUpdate.getText();
					if(index==3) {
						boolean shelter = oc.judgeShelter(mainPage.MUP.inUpdate.getText());
						if(shelter) {
							boolean updated = oc.updateUser(id,index,data);
							if(updated) {
								mainPage.MUP.UpdatePage.dispose();
								JOptionPane.showMessageDialog(null, "更新数据成功", "successful", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "更新数据失败", "failure", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "收容所id或不正确不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						boolean updated = oc.updateUser(id,index,data);
						if(updated) {
							mainPage.MUP.UpdatePage.dispose();
							JOptionPane.showMessageDialog(null, "更新数据成功", "successful", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "更新数据失败", "failure", JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "用户id或不正确不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------删除用户信息界面---------------------------------
			else if(e.getSource()==mainPage.MUP.deleteImfo) {
				mainPage.MUP.deleteWord.setText("用户名或id");
				mainPage.MUP.deletePage.setVisible(true);
			}
//----------------------------------确认删除----------------------------------------
			else if(e.getSource()==mainPage.MUP.deleteB) {
				String userID = mainPage.MUP.indelete.getText();
				boolean user = oc.judgeUser(userID);
				if(user) {
					boolean deleted = oc.deleteUser(userID);
					if(deleted) {
						mainPage.MUP.deletePage.dispose();
						JOptionPane.showMessageDialog(null, "删除数据成功", "successful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "删除数据失败", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "用户id或不正确不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//------------------------------------普通用户---------------------------------------
//------------------------------------查询用户信息---------------------------------------
			else if(e.getSource()==mainPage.UUP.SeeAll) {
				ResultSet rs = oc.seeAllUser();
				Object[] columnNames = {"用户编号","用户姓名","邮箱","电话","收容所名称"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.UUP.dbImfo.getModel();
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}

			else if(e.getSource()==mainPage.UUP.SeeByNOrID) {
				mainPage.UUP.selectWord.setText("用户名或id");
				mainPage.UUP.selectPage.setVisible(true);
			}
			
			else if(e.getSource()==mainPage.UUP.selectB) {
				String input = mainPage.UUP.inSelect.getText();
				boolean temp = oc.judgeUser(input);
				if(temp) {
					mainPage.UUP.selectPage.dispose();
					ResultSet rs = oc.seeUserByNameOrID(input);
					Object[] columnNames = {"用户编号","用户姓名","邮箱","电话","收容所名称"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UUP.dbImfo.getModel();
					dtm.setColumnIdentifiers(columnNames);
					dtm.getDataVector().clear();
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "用户id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------普通用户----------------------------------
//---------------------------------------查询信息----------------------------------
			else if(e.getSource()==mainPage.UAP.SeeAll) {
				ResultSet rs = oc.seeAllAnimal();
				Object[] columnNames = {"id","昵称","种类","性别","年龄","图像名","图像","收容所"};
				ImageTableCellRenderer render = new ImageTableCellRenderer();
				mainPage.UAP.dbImfo.setDefaultRenderer(Object.class, render);
				DefaultTableModel dtm = (DefaultTableModel)mainPage.UAP.dbImfo.getModel();
				dtm.setColumnIdentifiers(columnNames);
				dtm.getDataVector().clear();
				displayAnimal(dtm,rs);
			}
			else if(e.getSource()==mainPage.UAP.SeeByNOrID) {
				mainPage.UAP.selectWord.setText("name或id");
				mainPage.UAP.selectPage.setVisible(true);
			}
			else if(e.getSource()==mainPage.UAP.selectB) {
				String NameOrId = mainPage.UAP.inSelect.getText();
				ResultSet rs = null;
				boolean anms = oc.judgeAnimal(NameOrId);
				if(anms) {
					rs = oc.seeAnaimal(NameOrId);
					Object[] columnNames = {"id","昵称","种类","性别","年龄","图像名","图像","收容所"};
					ImageTableCellRenderer render = new ImageTableCellRenderer();
					mainPage.UAP.dbImfo.setDefaultRenderer(Object.class, render);
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UAP.dbImfo.getModel();
					dtm.setColumnIdentifiers(columnNames);
					dtm.getDataVector().clear();
					displayAnimal(dtm,rs);
				}else {
					JOptionPane.showMessageDialog(null, "动物id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------------------------------------------------
//---------------------------------------管理员------------------------------------
//---------------------------------------查询动物信息------------------------------
			else if(e.getSource()==mainPage.MAP.SeeAll){
				ResultSet rs = oc.seeAllAnimal();
				Object[] columnNames = {"id","昵称","种类","性别","年龄","图像名","图像","收容所"};
				ImageTableCellRenderer render = new ImageTableCellRenderer();
				mainPage.MAP.dbImfo.setDefaultRenderer(Object.class, render);
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MAP.dbImfo.getModel();
				dtm.setColumnIdentifiers(columnNames);
				dtm.getDataVector().clear();
				displayAnimal(dtm,rs);
			}
			
			else if(e.getSource()==mainPage.MAP.SeeByNOrID) {
				mainPage.MAP.selectWord.setText("name或id");
				mainPage.MAP.selectPage.setVisible(true);
			}
			
			else if(e.getSource()==mainPage.MAP.selectB) {
				String NameOrId = mainPage.MAP.inSelect.getText();
				ResultSet rs = null;
				boolean anms = oc.judgeAnimal(NameOrId);
				if(anms) {
					rs = oc.seeAnaimal(NameOrId);
					Object[] columnNames = {"id","昵称","种类","性别","年龄","图像名","图像","收容所"};
					ImageTableCellRenderer render = new ImageTableCellRenderer();
					mainPage.MAP.dbImfo.setDefaultRenderer(Object.class, render);
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MAP.dbImfo.getModel();
					dtm.setColumnIdentifiers(columnNames);
					dtm.getDataVector().clear();
					displayAnimal(dtm,rs);
				}else {
					JOptionPane.showMessageDialog(null, "动物id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------增加动物记录------------------------------------------
			else if(e.getSource()==mainPage.MAP.addImfo) {
				mainPage.MAP.insertPage.setVisible(true);
			}
			//文件选择器
			else if(e.getSource()==mainPage.MAP.yesFile) {
				JFileChooser fd = new JFileChooser();
				//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fd.showOpenDialog(null);
				File f = fd.getSelectedFile();
				if(f != null){
					try {
						mainPage.MAP.fs = new FileInputStream(f);
						mainPage.MAP.filePath = f.getAbsolutePath();
						mainPage.MAP.fileImfo.setText(f.getAbsolutePath());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			else if(e.getSource()==mainPage.MAP.yesAdd) {
				String name = mainPage.MAP.aInName.getText();
				String type = mainPage.MAP.aInType.getText();
				String gender = "";
				int index = mainPage.MAP.aGChoose.getSelectedIndex();
				if(index==0) {
					gender = "公";
				}else {
					gender = "母";
				}
				String age = mainPage.MAP.aInAge.getText();
				String ImgPath = mainPage.MAP.filePath;
				String sid = mainPage.MAP.aInShelterID.getText();
				boolean shelter = oc.judgeShelter(sid);
				if(shelter) {
					boolean added = oc.addAnimal(name, type, gender, age, ImgPath, mainPage.MAP.fs, sid);
					if(added) {
						mainPage.MAP.insertPage.dispose();
						JOptionPane.showMessageDialog(null, "插入数据成功", "successful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "插入数据失败", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "收容所id不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------更新动物记录------------------------------------------
			else if(e.getSource()==mainPage.MAP.updateImfo)	{
				mainPage.MAP.UpdatePage.setVisible(true);
				mainPage.MAP.inUpdateWord.setText("更新的动物id");
			}
			
			else if(e.getSource()==mainPage.MAP.updateB)	{
				System.out.println("?");
				int index = mainPage.MAP.updateType.getSelectedIndex();
				String data = mainPage.MAP.inUpdate.getText();
				String id = mainPage.MAP.inUpdateID.getText();
				boolean aID = oc.judgeAnimal(id);
				boolean update = true;
				if(aID) {
					if(index==1) {
						boolean shelter = oc.judgeShelter(data);
						if(shelter==false) {
							JOptionPane.showMessageDialog(null, "收容所id不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
							update = false;
						}
					}
					if(update) {
						boolean updated = oc.updateAnimal(index, data, id);
						if(updated) {
							JOptionPane.showMessageDialog(null, "更新数据成功", "successful", JOptionPane.INFORMATION_MESSAGE);
							mainPage.MAP.UpdatePage.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "更新数据失败", "failure", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "动物id不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//----------------------------------删除动物记录------------------------------------------
			else if(e.getSource() == mainPage.MAP.deleteImfo) {
				mainPage.MAP.deleteWord.setText("动物id");
				mainPage.MAP.deletePage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.MAP.deleteB) {
				String id = mainPage.MAP.indelete.getText();
				boolean delete = oc.judgeAnimal(id);
				if(delete) {
					boolean deleted = oc.deleteAnimal(id);
					if(deleted) {
						JOptionPane.showMessageDialog(null, "删除数据成功", "successful", JOptionPane.INFORMATION_MESSAGE);
						mainPage.MAP.deletePage.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "删除数据失败", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "动物id不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//------------------------------------------收容所-----------------------------------------
//------------------------------------------管理员-----------------------------------------
//------------------------------------------查询所有---------------------------------------
			else if(e.getSource() == mainPage.MSP.SeeAll) {
				ResultSet rs = oc.seeAllShelter();
				Object[] columnNames = {"收容所编号","收容所名称","收容所地址","收容所邮编","收容所总房间数","收容所剩余房间"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MSP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
//------------------------------------------查询部分---------------------------------------
			else if(e.getSource() == mainPage.MSP.SeeByNOrID) {
				mainPage.MSP.selectWord.setText("名称或id");
				mainPage.MSP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.MSP.selectB) {
				String input = mainPage.MSP.inSelect.getText();
				boolean temp = oc.judgeShelter(input);
				if(temp) {
					mainPage.MSP.selectPage.dispose();
					ResultSet rs = oc.seeShelter(input);
					Object[] columnNames = {"收容所编号","收容所名称","收容所地址","收容所邮编","收容所总房间数","收容所剩余房间"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MSP.dbImfo.getModel();
					dtm.getDataVector().clear();
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "收容所id或收容所名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//-----------------------------------------添加信息部分------------------------------------
			else if(e.getSource() == mainPage.MSP.addImfo) {
				mainPage.MSP.insertPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.MSP.YesAdd) {
				String name = mainPage.MSP.sInName.getText();
				String addr = mainPage.MSP.sInAdrr.getText();
				String post = mainPage.MSP.sInPost.getText();
				String room = mainPage.MSP.sInRoom.getText();
				String[] inputs = {name,addr,post,room};
				boolean added = oc.addShelter(inputs);
				if(added) {
					mainPage.MSP.insertPage.dispose();
					JOptionPane.showMessageDialog(null, "添加数据成功", "succesful", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "添加数据失败", "failure", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------更新数据----------------------------------------
			else if(e.getSource() == mainPage.MSP.updateImfo) {
				mainPage.MSP.UpdatePage.setVisible(true);
				mainPage.MSP.inUpdateWord.setText("id");
			}
			
			else if(e.getSource() == mainPage.MSP.updateB) {
				int index = mainPage.MSP.updateType.getSelectedIndex();
				String id = mainPage.MSP.inUpdateID.getText();
				String data = mainPage.MSP.inUpdate.getText();
				
				boolean update = oc.judgeShelter(id);
				if(update) {
					String[ ]inputs = {String.valueOf(index),data,id};
					boolean updated = oc.updateShelter(inputs);
					if(updated) {
						mainPage.MSP.UpdatePage.dispose();
						JOptionPane.showMessageDialog(null, "更新数据成功", "succesful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "更新数据失败", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "关键词不存在", "failure", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//---------------------------------------普通用户---------------------------------------
//---------------------------------------仅查询-----------------------------------------
			else if(e.getSource() == mainPage.USP.SeeAll) {
				ResultSet rs = oc.seeAllShelter();
				Object[] columnNames = {"收容所编号","收容所名称","收容所地址","收容所邮编","收容所总房间数","收容所剩余房间"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.USP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
			
			else if(e.getSource() == mainPage.USP.SeeByNOrID) {
				mainPage.USP.selectWord.setText("名称或id");
				mainPage.USP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.USP.selectB) {
				String input = mainPage.USP.inSelect.getText();
				boolean temp = oc.judgeShelter(input);
				if(temp) {
					mainPage.USP.selectPage.dispose();
					ResultSet rs = oc.seeShelter(input);
					Object[] columnNames = {"收容所编号","收容所名称","收容所地址","收容所邮编","收容所总房间数","收容所剩余房间"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.USP.dbImfo.getModel();
					dtm.getDataVector().clear();
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "收容所id或收容所名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//-------------------------------------健康信息--------------------------------------
//-------------------------------------管理员----------------------------------------
//-------------------------------------查询信息--------------------------------------
			else if(e.getSource() == mainPage.MHP.SeeAll) {
				ResultSet rs = oc.seeAllHealth();
				Object[] columnNames = {"健康码","动物昵称","员工名称","健康信息","检查日期","备注"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MHP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
			
			else if(e.getSource() == mainPage.MHP.SeeByNOrID) {
				mainPage.MHP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.MHP.selectB) {
				boolean select = true;
				int index = mainPage.MHP.seeType.getSelectedIndex();
				String data = mainPage.MHP.inSelect.getText();
				if(index==0) {
					//动物
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "动物id或动物名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//用户
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "用户id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeHealth(inputs);
					Object[] columnNames = {"健康码","动物昵称","员工名称","健康信息","检查日期","备注"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MHP.dbImfo.getModel();
					dtm.getDataVector().clear();  
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
					mainPage.MHP.selectPage.dispose();
				}
			}
			
			else if(e.getSource()==mainPage.MHP.addImfo) {
				mainPage.MHP.insertPage.setVisible(true);
			}
			
			else if(e.getSource()==mainPage.MHP.YesAdd) {
				String uid = mainPage.MHP.uInName.getText();
				String aid = mainPage.MHP.aInName.getText();
				String type = mainPage.MHP.hInType.getText();
				String more = mainPage.MHP.inmore.getText();
				boolean user = oc.judgeUser(uid);
				boolean animal = oc.judgeAnimal(aid);
				if(user&&animal) {
					String[]inputs = {aid,uid,type,more};
					boolean added = oc.addHealth(inputs);
					if(added) {
						mainPage.MHP.insertPage.dispose();;
						JOptionPane.showMessageDialog(null, "新增数据成功", "succesful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "新增数据失败", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "输入关键字错误", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//-----------------------------------普通用户----------------------------------
//-----------------------------------仅查询------------------------------------
			else if(e.getSource() == mainPage.UHP.SeeAll) {
				ResultSet rs = oc.seeAllHealth();
				Object[] columnNames = {"健康码","动物昵称","员工名称","健康信息","检查日期","备注"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.UHP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
			
			else if(e.getSource() == mainPage.UHP.SeeByNOrID) {
				mainPage.UHP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.UHP.selectB) {
				boolean select = true;
				int index = mainPage.UHP.seeType.getSelectedIndex();
				String data = mainPage.UHP.inSelect.getText();
				if(index==0) {
					//动物
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "动物id或动物名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//用户
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "用户id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeHealth(inputs);
					Object[] columnNames = {"健康码","动物昵称","员工名称","健康信息","检查日期","备注"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UHP.dbImfo.getModel();
					dtm.getDataVector().clear();  
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
					mainPage.UHP.selectPage.dispose();
				}
			}
			
//--------------------------------------疫苗信息------------------------------------
//--------------------------------------管理员--------------------------------------
//--------------------------------------查询----------------------------------------
			else if(e.getSource() == mainPage.MVP.SeeAll) {
				ResultSet rs = oc.seeAllV();
				Object[] columnNames = {"疫苗编号","动物昵称","员工名称","疫苗名称","接种日期","备注"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MVP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
			
			else if(e.getSource() == mainPage.MVP.SeeByNOrID) {
				mainPage.MVP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.MVP.selectB) {
				boolean select = true;
				int index = mainPage.MVP.seeType.getSelectedIndex();
				String data = mainPage.MVP.inSelect.getText();
				if(index==0) {
					//动物
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "动物id或动物名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//用户
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "用户id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeV(inputs);
					Object[] columnNames = {"疫苗编号","动物昵称","员工名称","疫苗名称","接种日期","备注"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MVP.dbImfo.getModel();
					dtm.getDataVector().clear();  
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
					mainPage.MVP.selectPage.dispose();
				}
			}
//------------------------------------添加信息------------------------------------------
			else if(e.getSource()==mainPage.MVP.addImfo) {
				mainPage.MVP.insertPage.setVisible(true);
			}
			
			else if(e.getSource()==mainPage.MVP.YesAdd) {
				String uid = mainPage.MVP.uInName.getText();
				String aid = mainPage.MVP.aInName.getText();
				int index = mainPage.MVP.vInType.getSelectedIndex();
				String more = mainPage.MVP.inmore.getText();
				String vid;
				String type;
				if(index==0) {
					type = "卫佳捌";
					vid = "001";
				}else if(index==1) {
					type = "三联疫苗";
					vid = "002";
				}else if(index==2) {
					type = "兔瘟疫苗";
					vid = "003";
				}else if(index==3) {
					type = "新城疫疫苗";
					vid = "004";
				}else {
					type = "鸭肝炎病毒疫苗";
					vid = "005";
				}
				boolean user = oc.judgeUser(uid);
				boolean animal = oc.judgeAnimal(aid);
				if(user&&animal) {
					String[]inputs = {vid,aid,uid,type,more};
					boolean added = oc.addV(inputs);
					if(added) {
						mainPage.MVP.insertPage.dispose();;
						JOptionPane.showMessageDialog(null, "新增数据成功", "succesful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "新增数据失败", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "输入关键字错误", "输入错误", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------普通用户-------------------------------------
//---------------------------------------仅查询---------------------------------------
			else if(e.getSource() == mainPage.UVP.SeeAll) {
				ResultSet rs = oc.seeAllV();
				Object[] columnNames = {"疫苗编号","动物昵称","员工名称","疫苗名称","接种日期","备注"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.UVP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
			
			else if(e.getSource() == mainPage.UVP.SeeByNOrID) {
				mainPage.UVP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.UVP.selectB) {
				boolean select = true;
				int index = mainPage.UVP.seeType.getSelectedIndex();
				String data = mainPage.UVP.inSelect.getText();
				if(index==0) {
					//动物
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "动物id或动物名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//用户
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "用户id或用户名不存在", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeV(inputs);
					Object[] columnNames = {"疫苗编号","动物昵称","员工名称","疫苗名称","接种日期","备注"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UVP.dbImfo.getModel();
					dtm.getDataVector().clear();  
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
					mainPage.UVP.selectPage.dispose();
				}
			}
			
		}
	}
	
	//显示表格数据 测试数据的正确性
	public void testDisplay(ResultSet rs) {
		try {
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				for(int i = 1;i<=col;i++) {
					System.out.print(rs.getString(i)+" ");
				}
				System.out.println();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//显示数据库数据
	public void displayTable(DefaultTableModel dtm,ResultSet rs) {
		//将数据显示在表格中
		try {
			int col = rs.getMetaData().getColumnCount();
			Object[]rowData = null;
			rowData = new Object[col];
			while(rs.next()) {
				for(int i =1;i<=col;i++) {
					rowData[i-1] = rs.getString(i);
				}
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//因为动物表带有二进制数据 所以单独写显示类
	public void displayAnimal(DefaultTableModel dtm,ResultSet rs) {
		try {
			int col = rs.getMetaData().getColumnCount();
			Object[]rowData = null;
			rowData = new Object[col];
			String filePath = null;
			while(rs.next()) {
				for(int i=1;i<=col;i++) {
					//保存图片名称
					if(i==2) {
						filePath = rs.getString(i);
						rowData[i-1] = filePath;
						filePath = "src/savePic/"+filePath+".jpg";
					}
					else if(i==7) {
						//如果是图像文件
						byte[] temp = rs.getBytes(7);
						File file = new File(filePath);
						ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(temp);
						BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
		                ImageIO.write(bufferedImage, "jpg", file);   //中间的参数支持：png, jpg, gif
						//保存到盘上之后 然后就读取显示
		                
		                ImageIcon image = new ImageIcon(filePath);
		                Image pic = image.getImage();
		                pic = pic.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		                image.setImage(pic);
		                rowData[i-1] = image;
		                byteArrayInputStream.close();
					}else {
						rowData[i-1] = rs.getString(i);
					}
				}
				dtm.addRow(rowData);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//渲染类
	class ImageTableCellRenderer extends DefaultTableCellRenderer{
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column){
			DefaultTableCellRenderer defaultRenderer=new DefaultTableCellRenderer();
			JLabel renderer=(JLabel)defaultRenderer.getTableCellRendererComponent(table, value,isSelected,
					hasFocus, row, column);
			if(value instanceof ImageIcon){
				renderer.setIcon((ImageIcon) value);
				renderer.setText("");//这里可以设置同时显示图片和文字
			}
			return  renderer;
		}
		public ImageTableCellRenderer(){
			
		}
	}

	public static void main(String[] args) {
		//控制行输入相应参数 传递至业务逻辑类
//		System.out.println(args[0]+" "+args[1]+" "+args[2]);
//		new AllControl(args[0],args[1],args[2]);
		
		new AllControl("orcl","system","62591102");
	}
}
