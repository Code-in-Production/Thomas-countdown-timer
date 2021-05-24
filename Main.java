package CountdownTimer;
import java.util.*;
import javax.swing.*;

public class Main {
	
	public static Display display = new Display();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		JFrame myFrame = new JFrame("Timer");
		
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().add(display);
		myFrame.pack();
		
	}
}