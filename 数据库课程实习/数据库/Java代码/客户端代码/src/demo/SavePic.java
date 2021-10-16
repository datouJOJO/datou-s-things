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
 * 此工程用于将图片文件保存到数据库的animal表格中
 */
public class SavePic {
	//读取animal表中所有照片名 还有动物id
	//保存在resultset中 然后 for循环 有多少个记录
	//就update set多少次
	private ResultSet rs = null;
	
	//连接数据库
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
			//保存照片信息
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO: handle exceptione
			e.printStackTrace();
		}
	}
	
	public void savePic() {
		//根据读取到的图片名称 将相应的图片信息保存在表的相应记录中
		try {
			while(rs.next()) {
				//分别读取每张图片的信息
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
			System.out.println("更新成功");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("更新失败");
	}
	
	//测试是否成功将图片转化成字符流 是否可以通过输出流将保存的二进制图片文件输出 并且可以查看
	public void seePic() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt
					.executeQuery("select * from animalimfo where animalid='011'");
			rst.next();
			// 获取BLOB数据和它的输入流，然后通过输入流把数据写到文件中
			Blob blob = rst.getBlob("imgdata");
			//文件换成你要存储的文件
			FileOutputStream out = new FileOutputStream(new File("c:\\1.jpg"));
			InputStream in = blob.getBinaryStream();
			int i;
			while ((i = in.read()) != -1)
				out.write(i);
			// 关闭输入、输出流
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println("读取照片数据中....");
		SavePic sp = new SavePic(args[0],args[1],args[2]);
		System.out.println("读取照片数据成功...");
	}
}
