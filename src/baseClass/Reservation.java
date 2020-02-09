package baseClass;

import java.time.LocalTime;
import java.util.Date;

//Holds appointment information
public class Reservation {

	private String emailId;
	private String question;
	private LocalTime time;
	private Date bannedDate;
	private boolean isPresent;
	private boolean isBanned;
	public boolean isBanned() {
		return isBanned;
	}
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	public boolean isPresent() {
		return isPresent;
	}
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time.withNano(0);
	}
	public Date getBannedDate() {
		return bannedDate;
	}
	public void setBannedDate(Date bannedDate) {
		this.bannedDate = bannedDate;
	}
}

