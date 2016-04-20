package bg.ittalents.model.DAO;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import bg.ittalents.model.POJO.Organization;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

public interface IOrganizationDAO {

	public int addOrganization(Organization organization) throws WorkPlanDAOException, DBException;

	public static IOrganizationDAO getDAO(String storage) throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new OrganizationDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	String getOrgName(int orgId) throws  DBException;

	Organization getOrgByID(int orgId) throws WorkPlanDAOException, DBException;

	List<Organization> getAllOrganizations() throws DBException;
}
