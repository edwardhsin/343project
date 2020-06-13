import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class inputScreen{
	public void displayMenu() {
		System.out.println("(1) Display Tenant List \n"
						+ "(2) Display Rent Record \n"
						+ "(3) Display Expense Record \n"
						+ "(4) Display Annual Summary \n"
						+ "(5) Add New Tenant \n"
						+ "(6) Add Rental Payment \n"
						+ "(7) Add Expense Payment  ");
	}
	
	public void specifySecondInputs(int insertNum) {
		if (insertNum == 1 ) {
			System.out.println("you chose to display tenant list\n");
		}
		else if (insertNum == 2) {
			System.out.println("you chose to display rent records");
		}
		else if (insertNum == 3) {
			System.out.println("you chose to display expense records");
		}
		else if (insertNum == 4) {
			System.out.println("you chose to display annual summary...");
		}
		else if (insertNum == 5 ) {
			System.out.println("you chose to add new tenant");
			System.out.println("Enter a name and room number");
		}
		else if (insertNum == 6 ) {
			System.out.println("you chose to add new rent payment");
			System.out.println("Enter a room, name, amt (in that order)");
		}
		else if (insertNum == 7 ) {
			System.out.println("you chose to add new expense payment");
			System.out.println("Enter a month, date, payee, amt (in that order)");
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
    
    public boolean verifyRoom(int insertNum) {
    	if (insertNum < 101 && insertNum > 120) {
    		return false;
    	}
    	else
    		for (int i = 0; i < tenantS.size() ; i++) {
    			if (tenantS.get(i).getTenantRoom() == insertNum) {
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
        
        inputScreen menu = new inputScreen();
        //menu.displayMenu();
        
        // initilize all the lists first  ->   List<Book> books = new ArrayList<Book>(); 
        
        /*
        List<Tenant> allTenantsExample = new ArrayList<Tenant>();
        
        System.out.println("size: " + allTenantsExample.size());
        allTenantsExample.add(new Tenant("John Smith", 101 ));
        allTenantsExample.add(new Tenant("Jane Doe", 102 ));
        System.out.println("size: " + allTenantsExample.size());
        System.out.println("index 0: " + allTenantsExample.get(0).getName() + " , " + allTenantsExample.get(0).getTenantRoom());
        allTenantsExample.get(0).tenantTest();
        */
       // tenantList TenantRecord = new tenantList(allTenantsExample); ^^^ need 2 lists, inefficient
        
        
        // ***Working sample here
        // add tenant one by one to record
        tenantRecord tenantList = new tenantRecord();
        tenantList.getSize(); // initially 0
        tenantList.addTenant(new Tenant("John Smith", 101 ));
        tenantList.addTenant(new Tenant("Jane Doe", 102 ) );
        
        String name3 = "Sam W";
        int room3 = 103;
        tenantList.addTenant(new Tenant( name3  , room3  ));
        tenantList.getSize(); // now 3
        
        tenantList.displayAllTenantInfo();
        boolean isEmptyAndExists = tenantList.verifyRoom(101);
        System.out.println(isEmptyAndExists);
        
        isEmptyAndExists = tenantList.verifyRoom(115);
        System.out.println(isEmptyAndExists);
        
        System.out.println("\n\n\n");
        
        
        
        // while loop  while(string continue = "y")
        String continueStr = "y";
        int menuSelect = 0;
        
        String strInput = "";
        int integerInput = 0;
        
        while (continueStr.equalsIgnoreCase("y") ) {
        	menu.displayMenu();
        	menuSelect = inputInt.nextInt();
        	System.out.println("you selected choice " + menuSelect);
        	//menu.specifySecondInputs(menuSelect);
        	
        	if (menuSelect == 1) {
        		menu.specifySecondInputs(menuSelect);
        		tenantList.displayAllTenantInfo();
        	}
        	else if (menuSelect == 2) {
        		
        	}
			else if (menuSelect == 3) {
			        		
			}
			else if (menuSelect == 4) {
				/*
				 * annual summary should have function for this
			       sumRent;   floats
			       sumExpense;
			       balance;
			       
			       for (i = 0; i < rentList.size; i++){
			       		sumRent += rentList.get(i).getAmt();
			       }
			       for (i = 0; i < expenseList.size; i++){
			       		sumExpense += rentList.get(i).getAmt();
			       }
			       
					balance = sumRent - sumExpense
			 */
			}
			else if (menuSelect == 5) {
        		tenantList.displayAllTenantInfo();
        		menu.specifySecondInputs(menuSelect);
        		strInput = inputStr.nextLine();
        		integerInput = inputInt.nextInt();
        		
        		if (isEmptyAndExists) {
        			tenantList.addTenant(new Tenant (   strInput  ,   integerInput   ) );
        		}
        	}
			else if (menuSelect == 6) {
				//rentList.displayAllRentPaymenyts
				menu.specifySecondInputs(menuSelect);
			}
			else if (menuSelect == 7) {
				
	        	
			}
			else  {
	        	System.out.println("invalid selection");
			}
        	
        	System.out.println("Return to Menu (y/n)");
        	continueStr = inputStr.nextLine();
        }
	}
}
