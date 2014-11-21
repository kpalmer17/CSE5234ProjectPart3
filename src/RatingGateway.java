import java.sql.*;

public class RatingGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public RatingGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(RATINGID) FROM RATING");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (int rating, String comment, String date, int userid, int barid) throws Exception {
		String sql = "INSERT INTO RATING VALUES (" + nextID + ", " + rating + ", '" + comment + "', '" + date + "', " + userid + ", " + barid + ")";
		stmt.executeUpdate(sql);
		nextID++;
	}
	
	public void update (int ratingid, int rating, String comment, String date, int userid, int barid) throws Exception {
		String sql = "DELETE FROM RATING WHERE RATINGID = " + ratingid;
		stmt.executeUpdate(sql);
		
		sql = "INSERT INTO RATING VALUES (" + ratingid + ", " + rating + ", '" + comment + "', '" + date + "', " + userid + ", " + barid + ")";
		stmt.executeUpdate(sql);
	}
	
	public void destroy (int ratingid) throws Exception{
		
		String sql = "DELETE FROM RATING WHERE RATINGID = " + ratingid;
		stmt.executeUpdate(sql);
	}
	
	public void find (int ratingid)
	{
		
	}
	
	
}
