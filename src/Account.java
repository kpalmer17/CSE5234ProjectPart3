
public class Account {
	private int userid = -1;
	private String username;
	private String password;

	public Account (String user, String pass) {
		setUsername(user);
		setPassword(pass);
	}
	public Account (int userID, String user, String pass) {
		setUserid(userID);
		setUsername(user);
		setPassword(pass);
	}


	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
