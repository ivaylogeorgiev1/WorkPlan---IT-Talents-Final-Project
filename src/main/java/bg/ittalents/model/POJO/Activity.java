package bg.ittalents.model.POJO;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

public class Activity {

	private int id;
	private String summary;
	private String description;
	private String issueKey;
	private double estimate;
	private Timestamp createdOn;
	private String createdOnString;
	private Timestamp finishedOn;
	private String finishedOnString;
	private int reportedID;
	private int assigneeID;
	private String status = ActivityStatus.ToDo.toString();
	private String type;
	private Integer sprintID;
	private Integer connectedToID;
	private int projectID;
	private int prioriy;
	private String connectedType;
	

	public Activity() {

	}

	public Activity(int id, String summary, String description, String version, double estimate, Timestamp createdOn,
			Timestamp finishedOn, int reporterID, int assigneeID, String status, String type, int sprintID,
			int connectedToID, int projectID) {
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.issueKey = version;
		this.estimate = estimate;
		this.createdOn = createdOn;
		this.createdOnString = createdOn.toLocalDateTime().format(
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		this.finishedOn = finishedOn;
		if (finishedOn != null) {
			this.finishedOnString = finishedOn.toLocalDateTime().format(
					DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		}
		this.reportedID = reporterID;
		this.assigneeID = assigneeID;
		this.status = status;
		this.type = type;
		this.sprintID = sprintID;
		this.connectedToID = connectedToID;
		this.projectID = projectID;
	}

	public Activity(String summary, int reporterID, String status, String type, int projectID) {
		this.summary = summary;
		this.reportedID = reporterID;
		this.status = status;
		this.type = type;
		this.projectID = projectID;
	}

	public Activity(int id, String summary, String description, String issueKey, double estimate, Timestamp createdOn,
			Timestamp finishedOn, int reportedID, int assigneeID, String status, String type, int sprintID,
			int connectedToID, int projectID, int prioriy, String connectedType) {
		super();
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.issueKey = issueKey;
		this.estimate = estimate;
		this.createdOn = createdOn;
		this.createdOnString = createdOn.toString();
		this.finishedOn = finishedOn;
		if (finishedOn != null) {
			this.finishedOnString = finishedOn.toString();
		}
		this.reportedID = reportedID;
		this.assigneeID = assigneeID;
		this.status = status;
		this.type = type;
		this.sprintID = sprintID;
		this.connectedToID = connectedToID;
		this.projectID = projectID;
		this.prioriy = prioriy;
		this.connectedType = connectedType;
	}

	public String getCreatedOnString() {
		return createdOnString;
	}

	public void setCreatedOnString(String createdOnString) {
		this.createdOnString = createdOnString;
	}

	public Timestamp getFinishedOn() {
		return finishedOn;
	}

	public void setFinishedOn(Timestamp finishedOn) {
		this.finishedOn = finishedOn;
	}

	public String getFinishedOnString() {
		return finishedOnString;
	}

	public void setFinishedOnString(String finishedOnString) {
		this.finishedOnString = finishedOnString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssueKey() {
		return issueKey;
	}

	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}

	public double getEstimate() {
		return estimate;
	}

	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return finishedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.finishedOn = updatedOn;
	}

	public int getReportedID() {
		return reportedID;
	}

	public void setReportedID(int reportedID) {
		this.reportedID = reportedID;
	}

	public int getAssigneeID() {
		return assigneeID;
	}

	public void setAssigneeID(int assigneeID) {
		this.assigneeID = assigneeID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSprintID() {
		return sprintID;
	}

	public void setSprintID(Integer sprintID) {
		this.sprintID = sprintID;
	}

	public Integer getConnectedToID() {
		return connectedToID;
	}

	public void setConnectedToID(Integer connectedToID) {
		this.connectedToID = connectedToID;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getPrioriy() {
		return prioriy;
	}

	public void setPrioriy(int prioriy) {
		this.prioriy = prioriy;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", summary=" + summary + ", description=" + description + ", issueKey=" + issueKey
				+ ", estimate=" + estimate + ", createdOn=" + createdOn + ", createdOnString=" + createdOnString
				+ ", finishedOn=" + finishedOn + ", finishedOnString=" + finishedOnString + ", reportedID=" + reportedID
				+ ", assigneeID=" + assigneeID + ", status=" + status + ", type=" + type + ", sprintID=" + sprintID
				+ ", connectedToID=" + connectedToID + ", projectID=" + projectID + ", prioriy=" + prioriy
				+ ", connectedType=" + connectedType + "]";
	}

	public String getConnectedType() {
		return connectedType;
	}

	public void setConnectedType(String connectedType) {
		this.connectedType = connectedType;
	}
	
	static public String getPriorityName(Integer number){
		Map<Integer, String> names=new HashMap<Integer, String>();
		names.put(1, "Lowest");
		names.put(2, "Low");
		names.put(3, "Normal");
		names.put(4, "High");
		names.put(5, "Highest");
		
		return names.get(number);
	}

}
