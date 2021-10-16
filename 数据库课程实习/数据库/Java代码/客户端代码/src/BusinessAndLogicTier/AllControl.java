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
	//�ж��ǹ����ߵ�¼��������ͨ�û���¼
	private int flag = 0;
	public AllControl(String oraclName,String user,String pwd) {
		// TODO Auto-generated constructor stub
		//�����û����������
		System.out.println("���ڼ����û���Ϣ...");
		oc = new OracleConnection(oraclName,user,pwd);
		userNameAndPwd = new HashMap<String, String>();
		managerNameAndPwd = new HashMap<String, String>();
		loadUserImfo();
		System.out.println("�������...");
		//�����ʼ��
		mainPage = new MainPage();
		mainPage.loginpage.login.addActionListener(this);
		//��ѡ�񴰿ڵİ�ť��Ӽ���
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
		//�ȱ����û����˻�����
		try {
			while(oc.userImfo.next()) {
				userNameAndPwd.put(oc.userImfo.getString(1),oc.userImfo.getString(2));
			}
			//�ٱ������Ա���˻�����
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
			//0������ͨ�û� 1���������
		if(userNameAndPwd.containsKey(name)) {
			if(userNameAndPwd.get(name).equals(pwd)) {
				flag = 0;
				//Ȼ������û�ҳ��
				mainPage.choosePage.chooseP.setVisible(true);
				mainPage.loginpage.loginPage.dispose();
			}else {
				//����������Ϣ
				JOptionPane.showMessageDialog(null, "��������ȷ���û���������", "�������", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(managerNameAndPwd.containsKey(name)) {
			if(managerNameAndPwd.get(name).equals(pwd)) {
				flag = 1;
				//Ȼ����ع���ҳ��
				mainPage.choosePage.chooseP.setVisible(true);
				mainPage.loginpage.loginPage.dispose();
		}
		else {
			//����������Ϣ
			JOptionPane.showMessageDialog(null, "��������ȷ���û���������", "�������", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		
//----------------------------------------�û���Ϣ��ѯ--------------------------------
		else if(e.getSource()==mainPage.choosePage.userimfo) {
			if(flag==1) {
				mainPage.MUP.mainSelect.setVisible(true);
			}else {
				mainPage.UUP.mainSelect.setVisible(true);
			}
//-----------------------------------------������Ϣ��ѯ-------------------------------
		}else if(e.getSource()==mainPage.choosePage.animalimfo) {
			if(flag==1) {
				mainPage.MAP.mainSelect.setVisible(true);
			}else {
				mainPage.UAP.mainSelect.setVisible(true);
			}
//---------------------------------------��������Ϣ��ѯ-------------------------------
		}else if(e.getSource()==mainPage.choosePage.shelterImfo) {
			if(flag==1) {
				mainPage.MSP.mainSelect.setVisible(true);
			}else {
				mainPage.USP.mainSelect.setVisible(true);
			}
//---------------------------------------������Ϣ��ѯ---------------------------------
		}else if(e.getSource()==mainPage.choosePage.healthImfo) {
			if(flag==1) {
				mainPage.MHP.mainSelect.setVisible(true);
			}else {
				mainPage.UHP.mainSelect.setVisible(true);
			}
//---------------------------------------������Ϣ��ѯ---------------------------------
		}else if(e.getSource()==mainPage.choosePage.VaccinesImfo) {
			if(flag==1) {
				mainPage.MVP.mainSelect.setVisible(true);
			}else {
				mainPage.UVP.mainSelect.setVisible(true);
			}
		}
//---------------------------------------��ѯ�����û���Ϣ-----------------------------
		else{
			if(e.getSource()==mainPage.MUP.SeeAll) {
				ResultSet rs = oc.seeAllUser();
				Object[] columnNames = {"�û����","�û�����","����","�绰","����������"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MUP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
//--------------------------------------�ؼ��ֲ�ѯ�û���Ϣ����----------------------------			
			else if(e.getSource()==mainPage.MUP.SeeByNOrID) {
				mainPage.MUP.selectWord.setText("�û�����id");
				mainPage.MUP.selectPage.setVisible(true);
			}
//---------------------------------------ȷ�ϲ�ѯ�û���Ϣ��ť-------------------------			
			else if(e.getSource()==mainPage.MUP.selectB) {
				String input = mainPage.MUP.inSelect.getText();
				boolean temp = oc.judgeUser(input);
				if(temp) {
					mainPage.MUP.selectPage.dispose();
					ResultSet rs = oc.seeUserByNameOrID(input);
					Object[] columnNames = {"�û����","�û�����","����","�绰","����������"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MUP.dbImfo.getModel();
					dtm.getDataVector().clear();
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "�û�id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------�����û���¼����---------------------------------
			else if(e.getSource()==mainPage.MUP.addImfo) {
				mainPage.MUP.insertPage.setVisible(true);
			}
//---------------------------------------ȷ�������û���Ϣ��ť----------------------------------			
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
						JOptionPane.showMessageDialog(null, "�������ݳɹ�", "successful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "������id����ȷ������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------�����û���Ϣ����-------------------------------
			else if(e.getSource()==mainPage.MUP.updateImfo) {
				mainPage.MUP.UpdatePage.setVisible(true);
				mainPage.MUP.inUpdateWord.setText("���µ�Ա��id");
			}
//----------------------------------ȷ�ϸ���---------------------------------------
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
								JOptionPane.showMessageDialog(null, "�������ݳɹ�", "successful", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "������id����ȷ������", "�������", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						boolean updated = oc.updateUser(id,index,data);
						if(updated) {
							mainPage.MUP.UpdatePage.dispose();
							JOptionPane.showMessageDialog(null, "�������ݳɹ�", "successful", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "�û�id����ȷ������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------ɾ���û���Ϣ����---------------------------------
			else if(e.getSource()==mainPage.MUP.deleteImfo) {
				mainPage.MUP.deleteWord.setText("�û�����id");
				mainPage.MUP.deletePage.setVisible(true);
			}
//----------------------------------ȷ��ɾ��----------------------------------------
			else if(e.getSource()==mainPage.MUP.deleteB) {
				String userID = mainPage.MUP.indelete.getText();
				boolean user = oc.judgeUser(userID);
				if(user) {
					boolean deleted = oc.deleteUser(userID);
					if(deleted) {
						mainPage.MUP.deletePage.dispose();
						JOptionPane.showMessageDialog(null, "ɾ�����ݳɹ�", "successful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "ɾ������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "�û�id����ȷ������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//------------------------------------��ͨ�û�---------------------------------------
//------------------------------------��ѯ�û���Ϣ---------------------------------------
			else if(e.getSource()==mainPage.UUP.SeeAll) {
				ResultSet rs = oc.seeAllUser();
				Object[] columnNames = {"�û����","�û�����","����","�绰","����������"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.UUP.dbImfo.getModel();
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}

			else if(e.getSource()==mainPage.UUP.SeeByNOrID) {
				mainPage.UUP.selectWord.setText("�û�����id");
				mainPage.UUP.selectPage.setVisible(true);
			}
			
			else if(e.getSource()==mainPage.UUP.selectB) {
				String input = mainPage.UUP.inSelect.getText();
				boolean temp = oc.judgeUser(input);
				if(temp) {
					mainPage.UUP.selectPage.dispose();
					ResultSet rs = oc.seeUserByNameOrID(input);
					Object[] columnNames = {"�û����","�û�����","����","�绰","����������"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UUP.dbImfo.getModel();
					dtm.setColumnIdentifiers(columnNames);
					dtm.getDataVector().clear();
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "�û�id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------��ͨ�û�----------------------------------
//---------------------------------------��ѯ��Ϣ----------------------------------
			else if(e.getSource()==mainPage.UAP.SeeAll) {
				ResultSet rs = oc.seeAllAnimal();
				Object[] columnNames = {"id","�ǳ�","����","�Ա�","����","ͼ����","ͼ��","������"};
				ImageTableCellRenderer render = new ImageTableCellRenderer();
				mainPage.UAP.dbImfo.setDefaultRenderer(Object.class, render);
				DefaultTableModel dtm = (DefaultTableModel)mainPage.UAP.dbImfo.getModel();
				dtm.setColumnIdentifiers(columnNames);
				dtm.getDataVector().clear();
				displayAnimal(dtm,rs);
			}
			else if(e.getSource()==mainPage.UAP.SeeByNOrID) {
				mainPage.UAP.selectWord.setText("name��id");
				mainPage.UAP.selectPage.setVisible(true);
			}
			else if(e.getSource()==mainPage.UAP.selectB) {
				String NameOrId = mainPage.UAP.inSelect.getText();
				ResultSet rs = null;
				boolean anms = oc.judgeAnimal(NameOrId);
				if(anms) {
					rs = oc.seeAnaimal(NameOrId);
					Object[] columnNames = {"id","�ǳ�","����","�Ա�","����","ͼ����","ͼ��","������"};
					ImageTableCellRenderer render = new ImageTableCellRenderer();
					mainPage.UAP.dbImfo.setDefaultRenderer(Object.class, render);
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UAP.dbImfo.getModel();
					dtm.setColumnIdentifiers(columnNames);
					dtm.getDataVector().clear();
					displayAnimal(dtm,rs);
				}else {
					JOptionPane.showMessageDialog(null, "����id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------------------------------------------------
//---------------------------------------����Ա------------------------------------
//---------------------------------------��ѯ������Ϣ------------------------------
			else if(e.getSource()==mainPage.MAP.SeeAll){
				ResultSet rs = oc.seeAllAnimal();
				Object[] columnNames = {"id","�ǳ�","����","�Ա�","����","ͼ����","ͼ��","������"};
				ImageTableCellRenderer render = new ImageTableCellRenderer();
				mainPage.MAP.dbImfo.setDefaultRenderer(Object.class, render);
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MAP.dbImfo.getModel();
				dtm.setColumnIdentifiers(columnNames);
				dtm.getDataVector().clear();
				displayAnimal(dtm,rs);
			}
			
			else if(e.getSource()==mainPage.MAP.SeeByNOrID) {
				mainPage.MAP.selectWord.setText("name��id");
				mainPage.MAP.selectPage.setVisible(true);
			}
			
			else if(e.getSource()==mainPage.MAP.selectB) {
				String NameOrId = mainPage.MAP.inSelect.getText();
				ResultSet rs = null;
				boolean anms = oc.judgeAnimal(NameOrId);
				if(anms) {
					rs = oc.seeAnaimal(NameOrId);
					Object[] columnNames = {"id","�ǳ�","����","�Ա�","����","ͼ����","ͼ��","������"};
					ImageTableCellRenderer render = new ImageTableCellRenderer();
					mainPage.MAP.dbImfo.setDefaultRenderer(Object.class, render);
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MAP.dbImfo.getModel();
					dtm.setColumnIdentifiers(columnNames);
					dtm.getDataVector().clear();
					displayAnimal(dtm,rs);
				}else {
					JOptionPane.showMessageDialog(null, "����id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------���Ӷ����¼------------------------------------------
			else if(e.getSource()==mainPage.MAP.addImfo) {
				mainPage.MAP.insertPage.setVisible(true);
			}
			//�ļ�ѡ����
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
					gender = "��";
				}else {
					gender = "ĸ";
				}
				String age = mainPage.MAP.aInAge.getText();
				String ImgPath = mainPage.MAP.filePath;
				String sid = mainPage.MAP.aInShelterID.getText();
				boolean shelter = oc.judgeShelter(sid);
				if(shelter) {
					boolean added = oc.addAnimal(name, type, gender, age, ImgPath, mainPage.MAP.fs, sid);
					if(added) {
						mainPage.MAP.insertPage.dispose();
						JOptionPane.showMessageDialog(null, "�������ݳɹ�", "successful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "������id������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//----------------------------------���¶����¼------------------------------------------
			else if(e.getSource()==mainPage.MAP.updateImfo)	{
				mainPage.MAP.UpdatePage.setVisible(true);
				mainPage.MAP.inUpdateWord.setText("���µĶ���id");
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
							JOptionPane.showMessageDialog(null, "������id������", "�������", JOptionPane.ERROR_MESSAGE);
							update = false;
						}
					}
					if(update) {
						boolean updated = oc.updateAnimal(index, data, id);
						if(updated) {
							JOptionPane.showMessageDialog(null, "�������ݳɹ�", "successful", JOptionPane.INFORMATION_MESSAGE);
							mainPage.MAP.UpdatePage.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "����id������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//----------------------------------ɾ�������¼------------------------------------------
			else if(e.getSource() == mainPage.MAP.deleteImfo) {
				mainPage.MAP.deleteWord.setText("����id");
				mainPage.MAP.deletePage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.MAP.deleteB) {
				String id = mainPage.MAP.indelete.getText();
				boolean delete = oc.judgeAnimal(id);
				if(delete) {
					boolean deleted = oc.deleteAnimal(id);
					if(deleted) {
						JOptionPane.showMessageDialog(null, "ɾ�����ݳɹ�", "successful", JOptionPane.INFORMATION_MESSAGE);
						mainPage.MAP.deletePage.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "ɾ������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "����id������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//------------------------------------------������-----------------------------------------
//------------------------------------------����Ա-----------------------------------------
//------------------------------------------��ѯ����---------------------------------------
			else if(e.getSource() == mainPage.MSP.SeeAll) {
				ResultSet rs = oc.seeAllShelter();
				Object[] columnNames = {"���������","����������","��������ַ","�������ʱ�","�������ܷ�����","������ʣ�෿��"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.MSP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
//------------------------------------------��ѯ����---------------------------------------
			else if(e.getSource() == mainPage.MSP.SeeByNOrID) {
				mainPage.MSP.selectWord.setText("���ƻ�id");
				mainPage.MSP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.MSP.selectB) {
				String input = mainPage.MSP.inSelect.getText();
				boolean temp = oc.judgeShelter(input);
				if(temp) {
					mainPage.MSP.selectPage.dispose();
					ResultSet rs = oc.seeShelter(input);
					Object[] columnNames = {"���������","����������","��������ַ","�������ʱ�","�������ܷ�����","������ʣ�෿��"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MSP.dbImfo.getModel();
					dtm.getDataVector().clear();
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "������id����������������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//-----------------------------------------�����Ϣ����------------------------------------
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
					JOptionPane.showMessageDialog(null, "������ݳɹ�", "succesful", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "�������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------��������----------------------------------------
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
						JOptionPane.showMessageDialog(null, "�������ݳɹ�", "succesful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "�ؼ��ʲ�����", "failure", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//---------------------------------------��ͨ�û�---------------------------------------
//---------------------------------------����ѯ-----------------------------------------
			else if(e.getSource() == mainPage.USP.SeeAll) {
				ResultSet rs = oc.seeAllShelter();
				Object[] columnNames = {"���������","����������","��������ַ","�������ʱ�","�������ܷ�����","������ʣ�෿��"};
				DefaultTableModel dtm = (DefaultTableModel)mainPage.USP.dbImfo.getModel();
				dtm.getDataVector().clear();  
				dtm.setColumnIdentifiers(columnNames);
				displayTable(dtm, rs);
			}
			
			else if(e.getSource() == mainPage.USP.SeeByNOrID) {
				mainPage.USP.selectWord.setText("���ƻ�id");
				mainPage.USP.selectPage.setVisible(true);
			}
			
			else if(e.getSource() == mainPage.USP.selectB) {
				String input = mainPage.USP.inSelect.getText();
				boolean temp = oc.judgeShelter(input);
				if(temp) {
					mainPage.USP.selectPage.dispose();
					ResultSet rs = oc.seeShelter(input);
					Object[] columnNames = {"���������","����������","��������ַ","�������ʱ�","�������ܷ�����","������ʣ�෿��"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.USP.dbImfo.getModel();
					dtm.getDataVector().clear();
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
				}else {
					JOptionPane.showMessageDialog(null, "������id����������������", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
			
//-------------------------------------������Ϣ--------------------------------------
//-------------------------------------����Ա----------------------------------------
//-------------------------------------��ѯ��Ϣ--------------------------------------
			else if(e.getSource() == mainPage.MHP.SeeAll) {
				ResultSet rs = oc.seeAllHealth();
				Object[] columnNames = {"������","�����ǳ�","Ա������","������Ϣ","�������","��ע"};
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
					//����
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "����id������������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//�û�
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "�û�id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeHealth(inputs);
					Object[] columnNames = {"������","�����ǳ�","Ա������","������Ϣ","�������","��ע"};
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
						JOptionPane.showMessageDialog(null, "�������ݳɹ�", "succesful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "����ؼ��ִ���", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//-----------------------------------��ͨ�û�----------------------------------
//-----------------------------------����ѯ------------------------------------
			else if(e.getSource() == mainPage.UHP.SeeAll) {
				ResultSet rs = oc.seeAllHealth();
				Object[] columnNames = {"������","�����ǳ�","Ա������","������Ϣ","�������","��ע"};
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
					//����
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "����id������������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//�û�
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "�û�id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeHealth(inputs);
					Object[] columnNames = {"������","�����ǳ�","Ա������","������Ϣ","�������","��ע"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UHP.dbImfo.getModel();
					dtm.getDataVector().clear();  
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
					mainPage.UHP.selectPage.dispose();
				}
			}
			
//--------------------------------------������Ϣ------------------------------------
//--------------------------------------����Ա--------------------------------------
//--------------------------------------��ѯ----------------------------------------
			else if(e.getSource() == mainPage.MVP.SeeAll) {
				ResultSet rs = oc.seeAllV();
				Object[] columnNames = {"������","�����ǳ�","Ա������","��������","��������","��ע"};
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
					//����
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "����id������������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//�û�
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "�û�id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeV(inputs);
					Object[] columnNames = {"������","�����ǳ�","Ա������","��������","��������","��ע"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.MVP.dbImfo.getModel();
					dtm.getDataVector().clear();  
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
					mainPage.MVP.selectPage.dispose();
				}
			}
//------------------------------------�����Ϣ------------------------------------------
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
					type = "���Ѱ�";
					vid = "001";
				}else if(index==1) {
					type = "��������";
					vid = "002";
				}else if(index==2) {
					type = "��������";
					vid = "003";
				}else if(index==3) {
					type = "�³�������";
					vid = "004";
				}else {
					type = "Ѽ���ײ�������";
					vid = "005";
				}
				boolean user = oc.judgeUser(uid);
				boolean animal = oc.judgeAnimal(aid);
				if(user&&animal) {
					String[]inputs = {vid,aid,uid,type,more};
					boolean added = oc.addV(inputs);
					if(added) {
						mainPage.MVP.insertPage.dispose();;
						JOptionPane.showMessageDialog(null, "�������ݳɹ�", "succesful", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "��������ʧ��", "failure", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "����ؼ��ִ���", "�������", JOptionPane.ERROR_MESSAGE);
				}
			}
//---------------------------------------��ͨ�û�-------------------------------------
//---------------------------------------����ѯ---------------------------------------
			else if(e.getSource() == mainPage.UVP.SeeAll) {
				ResultSet rs = oc.seeAllV();
				Object[] columnNames = {"������","�����ǳ�","Ա������","��������","��������","��ע"};
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
					//����
					boolean animal = oc.judgeAnimal(data);
					if(!animal) {
						select = false;
						JOptionPane.showMessageDialog(null, "����id������������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//�û�
					boolean user = oc.judgeUser(data);
					if(!user) {
						select = false;
						JOptionPane.showMessageDialog(null, "�û�id���û���������", "�������", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(select) {
					String[]inputs = {data,String.valueOf(index)};
					ResultSet rs = oc.seeV(inputs);
					Object[] columnNames = {"������","�����ǳ�","Ա������","��������","��������","��ע"};
					DefaultTableModel dtm = (DefaultTableModel)mainPage.UVP.dbImfo.getModel();
					dtm.getDataVector().clear();  
					dtm.setColumnIdentifiers(columnNames);
					displayTable(dtm, rs);
					mainPage.UVP.selectPage.dispose();
				}
			}
			
		}
	}
	
	//��ʾ������� �������ݵ���ȷ��
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
	
	//��ʾ���ݿ�����
	public void displayTable(DefaultTableModel dtm,ResultSet rs) {
		//��������ʾ�ڱ����
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
	
	//��Ϊ�������ж��������� ���Ե���д��ʾ��
	public void displayAnimal(DefaultTableModel dtm,ResultSet rs) {
		try {
			int col = rs.getMetaData().getColumnCount();
			Object[]rowData = null;
			rowData = new Object[col];
			String filePath = null;
			while(rs.next()) {
				for(int i=1;i<=col;i++) {
					//����ͼƬ����
					if(i==2) {
						filePath = rs.getString(i);
						rowData[i-1] = filePath;
						filePath = "src/savePic/"+filePath+".jpg";
					}
					else if(i==7) {
						//�����ͼ���ļ�
						byte[] temp = rs.getBytes(7);
						File file = new File(filePath);
						ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(temp);
						BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
		                ImageIO.write(bufferedImage, "jpg", file);   //�м�Ĳ���֧�֣�png, jpg, gif
						//���浽����֮�� Ȼ��Ͷ�ȡ��ʾ
		                
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
	
	//��Ⱦ��
	class ImageTableCellRenderer extends DefaultTableCellRenderer{
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column){
			DefaultTableCellRenderer defaultRenderer=new DefaultTableCellRenderer();
			JLabel renderer=(JLabel)defaultRenderer.getTableCellRendererComponent(table, value,isSelected,
					hasFocus, row, column);
			if(value instanceof ImageIcon){
				renderer.setIcon((ImageIcon) value);
				renderer.setText("");//�����������ͬʱ��ʾͼƬ������
			}
			return  renderer;
		}
		public ImageTableCellRenderer(){
			
		}
	}

	public static void main(String[] args) {
		//������������Ӧ���� ������ҵ���߼���
//		System.out.println(args[0]+" "+args[1]+" "+args[2]);
//		new AllControl(args[0],args[1],args[2]);
		
		new AllControl("orcl","system","62591102");
	}
}
