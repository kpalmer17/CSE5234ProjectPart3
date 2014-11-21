
public class Item {

	private int itemid = -1;
	private String name;
	private String type;
	private double price;
	private int menuid;
	private int specialid;
	
	public Item (String name, String type, double price, int menuid, int specialid) {
		this.setName(name);
		this.setType(type);
		this.setPrice(price);
		this.setMenuid(menuid);
		this.setSpecialid(specialid);
	}
	
	public Item (int itemid, String name, String type, double price, int menuid, int specialid) {
		this.setItemid(itemid);
		this.setName(name);
		this.setType(type);
		this.setPrice(price);
		this.setMenuid(menuid);
		this.setSpecialid(specialid);
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public int getSpecialid() {
		return specialid;
	}

	public void setSpecialid(int specialid) {
		this.specialid = specialid;
	}
}
