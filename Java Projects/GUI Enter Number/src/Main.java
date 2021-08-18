
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		String fn = JOptionPane.showInputDialog("Enter a number:");
		
		int number = Integer.parseInt(fn);
		
		JOptionPane.showMessageDialog(null, "The number you entered is: " + number);
		
	}
	
}
