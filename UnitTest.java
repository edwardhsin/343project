import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UnitTest {
	
	@Test
	void test() {
		System.out.println("Unit Test For Adding Tenant");
		
	  	// using empty constructor for tenantRecord
		// adding a Tenant to empty tenantS ArrayList in tenantRecord object
		tenantRecord tenantList = new tenantRecord();
		tenantList.addTenant(new Tenant("John Smith", 101));
		
		String name = tenantList.tenantS.get(0).name;
		int roomNum = tenantList.tenantS.get(0).roomNum;

		
		// failure trace if test fails
		
		assertEquals(tenantList.tenantS.get(0).roomNum, roomNum);
		assertEquals(roomNum, 101);
		
		assertTrue(tenantList.tenantS.get(0).name.equals(name));
		assertTrue(name.equals("John Smith"));
	}
}
