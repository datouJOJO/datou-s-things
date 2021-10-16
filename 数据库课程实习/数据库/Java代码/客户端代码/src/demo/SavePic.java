package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/*
 * �˹������ڽ�ͼƬ�ļ����浽���ݿ��animal�����
 */
public class SavePic {
	//��ȡanimal����������Ƭ�� ���ж���id
	//������resultset�� Ȼ�� forѭ�� �ж��ٸ���¼
	//��update set���ٴ�
	private ResultSet rs = null;
	
	//�������ݿ�
	Connection conn = null;
	private String url = "jdbc:oracle:"+"thin:@127.0.0.1:1521:";
	private String user;
	private String pwd;
	public ResultSet userImfo;
	public ResultSet managerImfo;
	public SavePic(String orclName,String user,String pwd) {
		// TODO Auto-generated constructor stub
		try {
			this.url = this.url+orclName;
			this.user = user;
			this.pwd = pwd;
			
			conn = connect();
			readPic();
			savePic();
//			seePic();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection connect() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(url,user,pwd);
	}
	
	public void readPic() {
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeImage(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			//������Ƭ��Ϣ
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO: handle exceptione
			e.printStackTrace();
		}
	}
	
	public void savePic() {
		//���ݶ�ȡ����ͼƬ���� ����Ӧ��ͼƬ��Ϣ�����ڱ����Ӧ��¼��
		try {
			while(rs.next()) {
				//�ֱ��ȡÿ��ͼƬ����Ϣ
				String id = rs.getString(1);
				String pic = rs.getString(2);
				
				File file = new File(pic);
				FileInputStream fis = new FileInputStream(file);
				CallableStatement call;
				call = conn.prepareCall("{call addImage(?,?)}");
				call.setString(1, id);
				call.setBinaryStream(2, fis,fis.available());
				call.execute();
				call.close();
				fis.close();
			}
			System.out.println("���³ɹ�");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("����ʧ��");
	}
	
	//�����Ƿ�ɹ���ͼƬת�����ַ��� �Ƿ����ͨ�������������Ķ�����ͼƬ�ļ���� ���ҿ��Բ鿴
	public void seePic() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt
					.executeQuery("select * from animalimfo where animalid='011'");
			rst.next();
			// ��ȡBLOB���ݺ�������������Ȼ��ͨ��������������д���ļ���
			Blob blob = rst.getBlob("imgdata");
			//�ļ�������Ҫ�洢���ļ�
			FileOutputStream out = new FileOutputStream(new File("c:\\1.jpg"));
			InputStream in = blob.getBinaryStream();
			int i;
			while ((i = in.read()) != -1)
				out.write(i);
			// �ر����롢�����
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println("��ȡ��Ƭ������....");
		SavePic sp = new SavePic(args[0],args[1],args[2]);
		System.out.println("��ȡ��Ƭ���ݳɹ�...");
	}
}
