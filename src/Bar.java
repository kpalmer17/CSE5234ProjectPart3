
public class Bar {

	private int barid = -1;
	private String name;
	private String type;
	private int price;
	private String address;
	private String open;
	private String close;
	
	
	public Bar (String name, String type, int price, String address, String open, String close) {
		this.setName(name);
		this.setType(type);
		this.setPrice(price);
		this.setAddress(address);
		this.setOpen(open);
		this.setClose(close);
	}
	
	public Bar (int barid, String name, String type, int price, String address, String open, String close) {
		this.setBarid(barid);
		this.setName(name);
		this.setType(type);
		this.setPrice(price);
		this.setAddress(address);
		this.setOpen(open);
		this.setClose(close);
	}

	public int getBarid() {
		return barid;
	}

	public void setBarid(int barid) {
		this.barid = barid;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}
	
	
}
