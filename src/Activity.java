
public class Activity {

	private int activityid = -1;
	private String name;
	private String type;
	private int day;
	private String start;
	private String end;
	private double cost;
	private int barid;
	
	public Activity (String name, String type, int day, String start, String end, double cost, int barid) {
		this.name = name;
		this.type = type;
		this.day = day;
		this.start = start;
		this.end = end;
		this.cost = cost;
		this.barid = barid;
	}
	
	public Activity (int activityid, String name, String type, int day, String start, String end, double cost, int barid) {
		this.activityid = activityid;
		this.name = name;
		this.type = type;
		this.day = day;
		this.start = start;
		this.end = end;
		this.cost = cost;
		this.barid = barid;
	}
	public int getActivityid() {
		return activityid;
	}
	public void setActivityid(int activityid) {
		this.activityid = activityid;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getBarid() {
		return barid;
	}
	public void setBarid(int barid) {
		this.barid = barid;
	}
}
