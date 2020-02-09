package baseClass;

import java.util.Queue;

//Manages the queue of reservations
public class QueueManager {

	private Queue<Reservation> reservationQueue;

	public Queue<Reservation> getReservationQueue() {
		return reservationQueue;
	}

	public void setReservationQueue(Queue<Reservation> reservationQueue) {
		this.reservationQueue = reservationQueue;
	}
	public void Enqueue(Reservation newRes){
		reservationQueue.add(newRes);
	}
	public Reservation Dequeue() {
		Reservation recent=reservationQueue.poll();
		return recent;
	}
	public Reservation getHead() {
		Reservation recent=reservationQueue.peek();
		return recent;
	}
	public int getQueueLength() {
		Queue<Reservation> queue = this.getReservationQueue();
		int length = queue.size();
		return length;
	}
}