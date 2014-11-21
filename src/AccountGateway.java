import java.sql.*;

public class AccountGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public AccountGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(USERID) FROM ACCOUNT");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (Account account) throws Exception {
		
		String sql = "INSERT INTO ACCOUNT VALUES (" + nextID + ", '" + account.getUsername() + "', '" + account.getPassword() + "')";
		account.setUserid(nextID);
		stmt.executeUpdate(sql);
		nextID++;
	}
	
	public void update (Account account) throws Exception {
		String sql = "DELETE FROM ACCOUNT WHERE USERID = " + account.getUserid();
		stmt.executeUpdate(sql);
		sql = "INSERT INTO ACCOUNT VALUES (" + account.getUserid() + ", '" + account.getUsername() + "', '" + account.getPassword() + "')";
		stmt.executeUpdate(sql);
	}
	
	public void destroy (Account account)throws Exception {
		String sql = "DELETE FROM ACCOUNT WHERE USERID = " + account.getUserid();
		stmt.executeUpdate(sql);
	}
	
	public Account find (int userid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM ACCOUNT WHERE ACCOUNTID = " + userid);
		
		int userID = res.getInt("USERID");
		String username = res.getString("USERNAME");
		String pass = res.getString("PASSWORD");
		
		return new Account(userID, username, pass);
		
		
	}
}
