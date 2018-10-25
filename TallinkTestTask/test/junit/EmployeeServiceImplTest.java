package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tallink.employee.model.beans.EmployeeRequestBean;
import com.tallink.employee.model.beans.EmployeeResponseBean;
import com.tallink.employee.services.ServiceLocator;

public class EmployeeServiceImplTest {

	@Test
	public void getEmployeesTest() {
		try {
			EmployeeResponseBean allEmployees = ServiceLocator.getEmployeeService().getAllEmployees();
			//we are 100% sure that there are several employee
			assertNotEquals(null, allEmployees);
			assertNotEquals(null, allEmployees.getChildren());
			assertNotEquals(0, allEmployees.getChildren().size());
		} catch(Exception ee) {
			fail();
		}
	}
	
	@Test
	public void addEmployeeTest() {
		try {
			
			//1. prepare data
			EmployeeResponseBean responseBean = ServiceLocator.getEmployeeService().getAllEmployees();
			
			long newEmployeeManagerId = getIdOfAnyEmployee(responseBean);
			String newEmployeeFirstName = "TestFirstName";
			String newEmployeeSecondName = "TestSecondName";
			EmployeeRequestBean newEmployee = new EmployeeRequestBean();
			newEmployee.setManagerId(newEmployeeManagerId);
			newEmployee.setFirstName(newEmployeeFirstName);
			newEmployee.setSecondName(newEmployeeSecondName);
			assertEquals(false, containsEmployee(responseBean, newEmployee));
			
			//2. add new employee
			ServiceLocator.getEmployeeService().addEmployee(newEmployee);
			
			//3. check that new employee has been added
			responseBean = ServiceLocator.getEmployeeService().getAllEmployees();
			assertEquals(false, containsEmployee(responseBean, newEmployee));			
			
			
		} catch(Exception ee) {
            ee.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void deleteEmployeeTest() {
		try {
			//1. prepare data
			EmployeeResponseBean responseBean = ServiceLocator.getEmployeeService().getAllEmployees();
			long toDeleteEmployeeId = findAnyNotCeoEmployee(responseBean);
			
			EmployeeRequestBean employeeRequestBean = new EmployeeRequestBean();
			employeeRequestBean.setEmployeeId(toDeleteEmployeeId);
			
			//2. delete
			ServiceLocator.getEmployeeService().deleteEmployee(employeeRequestBean);
			
			//3. check that there is no that employee anymore
			assertEquals(false, containsEmployee(responseBean, employeeRequestBean));		
			
		} catch(Exception ee) {			
			fail();
		}
		
	}
	
	@Test
	public void deleteCEOEmployeeTest() {
		try {
			//1. prepare data
			EmployeeResponseBean responseBean = ServiceLocator.getEmployeeService().getAllEmployees();
			long toDeleteCeoEmployeeId = getIdOfCeoEmployee(responseBean);
			
			EmployeeRequestBean employeeRequestBean = new EmployeeRequestBean();
			employeeRequestBean.setEmployeeId(toDeleteCeoEmployeeId);
			//2. delete
			ServiceLocator.getEmployeeService().deleteEmployee(employeeRequestBean);
			fail("we have to get an error here");
		} catch(Exception ee) {
			
		}
		
	}
	
	private long getIdOfAnyEmployee(EmployeeResponseBean employeeResponseBean) {
		return employeeResponseBean.getEmployeeId();
	}
	
	private long getIdOfCeoEmployee(EmployeeResponseBean employeeResponseBean) {
		if (employeeResponseBean.getManagerId() == 0) {
			return employeeResponseBean.getEmployeeId();
		}
		
		if (employeeResponseBean.getChildren() != null && employeeResponseBean.getChildren().size() != 0) {
			for (EmployeeResponseBean bean : employeeResponseBean.getChildren()) {
				long result = getIdOfCeoEmployee(bean);
				if (result != -1) {
					return result;
				}
			}
		} 
		return -1;
	}
	
	private long findAnyNotCeoEmployee(EmployeeResponseBean employeeResponseBean) {
		if (employeeResponseBean.getManagerId() != 0) {
			return employeeResponseBean.getEmployeeId();
		}
		
		if (employeeResponseBean.getChildren() != null && employeeResponseBean.getChildren().size() != 0) {
			for (EmployeeResponseBean bean : employeeResponseBean.getChildren()) {
				long result = findAnyNotCeoEmployee(bean);
				if (result != -1) {
					return result;
				}
			}
		} 
		return -1;
	}
	
	//rename parameters
	private boolean containsEmployee(EmployeeResponseBean employeeResponseBean, EmployeeRequestBean employeeRequestBean) {
		if (employeeResponseBean.getManagerId() == employeeRequestBean.getManagerId() &&
				employeeResponseBean.getEmployeeId() == employeeRequestBean.getEmployeeId() &&
				employeeResponseBean.getFirstName().equals(employeeRequestBean.getFirstName()) &&
				employeeResponseBean.getSecondName().equals(employeeRequestBean.getSecondName())) {
			return true;
		}
		
		if (employeeResponseBean.getChildren() != null && employeeResponseBean.getChildren().size() != 0) {
			for (EmployeeResponseBean bean : employeeResponseBean.getChildren()) {
				boolean result = containsEmployee(bean, employeeRequestBean);
				if (result) {
					return result;
				}
			}
		} 
		return false;
	}
	
	private boolean containsEmployee(EmployeeResponseBean employeeResponseBean, long employeeId) {
		if (employeeResponseBean.getEmployeeId() == employeeId) {
			return true;
		}
		
		if (employeeResponseBean.getChildren() != null && employeeResponseBean.getChildren().size() != 0) {
			for (EmployeeResponseBean bean : employeeResponseBean.getChildren()) {
				boolean result = containsEmployee(bean, employeeId);
				if (result) {
					return result;
				}
			}
		} 
		return false;
	}

}
