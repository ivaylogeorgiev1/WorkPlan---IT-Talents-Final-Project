package bg.ittalents.model.POJO;

public class Project {

	private Integer id;
	private String name;
	private Integer organizationID;
	private String key;
	private Integer projectLeaderID;
	private Integer issueCount=0;

	public Project() {

	}
	public Project(Integer id,String name, Integer organizationID, String key,
			Integer projectLeaderID) {
		this.id=id;
		this.name = name;
		this.organizationID = organizationID;
		this.key = key;
		this.projectLeaderID = projectLeaderID;
	}
	
	public Project(Integer id,String name, Integer organizationID, String key,
			Integer projectLeaderID,Integer issueCount) {
		this.id=id;
		this.name = name;
		this.organizationID = organizationID;
		this.key = key;
		this.projectLeaderID = projectLeaderID;
		this.issueCount=issueCount;
	}
	
	public Project(String name, Integer organizationID, String key,
			Integer projectLeaderID) {
		this.name = name;
		this.organizationID = organizationID;
		this.key = key;
		this.projectLeaderID = projectLeaderID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(Integer organizationID) {
		this.organizationID = organizationID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getProjectLeaderID() {
		return projectLeaderID;
	}

	public void setProjectLeaderID(Integer projectLeaderID) {
		this.projectLeaderID = projectLeaderID;
	}
	public Integer getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(Integer issueCount) {
		this.issueCount = issueCount;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", organizationID=" + organizationID + ", key=" + key
				+ ", projectLeaderID=" + projectLeaderID + ", issueCount=" + issueCount + "]";
	}
	

}
