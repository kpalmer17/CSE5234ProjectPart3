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
	
	public void insert (Item item ) throws Exception{
		
		if (item.getMenuid() > 0)
		{
			String sql = "INSERT INTO ITEM VALUES (" + nextID + ", '" + item.getName() + "', '" + item.getType() + "', " + item.getPrice() + ", " + item.getMenuid() + ", " + "null" + ")";
			stmt.executeUpdate(sql);
			item.setItemid(nextID);
			nextID++;
		}
		else
		{
			String sql = "INSERT INTO ITEM VALUES (" + nextID + ", '" + item.getName() + "', '" + item.getType() + "', " + item.getPrice() + ", " + "null" + ", " + item.getSpecialid() + ")";
			stmt.executeUpdate(sql);
			item.setItemid(nextID);
			nextID++;
		}
	}
	
	public void update (Item item) throws Exception {
		
		String sql = "DELETE FROM ITEM WHERE ITEMID = " + item.getItemid();
		stmt.executeUpdate(sql);
		
		if (item.getMenuid() > 0)
		{
			sql = "INSERT INTO ITEM VALUES (" + item.getItemid() + ", '" + item.getName() + "', '" + item.getType() + "', " + item.getPrice() + ", " + item.getMenuid() + ", " + "null" + ")";
			stmt.executeUpdate(sql);
			nextID++;
		}
		else
		{
			sql = "INSERT INTO ITEM VALUES (" + item.getItemid() + ", '" + item.getName() + "', '" + item.getType() + "', " + item.getPrice() + ", " + "null" + ", " + item.getSpecialid() + ")";
			stmt.executeUpdate(sql);
			nextID++;
		}
	}
	
	public void destroy (Item item) throws Exception{
		
		String sql = "DELETE FROM ITEM WHERE ITEMID = " + item.getItemid();
		stmt.executeUpdate(sql);
	}
	
	public Item find (int itemid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM ITEM WHERE ITEMID = " + itemid);
		res.next();
		int itemID = res.getInt("ITEMID");
		String name = res.getString("NAME");
		String type = res.getString("TYPE");
		double price = res.getDouble("PRICE"); 
		int menuid = res.getInt("MENUID");
		if (res.wasNull()) menuid = -1;
		int specialid = res.getInt("SPECIALID");
		if (res.wasNull()) specialid = -1;
		
		return new Item(itemID, name, type, price, menuid, specialid);
		
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
