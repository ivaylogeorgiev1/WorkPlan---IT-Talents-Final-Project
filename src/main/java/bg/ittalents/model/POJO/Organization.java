package bg.ittalents.model.POJO;

public class Organization {
	
	private Integer id;
	private String name;
	private int adminID;
	
	public Organization() {
	}

	public Organization(String name, Integer adminID, Integer id) {
		super();
		this.name = name;
		this.adminID = adminID;
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", adminID=" + adminID + "]";
	}

}
