import java.sql.*;

public class RatingGateway {
	
	public int nextID = 0;
	private Connection conn = null;
	private Statement stmt = null;
	
	public RatingGateway () throws Exception {
		try{
			//connect to h2 DB
			Class.forName("org.h2.Driver");
        	conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			stmt = conn.createStatement();
    	
			//Find the number of rows in the table
			ResultSet res = stmt.executeQuery("SELECT MAX(RATINGID) FROM RATING");
			while (res.next()){
				nextID = res.getInt(1) + 1;
			}
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}catch (Exception e){
		  e.printStackTrace();
		  }
	}
	
	public void insert (Rating rating) throws Exception {
		String sql = "INSERT INTO RATING VALUES (" + nextID + ", " + rating.getBarid() + ", '" + rating.getComment() + "', '" + rating.getDate() + "', " + rating.getRating() + ", " + rating.getUserid() + ")";
		stmt.executeUpdate(sql);
		rating.setRatingid(nextID);
		nextID++;
	}
	
	public void update (Rating rating) throws Exception {
		String sql = "DELETE FROM RATING WHERE RATINGID = " + rating.getRatingid();
		stmt.executeUpdate(sql);
		
		sql = "INSERT INTO RATING VALUES (" + rating.getRatingid() + ", " + rating.getBarid() + ", '" + rating.getComment() + "', '" + rating.getDate() + "', " + rating.getRating() + ", " + rating.getUserid() + ")";
		stmt.executeUpdate(sql);
	}
	
	public void destroy (Rating rating) throws Exception{
		
		String sql = "DELETE FROM RATING WHERE RATINGID = " + rating.getRatingid();
		stmt.executeUpdate(sql);
	}
	
	public Rating find (int ratingid) throws SQLException
	{
		ResultSet res = stmt.executeQuery("SELECT * FROM RATING WHERE RATINGID = " + ratingid);
		res.next();
		int rating = res.getInt("RATING");
		String comment = res.getString("COMMENT");
		String date = res.getString("DATE");
		int userid = res.getInt("USERID");
		int barid = res.getInt("BARID");
		
		return new Rating (ratingid, rating, comment, date, userid, barid);
	}
	
	
}
