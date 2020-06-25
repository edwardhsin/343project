import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// write to file
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;

class inputScreen{
	public void enterUsernamePassword(String user, String pass) {
		if (!user.equals("username") && !pass.equals("password")) {
			System.out.println("oops wrong username password; end");
			System.exit(0);
		}
		else if (user.equals("username") && pass.equals("password")) {
			System.out.println("logged in successfully");
			
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
				System.out.println("Enter a name, room, month, amt (in that order)");
				break;
			case 7:
				System.out.println("Enter a month, date, payee, amt, category (in that order)");
				break;
		}
	}
	
	public void enterTenantInfo(String insertName, int insertRoom, tenantRecord insertTList) {
		//check if room is empty and exists
		if (insertTList.verifyRoom(insertRoom)) {
			insertTList.addTenant(new Tenant(insertName, insertRoom));

			System.out.println("new tenant added successfully");
		}
	}
	
	public void enterRentPaymentInfo(String insertName, int insertRoom, int insertMonth, int insertAmt, rentRecord insertRList, tenantRecord insertTList) {
		//insertRList.rentData.get(1).rentArray[insertMonth] = 999;
		
		// check if tenant and room pair exist in tenantList
		if( insertTList.verifyTenantAndRoom(insertName, insertRoom) ) {
			for (int i = 0; i < insertRList.getSize(); i++) {
				if (insertRList.rentData.get(i).rentArray[0] == insertRoom) {
					//insertRList.rentData.get(i).rentArray[insertMonth] = insertAmt;
					// following sequence
					//insertRList.rentData.get(i).makeRentPayment(insertMonth, insertAmt);
					// following sequence
					
					insertRList.recieveInfo(i, insertMonth, insertAmt);
					System.out.println("rent payment added successfully");
				}
			}
		}
		
	}
	
