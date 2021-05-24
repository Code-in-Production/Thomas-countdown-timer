package CountdownTimer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Display extends JPanel implements ActionListener {
	
	//private static final long serialVersionUID = 1L;
	JLabel h = new JLabel("h");
	JLabel m = new JLabel("m");
	JLabel s = new JLabel("s");

	static JTextField hours = new JTextField("");
	static JTextField minutes = new JTextField("");
	static JTextField seconds = new JTextField("");
	

	static JLabel time = new JLabel("");

	
	static int[] arr = new int[3];
	
	public static JButton start = new JButton("Start");
	public static JButton pause = new JButton("Pause");
	
	static Timer t;
	static int state = 0;
	static int secondsRemaining = 0;
	
	Display () {
		this.setPreferredSize((new Dimension(200, 120)));
		this.setVisible(true);
		this.setLayout(null);
		
		add(h);
		h.setBounds(35, 40, 40, 20);

		add(m);
		m.setBounds(95, 40, 40, 20);

		add(s);
		s.setBounds(155, 40, 40, 20);
		
		add(hours);
		hours.setBounds(20, 60, 40, 20);
		hours.setVisible(true);

		add(minutes);
		minutes.setBounds(80, 60, 40, 20);
		minutes.setVisible(true);

		add(seconds);
		seconds.setBounds(140, 60, 40, 20);
		seconds.setVisible(true);

		add(time);
		time.setBounds(20, 60, 180, 20);
		time.setVisible(false);

		start.setBounds(20, 90, 70, 25);
		add(start);
		start.addActionListener(this);
		
		pause.setBounds(110, 90, 70, 25);
		add(pause);
		pause.addActionListener(this);
	}
	
	public void runTimer() {
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(secondsRemaining == 0 || state == 0) {
					t.cancel();
					return;
				}
				secondsRemaining--;

				arr[0] = secondsRemaining / 3600;
				arr[1] = secondsRemaining % 3600 / 60;
				arr[2] = secondsRemaining % 60;
				
				time.setText((arr[0] == 0 ? "" : arr[0] + ":") + (arr[1] / 10 > 0 ? arr[1] : "0" + arr[1]) + ":" + 
						(arr[2] / 10 > 0 ? arr[2] : "0" + arr[2]));
			}
		}, 1000, 1000);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "Start") {
			if(state == 0) {

			try {
				arr[2] = Math.max(Integer.parseInt(seconds.getText()), 0);
			} catch (Exception exception) {
				arr[2] = 0;
			}
			
			try {
				arr[1] = Math.max(Integer.parseInt(minutes.getText()), 0);
			} catch (Exception exception) {
				arr[1] = 0;
			}
			
			try {
				arr[0] = Math.max(Integer.parseInt(hours.getText()), 0);
			} catch (Exception exception) {
				arr[0] = 0;
			}
			
			arr[1] += arr[2] / 60;
			arr[2] %= 60;
			
			arr[0] += arr[1] / 60;
			arr[1] %= 60;
			
			time.setText((arr[0] == 0 ? "" : arr[0] + ":") + (arr[1] / 10 > 0 ? arr[1] : "0" + arr[1]) + ":" + 
			(arr[2] / 10 > 0 ? arr[2] : "0" + arr[2]));
			
			time.setVisible(true);

			hours.setVisible(false);
			minutes.setVisible(false);
			seconds.setVisible(false);
			h.setVisible(false);
			m.setVisible(false);
			s.setVisible(false);
			
			secondsRemaining = 3600 * arr[0] + 60 * arr[1] + arr[2];
			
				state = 1;
				t = new Timer();
				runTimer();
			}
			
		}
		
		else if(e.getActionCommand() == "Pause") {
			if(state == 1) {
				t.cancel();
				t.purge();
				hours.setText(Integer.toString(arr[0]));
				minutes.setText(Integer.toString(arr[1]));
				seconds.setText(Integer.toString(arr[2]));

				time.setVisible(false);

				hours.setVisible(true);
				minutes.setVisible(true);
				seconds.setVisible(true);
				h.setVisible(true);
				m.setVisible(true);
				s.setVisible(true);
				
				state = 0;
			}
			
		}

	}
	
}