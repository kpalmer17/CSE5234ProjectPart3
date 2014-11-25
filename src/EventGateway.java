import java.sql.*;

public class EventGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public EventGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/examples-769-EJB", "sa", "");
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
	
	public void insert (Event event) throws Exception {
		
		String sql = "INSERT INTO EVENT VALUES (" + nextID + ", '" + event.getBarid() + "', '" + event.getDate() + "', '" + event.getDescription() + "', '" + event.getEnd() + "', '" + event.getName() + "', '" + event.getStart() + "')";
		stmt.executeUpdate(sql);
		event.setEventid(nextID);
		nextID++;
	}
	
	public void update (Event event) throws Exception{
		
		String sql = "DELETE FROM EVENT WHERE EVENTID = " + event.getEventid();
		stmt.executeUpdate(sql);

		sql = "INSERT INTO EVENT VALUES (" + event.getEventid() + ", '" + event.getBarid() + "', '" + event.getDate() + "', '" + event.getDescription() + "', '" + event.getEnd() + "', '" + event.getName() + "', " + event.getStart() + ")";
		stmt.executeUpdate(sql);
	}
	
	public void destroy (Event event) throws Exception{
		
		String sql = "DELETE FROM EVENT WHERE EVENTID = " + event.getEventid();
		stmt.executeUpdate(sql);
	}
	
	public Event find (int eventid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM EVENT WHERE EVENTID = " + eventid);
		res.next();
		String name = res.getString("NAME");
		String desc = res.getString("DESCRIPTION");
		String date = res.getString("DATE");
		String start = res.getString("START");
		String end = res.getString("END");
		int barid = res.getInt("BARID");
		
		return new Event (eventid, name, desc, date, start, end, barid);
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
