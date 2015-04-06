/**
 * UserObject class represents the the class for user data
 *
 * @author Ramos
 * @version 1.0
 */
public class UserObject 
{
	// User Attributes
	private String name;
	private String userName;
	private String email;
	private String password;
	
	/**
	 * UserObject
	 */
	public UserObject()
	{
		this.setName(null);
		this.setUserName(null);
		this.setEmail(null);
		this.setPassword(null);
	}

	/**
	 * getName
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getUserName
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * getEmail
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setEmail
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * getPassword
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setPassword
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
