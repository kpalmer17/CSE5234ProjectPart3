
public class Special {

	private int specialid;
	private String title;
	private int day;
	private String start;
	private String end;
	private int barid;
	
	public Special (String title, int day, String start, String end, int barid) {
		
	}
	public Special (int specialid, String title, int day, String start, String end, int barid) {
		
	}
	public int getSpecialid() {
		return specialid;
	}
	public void setSpecialid(int specialid) {
		this.specialid = specialid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
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
