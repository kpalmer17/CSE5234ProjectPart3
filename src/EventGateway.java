import java.sql.*;

public class EventGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public EventGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(EVENTID) FROM EVENT");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (String name, String description, String date, String start, String end, int barid) throws Exception {
		
		String sql = "INSERT INTO EVENT VALUES (" + nextID + ", '" + name + "', '" + description + "', '" + date + "', '" + start + "', '" + end + "', " + barid + ")";
		stmt.executeUpdate(sql);
		nextID++;
	}
	
	public void update (int eventid, String name, String description, String date, String start, String end, int barid) throws Exception{
		
		String sql = "DELETE FROM EVENT WHERE EVENTID = " + eventid;
		stmt.executeUpdate(sql);

		sql = "INSERT INTO EVENT VALUES (" + eventid + ", '" + name + "', '" + description + "', '" + date + "', '" + start + "', '" + end + "', " + barid + ")";
		stmt.executeUpdate(sql);
	}
	
	public void destroy (int eventid) throws Exception{
		
		String sql = "DELETE FROM EVENT WHERE EVENTID = " + eventid;
		stmt.executeUpdate(sql);
	}
	
	public void find (int eventid)
	{
		
	}
	
	public int[] findForBar (int barid)throws Exception
	{
		int size = 0;
		
		//get the size of the array that is about to be passed in
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT EVENTID FROM EVENT WHERE BARID = " + barid + ")");
		while (res.next()){
			size = res.getInt(1);
		}
		
		int[] results;
		results = new int[size];
		int i = 0;
		//get the array
		res = stmt.executeQuery("SELECT EVENTID FROM EVENT WHERE BARID = " + barid);
		while (res.next()){
			
			results[i] = res.getInt(1);
			i++;
		}
		
		return results;
	}
}
