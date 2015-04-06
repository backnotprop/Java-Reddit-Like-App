import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 * 
 * View Class
 * builds the GUI and appropriate classes and methods
 *
 * @author Ramos
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame
{
    // GUI Build
	public static final int FRAMEWIDTH = 275;
	public static final int FRAMEHEIGHT = 535;
	public static final int POSTLISTSIZE = 200;
	private JPanel homeScreenLogo;
	private JPanel homeScreenInput;
	private JPanel submitButtonPanel;
	private JLabel Logo;
	private JTabbedPane postDubsMain;
	private JLabel name;
	private JTextField enterName;
	private JLabel email;
	private JTextField enterEmail;
	private JLabel userName;
	private JTextField enterUserName;
	private JLabel password;
	private JTextField enterPassword;
	private JButton loginButton;
	private JComponent newPosts;
	private JComponent top20Posts;
	private JComponent createPost;
	private JButton postButton;
	private JPanel postButtonPanel;
	private JTextField postHere;
	private JLabel postTabImage;
	private JScrollPane scroll;
	private JPanel postPanel;
	private JLabel title;
	private JTextField enterTitle;
	private JLabel link;
	private JTextField enterLink;
	private Post[] listOfPosts;
	private JPanel postClick;
	private JLabel postTitle;
	private JLabel postLink;
	private JLabel postClickedImage;
	private JButton backToPosts;
	private JButton upVote;
	private JButton downVote;
	private JPanel backButtonPanel;
	private UserObject newUser;
	private JLabel postContent;
	private JTextField contentTextField;
	private JLabel categoryLabel;
	private JComboBox categoryDropDown;
	private String[] categoryChoices = { "Sports", "Funny", "World News", "Upworthy", "Breaking", "US Only" };
	private JLabel contentLabel;
	private JLabel postCategory;
	private JPanel viewPostImage;
	private JPanel postClickContent;
	private JPanel postInputFields;
	private JLabel postVotes;
	public Post temp;
	private JPanel commentPanel;
	private JScrollPane commentScroll;
	private JPanel scrollPanel;
	private JButton commentButton;
	private JLabel commentBelow;
	private JTextField addComment;
	private JPanel addCommentPanel;	
	private JLabel[] listOfComments;
	
	private JPanel webPage;
	private JButton goUrl;
	private JButton backToPost;
	
	private int urlClicked;
	
	//Attributes for the posts
	private int id = 0;
	private int numUp = 0;
	private int numDown =0;
	private String addOn;

	PrintWriter out;
	
	private JPanel topPosts;
	private JScrollPane scrollTopPosts;
		
	public View()
	{
		this.setLayout( new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS) );
		
		//Creates the various GUI pieces for the Home Screen
		homeScreenLogo = new JPanel();
		homeScreenInput = new JPanel();
		homeScreenInput.setBackground(Color.BLUE);
		name = new JLabel("Enter Your Name");
		name.setForeground(Color.WHITE);
		enterName = new JTextField();
		enterName.setText("");
		enterName.setBackground(Color.ORANGE);
		email = new JLabel("Enter Your Email");
		email.setForeground(Color.WHITE);
		enterEmail = new JTextField();
		enterEmail.setText("");
		enterEmail.setBackground(Color.ORANGE);
		userName = new JLabel("Enter A Username");
		userName.setForeground(Color.WHITE);
		enterUserName = new JTextField();
		enterUserName.setText("");
		enterUserName.setBackground(Color.ORANGE);
		password = new JLabel("Enter Your Password");
		password.setForeground(Color.WHITE);
		enterPassword = new JTextField();
		enterPassword.setText("");
		enterPassword.setBackground(Color.ORANGE);
		loginButton = new JButton("Register");
		loginButton.addActionListener( new loginButtonHandler() );
		loginButton.setBackground(Color.BLUE);
		loginButton.setForeground(Color.WHITE);
		submitButtonPanel = new JPanel();
		submitButtonPanel.setBackground(Color.ORANGE);
		submitButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Instantiates JComponents for the post Screen
		title = new JLabel("Enter Your Post's Title");
		title.setForeground(Color.WHITE);
		enterTitle = new JTextField();
		enterTitle.setBackground(Color.ORANGE);
		link = new JLabel("Enter Imgur Link");
		link.setForeground(Color.WHITE);
		enterLink = new JTextField();
		enterLink.setBackground(Color.ORANGE);
		
		//Creates the JPanel that will be used when a post
		//is clicked on
		postClickContent = new JPanel();
		postClickContent.setLayout( new BoxLayout(postClickContent, BoxLayout.PAGE_AXIS) );
		
		//Creates everything having to do with
		//commenting on a post
		scrollPanel = new JPanel();
		scrollPanel.setLayout( new GridLayout(100, 0));
		listOfComments = new JLabel[100];
		for (int i = 0; i < 100; i++)
		{
			listOfComments[i] = new JLabel();
			listOfComments[i].setText("");
			scrollPanel.add(listOfComments[i]);
		}
		commentScroll = new JScrollPane(scrollPanel);
		commentPanel = new JPanel();
		commentPanel.setLayout( new BorderLayout());
		commentPanel.setBackground(Color.BLUE);
		addCommentPanel = new JPanel();
		addCommentPanel.setLayout( new GridLayout(3,0)) ;
		addCommentPanel.setBackground(Color.BLUE);
		commentButton = new JButton("Click to Post Comment!");
		commentButton.setBackground(Color.ORANGE);
		commentButton.setForeground(Color.BLUE);
		commentButton.addActionListener( new postClickedHandler()  );
		commentBelow = new JLabel("Leave Your Comment Below:");
		commentBelow.setForeground(Color.ORANGE);
		addCommentPanel.add(commentBelow);
		addComment = new JTextField();
		addComment.setBackground(Color.ORANGE);
		addComment.setForeground(Color.BLUE);
		addCommentPanel.add(addComment);
		addCommentPanel.add(commentButton);
		
		goUrl = new JButton("Go To Url");
		goUrl.addActionListener( new urlButtonHandler() );
		
		commentPanel.add(addCommentPanel, BorderLayout.NORTH);
		commentPanel.add(commentScroll, BorderLayout.CENTER);
		
		//Creates the JPanel and components that will be used to
		//provide information about the post
		postClick = new JPanel();
		postClick.setLayout( new GridLayout(6,0));
		postClick.setBackground(Color.BLUE);
		viewPostImage = new JPanel();
		viewPostImage.setBackground(Color.BLUE);
		ImageIcon postClickHeader = new ImageIcon(getClass().getResource("Logo/minion2.jpg"));
		postClickedImage = new JLabel();
		postClickedImage.setIcon(postClickHeader);
		viewPostImage.add(postClickedImage);
		postTitle = new JLabel();
		postTitle.setForeground(Color.ORANGE);
		postLink = new JLabel();
		postLink.setForeground(Color.ORANGE);
		contentLabel = new JLabel();
		contentLabel.setMaximumSize(new Dimension(50,50));
		contentLabel.setForeground(Color.ORANGE);
		postCategory = new JLabel();
		postCategory.setForeground(Color.ORANGE);
		postVotes = new JLabel();
		postVotes.setForeground(Color.ORANGE);
		backToPosts = new JButton("Back!");
		upVote = new JButton("Like");
		upVote.addActionListener( new postClickedHandler() );
		downVote = new JButton("Dislike");
		downVote.addActionListener( new postClickedHandler() );
		backToPosts.addActionListener( new backButtonClickHandler() );
		
		//adds all of the post information to the JPanel
		postClick.add(postCategory);
		postClick.add(postTitle);
		postClick.add(postLink);
		postClick.add(goUrl);
		postClick.add(contentLabel);
		postClick.add(postVotes);
		postClick.add(commentPanel);
		
		//adds the like, dislike, and back button to the screen
		
		backButtonPanel = new JPanel();
		backButtonPanel.setLayout( new FlowLayout(FlowLayout.CENTER));
		backButtonPanel.setBackground(Color.BLUE);
		backButtonPanel.add(backToPosts);
		backButtonPanel.add(upVote);
		backButtonPanel.add(downVote);
		
		//adds all of the panels onto a final panel to create
		//the finished panel
		//postClickContent.add(viewPostImage);
		postClickContent.add(postClick);
		postClickContent.add(commentPanel);
		postClickContent.add(backButtonPanel);

		//Creates and places the logo into the logo panel
		ImageIcon pdLogo = new ImageIcon(getClass().getResource("Logo/minion.jpg"));
		Logo = new JLabel();
		Logo.setIcon(pdLogo);
		homeScreenLogo.add(Logo);
		
		//Adds the various text fields and labels to the input panel
		homeScreenInput.setLayout( new BoxLayout(homeScreenInput, BoxLayout.PAGE_AXIS) );
		homeScreenInput.add(name);
		homeScreenInput.add(enterName);
		homeScreenInput.add(email);
		homeScreenInput.add(enterEmail);
		homeScreenInput.add(userName);
		homeScreenInput.add(enterUserName);
		homeScreenInput.add(password);
		homeScreenInput.add(enterPassword);
		
		//Adds the submitButton to the submitButtonPanel
		submitButtonPanel.add(loginButton);
		
		//Creates the tabbed pane
		postDubsMain = new JTabbedPane();
		postDubsMain.setBackground(Color.BLUE);
		newPosts = makeTextPanel("");
		top20Posts = makeTextPanel("");
		createPost = makeTextPanel("");
		
		//Creates the panels needed for the top 20 posts
		top20Posts.setLayout( new BorderLayout() );
		topPosts = new JPanel();
		topPosts.setLayout( new GridLayout(200,0));
		scrollTopPosts = new JScrollPane(topPosts);
		top20Posts.add(scrollTopPosts, BorderLayout.CENTER);
		
		//adds tabs to the tabbed pane
		postDubsMain.addTab("Top 20 Posts", top20Posts);
		postDubsMain.addTab("New Posts", newPosts);
		postDubsMain.addTab("Create Post", createPost);
		
		
		//Setting up the createPost tab
		createPost.setLayout( new BorderLayout() );
		postInputFields = new JPanel();
		postInputFields.setLayout( new BoxLayout(postInputFields, BoxLayout.PAGE_AXIS));
		postInputFields.setBackground(Color.BLUE);
		postButton = new JButton("Post!");
		postButton.addActionListener( new postButtonHandler() );
		postButton.setBackground(Color.BLUE);
		postButton.setForeground(Color.WHITE);
		postButtonPanel = new JPanel();
		postButtonPanel.setLayout( new FlowLayout(FlowLayout.CENTER));
		postButtonPanel.setBackground(Color.ORANGE);
		postButtonPanel.add(postButton);
		postHere = new JTextField();
		ImageIcon postHeader = new ImageIcon(getClass().getResource("Logo/minion1.jpg"));
		postTabImage = new JLabel();
		postTabImage.setIcon(postHeader);
		postContent = new JLabel();
		postContent.setForeground(Color.WHITE);
		postContent.setText("Enter Post Content");
		contentTextField = new JTextField();
		contentTextField.setBackground(Color.ORANGE);
		categoryLabel = new JLabel("Choose Category");
		categoryLabel.setBackground(Color.BLUE);
		categoryLabel.setForeground(Color.WHITE);
		categoryDropDown = new JComboBox(categoryChoices);
		categoryDropDown.setBackground(Color.ORANGE);
		postInputFields.add(categoryLabel);
		postInputFields.add(categoryDropDown);
		postInputFields.add(title);
		postInputFields.add(enterTitle);
		postInputFields.add(link);
		postInputFields.add(enterLink);
		postInputFields.add(postContent);
		postInputFields.add(contentTextField);
		createPost.add(postTabImage, BorderLayout.NORTH);
		createPost.add(postInputFields, BorderLayout.CENTER);
		createPost.add(postButtonPanel, BorderLayout.SOUTH);
		
		//Setting up the newPost tab
		newPosts.setLayout( new BorderLayout() );
		postPanel = new JPanel();
		postPanel.setLayout( new GridLayout(200, 0));
		scroll = new JScrollPane(postPanel);
		newPosts.add(scroll);
		
		//Setting up the panel to view the website in
		webPage = new JPanel();
		webPage.setLayout( new BorderLayout() );
		
		//Adds each panel to the frame
		this.add(homeScreenLogo);
		this.add(homeScreenInput);
		this.add(submitButtonPanel);
		this.add(postDubsMain);
		this.add(postClickContent);
		this.add(webPage);
		
		
		File f = new File("input.txt");
		if (f.exists())
		{
			try 
			{
			grabUserInfo("input.txt");
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			homeScreenLogo.setVisible(false);
			homeScreenInput.setVisible(false);
			submitButtonPanel.setVisible(false);
			postClickContent.setVisible(false);
			webPage.setVisible(false);
			}
			else if (!f.exists())
			{
				postDubsMain.setVisible(false);
				postClickContent.setVisible(false);
				webPage.setVisible(false);
			}
		
		
		this.setTitle("PostDubs");
		this.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		this.setResizable(false);
		this.setLocation(500,100);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creates array of post objects and adds actionListener
		listOfPosts = new Post[POSTLISTSIZE];
		for (int i = 0; i < listOfPosts.length; i++)
		{
			listOfPosts[i] = new Post();
			listOfPosts[i].setBackground(Color.ORANGE);
			listOfPosts[i].setForeground(Color.BLUE);
			listOfPosts[i].addActionListener(new postClickedHandler() );
		}
		
		
		try {
			queryPosts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			topPosts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		backToPost = new JButton("Back to Post");
		backToPost.addActionListener( new backPostButtonHandler() );
		webPage.add(backToPost, BorderLayout.SOUTH);
	}
	
	private class urlButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//System.out.println(getAddOn() +'1');
			String newUrl1 = "http://m.imgur.com/gallery/";
			//String newUrl2 = listOfPosts[getUrlClicked()].getLink();
			String totalUrl = newUrl1 + getAddOn();
			
			webPage.add(new WebPageSetup(totalUrl), BorderLayout.CENTER);
			webPage.revalidate();
			webPage.repaint();
			
			postClickContent.setVisible(false);
			webPage.setVisible(true);
		}
	}
	
	public int getPostNum(int i)
	{
		setUrlClicked(i);
		return urlClicked;
	}
	
	private class backPostButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			webPage.setVisible(false);
			postClickContent.setVisible(true);
		}
	}
	
	private class loginButtonHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
				out = new PrintWriter(new FileWriter("input.txt"));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (e.getSource() == loginButton)
			{
				if (enterName.getText().equals("") || enterEmail.getText().equals("") || enterUserName.getText().equals("") || enterPassword.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Fill Out The Empty Fields");
				}
				else if (enterName.getText() != "")
				{
					newUser = new UserObject();
					newUser.setName(enterName.getText());
					newUser.setUserName(enterUserName.getText());
					newUser.setEmail(enterEmail.getText());
					newUser.setPassword(enterPassword.getText());
					homeScreenLogo.setVisible(false);
					homeScreenInput.setVisible(false);
					submitButtonPanel.setVisible(false);
					postDubsMain.setVisible(true);
					
					try {
						dbUser(enterName.getText(),enterUserName.getText(),enterEmail.getText(), enterPassword.getText());
					} catch (SQLException e1) {
						 //TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					try {
						queryPosts();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					out.println(newUser.getUserName());
					out.close();
					
					try {
						topPosts();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}
    
    /**
     * grabUserInfo
     * ask for username, find data for that user in response
     */
	public String grabUserInfo(String file) throws Exception
	{
		String username = "";
		Scanner s = new Scanner(" ");
		s = new Scanner(new File(file));
		username = s.nextLine();
		
		s.close();
		return username;
	}
	String nameU;
	int userId;
	private class postButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{		
			for (int i = 0; i < POSTLISTSIZE; i++)
			{
				if (listOfPosts[i].getText() == "")
				{	
					
					try {
						nameU = grabUserInfo("input.txt");
						System.out.println(nameU);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						userId = getUserId(nameU);
						System.out.println(userId);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					listOfPosts[i].setTitle(enterTitle.getText());
					listOfPosts[i].setLink(enterLink.getText());
					listOfPosts[i].setContent(contentTextField.getText());
					listOfPosts[i].setCategory(String.valueOf(categoryDropDown.getSelectedItem()));
					if (String.valueOf(categoryDropDown.getSelectedItem()) == "Sports")
						listOfPosts[i].setCatId(1);
					else if (String.valueOf(categoryDropDown.getSelectedItem()) == "Funny")
						listOfPosts[i].setCatId(2);
					else if (String.valueOf(categoryDropDown.getSelectedItem()) == "World News")
						listOfPosts[i].setCatId(3);
					else if (String.valueOf(categoryDropDown.getSelectedItem()) == "Upworthy")
						listOfPosts[i].setCatId(4);
					else if (String.valueOf(categoryDropDown.getSelectedItem()) == "Breaking")
						listOfPosts[i].setCatId(5);
					else if(String.valueOf(categoryDropDown.getSelectedItem()) == "US Only")
						listOfPosts[i].setCatId(6);
					listOfPosts[i].setText(enterTitle.getText()); 
					
					try {
						dbPost(listOfPosts[i].getCatId(),userId, listOfPosts[i].getTitle(),listOfPosts[i].getLink(), listOfPosts[i].getContent());
						queryPosts();
					} catch (SQLException e1) {
						 //TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					getPostNum(i);
					try {
						topPosts();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		}
	}
	
    /**
     * postClickHandler class
     * Post controls
     */
	private class postClickedHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			paintPost(e);
			
			
			if (e.getSource() == upVote)
			{
				
				try {
					System.out.println(numUp);
					numUp = updateUpVotes(numUp, id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				postVotes.setText("# of Likes: " + numUp + "    # of Dislikes: " + numDown);
			}
			
			if (e.getSource() == downVote)
			{
				try {
					System.out.println(numDown);
					numDown = updateDownVotes(numDown, id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				postVotes.setText("# of Likes: " + numUp + "    # of Dislikes: " + numDown);
			}
			
			if (e.getSource() == commentButton)
			{
				try {
					nameU = grabUserInfo("Input.txt");
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					dbComment(id,nameU, addComment.getText());
					queryPosts();
				} catch (SQLException e1) {
					 //TODO Auto-generated catch block
					e1.printStackTrace();
				}
				postClickContent.setVisible(false);
				try {
					topPosts();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				postDubsMain.setVisible(true);
			}
			
			
		}
	}
		
	private class backButtonClickHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			postClickContent.setVisible(false);
			postDubsMain.setVisible(true);
			for(int i =0; i < listOfComments.length; i++){
				scrollPanel.remove(listOfComments[i]);			
			}
		}	
	}
	
	public static JComponent makeTextPanel(String text) 
	{  
		JPanel panel = new JPanel(false);  
		JLabel filler = new JLabel(text);  
		filler.setHorizontalAlignment(JLabel.CENTER);  
		panel.setLayout(new GridLayout(1, 1));  
		panel.add(filler);  
		return panel;  
	}

	public void dbUser(String name, String userName, String email,String password) throws SQLException{
		Connection connection = getConnect();
		
		if (connection != null) {
			//System.out.println("Connection Works!");
			String insertTableSQL = "INSERT INTO users" + "(name, userName, email, password) VALUES" + "(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);

			preparedStatement.executeUpdate();
			
		} else {
			//System.out.println("Failed to make connection!");
		}
	  }
	
	public void dbComment(int postId, String userName, String content) throws SQLException{
		Connection connection = getConnect();
		
		if (connection != null) {
			//System.out.println("Connection Works!");
			String insertTableSQL = "INSERT INTO comments" + "(postID, userName, content) VALUES" + "(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, postId);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, content);
			

			preparedStatement.executeUpdate();
			
		} else {
			//System.out.println("Failed to make connection!");
		}
	  }
	
	public void dbPost(int user, int cat, String title,String link, String body) throws SQLException{
		Connection connection = getConnect();
		
		if (connection != null) {
			//System.out.println("Connection Works!");
			String insertTableSQL = "INSERT INTO posts" + "(catID, userID, title, link, content) VALUES" + "(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, user);
			preparedStatement.setInt(2, cat);
			preparedStatement.setString(3, title);
			preparedStatement.setString(4, link);
			preparedStatement.setString(5, body);
			//preparedStatement.setString(4,"pass23");
			// execute insert SQL statement
			preparedStatement.executeUpdate();
			
		} else {
			//System.out.println("Failed to make connection!");
		}
	  }
	
	public void queryPosts() throws SQLException{
		
		Connection connection = getConnect();
	if (connection != null) {
		//System.out.println("Connection Works!");
		// create the java statement
	      Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM posts";
			ResultSet rs = st.executeQuery(query);
			
		int count = 0;
		// iterate through the java resultset
	    while (rs.next())
	    {
	      int id = rs.getInt("id");
	      int catID = rs.getInt("catID");
	      //int userID = rs.getInt("userID");
	      String title = rs.getString("title");
	      String link = rs.getString("link");
	      String content = rs.getString("content");
	      int ups = rs.getInt("ups");
	      int downs = rs.getInt("downs");
	      String catName = "";
	      //System.out.println(id +" "+ catID + " " + userID);
	      if(catID == 1){
				catName = "Sports";
				}
				else if(catID == 2){
					
				catName = "Funny";
				}
				else if(catID == 3){
				catName = "World News";
				}
				else if(catID == 4){
				catName = "Upworthy";
				}
				else if(catID == 5){
				catName = "Breaking News";
				}
				else if(catID == 6){
				catName = "US Only";
				}

				listOfPosts[count].setTitle(title);
				listOfPosts[count].setLink(link);
				listOfPosts[count].setContent(content);
				listOfPosts[count].setCategory(catName);
				listOfPosts[count].setText(title);
				
				//adds
				postPanel.add(listOfPosts[count]);
				postHere.setText("");						
				postDubsMain.setSelectedIndex(0);
				
				count++;
	    }
	    st.close();
	
			} else {
				//System.out.println("Failed to make connection!");
			}
	}
	
	public int getPostId(String title) throws SQLException{
		int id = 0;
		Connection connection = getConnect();
		if (connection != null) {
		//System.out.println("Connection Works!");
		// create the java statement
		  Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM posts WHERE title='"+title+"'";
			ResultSet rs = st.executeQuery(query);
			
		// iterate through the java resultset
		
			if(rs.next()){
				id = rs.getInt("id");
			}
		//System.out.println(id);
		st.close();
		
		} else {
		//System.out.println("Failed to make connection!");
		}
		
		return id;
	}
	
	
public void topPosts() throws SQLException{
		
		Connection connection = getConnect();
	if (connection != null) {
		System.out.println("Connection Works!");
		// create the java statement
	      Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			int num = 10;
			String query = "SELECT * FROM posts  WHERE ups >'"+num+"' ORDER BY ups DESC";
			ResultSet rs = st.executeQuery(query);

		// iterate through the java resultset
	    while (rs.next())
	    {
	      
	      String title = rs.getString("title");
	      for(int i = 0; i < listOfPosts.length; i++){
	      	
	      	if(title.equals(listOfPosts[i].getTitle())){
	      	
	      		topPosts.add(listOfPosts[i]);
	      		//System.out.println(topPosts);
	      	}
	      
	      }
	      

	    }
	    st.close();
	
			} else {
				System.out.println("Failed to make connection!");
			}
	}
	
	
	
	
	public int getUserId(String name) throws SQLException{
		int id = 0;
		Connection connection = getConnect();
		if (connection != null) {
		System.out.println("Connection Works!");
		
		
		// create the java statement
		  Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM users WHERE userName='"+name+"'";
			ResultSet rs = st.executeQuery(query);
			
		// iterate through the java resultset
		
			if(rs.next()){
				id = rs.getInt("id");
			}
		//System.out.println(id);
		st.close();
		
		} else {
		System.out.println("Failed to make connection!");
		}
		
		return id;
	}
	
public int getDownVotes(int id) throws SQLException{
		
		int votes = 0;
		Connection connection = getConnect();
		if (connection != null) {
		System.out.println("Connection Works!");
		// create the java statement
		  Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM posts WHERE id='"+id+"'";
			ResultSet rs = st.executeQuery(query);
			
		// iterate through the java resultset
		
			if(rs.next()){
				votes = rs.getInt("downs");
			}
		//System.out.println(id);
		st.close();
		
		} else {
		System.out.println("Failed to make connection!");
		}
		
		return votes;
	}
public int getUpVotes(int id) throws SQLException{
		
		int votes = 0;
		Connection connection = getConnect();
		if (connection != null) {
		System.out.println("Connection Works!");
		// create the java statement
		  Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM posts WHERE id='"+id+"'";
			ResultSet rs = st.executeQuery(query);
			
		// iterate through the java resultset
		
			if(rs.next()){
				votes = rs.getInt("ups");
			}
		//System.out.println(id);
		st.close();
		
		} else {
		System.out.println("Failed to make connection!");
		}
		
		return votes;
	}
	
	public int updateDownVotes(int cur, int id) throws SQLException{

		int newD = cur + 1;
		Connection connection = getConnect();
		if (connection != null) {
		System.out.println("Connection Works!");
		// create the java statement
		  Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "UPDATE posts SET downs='"+newD+"' WHERE id='"+id+"'";
			st.executeUpdate(query);
	
		st.close();
		
		} else {
		System.out.println("Failed to make connection!");
		}
		return newD;
	}
	
	public int updateUpVotes(int cur, int id) throws SQLException{

		int newD = cur + 1;
		Connection connection = getConnect();
		if (connection != null) {
		System.out.println("Connection Works!");
		// create the java statement
		  Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "UPDATE posts SET ups='"+newD+"' WHERE id='"+id+"'";
			st.executeUpdate(query);
	
		st.close();
		
		} else {
		System.out.println("Failed to make connection!");
		}
		return newD;
	}
	
public void queryComments(int pid) throws SQLException{
		
		Connection connection = getConnect();
	if (connection != null) {
		System.out.println("Connection Works!");
		// create the java statement
	      Statement st = connection.createStatement();
		// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM comments WHERE postID='"+pid+"'";
			ResultSet rs = st.executeQuery(query);
			int count =0;
		// iterate through the java resultset
	    while (rs.next())
	    {
	      
	      String uName = rs.getString("userName");
	      String content = rs.getString("content");
	      
	
				temp.commentList[count] = uName + ": " + content;
				count++;

	    }
	    st.close();
	
			} else {
				System.out.println("Failed to make connection!");
			}
	}

	public void paintPost(ActionEvent e){
		
		postDubsMain.setVisible(false);
		postClickContent.setVisible(true);

		for (int i = 0; i < POSTLISTSIZE; i++)
		{
			if (e.getSource() == listOfPosts[i])
			{
				setAddOn(listOfPosts[i].getLink());
				temp = listOfPosts[i];
				postTitle.setText("Title: " + listOfPosts[i].getTitle());
				postLink.setText("Imgur Link: " + listOfPosts[i].getLink());
				contentLabel.setText("<html><div style=\"width:265px;\">Post Content: " + listOfPosts[i].getContent() +"</div></html>");
				postCategory.setText("Post Category: " + listOfPosts[i].getCategory());
				
				try {
					id = getPostId(listOfPosts[i].getTitle());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					queryComments(id);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					numUp = getUpVotes(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					numDown = getDownVotes(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				postVotes.setText("# of Likes: " + numUp + "    # of Dislikes: " + numDown);
				for (int j = 0; j < 100; j++)
				{
					listOfComments[j].setText(listOfPosts[i].commentList[j]);
					scrollPanel.add(listOfComments[j]);
				}
			}
		}
			
	}

	public Connection getConnect(){
		
	//System.out.println("-------- Testing for Connection------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//System.out.println("Driver not found/working?");
			e.printStackTrace();
	}
	
	//System.out.println("MySQL JDBC Driver Works!");
	Connection connection = null;
        
	//url to database
        
	String url = "INSERT URL TO MYSQL";
	
	//db login info
	String login = "MYSQL USER";
	String password ="MYSQL PASS";
        
        
	try {
	//test login
	connection = DriverManager.getConnection(url, login, password);
	
	} catch (SQLException e) {
	//System.out.println("Connection Failed! Check output");
	e.printStackTrace();

	}
	return connection;
	}

	public int getUrlClicked() {
		return urlClicked;
	}

	public void setUrlClicked(int urlClicked) {
		this.urlClicked = urlClicked;
	}
	
	public String getAddOn()
	{
		return addOn;
	}
	
	public void setAddOn(String add)
	{
		this.addOn = add;
	}
}
