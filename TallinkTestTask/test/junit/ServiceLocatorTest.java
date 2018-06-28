package junit;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.tallink.employee.services.ServiceLocator;

public class ServiceLocatorTest {

	@Test
	public void test() {
		assertNotEquals(null, ServiceLocator.getEmployeeService());
	}

}
