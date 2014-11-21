import java.sql.*;

public class ItemGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public ItemGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(ITEMID) FROM ITEM");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (String name, String type, double price, int menuid, int specialid ) throws Exception{
		
		if (menuid > 0)
		{
			String sql = "INSERT INTO ITEM VALUES (" + nextID + ", '" + name + "', '" + type + "', " + price + ", " + menuid + ", " + "null" + ")";
			stmt.executeUpdate(sql);
			nextID++;
		}
		else
		{
			String sql = "INSERT INTO ITEM VALUES (" + nextID + ", '" + name + "', '" + type + "', " + price + ", " + "null" + ", " + specialid + ")";
			stmt.executeUpdate(sql);
			nextID++;
		}
	}
	
	public void update (int itemid, String name, String type, double price, int menuid, int specialid) throws Exception {
		
		String sql = "DELETE FROM ITEM WHERE ITEMID = " + itemid;
		stmt.executeUpdate(sql);
		
		if (menuid > 0)
		{
			sql = "INSERT INTO ITEM VALUES (" + itemid + ", '" + name + "', '" + type + "', " + price + ", " + menuid + ", " + "null" + ")";
			stmt.executeUpdate(sql);
			nextID++;
		}
		else
		{
			sql = "INSERT INTO ITEM VALUES (" + itemid + ", '" + name + "', '" + type + "', " + price + ", " + "null" + ", " + specialid + ")";
			stmt.executeUpdate(sql);
			nextID++;
		}
	}
	
	public void destroy (int itemid) throws Exception{
		
		String sql = "DELETE FROM ITEM WHERE ITEMID = " + itemid;
		stmt.executeUpdate(sql);
	}
	
	public void find (int itemid)
	{
		
	}
	
	public int[] findForMenu (int menuid) throws Exception
	{
		int size = 0;
		
		//get the size of the array that is about to be passed in
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT ITEMID FROM ITEM WHERE MENUID = " + menuid + ")");
		while (res.next()){
			size = res.getInt(1);
		}
		
		int[] results;
		results = new int[size];
		int i = 0;
		//get the array
		res = stmt.executeQuery("SELECT ITEMID FROM ITEM WHERE MENUID = " + menuid);
		while (res.next()){
			
			results[i] = res.getInt(1);
			i++;
		}
		
		return results;
	}
	
	public int[] findForSpecial (int specialid) throws Exception
	{
		int size = 0;
		
		//get the size of the array that is about to be passed in
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT ITEMID FROM ITEM WHERE SPECIALID = " + specialid + ")");
		while (res.next()){
			size = res.getInt(1);
		}
		
		int[] results;
		results = new int[size];
		int i = 0;
		//get the array
		res = stmt.executeQuery("SELECT ITEMID FROM ITEM WHERE SPECIALID = " + specialid);
		while (res.next()){
			
			results[i] = res.getInt(1);
			i++;
		}
		
		return results;
	}
}
