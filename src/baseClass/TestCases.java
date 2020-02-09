package baseClass;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;

import static org.junit.Assert.*;

import org.junit.Test;

import UIdesign.TAreport;
import UIdesign.TAreport.ButtonAbsent;

// Class to perform test cases.
public class TestCases {
	@Test
	// Test case for the scenario when the student is absent.
	public void testWhenStudentIsAbsent() {
		
		Reservation reservationAbsent = new Reservation();
		LocalTime nineMinutesLater = LocalTime.now().plusMinutes(9);
		reservationAbsent.setTime(nineMinutesLater);
		
		QueueManager queuemanager = new QueueManager();
		Queue<Reservation> testQueue=new LinkedList();
		
		
		Reservation reservationDummy = new Reservation();
		
		
		queuemanager.setReservationQueue(testQueue);
		queuemanager.Enqueue(reservationAbsent);
		
		 
		queuemanager.Enqueue(reservationDummy);
		
		
		int queueLengthBeforeAdd = queuemanager.getQueueLength();
		System.out.println("Initial queue length (For the absent scenario): "+queueLengthBeforeAdd);
		
	
		TAreport tareport = new TAreport();
		TAreport.ButtonAbsent ba = tareport.new ButtonAbsent(queuemanager);
		ActionEvent ae = null;
		ba.actionPerformed(ae);
		
		
		int queueLengthAfterAdd = queuemanager.getQueueLength();
		System.out.println("Queue length after marking the student as absent: "+queueLengthAfterAdd);
		
		
		assertEquals(queueLengthBeforeAdd, queueLengthAfterAdd);
	}
	@Test
	// Test case for the scenario when the student is banned.
	public void testWhenStudentGetsBanned() {
		
		Reservation reservationBanned = new Reservation();
		LocalTime twelveMinutesLater = LocalTime.now().plusMinutes(12);
		reservationBanned.setTime(twelveMinutesLater);

		QueueManager queuemanager = new QueueManager();
		Queue<Reservation> testQueue=new LinkedList();
		
		
		Reservation reservationDummy = new Reservation();
		
		
		queuemanager.setReservationQueue(testQueue);
		queuemanager.Enqueue(reservationBanned);
		
		
		queuemanager.Enqueue(reservationDummy);
		
		
		int queueLengthBeforeAdd = queuemanager.getQueueLength();
		System.out.println("Initial queue length (For the banned student scenario): "+queueLengthBeforeAdd);
		
		TAreport obj = new TAreport();
		
		TAreport.ButtonAbsent ban = obj.new ButtonAbsent(queuemanager);
		ActionEvent ae = null;
		ban.actionPerformed(ae);
		
		int queueLengthAfterAdd = queuemanager.getQueueLength();
		System.out.println("Queue length after marking the banned student as absent: "+queueLengthAfterAdd);
		
		assertEquals(queueLengthBeforeAdd-1, queueLengthAfterAdd);
	}
}