	public void enterExpensePaymentInfo(int insertMonth, int insertDate, String insertPayee, int insertAmt, String insertCat, expenseRecord insertEList) {
		insertEList.addExpense(new expensePayment(  insertMonth,  insertDate,  insertPayee,  insertAmt,  insertCat     ));
		System.out.println("expense payment added successfully");
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
    
    /*  this is func in inputScreen
    public int enterTenantInfo(String insertName, int insertRoom) {
    	return insertRoom;
    }
    */
    
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
    
    
    public boolean verifyTenantAndRoom(String insertName, int insertRoom) {
    	for (int i = 0; i < tenantS.size(); i++) {
    		if(tenantS.get(i).getName().equals(insertName) && tenantS.get(i).getTenantRoom() == insertRoom) {
    			System.out.println("Room-Tenant found... returning true");
    			return true;
    		}
    	}
    	
    	System.out.println("invalid: Tenant or Room doesnt exist");
    	return false;
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

class rentRecord  
{ 
    ArrayList<rentRow> rentData = new ArrayList<rentRow>();
    
    // in main should be like    rList.addRentPayment( new rentPayment(name, roomNum, month, amt )
    public void addRentRow(rentRow insertRow) {
    	// verify funct for tenant and room//      if(verfiyRoomAndTenent) {
    	rentData.add(insertRow);
    }
    
    
    // room --- jan feb mar apr
    // 101      300 400 --- 900
    
    // list all rooms or only occupied rooms?
    
    public void displayRentPayments() {
    	System.out.println("Apt\tJan\tFeb\tMar\tApr\tMay\tJun\tJly\tAug\tSept\tOct\tNov\tDec");
   
    	for (int i = 0; i < rentData.size(); i++) {
    		rentData.get(i).printRentRow();
    	}
    }
    
    public boolean verifyTenantAndRoom(String insertName, int insertRoom) {
    	for (int i = 0; i < rentData.size(); i++) {
    		if(rentData.get(i).name.equals(insertName) && rentData.get(i).roomNum == insertRoom) {
    			System.out.println("Room-Tenant found... returning true");
    			return true;
    		}
    	}
    	
    	System.out.println("invalid: Tenant or Room doesnt exist");
    	return false;
    }
    
    public int getIndexOfRow(String insertName, int insertRoom) {
    	for (int i = 0; i < rentData.size(); i++) {
    		if(rentData.get(i).name.equals(insertName) && rentData.get(i).roomNum == insertRoom) {
    			System.out.println("Room-Tenant found... returning true");
    			return i;
    		}
    	}
    	
    	System.out.println("invalid: Tenant or Room doesnt exist");
    	return 0 ; /// will change index[[0] room num
    }
    
    public int getSize() {
    	return rentData.size();
    }
    
    public void recieveInfo(int index, int month, int room) {
    	rentData.get(index).makeRentPayment(month, room);
    }
   
}

class rentRow{
	public String name;
	public int roomNum;
    //public int month;
    //public int amt = 0;
    
    public int[] rentArray = new int[13]; // int elemnets defaults to 0
      
    rentRow(int insertRoom) 
    {
        this.rentArray[0] = insertRoom;
    } 
    
    public void makeRentPayment(int insertMonth, int insertAmt) {
    	// index[0] is room num ,  index[1 - 12] shall be jan - dec
    	rentArray[insertMonth] = insertAmt;
    }
    
    public void printRentRow() {
    	for (int i = 0; i < rentArray.length; i++) {
    		System.out.print(rentArray[i] + "\t");
    	}
    	System.out.println();
    }
    
}

class expenseRecord{
	ArrayList<expensePayment> expenseRecordList = new ArrayList<expensePayment>(); 
	
	expenseRecord(){
		 
	}
	
	public void addExpense(expensePayment insertExpensePayment) {
		expenseRecordList.add(insertExpensePayment);
	}
	
	public List<expensePayment> getExpenseRecordList (){
		return expenseRecordList;
	}
	
    public void displayAllExpense() {
    	System.out.println("Expense Record display");
    	System.out.println("Date\tPayee\t\tAmt\tCategory");
    	for (int i = 0; i < expenseRecordList.size() ; i++) {
    		System.out.println(  expenseRecordList.get(i).getMonth() + "/" + expenseRecordList.get(i).getDate() + "\t" + expenseRecordList.get(i).getPayee() + "\t" + expenseRecordList.get(i).getAmt() + "\t" + expenseRecordList.get(i).getCategory());
    	}
    	System.out.println();
    }
}

class expensePayment{
	public int month;
	public int date;
	public String category;
	public String payee;
	public int amt;
	
	expensePayment(int insertMonth, int insertDate, String insertPayee, int insertAmt, String insertCategory) {
		this.month = insertMonth;
		this.date = insertDate;
		this.category = insertCategory;
		this.payee = insertPayee;
		this.amt = insertAmt;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDate() {
		return date;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getPayee() {
		return payee;
	}
	
	public int getAmt() {
		return amt;
	}
}
public class Program {
	
	public static File createFile() {
		try {
		      File fileObject = new File("343group3_Output.txt");
		      if (fileObject.createNewFile()) {
		        System.out.println("File created: " + fileObject.getName());
		        return fileObject;
		      } else {
		        System.out.println("File already exists.");
		        return fileObject;
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return null;
	 }
		
		
	
	
	public static void main(String[] args) throws IOException {
        System.out.println("--- main ---");
        
        File outputFile = createFile();
        System.out.println(outputFile.getPath());
        
        FileWriter fw = new FileWriter(outputFile,true);
  	  	PrintWriter pw = new PrintWriter(fw);
  	  	
	  	 pw.println("343 testing new output, once more");
		  pw.close();
        
        Scanner inputStr = new Scanner(System.in); 
        Scanner inputInt = new Scanner(System.in); 
        
        /*
        String user = "username";
        String pass = "password";
        System.out.println("Enter username: ");
        String userStr = inputStr.nextLine();
        System.out.println("Enter Password: ");
        String passStr = inputStr.nextLine();
        */
        
        inputScreen menu = new inputScreen();
        //menu.enterUsernamePassword(user, pass);
    
        tenantRecord tenantList = new tenantRecord();
        rentRecord rentList = new rentRecord();
        expenseRecord expenseList = new expenseRecord();
        
        tenantList.addTenant(new Tenant("John Smith", 101 ));
        tenantList.addTenant(new Tenant("Jane Doe", 102) ) ;
        tenantList.addTenant(new Tenant("Sam W"  , 103));
        
        expenseList.addExpense(new expensePayment(1,1,"Gas Comp",500,"Utilities"));
        
        for (int i = 101; i < 121 ; i++) {
        	rentList.addRentRow(new rentRow(i));
        }
    
        String continueStr = "y";
        int menuSelect = 0;
        
        String strInput = "";
        int integerInput = 0;
        int integerInput2 = 0;
        int integerInput3 = 0;
        
        while (continueStr.equalsIgnoreCase("y") ) {
        	menu.displayMenu();
        	menuSelect = inputInt.nextInt();
        	
        	menu.selectOption(menuSelect);
        	
        	switch (menuSelect) {
        	  case 1:
        		  tenantList.displayAllTenantInfo();
        		  break;
        	  case 2:
        		  rentList.displayRentPayments();
        		  break;
        	  case 3:
        		  expenseList.displayAllExpense();
        		  break;
        	  case 4:
        		  
        		  break;
        	  case 5:
        		  tenantList.displayAllTenantInfo();
        		  
        		  menu.specifySecondInputs(menuSelect);
        		  
        		  strInput = inputStr.nextLine();
        		  integerInput = inputInt.nextInt();
        		  
        		  menu.enterTenantInfo(strInput, integerInput, tenantList);
        		  
	        	  break;
        	  case 6:
        		  rentList.displayRentPayments();
        		  
        		  menu.specifySecondInputs(menuSelect);
        		  
        		  strInput = inputStr.nextLine();
        		  integerInput = inputInt.nextInt();
        		  integerInput2 = inputInt.nextInt();
        		  integerInput3 = inputInt.nextInt();
        		  
        		  menu.enterRentPaymentInfo(strInput, integerInput, integerInput2, integerInput3, rentList, tenantList);
        		  
        		  break;
        	  case 7:
        		  expenseList.displayAllExpense();
        		  menu.specifySecondInputs(menuSelect);
        		  
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
