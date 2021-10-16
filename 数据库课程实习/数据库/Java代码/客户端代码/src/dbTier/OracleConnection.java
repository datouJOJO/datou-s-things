package dbTier;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class OracleConnection {
	Connection conn = null;
	private String url = "jdbc:oracle:"+"thin:@127.0.0.1:1521:";	//需要填入Oracle
	private String user;
	private String pwd;
	public ResultSet userImfo;
	public ResultSet managerImfo;
	
	public OracleConnection(String orclName,String user,String pwd) {
		try {
			this.url = this.url+orclName;
			this.user = user;
			this.pwd = pwd;
			conn = connect();
			userImfo = loadUserImfo();
			managerImfo = loadManagetImfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection connect() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("开始连接数据库");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(url,user,pwd);
	}
	//读取用户账户密码
	public ResultSet loadUserImfo() {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeUserNameAndPwd(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(1);
			//获取结果集的列数
//			int col = rs.getMetaData().getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//读取管理员账户密码
	private ResultSet loadManagetImfo() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeManagerNameAndPwd(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//读取所有用户信息
	public ResultSet seeAllUser() {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeAllUserImfo(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean judgeUser(String input) {
		CallableStatement call;
		int flag = -1;
		try {
			call = conn.prepareCall("{call judgeUser(?,?)}");
			call.setString(1,input);
			call.registerOutParameter(2, OracleTypes.INTEGER);
			call.execute();
			flag = call.getInt(2);
			if(flag ==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public ResultSet seeUserByNameOrID(String input) {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call SeeUserbyNameOrID(?,?)}");
			call.setString(1, input);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(2);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	public boolean addUser(String[]data) {
		int i = 1;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call addUser(?,?,?,?,?)}");
			call.setString(1, data[0]);
			call.setString(2, data[1]);
			call.setString(3, data[2]);
			call.setInt(4, Integer.parseInt(data[3]));
			call.setInt(5, Integer.parseInt(data[4]));
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//判断收容所存不存在
	public boolean judgeShelter(String input) {
		CallableStatement call;
		int flag = -1;
		try {
			call = conn.prepareCall("{call judgeShelter(?,?)}");
			call.setInt(1,Integer.parseInt(input));
			call.registerOutParameter(2, OracleTypes.INTEGER);
			call.execute();
			flag = call.getInt(2);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		if(flag == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	//更新用户数据
	public boolean updateUser(String userID,int index,String data) {
		CallableStatement call;
		int flag = -1;
		try {
			call = conn.prepareCall("{call UpdateUser(?,?,?)}");
			call.setString(1,userID);
			call.setInt(2, index);
			call.setString(3,data);
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
		}
			return false;
	}
	
	//删除用户数据
	public boolean deleteUser(String userID) {
		CallableStatement call;
		try {
			call = conn.prepareCall("{call deleteUser(?)}");
			call.setString(1,userID);
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
		}
			return false;
	}
	
	//读入动物表数据
	public ResultSet seeAllAnimal() {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeAllAnimals(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//根据关键字查询动物数据
	public ResultSet seeAnaimal(String input) {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeAnimalByNameOrId(?,?)}");
			call.setString(1, input);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//判断查询的动物是否存在
	public boolean judgeAnimal(String input) {
		CallableStatement call;
		try {
			call = conn.prepareCall("{call judgeAnimal(?,?)}");
			call.setString(1, input);
			call.registerOutParameter(2, OracleTypes.INTEGER);
			call.execute();
			int flag = call.getInt(2);
			if(flag>=1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//增加动物数据
	public boolean addAnimal(String name,String type,String gender,String age,String img,FileInputStream fs,String sID) {
		CallableStatement call;
		try {
			call = conn.prepareCall("{call addAnimal(?,?,?,?,?,?,?)}");
			call.setString(1, name);
			call.setString(2, type);
			call.setString(3, gender);
			call.setString(4, age);
			call.setString(5, img);
			call.setBinaryStream(6, fs,fs.available());
			call.setString(7, sID);
			call.execute();
			fs.close();
			call.close();
			return true;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateAnimal(int index,String data,String id) {
		CallableStatement call;
		try {
			call = conn.prepareCall("{call updateAnimmal(?,?,?)}");
			call.setString(1, id);
			call.setString(2, data);
			call.setInt(3, index);
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteAnimal(String input) {
		CallableStatement call;
		try {
			call = conn.prepareCall("{call deleteAnimal(?)}");
			call.setString(1, input);
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet seeAllShelter() {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeAllShelter(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet seeShelter(String input) {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeShelterimfo(?,?)}");
			call.setString(1, input);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(2);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return rs;
	} 
	
	public boolean addShelter(String[]inputs) {
		int i = 1;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call addShelter(?,?,?,?)}");
			while(i<=4) {
				call.setString(i, inputs[i-1]);
				i++;
			}
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateShelter(String[]inputs) {
		int i =1;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call updateS(?,?,?)}");
			while(i<=3) {
				call.setString(i, inputs[i-1]);
				i++;
			}
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet seeAllHealth() {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeAllHealth(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet seeHealth(String[]inputs) {
		int i = 1;
		CallableStatement call;
		ResultSet rs = null;
		try {
			call = conn.prepareCall("{call seeHealthImfo(?,?,?)}");
			while(i<=2) {
				call.setString(i, inputs[i-1]);
				i++;
			}
			call.registerOutParameter(3, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean addHealth(String[]inputs) {
		int i =1;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call addHealth(?,?,?,?)}");
			while(i<=4) {
				call.setString(i, inputs[i-1]);
				i++;
			}
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet seeAllV() {
		ResultSet rs = null;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call seeAllV(?)}");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet seeV(String[]inputs) {
		int i = 1;
		CallableStatement call;
		ResultSet rs = null;
		try {
			call = conn.prepareCall("{call seeVaccinesImfo(?,?,?)}");
			while(i<=2) {
				call.setString(i, inputs[i-1]);
				i++;
			}
			call.registerOutParameter(3, OracleTypes.CURSOR);
			call.execute();
			rs = ((OracleCallableStatement)call).getCursor(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public boolean addV(String[]inputs) {
		int i =1;
		CallableStatement call;
		try {
			call = conn.prepareCall("{call addV(?,?,?,?,?)}");
			while(i<=5) {
				call.setString(i, inputs[i-1]);
				i++;
			}
			call.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//	public static void main(String[] args) {
//		OracleConnection oc = new OracleConnection();
//	}
}
