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
	
	public void insert (String title, String day, String start, String end, int barid) throws Exception {
		
		String sql = "INSERT INTO SPECIAL VALUES (" + nextID + ", '" + title + "', '" + day + "', '" + start + "', '" + end + "', "  + barid + ")";
		stmt.executeUpdate(sql);
		nextID++;
	}
	
	public void update (int specialid, String title, String day, String start, String end, int barid) throws Exception{
		String sql = "UPDATE SPECIAL SET TITLE = '" + title + "', DAY ='" + day + "', STARTTIME ='" + start + "', ENDTIME='" + end + "', BARID="  + barid + " WHERE SPECIALID = " + specialid;
		stmt.executeUpdate(sql);
		
	}
	
	public void destroy (int specialid) throws Exception{
		
		//create item gateway
		ItemGateway items = new ItemGateway();
		
		//find items associated with this menu
		int specialitems[] = items.findForSpecial(specialid);
		
		//delete all items associated with this menu
		for (int i = 0; i < specialitems.length; i++){
			//delete the item
			items.destroy(specialitems[i]);
		}
		//delete the menu
		String sql = "DELETE FROM SPECIAL WHERE SPECIALID = " + specialid;
		stmt.executeUpdate(sql);
	}
	
	public void find (int specialid)
	{
		
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
