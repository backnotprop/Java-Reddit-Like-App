import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/** 
 * WebPageSetup
 * Class includes methods and construction for WebView Browser to be included in App
 * 
 * @author Ramos
 */  
@SuppressWarnings("serial")
public class WebPageSetup extends JPanel {  
     
	//Attributes set to private to be used
    private Stage stage;  
    private WebView browser;  
    private JFXPanel jfxPanel;  
    private JButton swingButton;  
    private WebEngine webEngine;  
    String newUrl;
  
    /**
     * WebPageSetup method creates the Web page to be put in JPanel
     * @param url
     */
    public WebPageSetup(String url) {
 		// TODO Auto-generated constructor stub
     	newUrl = url;
     	initComponents(); 
 	}
    
    /**
     * WebPageSetup
     */
    public WebPageSetup(){  
        initComponents();  
    }  
  
 
    /**
     * Main ran for the web browser
     * @param args
     */
	public static void main(String ...args){  
		
        // To be run later and not at start
        SwingUtilities.invokeLater(new Runnable() {  
            
        	/**
             * Runs the main in this class
             */
        	@Override
            public void run() {  
                final JFrame frame = new JFrame();  
                frame.getContentPane().add(new WebPageSetup());  
                frame.setMaximumSize(new Dimension(275, 535));  
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                frame.setVisible(true);  
            }  
        });     
    }  
     
	/**
	 * initComponents
	 * 
	 * Sets up WebView Attributes and JFXPanel
	 */
    private void initComponents(){  
        
    	//Setting up panel and action listeners
        jfxPanel = new JFXPanel();  
        createScene();        
        setLayout(new BorderLayout());  
        add(jfxPanel, BorderLayout.CENTER);     
        swingButton = new JButton();  
        swingButton.addActionListener(new ActionListener() {
 
        	/**
        	 * Action Listener
        	 */
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
 
                    @Override
                    public void run() {
                        webEngine.reload();
                    }
                });
            }
        });  
        //reload button
        swingButton.setText("Reload");  
        add(swingButton, BorderLayout.SOUTH);  
    }     

 
    /** 
     * createScene 
     * 
     * Note: Key is that Scene needs to be created and run on "FX user thread" 
     *       NOT on the AWT-EventQueue Thread 
     * 
     */  
    private void createScene() {  
        PlatformImpl.startup(new Runnable() {  
            @Override
            public void run() {  
                 
            	//creates a scene to be used to run WebView
                stage = new Stage();  
                stage.setWidth(275);
                stage.setHeight(535);
                stage.setTitle("Postdubs");  
                stage.setResizable(false);  
   
                Group root = new Group();  
                Scene scene = new Scene(root,275,535);  
                stage.setScene(scene);  
                 
                // Set up the embedded browser:
                browser = new WebView();
                browser.setPrefWidth(275);             
                webEngine = browser.getEngine();
				webEngine.load(newUrl);
                
                ObservableList<Node> children = root.getChildren();
                children.add(browser);   
                
                //set scene
                jfxPanel.setScene(scene);  
            }  
        });  
    }
}