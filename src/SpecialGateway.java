import java.sql.*;

public class SpecialGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public SpecialGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(SPECIALID) FROM SPECIAL");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
			  s.printStackTrace();
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (Special special) throws Exception {
		
		String sql = "INSERT INTO SPECIAL VALUES (" + nextID + ", '" + special.getTitle() + "', '" + special.getDay() + "', '" + special.getStart() + "', '" + special.getEnd() + "', "  + special.getBarid() + ")";
		stmt.executeUpdate(sql);
		special.setSpecialid(nextID);
		nextID++;
	}
	
	public void update (Special special) throws Exception{
		String sql = "UPDATE SPECIAL SET TITLE = '" + special.getTitle() + "', DAY ='" + special.getDay() + "', STARTTIME ='" + special.getStart() + "', ENDTIME='" + special.getEnd() + "', BARID="  + special.getBarid() + " WHERE SPECIALID = " + special.getSpecialid();
		stmt.executeUpdate(sql);	
	}
	
	public void destroy (Special special) throws Exception{
		
		//create item gateway
		ItemGateway items = new ItemGateway();
		
		//find items associated with this menu
		int specialitems[] = items.findForSpecial(special.getSpecialid());
		
		//delete all items associated with this menu
		for (int i = 0; i < specialitems.length; i++){
			//delete the item
			Item item = items.find(specialitems[i]);
			items.destroy(item);
		}
		//delete the menu
		String sql = "DELETE FROM SPECIAL WHERE SPECIALID = " + special.getSpecialid();
		stmt.executeUpdate(sql);
	}
	
	public Special find (int specialid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM SPECIAL WHERE SPECIALID = " + specialid);
		res.next();
		String title = res.getString("TITLE");
		int day = res.getInt("DAY");
		String start = res.getString("STARTTIME");
		String end = res.getString("ENDTIME");
		int barid = res.getInt("BARID");
		
		return new Special (specialid, title, day, start, end, barid);
		
	}
	
	public int[] findForBar (int barid) throws Exception
	{
		int size = 0;
		
		//get the size of the array that is about to be passed in
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT SPECIALID FROM SPECIAL WHERE BARID = " + barid + ")");
		while (res.next()){
			size = res.getInt(1);
		}
		
		int[] results;
		results = new int[size];
		int i = 0;
		//get the array
		res = stmt.executeQuery("SELECT SPECIALID FROM SPECIAL WHERE BARID = " + barid);
		while (res.next()){
			
			results[i] = res.getInt(1);
			i++;
		}
		
		return results;
	}
}
