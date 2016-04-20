package bg.ittalents;

import static org.junit.Assert.*;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import org.junit.Test;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.DAO.ILogDAO;
import bg.ittalents.model.DAO.IProjectDAO;
import bg.ittalents.model.DAO.ISprintDAO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Log;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.Sprint;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.*;

public class DAOTests {
	User user = new User();

	// @Test
	// public void dBConnectionTest() {
	// com.example.model.DAO.DBConnection.getInstance();
	// }
	//
	// @Test
	// public void checkEmail() throws UnsupportedDataTypeException, DBException
	// {
	// System.out.println(IUserDAO.getDAO("db").isThereSuchAUser("drago@abv.bg"));
	// }
	//
	// @Test
	// public void CheckCreateUser() throws UnsupportedDataTypeException,
	// DBException, WorkPlanDAOException {
	// user.setEmail("morga@abv.bg");
	// user.setPassword("kolon123");
	// user.setUsername("klnka1");
	// System.out.println(IUserDAO.getDAO("db").addUser(user));
	// }

	// @Test
	// public void CheckAddsprint() throws UnsupportedDataTypeException,
	// DBException, WorkPlanDAOException {
	//
	// System.out.println(ISprintDAO.getDAO("db").addSprint(new Sprint(0,
	// "test", true, 1)));
	// }

	// @Test
	// public void CheckUpdateUSer() {
	// User user8=null;
	// try {
	// user8 =IUserDAO.getDAO("db").getUserById(8);
	// } catch (UnsupportedDataTypeException | DBException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// user8.setLastProjectId(22);
	// System.out.println(user8);
	// try {
	// IUserDAO.getDAO("db").updateUser(user8);
	// } catch (UnsupportedDataTypeException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (DBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (WorkPlanDAOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// @Test
	// public void CheckIncrementIssueCount() {
	// Project project=new Project(15, "blaa", 3, "blaa", 8, 0);
	//
	// try {
	// IProjectDAO.getDAO("db").incrementIssuecount(project);
	// } catch (UnsupportedDataTypeException | WorkPlanDAOException |
	// DBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// @Test
	// public void CheckIncrementIssueCount() {
	// Activity act=null;
	// try {
	// act = IActivityDAO.getDAO("db").getActivityByID(37);
	// } catch (UnsupportedDataTypeException | WorkPlanDAOException |
	// DBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println(act);
	// }

	// @Test
	// public void CheckCreateActivty() {
	// Activity act=new Activity();
	// act.setProjectID(31);
	// act.setReportedID(8);
	// act.setSummary("puse");
	// act.setId(51);
	// act.setAssigneeID(11);
	// act.setType("Task");
	// try {
	// IActivityDAO.getDAO("db").updateActivity(act);
	// } catch (UnsupportedDataTypeException | DBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println(act);
	// }

//	@Test
//	public void getLog() {
//
//		try {
//			Log log = ILogDAO.getDAO("db").getLogById(47);
//			System.out.println(log);
//
//		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Test
	public void getLogsForIssue() {

		try {
			List<Log> logList = ILogDAO.getDAO("db").getLogsByIssueId(48);
			for (Log log : logList) {
				System.out.println(log);
			}

		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
