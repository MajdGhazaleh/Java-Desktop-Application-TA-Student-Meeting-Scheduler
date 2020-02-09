package UIdesign;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import baseClass.QueueManager;
import baseClass.Reservation;

// Allows the TA to report the student as present or absent
public class TAreport extends JFrame implements ActionListener {

	static JFrame frame1 = new JFrame();
	static JPanel panel = (JPanel) frame1.getContentPane();
	JPanel queuePanel;
	JLabel student_email, student_question, email_text, question_text, error_message;
	JButton present, absent;

	public TAreport() {

	}

	// Initializes the appointments/reservations randomly and returns a QueueManager (that manages the queue).
	public QueueManager getRandomizedAppointments() {

		String emailIds[] = { "aamalik@buffalo.edu", "karen@buffalo.edu", "jimbo@buffalo.edu", "majdos@buffalo.edu" };
		String questions[] = { "What is IDE?", "","Can you give us an A?","" };
		int number_students = (int) ((Math.random() * 5));
		QueueManager manager = new QueueManager();
		Queue<Reservation> randomQueue = new LinkedList();
		int minutes[] = {11, 5, 0};
		for (int i = 0; i < number_students; i++) {
			Reservation temp = new Reservation();
			temp.setEmailId(emailIds[i]);
			temp.setQuestion(questions[i]);
			int randomTime=(int) ((Math.random() * 3));
			temp.setTime(LocalTime.now().minusMinutes(minutes[randomTime]));
			randomQueue.add(temp);
		}
		manager.setReservationQueue(randomQueue);
		return manager;
	}

	/*
	 * Parameterized constructor takes QueuManager as an argument and Fetches first appointment from the queue and 
	 * allows the TA to report the student as present or absent
	 */
	public TAreport(QueueManager manager) {
		Reservation temp = manager.getReservationQueue().peek();
		this.getContentPane().setBackground(Color.WHITE);
		ButtonPresent presButton = new ButtonPresent(manager);
		ButtonAbsent absButton = new ButtonAbsent(manager);
		panel = new JPanel(new GridLayout(3, 1));
		if (manager.getReservationQueue().size() > 0) {
			student_email = new JLabel("Student Email :");
			email_text = new JLabel(temp.getEmailId());
			student_question = new JLabel("Optional Question :");
			question_text = new JLabel(temp.getQuestion());
			present = new JButton("Present");
			absent = new JButton("Absent");
			panel.add(student_email);
			panel.add(email_text);
			panel.add(student_question);
			panel.add(question_text);
			panel.add(present);
			panel.add(absent);

			present.addActionListener(presButton);
			absent.addActionListener(absButton);
		} else {
			error_message = new JLabel("<html><body>No  Reservation right now <br> Please Login later</body></html> ",
					SwingConstants.CENTER);
			panel.add(error_message);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel, BorderLayout.CENTER);
		setTitle("TA Report");
		setBackground(Color.white);
		setSize(600, 300);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
	}
	
// Launch the GUI
	public static void main(String args[]) {
		TAreport report = new TAreport();
		QueueManager manager = report.getRandomizedAppointments();
		new TAreport(manager);

	}

// 	Parameterized Constructor takes QueueManager as parameter and handles the scenario when the student is present
	public class ButtonPresent implements ActionListener {
		Reservation present;
		QueueManager manager;

		ButtonPresent(QueueManager manager) {
			this.manager = manager;
		}

// Removes the present person from the queue and displays the updated queue to the TA
		public void actionPerformed(ActionEvent ae) {
			this.present = manager.Dequeue();
			this.present.setPresent(true);

			TAreport.this.dispose();
			QueueOfReservation quRes = new QueueOfReservation(manager, present);

		}
	}

// 	Parameterized Constructor takes QueueManager as parameter and handles the scenario when the student is absent
	public class ButtonAbsent implements ActionListener {
		Reservation absent;
		QueueManager manager;

		public ButtonAbsent(QueueManager manager) {
			this.manager = manager;
		}
		
/* 
 * Adds/removes absent student from queue based on reservation time
 */

		public void actionPerformed(ActionEvent ae) {
			this.absent = manager.Dequeue();
			
			TAreport.this.dispose();
			if (Math.abs(MINUTES.between(LocalTime.now().withNano(0), absent.getTime())) > 10) {
				Date date = new Date();
				absent.setBannedDate(date);
				absent.setBanned(true);
			} else {
				manager.Enqueue(absent);
			}
			
			QueueOfReservation quRes = new QueueOfReservation(manager, absent);
		}
	}
}
