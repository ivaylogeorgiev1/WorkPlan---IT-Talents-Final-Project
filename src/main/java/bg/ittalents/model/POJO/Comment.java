package bg.ittalents.model.POJO;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Comment {
	private int id;
	private String createdOn;
	private String text;
	private int activityID;
	private int userID;
	private int isWorkLog=0;
	private double workHours=0;

	public Comment(String text, int activityID, int userID) {
		this.text = text;
		this.activityID = activityID;
		this.userID = userID;
	}

	public Comment(int id, Timestamp time, String text, int activityID,
			int userID) {
		this.id = id;
		this.createdOn = time.toLocalDateTime().format(
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		this.text = text;
		this.activityID = activityID;
		this.userID = userID;
	}
	
	public Comment(int id, Timestamp time, String text, int activityID,
			int userID,int isWorkLog,double workHours) {
		this.id = id;
		this.createdOn = time.toLocalDateTime().format(
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		this.text = text;
		this.activityID = activityID;
		this.userID = userID;
		this.isWorkLog=isWorkLog;
		this.workHours=workHours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedOn() {
		return createdOn;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getIsWorkLog() {
		return isWorkLog;
	}

	public void setIsWorkLog(int isWorkLog) {
		this.isWorkLog = isWorkLog;
	}

	public double getWorkHours() {
		return workHours;
	}

	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}

}
