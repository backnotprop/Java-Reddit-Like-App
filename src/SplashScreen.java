import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;


/**
 * SplashScreen class represents the splash screen for Postdubs
 *
 * @author Ramos
 * @version 1.0
 */
public class SplashScreen 
{
	
	/**
	 * Constructor SplashScreen
	 * 
	 */
	public SplashScreen()
	{
		//Create Jwindow
		JWindow window = new JWindow();
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(getClass().getResource("Logo/logo.jpg")));
		window.getContentPane().add(image, SwingConstants.CENTER);
		window.getContentPane().setBackground(Color.WHITE);
		window.setBounds(500, 100, 275, 535);
		window.setVisible(true);
		
		//sleep represents the splash
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		window.setVisible(false);
		window.dispose();
	}
}
