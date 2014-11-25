import java.sql.*;

public class ActivityGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public ActivityGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/examples-769-EJB", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(ACTIVITYID) FROM ACTIVITY");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (Activity activity) throws Exception {
		String sql = "INSERT INTO ACTIVITY VALUES (" + nextID + ", '" + activity.getBarid() + "', '" + activity.getCost() + "', '" + activity.getDay() + "', '" + activity.getEnd() + "', '" + activity.getName() + "', " + activity.getStart() + ", " + activity.getType() + ")";
		stmt.executeUpdate(sql);
		activity.setActivityid(nextID);
		nextID++;
	}
	
	public void update (Activity activity) throws Exception{
		
		String sql = "DELETE FROM ACTIVITY WHERE ACTIVITYID = " + activity.getActivityid();
		stmt.executeUpdate(sql);
		sql = "INSERT INTO ACTIVITY VALUES (" + activity.getActivityid() + ", '" + activity.getBarid() + "', '" + activity.getCost() + "', '" + activity.getDay() + "', '" + activity.getEnd() + "', '" + activity.getName() + "', " + activity.getStart() + ", " + activity.getType() + ")";
		stmt.executeUpdate(sql);
	}
	
	public void destroy (Activity activity) throws Exception{
		
		String sql = "DELETE FROM ACTIVITY WHERE ACTIVITYID = " + activity.getActivityid();
		stmt.executeUpdate(sql);
	}
	
	public Activity find (int activityid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM ACTIVITY WHERE ACTIVITYID = " + activityid);
		res.next();
		String name = res.getString("NAME");
		String type = res.getString("TYPE");
		int day = res.getInt("DAY");
		String start = res.getString("START");
		String end = res.getString("END");
		double cost = res.getDouble("COST");
		int barid = res.getInt("BARID");
		
		return new Activity(activityid, name, type, day, start, end, cost, barid);
	}
	
	public int[] findForBar (int barid) throws Exception
	{
		int size = 0;
		
		//get the size of the array that is about to be passed in
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT ACTIVITYID FROM ACTIVITY WHERE BARID = " + barid + ")");
		while (res.next()){
			size = res.getInt(1);
		}
		
		int[] results;
		results = new int[size];
		int i = 0;
		//get the array
		res = stmt.executeQuery("SELECT ACTIVITYID FROM ACTIVITY WHERE BARID = " + barid);
		while (res.next()){
			
			results[i] = res.getInt(1);
			i++;
		}
		
		return results;
	}
}
