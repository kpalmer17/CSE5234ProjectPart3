
public class Menu {

	private int menuid;
	private String title;
	private String day;
	private String start;
	private String end;
	private int barid;
	
	public Menu (String title, String day, String start, String end, int barid) {
		this.setTitle(title);
		this.setDay(day);
		this.setStart(start);
		this.setEnd(end);
		this.setBarid(barid);
	}
	
	public Menu (int menuid, String title, String day, String start, String end, int barid) {
		this.setMenuid(menuid);
		this.setTitle(title);
		this.setDay(day);
		this.setStart(start);
		this.setEnd(end);
		this.setBarid(barid);
	}

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getBarid() {
		return barid;
	}

	public void setBarid(int barid) {
		this.barid = barid;
	}
}
