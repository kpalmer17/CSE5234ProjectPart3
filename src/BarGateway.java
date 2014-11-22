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
	
	public void insert (Bar bar) throws Exception{
		String sql = "INSERT INTO BAR VALUES (" + nextID + ", '" + bar.getName() + "', '" + bar.getType() + "', " +bar.getPrice() + ", '" + bar.getAddress() + "', '" + bar.getOpen() + "', '" + bar.getClose() + "')";
		stmt.executeUpdate(sql);
		bar.setBarid(nextID);
		nextID++;
	}
	
	public void update (Bar bar) throws Exception{
		String sql = "UPDATE BAR SET NAME = '" + bar.getName() + "', TYPE ='" + bar.getType() + "', PRICE =" +bar.getPrice() + ", OPENHOUR ='" +bar.getOpen()+ "', CLOSEHOUR ='" +bar.getClose()+"' WHERE BARID ="+bar.getBarid();
		stmt.executeUpdate(sql);
	}
	
	public void destroy (Bar bar) throws Exception{
		//create gateways
		ActivityGateway activities = new ActivityGateway();
		EventGateway events = new EventGateway();
		MenuGateway menus = new MenuGateway();
		SpecialGateway specials = new SpecialGateway();
		
		//find activities for this bar
		int activityitems[] = activities.findForBar(bar.getBarid());
		
		//delete all activities associated with this bar
		for (int i = 0; i < activityitems.length; i++){
			//delete the item
			Activity activity = activities.find(activityitems[i]);
			activities.destroy(activity);
		}
		
		//find events for this bar
		int eventitems[] = events.findForBar(bar.getBarid());
		
		//delete all events associated with this bar
		for (int i = 0; i < eventitems.length; i++){
			//delete the item
			Event event = events.find(eventitems[i]);
			events.destroy(event);
		}
		
		//find menus for this bar
		int menuitems[] = menus.findForBar(bar.getBarid());
				
		//delete all menus associated with this bar
		for (int i = 0; i < menuitems.length; i++){
			//delete the item
			
			Menu menu = menus.find(menuitems[i]);
			menus.destroy(menu);
		}
				
				
		//find specials for this bar
		int specialitems[] = specials.findForBar(bar.getBarid());
				
		//delete all specials associated with this bar
		for (int i = 0; i < specialitems.length; i++){
			//delete the item
			Special special = specials.find(specialitems[i]);
			specials.destroy(special);
		}
				
		//delete the bar
		String sql = "DELETE FROM BAR WHERE BARID = " + bar.getBarid() ;
		stmt.executeUpdate(sql);
	}
	
	public Bar find (int barid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM BAR WHERE BARID = " + barid);
		res.next();
		String name = res.getString("NAME");
		String type = res.getString("TYPE");
		int price = res.getInt("PRICE");
		String address = res.getString("ADDRESS");
		String open = res.getString("OPENHOUR");
		String close = res.getString("CLOSEHOUR");
		
		return new Bar (barid, name, type, price, address, open, close);
	}
}
