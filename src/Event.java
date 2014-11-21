
public class Event {

	private int eventid; 
	private String name; 
	private String description; 
	private String date;
	private String start; 
	private String end; 
	private int barid;
	
	public Event (String name, String description, String date, String start, String end, int barid) {
		this.setName(name);
		this.setDescription(description);
		this.setDate(date);
		this.setStart(start);
		this.setEnd(end);
		this.setBarid(barid);
	}
	
	public Event (int eventid, String name, String description, String date, String start, String end, int barid) {
		this.setEventid(eventid);
		this.setName(name);
		this.setDescription(description);
		this.setDate(date);
		this.setStart(start);
		this.setEnd(end);
		this.setBarid(barid);
	}

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
