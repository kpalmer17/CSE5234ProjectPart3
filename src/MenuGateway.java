import java.sql.*;

public class MenuGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public MenuGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(MENUID) FROM MENU");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (String title, String day, String start, String end, int barid) throws Exception{
		String sql = "INSERT INTO MENU VALUES (" + nextID + ", '" + title + "', '" + day + "', '" + start + "', '" + end + "', "  + barid + ")";
		stmt.executeUpdate(sql);
		nextID++;
	}
	
	public void update (int menuid, String title, String day, String start, String end, int barid) throws Exception {
		String sql = "UPDATE MENU SET TITLE = '" + title + "', DAY ='" + day + "', STARTTIME ='" + start + "', ENDTIME='" + end + "', BARID="  + barid + " WHERE MENUID = " + menuid;
		stmt.executeUpdate(sql);
		
	}
	
	public void destroy (int menuid) throws Exception {
		
		//create item gateway
		ItemGateway items = new ItemGateway();
		
		//find items associated with this menu
		int menuitems[] = items.findForMenu(menuid);
		
		//delete all items associated with this menu
		for (int i = 0; i < menuitems.length; i++){
			//delete the item
			items.destroy(menuitems[i]);
		}
		//delete the menu
		String sql = "DELETE FROM MENU WHERE MENUID = " + menuid;
		stmt.executeUpdate(sql);
	}
	
	public void find (int menuid)
	{
		
	}
	
	public int[] findForBar (int barid) throws Exception
	{
		int size = 0;
		
		//get the size of the array that is about to be passed in
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT MENUID FROM MENU WHERE BARID = " + barid + ")");
		while (res.next()){
			size = res.getInt(1);
		}
		
		int[] results;
		results = new int[size];
		int i = 0;
		//get the array
		res = stmt.executeQuery("SELECT MENUID FROM MENU WHERE BARID = " + barid);
		while (res.next()){
			
			results[i] = res.getInt(1);
			i++;
		}
		
		return results;
	}
}
