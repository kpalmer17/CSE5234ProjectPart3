
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
		Bar bar = new Bar("Luckys", "Campus", 1, "1000 High Street", "12:00", "2:00");
		barGateway.insert(bar);
		barid = bar.getBarid();
		
		//test menu insert
		Menu menu = new Menu("Lunch", 1, "12:00", "3:00", barid);
		menuGateway.insert(menu);
		menuid = menu.getMenuid();
		
		//test insert items into menu
		Item item1 = new Item("burger", "food", 5.00, menuid, -1);
		Item item2 = new Item("fries", "food", 2.00, menuid, -1);
		itemGateway.insert(item1);
		itemGateway.insert(item2);
		
		//test specials insert
		Special special = new Special("Happy Hour", 6, "3:00", "6:00", barid);
		specialGateway.insert(special);
		specialid = special.getSpecialid();
		
		//test items into special insert
		Item item3 = new Item("bohdi", "beer", 3.00, -1, specialid);
		Item item4 = new Item("fireball", "liquor", 2.00, -1, specialid);
		itemGateway.insert(item3);
		itemGateway.insert(item4);
		
		//test activity insert
		Activity activity1 = new Activity("pool", "game", 1, "open", "clos", 1.00, barid);
		Activity activity2 = new Activity("karaoke", "singing", 3, "8:00", "12:00", 0.00, barid);
		activityGateway.insert(activity1);
		activityGateway.insert(activity2);
		
		//test event insert
		Event event = new Event("OSU vs Indiana", "Come watch the football game with us!!", "11/1/2015", "7:00", "2:00", barid);
		eventGateway.insert(event);
		
		//test account insert
		Account account = new Account("kpalmer17", "a123456");
		accountGateway.insert(account);
		int userid = account.getUserid();
		
		//test rating insert
		Rating rating = new Rating(5, "Great food, Great Drinks", "10/30/2014", userid, barid);
		ratingGateway.insert(rating);
		
	}

}
