
public class GatewayTests {

	public static void main(String[] args) throws Exception {
		
		//Create Gateways
		AccountGateway accountGateway = new AccountGateway();
		ActivityGateway activityGateway = new ActivityGateway();
		BarGateway barGateway = new BarGateway();
		EventGateway eventGateway = new EventGateway();
		ItemGateway itemGateway = new ItemGateway();
		MenuGateway menuGateway = new MenuGateway();
		RatingGateway ratingGateway = new RatingGateway();
		SpecialGateway specialGateway = new SpecialGateway();
		
		int barid = -1;
		int menuid = -1;
		int specialid = -1;
		
		
		//test bar insert
		barGateway.insert("Luckys", "Campus", 1, "1000 High Street", "12:00", "2:00");
		barid = barGateway.nextID -1;
		
		//test menu insert
		menuGateway.insert("Lunch", "Mon-Fri", "12:00", "3:00", barid);
		menuid = menuGateway.nextID -1;
		
		//test insert items into menu
		itemGateway.insert("burger", "food", 5.00, menuid, -1);
		itemGateway.insert("fries", "food", 2.00, menuid, -1);
		
		//test specials insert
		specialGateway.insert("Happy Hour", "Mon-Fri", "3:00", "6:00", barid);
		specialid = specialGateway.nextID -1;
		
		//test items into special insert
		itemGateway.insert("bohdi", "beer", 3.00, -1, specialid);
		itemGateway.insert("fireball", "liquor", 2.00, -1, specialid);
		
		//test activity insert
		activityGateway.insert("pool", "game", "all", "open", "close", 1.00, barid);
		activityGateway.insert("karaoke", "singing", "wednesday", "8:00", "12:00", 0.00, barid);
		
		//test event insert
		eventGateway.insert("OSU vs Indiana", "Come watch the football game with us!!", "11/1/2015", "7:00", "2:00", barid);
		
		//test account insert
		accountGateway.insert("kpalmer17", "a123456");
		int userid = accountGateway.nextID -1;
		
		//test rating insert
		ratingGateway.insert(5, "Great food, Great Drinks", "10/30/2014", userid, barid);
		
	}

}
