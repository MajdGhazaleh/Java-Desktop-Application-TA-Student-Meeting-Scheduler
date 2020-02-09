package UIdesign;

import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import baseClass.QueueManager;
import baseClass.Reservation;

// Displays the Updated Queue to the TA
public class QueueOfReservation extends JFrame {
	public QueueOfReservation() {

	}

	/*
	 * Parameterized constructor takes QueueManager and the current student
	 * Reservation as arguments and displays the Queue with details in tabular
	 * format to the TA
	 */
	public QueueOfReservation(QueueManager manager, Reservation student) {
		Queue<Reservation> queueRes = manager.getReservationQueue();
		String[] columns = new String[] { "Email", "Question", "Reservation Time" };
		int i = 0;
		Object[][] resData = new Object[queueRes.size() + 6][3];
		for (Reservation res : queueRes) {
			for (int j = 0; j < 3; j++) {
				if (i < queueRes.size()) {
					if (j == 0) {
						resData[i][j] = res.getEmailId();
					} else if (j == 1) {
						resData[i][j] = res.getQuestion();
					} else {
						resData[i][j] = res.getTime();
					}

				} else {
					resData[i][j] = "";
				}
			}
			i++;

		}

		this.setTitle("Reservation Queue");
		if (student.isBanned()) {
			int count = 0;
			for (int k = queueRes.size(); k < resData.length; k++) {
				for (int j = 0; j < 3; j++) {
					if (count < 3) {
						resData[k][j] = "";
					} else if (count == 3) {
						if (j == 0) {
							resData[k][j] = "Banned Student Details";
						} 
						else {
							resData[k][j]="";
						}
					} 
					else if(count == 4) {
						if (j == 0) {
							resData[k][j] ="Email";
						} else if (j == 1) {
							resData[k][j] = "Banned Status";
						} else {
							resData[k][j] ="Banned Date";
						}
					}
					else {
						if (j == 0) {
							resData[k][j] = student.getEmailId();
						} else if (j == 1) {
							resData[k][j] = student.isBanned();
						} else {
							resData[k][j] = student.getBannedDate();
						}
					}

				}
				count++;
			}

		}
		JTable table = new JTable(resData, columns);
		this.add(new JScrollPane(table));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new QueueOfReservation();
			}
		});
	}
}