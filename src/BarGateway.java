import java.sql.*;

public class BarGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public BarGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(BARID) FROM BAR");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (String name, String type, int price, String address, String open, String close) throws Exception{
		String sql = "INSERT INTO BAR VALUES (" + nextID + ", '" + name + "', '" + type + "', " +price + ", '" + address + "', '" + open + "', '" + close + "')";
		stmt.executeUpdate(sql);
		nextID++;
	}
	
	public void update (int barid, String name, String type, int price, String address, String open, String close) throws Exception{
		String sql = "UPDATE BAR SET NAME = '" + name + "', TYPE ='" + type + "', PRICE =" +price + ", OPENHOUR ='" +open+ "', CLOSEHOUR ='" +close+"' WHERE BARID ="+barid;
		stmt.executeUpdate(sql);
	}
	
	public void destroy (int barid) throws Exception{
		//create gateways
		ActivityGateway activities = new ActivityGateway();
		EventGateway events = new EventGateway();
		MenuGateway menus = new MenuGateway();
		SpecialGateway specials = new SpecialGateway();
		
		//find activities for this bar
		int activityitems[] = activities.findForBar(barid);
		
		//delete all activities associated with this bar
		for (int i = 0; i < activityitems.length; i++){
			//delete the item
			activities.destroy(activityitems[i]);
		}
		
		//find events for this bar
		int eventitems[] = events.findForBar(barid);
		
		//delete all events associated with this bar
		for (int i = 0; i < eventitems.length; i++){
			//delete the item
			events.destroy(eventitems[i]);
		}
		
		//find menus for this bar
		int menuitems[] = menus.findForBar(barid);
				
		//delete all menus associated with this bar
		for (int i = 0; i < menuitems.length; i++){
			//delete the item
			menus.destroy(menuitems[i]);
		}
				
				
		//find specials for this bar
		int specialitems[] = specials.findForBar(barid);
				
		//delete all specials associated with this bar
		for (int i = 0; i < specialitems.length; i++){
			//delete the item
			specials.destroy(specialitems[i]);
		}
				
		//delete the bar
		String sql = "DELETE FROM BAR WHERE BARID = " + barid;
		stmt.executeUpdate(sql);
	}
	
	public void find (int barid)
	{
		
	}
}
