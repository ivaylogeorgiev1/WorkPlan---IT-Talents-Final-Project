package bg.ittalents.model.POJO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Sprint {

	private int id;
	private String name;
	private boolean active;
	private int projectId;
	private LocalDateTime finishedOn;

	public Sprint(int id, String name, boolean is_active, int projectId,
			Timestamp finishedOn) {
		super();
			if (finishedOn != null) {
			this.finishedOn = finishedOn.toLocalDateTime();
		}
		this.id = id;
		this.name = name;
		this.setActive(is_active);
		this.setProjectId(projectId);
	}

	public Sprint(String name, boolean is_active, int project_id) {
		this.name = name;
		this.setActive(is_active);
		this.setProjectId(project_id);
	}

	public Sprint() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Sprint [id=" + id + ", "
				+ (name != null ? "name=" + name + ", " : "") + "active="
				+ active + ", projectId=" + projectId + "]";
	}

	public LocalDateTime getFinishedOn() {
		return finishedOn;
	}

}
