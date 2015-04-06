import javax.swing.*;

/**
 * Post class represents the the class for Posting methods
 *
 * @author Ramos
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Post extends JButton 
{
	//Attributes used to post
	private String title;
	private String link;
	private String content;
	private String category;
	private int upVotes;
	private int downVotes;
	private int numComments;
	private int catId;
	public String[] commentList;
	
	/**
	 * Constructor for post
	 */
	public Post()
	{
		//set initial attributes to null
		setTitle(null);
		setLink(null);
		setContent(null);
		setCategory(null);
		setUpVotes(0);
		setDownVotes(0);
		setNumComments(0);
		setCatId(0);
		commentList = new String[100];
		for (int i = 0; i < 100; i++)
		{
			commentList[i] = "";
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 
	 * @return
	 */
	public int getUpVotes() {
		return upVotes;
	}

	/**
	 * 
	 * @param upVotes
	 */
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}

	/**
	 * 
	 * @return
	 */
	public int getDownVotes() {
		return downVotes;
	}

	/**
	 * 
	 * @param downVotes
	 */
	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @return
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumComments() {
		return numComments;
	}

	/**
	 * 
	 * @param numComments
	 */
	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}

	/**
	 * 
	 * @return
	 */
	public int getCatId() {
		return catId;
	}

	/**
	 * 
	 * @param catId
	 */
	public void setCatId(int catId) {
		this.catId = catId;
	}
}
