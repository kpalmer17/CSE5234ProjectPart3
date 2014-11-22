
public class Rating {

	private int ratingid;
	private int rating;
	private String comment;
	private String date;
	private int userid;
	private int barid;
	
	public Rating (int rating, String comment, String date, int userid, int barid) {
		this.rating = rating;
		this.comment = comment;
		this.date = date;
		this.userid = userid;
		this.barid = barid;
	}
	public Rating (int ratingid, int rating, String comment, String date, int userid, int barid) {
		this.ratingid = ratingid;
		this.rating = rating;
		this.comment = comment;
		this.date = date;
		this.userid = userid;
		this.barid = barid;
	}
	public int getRatingid() {
		return ratingid;
	}
	public void setRatingid(int ratingid) {
		this.ratingid = ratingid;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBarid() {
		return barid;
	}
	public void setBarid(int barid) {
		this.barid = barid;
	}
}
