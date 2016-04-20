package bg.ittalents.model.POJO;

import java.util.regex.Pattern;


public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private String avatarPath;

	private Integer admin = 0;
	private Integer organizationId = null;
	private String organizationName;
	private String fullname;
	private Integer lastProjectId =0;

	public User() {

	}
	public User( Integer admin) {		
		this.admin = admin;
	}
	
	
	public User(int id, String username, String email, String password,
			String avatarPath, Integer admin, Integer organizationId,
			String fullName) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.avatarPath = avatarPath;
		this.admin = admin;
		this.organizationId = organizationId;
		this.fullname = fullName;
	}
	
	public User(int id, String username, String email, String password,
			String avatarPath, Integer admin, Integer organizationId,
			String fullName,int lastProjectId) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.avatarPath = avatarPath;
		this.admin = admin;
		this.organizationId = organizationId;
		this.fullname = fullName;
		this.lastProjectId=lastProjectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public Integer getAdmin() {
		return admin;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullName) {
		this.fullname = fullName;
	}
	
	public static boolean isPaswordStrong(String pass){
		boolean letter=false;
		boolean number=false;
		
		for (int i = 0; i < pass.length(); i++) {
			if (letter==false&&pass.charAt(i)>='A'&&pass.charAt(i)<='z') {
				letter=true;
			}
			if (number==false&&pass.charAt(i)>='0'&&pass.charAt(i)<='9') {
				number=true;
			}
			
			if (number&&letter&&pass.length()>4) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isMailValid(String mail){
		
			final Pattern pat = Pattern.compile(
					"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
		
		return pat.matcher(mail).matches();
	}
	public Integer getLastProjectId() {
		return lastProjectId;
	}
	public void setLastProjectId(Integer lastProjectId) {
		this.lastProjectId = lastProjectId;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", avatarPath=" + avatarPath + ", admin=" + admin + ", organizationId=" + organizationId
				+ ", organizationName=" + organizationName + ", fullname=" + fullname + ", lastProjectId="
				+ lastProjectId + "]";
	}


}
