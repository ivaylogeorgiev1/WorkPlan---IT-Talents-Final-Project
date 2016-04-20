package bg.ittalents.model.POJO;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Log {
	private Integer Id;
	private Integer activityId;
	private Integer userId;
	private String action;
	private Timestamp createdOn;
	private String createdOnString;
	private String issueKey;
	private String userFullName;
	
	
	public Log() {
		
	}
	
	public Log(Integer activityId, Integer userId, String action,String issueKey,String userFullName) {
		this.activityId = activityId;
		this.userId = userId;
		this.action = action;
		this.issueKey=issueKey;
		this.userFullName=userFullName;
	}
	
	



	public Log(Integer id, Integer activityId, Integer userId, String action, Timestamp createdOn,
			 String issueKey, String userFullName) {
		Id = id;
		this.activityId = activityId;
		this.userId = userId;
		this.action = action;
		this.createdOn = createdOn;
		this.createdOnString = createdOn.toLocalDateTime().format(
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		this.issueKey = issueKey;
		this.userFullName = userFullName;
	}

	public Integer getId() {
		return Id;
	}



	public void setId(Integer id) {
		Id = id;
	}



	public Integer getActivityId() {
		return activityId;
	}



	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public Timestamp getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}



	public String getCreatedOnString() {
		return createdOnString;
	}

	public String getIssueKey() {
		return issueKey;
	}

	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	@Override
	public String toString() {
		return "Log [Id=" + Id + ", activityId=" + activityId + ", userId=" + userId + ", action=" + action
				+ ", createdOn=" + createdOn + ", createdOnString=" + createdOnString + ", issueKey=" + issueKey
				+ ", userFullName=" + userFullName + "]";
	}
	
	
	
	
	

}
