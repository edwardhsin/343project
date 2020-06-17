import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class inputScreen{
	public void enterUsernamePassword(String user, String pass) {
		if (!user.equals("username") && !pass.equals("password")) {
			System.exit(0);
		}
	}
	public void displayMenu() {
		System.out.println("(1) Display Tenant List \n"
						+ "(2) Display Rent Record \n"
						+ "(3) Display Expense Record \n"
						+ "(4) Display Annual Summary \n"
						+ "(5) Add New Tenant \n"
						+ "(6) Add Rental Payment \n"
						+ "(7) Add Expense Payment  ");
	}
	
	
	public void selectOption(int insertNum) {
		switch (insertNum) {
			case 1:
				System.out.println("you chose to display tenant list1");
				break;
			case 2:
				System.out.println("you chose to display rent records");
				break;
			case 3:
				System.out.println("you chose to display expense records");
				break;
			case 4:
				System.out.println("you chose to display annual summary...");
				break;
			case 5:
				System.out.println("you chose to add new tenant");
				break;
			case 6:
				System.out.println("you chose to add new rent payment");
				break;
			case 7:
				System.out.println("you chose to add new expense payment");
				break;
		}
	}
	
	public void specifySecondInputs(int insertNum) {
		switch (insertNum) {
			case 5:
				System.out.println("Enter a name and room number");
				break;
			case 6:
				System.out.println("Enter a room, name, amt (in that order)");
				break;
			case 7:
				System.out.println("Enter a month, date, payee, amt (in that order)");
				break;
		}
	}
	
	public void enterTenantInfo(String insertName, int insertRoom, tenantRecord insertList) {
		if (insertList.verifyRoom(insertRoom)) {
			insertList.addTenant(new Tenant(insertName, insertRoom));
		}
		
	}
}

// geeks for geeks  composition library-book example
class tenantRecord  
{ 
    ArrayList<Tenant> tenantS = new ArrayList<Tenant>(); 
      
    // unused constructor
    tenantRecord (List<Tenant> insertListOfTenants) 
    { 
        this.tenantS = (ArrayList<Tenant>) insertListOfTenants;  
    } 
    
    // we use this constructor, bc we just initialize the list and add tenants 1by1, unlike the one above where
    // we create the list only when we have the whole list of tenants
    tenantRecord () 
    { 
    	
    } 
      
    public void addTenant(Tenant insertTenant) {
    	tenantS.add(insertTenant);
    }
    
    public List<Tenant> returnList(){ 
       return tenantS;   
    } 
    
    public void displayAllTenantInfo() {
    	System.out.println("\nTenant Record display");
    	for (int i = 0; i < tenantS.size() ; i++) {
    		System.out.println(  tenantS.get(i).getTenantRoom()  + " - " + tenantS.get(i).getName()   );
    	}
    	System.out.println();
    }
     
    public void getSize() {
    	System.out.println( "tenant record size: " + tenantS.size());
    }
    
    public int enterTenantInfo(String insertName, int insertRoom) {
    	return insertRoom;
    }
    
    public boolean verifyRoom(int insertNum) {
    	if (insertNum < 101 || insertNum > 120) {
    		System.out.println("room doesnt exist");
    		return false;
    	}
    	else 
    		for (int i = 0; i < tenantS.size() ; i++) {
    			if (tenantS.get(i).getTenantRoom() == insertNum) {
    				System.out.println("room occupied");
    				return false;
    			}
    		}
    	return true;
    	
    }
} 

class Tenant  
{ 
    public String name; 
    public int roomNum; 
      
    Tenant(String insertName, int insertNum) 
    {
        this.name = insertName; 
        this.roomNum = insertNum; 
    } 
    
    
    
    public String getName() {
    	return name;
    }
    
    public int getTenantRoom() {
    	return roomNum;
    }
    
    public void tenantTest() {
    	System.out.println("tenent test void func: ");
    }
} 

public class Program {
	
	public static void main(String[] args) {
        System.out.println("--- main ---");
        
        Scanner inputStr = new Scanner(System.in); 
        Scanner inputInt = new Scanner(System.in); 
        /*
        String user = "username";
        String pass = "password";
        System.out.println("Enter username: ");
        String userStr = inputStr.nextLine();
        System.out.println("Enter Password: ");
        String passStr = inputStr.nextLine();
        
        if (!userStr.equals(user) && !passStr.equals(pass)) {
        	System.out.println("Wrong username-password");
        }
        
        */
        
        inputScreen menu = new inputScreen();
    
        tenantRecord tenantList = new tenantRecord();
        tenantList.getSize(); // initially 0
        tenantList.addTenant(new Tenant("John Smith", 101 ));
        tenantList.addTenant(new Tenant("Jane Doe", 102 ) );
        
        String name3 = "Sam W";
        int room3 = 103;
        tenantList.addTenant(new Tenant( name3  , room3  ));
        
        boolean isEmptyAndExists = tenantList.verifyRoom(101);
        System.out.println(isEmptyAndExists);
        
        isEmptyAndExists = tenantList.verifyRoom(300);
        System.out.println(isEmptyAndExists);
        
        if(tenantList.verifyRoom(300)) {
        	System.out.println("101010101");
        }
    
        // while loop  while(string continue = "y")
        String continueStr = "y";
        int menuSelect = 0;
        
        String strInput = "";
        int integerInput = 0;
        
        while (continueStr.equalsIgnoreCase("y") ) {
        	menu.displayMenu();
        	menuSelect = inputInt.nextInt();
        	
        	menu.selectOption(menuSelect);
        	
        	switch (menuSelect) {
        	  case 1:
        		  tenantList.displayAllTenantInfo();
        		  break;
        	  case 2:
        		  
        		  break;
        	  case 3:
        		  
        		  break;
        	  case 4:
        		  
        		  break;
        	  case 5:
        		  menu.specifySecondInputs(menuSelect);
        		  
        		  strInput = inputStr.nextLine();
        		  integerInput = inputInt.nextInt();
        		  
        		  menu.enterTenantInfo(strInput, integerInput, tenantList);
        		  
        		  /*
        		  if (tenantList.verifyRoom(integerInput)) {
        			  tenantList.addTenant(new Tenant(strInput, integerInput));
        		  }
        		  */
	        	  break;
        	  case 6:
        		  
        		  break;
        	  case 7:
        		  
	        	  break;
        	  default:
        		  System.out.println("invalid selection");
        		  break;
        	}
        	
        	System.out.println("Return to Menu (y/n)");
        	continueStr = inputStr.nextLine();
        }
	}
}
