package junit;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

import com.tallink.employee.model.beans.EmployeeRequestBean;
import com.tallink.employee.utils.ValidationUtil;

public class ValidationUtilTest {
	
	@Test
	public void validateAddEmployeeOperationTest() {
		EmployeeRequestBean employeeRequestBean = new EmployeeRequestBean();
		employeeRequestBean.setManagerId(1);
		employeeRequestBean.setFirstName("TestFirstName");
		employeeRequestBean.setSecondName("TestSecondName");
		
		//1. positive case
		assertEquals(0, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		// min length firstName
		employeeRequestBean.setFirstName("Tes");
		assertEquals(0, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		// min length secondName
		employeeRequestBean.setFirstName("Tes");
		assertEquals(0, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		// max length firstName
		employeeRequestBean.setSecondName("rrwrwerfsdafjkljlsfddsfueirfre");
		assertEquals(0, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		// max length secondName
		employeeRequestBean.setSecondName("rrwrwerfsdafjkljlsfddsfueirfre");
		assertEquals(0, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		
		//2. negative cases employee id		
		// negative employeeId
		employeeRequestBean.setManagerId(-1);
		assertEquals(1, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		// employeeId is 0
		employeeRequestBean.setManagerId(0);
		assertEquals(1, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		//3. negative cases firstName
		//too big
		employeeRequestBean.setFirstName("rrwrwerfsdafjkljlsfddsfueirfreq");
		assertEquals(2, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		//too small
		employeeRequestBean.setFirstName("r");
		assertEquals(2, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		//not only letters
		employeeRequestBean.setFirstName("rrwrwerf34234231");
		assertEquals(2, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		//4. negative cases firstName
		//too big
		employeeRequestBean.setSecondName("rrwrwerfsdafjkljlsfddsfueirfreq");
		assertEquals(3, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		//too small
		employeeRequestBean.setSecondName("r");
		assertEquals(3, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
		//not only letters
		employeeRequestBean.setSecondName("rrwrwerf34234231");
		assertEquals(3, ValidationUtil.validateAddEmployeeOperation(employeeRequestBean).size());
		
	}
	
	@Test
	public void validateDeleteEmployeeOperationTest() {
		EmployeeRequestBean employeeRequestBean = new EmployeeRequestBean();
		employeeRequestBean.setEmployeeId(1);
		
		//1. correct case
		assertEquals(0, ValidationUtil.validateDeleteEmployeeOperation(employeeRequestBean).size());
		
		//2. negative cases
		// negative employeeId
		employeeRequestBean.setEmployeeId(-1);
		assertEquals(1, ValidationUtil.validateDeleteEmployeeOperation(employeeRequestBean).size());
		// employeeId is 0
		assertEquals(1, ValidationUtil.validateDeleteEmployeeOperation(employeeRequestBean).size());		
		
	}
	
	
	@Test
	public void isValidFirstNameTest() throws Exception {
		Method method = ValidationUtil.class.getDeclaredMethod("isValidFirstName", String.class);
		method.setAccessible(true);
		
		//1. correct second name
		String firstName = "Testname";
		assertEquals(true, method.invoke(null, firstName));
		
		// corner cases
		// min length 
		firstName = "Tes";
		assertEquals(true, method.invoke(null, firstName));		
		// max length 
		firstName = "rrwrwerfsdafjkljlsfddsfueirfre";
		assertEquals(true, method.invoke(null, firstName));
		
		//2. incorrect
		
		//too big
		firstName = "rrwrwerfsdafjkljlsfddsfueirfreq";
		assertEquals(false, method.invoke(null, firstName));
		
		//too small
		firstName = "r";
		assertEquals(false, method.invoke(null, firstName));
		
		//not only letters
		firstName = "rrwrwerf34234231";
		assertEquals(false, method.invoke(null, firstName));
		
	}
	
	@Test
	public void isValidSecondNameTest() throws Exception {
		Method method = ValidationUtil.class.getDeclaredMethod("isValidSecondName", String.class);
		method.setAccessible(true);
		
		//1. correct second name
		String secondName = "Testname";
		assertEquals(true, method.invoke(null, secondName));
		
		// corner cases
		
		// min length 
		secondName = "Tes";
		assertEquals(true, method.invoke(null, secondName));
		
		// max length 
		secondName = "rrwrwerfsdafjkljlsfddsfueirfre";
		assertEquals(true, method.invoke(null, secondName));
		
		//2. incorrect
		
		//too big
		secondName = "rrwrwerfsdafjkljlsfddsfueirfreq";
		assertEquals(false, method.invoke(null, secondName));
		
		//too small
		secondName = "r";
		assertEquals(false, method.invoke(null, secondName));
		
		//not only letters
		secondName = "rrwrwerf34234231";
		assertEquals(false, method.invoke(null, secondName));
		
	}

}
