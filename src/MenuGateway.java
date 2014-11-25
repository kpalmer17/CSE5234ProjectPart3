import java.sql.*;

public class MenuGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public MenuGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/examples-769-EJB", "sa", "");
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
	
	public void insert (Menu menu) throws Exception{
		String sql = "INSERT INTO MENU VALUES (" + nextID + ", '" + menu.getBarid() + "', '" + menu.getDay() + "', '" + menu.getEnd() + "', '" + menu.getStart() + "', '"  + menu.getTitle() + "')";
		stmt.executeUpdate(sql);
		menu.setMenuid(nextID);
		nextID++;
	}
	
	public void update (Menu menu) throws Exception {
		String sql = "UPDATE MENU SET TITLE = '" + menu.getTitle() + "', DAY ='" + menu.getDay() + "', START ='" + menu.getStart() + "', END='" + menu.getEnd() + "', BARID="  + menu.getBarid() + " WHERE MENUID = " + menu.getMenuid();
		stmt.executeUpdate(sql);
		
	}
	
	public void destroy (Menu menu) throws Exception {
		
		//create item gateway
		ItemGateway items = new ItemGateway();
		
		//find items associated with this menu
		int menuitems[] = items.findForMenu(menu.getMenuid());
		
		//delete all items associated with this menu
		for (int i = 0; i < menuitems.length; i++){
			//delete the item
			Item item = items.find(menuitems[i]);
			items.destroy(item);
		}
		//delete the menu
		String sql = "DELETE FROM MENU WHERE MENUID = " + menu.getMenuid();
		stmt.executeUpdate(sql);
	}
	
	public Menu find (int menuid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM MENU WHERE MENUID = " + menuid);
		res.next();
		String title = res.getString("TITLE");
		int day = res.getInt("DAY");
		String start = res.getString("START");
		String end = res.getString("END");
		int barid = res.getInt("BARID");
		
		return new Menu (menuid, title, day, start, end, barid);
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
