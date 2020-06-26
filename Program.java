import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// write to file
import java.io.File;  // Import the File class
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintStream;
import java.io.PrintWriter;

class inputScreen{
	PrintWriter inputPW;
	File file;
	
	inputScreen(PrintWriter insertPW, File outputFile) {
		this.inputPW = insertPW;
		this.file = outputFile;
	}
	
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
		/*
		System.out.println("(1) Display Tenant List \n"
						+ "(2) Display Rent Record \n"
						+ "(3) Display Expense Record \n"
						+ "(4) Display Annual Summary \n"
						+ "(5) Add New Tenant \n"
						+ "(6) Add Rental Payment \n"
						+ "(7) Add Expense Payment  ");
		*/
		
		
		writeToConsoleAndFile("from menu: " + file.getPath() + " \n(1) Display Tenant List \n"
						+ "(2) Display Rent Record \n"
						+ "(3) Display Expense Record \n"
						+ "(4) Display Annual Summary \n"
						+ "(5) Add New Tenant \n"
						+ "(6) Add Rental Payment \n"
						+ "(7) Add Expense Payment  ", System.out, inputPW);
		
	}
	
	/*
	public void endProcessPW(){
		inputPW.close();
	}
	*/
	
	public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.println(string);
		pw2.println(string);
	}

	public void selectOption(int insertNum) {
		switch (insertNum) {
			case 1:
				//System.out.println("you chose to display tenant list1");
				writeToConsoleAndFile("\nyou chose to display tenant list \n", System.out, inputPW);
				break;
			case 2:
				//System.out.println("you chose to display rent records");
				writeToConsoleAndFile("\nyou chose to display rent records\" \n", System.out, inputPW);
				break;
			case 3:
				//System.out.println("you chose to display expense records");
				writeToConsoleAndFile("\nyou chose to display expense records \n", System.out, inputPW);
				break;
			case 4:
				//System.out.println("you chose to display annual summary...");
				writeToConsoleAndFile("\nyou chose to display annual summary \n", System.out, inputPW);
				break;
			case 5:
				//System.out.println("you chose to add new tenant");
				writeToConsoleAndFile("\nyou chose to add new tenant \n", System.out, inputPW);
				break;
			case 6:
				//System.out.println("you chose to add new rent payment");
				writeToConsoleAndFile("\nyou chose to add new rent paymen \n", System.out, inputPW);
				break;
			case 7:
				//System.out.println("you chose to add new expense payment");
				writeToConsoleAndFile("\nyou chose to add new expense payment \n", System.out, inputPW);
				break;
		}
	}
	
	public void specifySecondInputs(int insertNum) {
		switch (insertNum) {
			case 5:
				//System.out.println("Enter a name and room number");
				writeToConsoleAndFile("\nEnter a name and room number \n", System.out, inputPW);
				break;
			case 6:
				//System.out.println("Enter a name, room, month, amt (in that order)");
				writeToConsoleAndFile("\nEnter a name, room, month, amt (in that order) \n", System.out, inputPW);
				break;
			case 7:
				//System.out.println("Enter a month, date, payee, amt, category (in that order)");
				writeToConsoleAndFile("\nEnter a month, date, payee, amt, category (in that order) \n", System.out, inputPW);
				break;
		}
	}
	
	public void enterTenantInfo(String insertName, int insertRoom, tenantRecord insertTList) {
		//check if room is empty and exists
		if (insertTList.verifyRoom(insertRoom)) {
			insertTList.addTenant(new Tenant(insertName, insertRoom));

			//System.out.println("new tenant added successfully");
			writeToConsoleAndFile("\nnew tenant added successfully\n", System.out, inputPW);
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
					//System.out.println("rent payment added successfully");
					writeToConsoleAndFile("\nrent payment added successfully\n", System.out, inputPW);
				}
			}
		}
		
	}
	
	public void enterExpensePaymentInfo(int insertMonth, int insertDate, String insertPayee, int insertAmt, String insertCat, expenseRecord insertEList) {
		insertEList.addExpense(new expensePayment(  insertMonth,  insertDate,  insertPayee,  insertAmt,  insertCat     ));
		//System.out.println("expense payment added successfully");
		writeToConsoleAndFile("\nexpense payment added successfully\n", System.out, inputPW);
	}
}

// geeks for geeks  composition library-book example
class tenantRecord  
{ 
    ArrayList<Tenant> tenantS = new ArrayList<Tenant>(); 
      
    PrintWriter tenantRecordPW;
    File file;
    
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
    
    tenantRecord (PrintWriter insertPW, File outputFile) {
		this.tenantRecordPW = insertPW;
		this.file = outputFile;
	}
      
    public void addTenant(Tenant insertTenant) {
    	tenantS.add(insertTenant);
    }
    
    public List<Tenant> returnList(){ 
       return tenantS;   
    } 
    
    public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.println(string);
		pw2.println(string);
	}
    
    public void displayAllTenantInfo() {
    	System.out.println("\nTenant Record display");
    	for (int i = 0; i < tenantS.size() ; i++) {
    		//System.out.println(  tenantS.get(i).getTenantRoom()  + " - " + tenantS.get(i).getName()   );
    		writeToConsoleAndFile(tenantS.get(i).getTenantRoom()  + " - " + tenantS.get(i).getName(), System.out, tenantRecordPW  );
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
    		//System.out.println("room doesnt exist");
    		writeToConsoleAndFile("\nroom doesnt exist\n"   , System.out, tenantRecordPW  );
    		return false;
    	}
    	else 
    		for (int i = 0; i < tenantS.size() ; i++) {
    			if (tenantS.get(i).getTenantRoom() == insertNum) {
    				//System.out.println("room occupied");
    				writeToConsoleAndFile("\nroom occupied\n"   , System.out, tenantRecordPW  );
    				return false;
    			}
    		}
    	return true;
    	
    }
    
    
    public boolean verifyTenantAndRoom(String insertName, int insertRoom) {
    	for (int i = 0; i < tenantS.size(); i++) {
    		if(tenantS.get(i).getName().equals(insertName) && tenantS.get(i).getTenantRoom() == insertRoom) {
    			//System.out.println("Room-Tenant found... returning true");
    			writeToConsoleAndFile("\nRoom-Tenant found... returning true\n"   , System.out, tenantRecordPW  );
    			return true;
    		}
    	}
    	
    	//System.out.println("invalid: Tenant or Room doesnt exist");
    	writeToConsoleAndFile("\ninvalid: Tenant or Room doesnt exist\n"   , System.out, tenantRecordPW  );
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
    
    PrintWriter rentRecordPW;
    File file;
    
    rentRecord(PrintWriter insertPW, File outputFile){
    	this.rentRecordPW = insertPW;
    	this.file = outputFile;
    }
    
    public ArrayList<rentRow> getrentData(){
    	return rentData;
    }
    
    public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.print(string);
		pw2.print(string);
	}
    
    // in main should be like    rList.addRentPayment( new rentPayment(name, roomNum, month, amt )
    public void addRentRow(rentRow insertRow) {
    	// verify funct for tenant and room//      if(verfiyRoomAndTenent) {
    	rentData.add(insertRow);
    }
    
    
    // room --- jan feb mar apr
    // 101      300 400 --- 900
    
    // list all rooms or only occupied rooms?
    
    public void displayRentPayments() {
    	//System.out.println("Apt\tJan\tFeb\tMar\tApr\tMay\tJun\tJly\tAug\tSept\tOct\tNov\tDec");
    	writeToConsoleAndFile("Apt\tJan\tFeb\tMar\tApr\tMay\tJun\tJly\tAug\tSept\tOct\tNov\tDec \n", System.out, rentRecordPW);
    	for (int i = 0; i < rentData.size(); i++) {
    		///rentData.get(i).printRentRow();
    		/// printRentRow is a sysoutprint... dont wanna introdue PW, file to rent...
    		for (int j = 0; j < 13; j++) {
    			writeToConsoleAndFile( Integer.toString(rentData.get(i).getIntAtIndex(j)) + "\t" , System.out, rentRecordPW);
    		}
    		writeToConsoleAndFile("\n", System.out, rentRecordPW);
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
    
    public int getIntAtIndex(int insertIndex) {
    	return rentArray[insertIndex];
    }
    
}

class expenseRecord{
	ArrayList<expensePayment> expenseRecordList = new ArrayList<expensePayment>(); 
	PrintWriter expRecordPW;
	File file;
	
	expenseRecord(){
		 
	}
	
	public ArrayList<expensePayment> getexpenseRecordList(){
		return expenseRecordList;
	}
	
	expenseRecord(PrintWriter insertPW, File outputFile){
		 this.expRecordPW = insertPW;
		 this.file = outputFile;
	}
	
	public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.print(string);
		pw2.print(string);
	}
	
	public void addExpense(expensePayment insertExpensePayment) {
		expenseRecordList.add(insertExpensePayment);
	}
	
	public List<expensePayment> getExpenseRecordList (){
		return expenseRecordList;
	}
	
    public void displayAllExpense() {
    	//System.out.println("Expense Record display");
    	writeToConsoleAndFile("Expense Record display \n", System.out, expRecordPW);
    	//System.out.println("Date\tPayee\t\tAmt\tCategory");
    	writeToConsoleAndFile("Date\tPayee\t\tAmt\tCategory \n", System.out, expRecordPW);
    	for (int i = 0; i < expenseRecordList.size() ; i++) {
    		//System.out.println(  expenseRecordList.get(i).getMonth() + "/" + expenseRecordList.get(i).getDate() + "\t" + expenseRecordList.get(i).getPayee() + "\t" + expenseRecordList.get(i).getAmt() + "\t" + expenseRecordList.get(i).getCategory());
    		writeToConsoleAndFile(expenseRecordList.get(i).getMonth() + "/" + expenseRecordList.get(i).getDate() + "\t" + expenseRecordList.get(i).getPayee() + "\t" + expenseRecordList.get(i).getAmt() + "\t" + expenseRecordList.get(i).getCategory(), System.out, expRecordPW);
    		writeToConsoleAndFile("\n", System.out, expRecordPW);
    	}
    	//System.out.println();
    	writeToConsoleAndFile("\n", System.out, expRecordPW);
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

class annualReport {
	public rentRecord RentRecords;
	public ArrayList<rentRow> rentRecordList = new ArrayList<rentRow>();
	public expenseRecord Expenses;
	public ArrayList<expensePayment> expenseRecordList = new ArrayList<expensePayment>(); 
	public HashMap<String, Integer> allExpenses = new HashMap<String, Integer>();
	
	annualReport(rentRecord insertrentRecord, expenseRecord insertExpenseRecord){
		this.RentRecords=insertrentRecord;
		this.Expenses=insertExpenseRecord;
		this.expenseRecordList=insertExpenseRecord.getexpenseRecordList();
		this.rentRecordList=insertrentRecord.getrentData();
	}
	public void calculateExpenses(){
		for (int i = 0; i < expenseRecordList.size(); i++) {
			// doesn't contain the key yet
			if (!allExpenses.containsKey(expenseRecordList.get(i).category)) {
				allExpenses.put(expenseRecordList.get(i).category, expenseRecordList.get(i).amt);
			}
			// it already exist in key
			else {
				int prevKeyAmt = allExpenses.get(expenseRecordList.get(i).category);
				allExpenses.put(expenseRecordList.get(i).category, expenseRecordList.get(i).amt + prevKeyAmt);
			}
		}
	}
	
	public int calculateRent(){
        int total = 0;
        for(rentRow r : rentRecordList){
            for(int i = 1; i < 13; i++){
                int value = r.rentArray[i];
                total += value;
            }
        }
        return total;
    }
	
	public void displayCalculatedExpenses(){
		for (String name: allExpenses.keySet()){
            String key = name.toString();
            String value = allExpenses.get(name).toString();  
            System.out.println(key + " " + value);  
		} 
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
		
	/*	
	public static void writeToConsoleAndFile(String msg, PrintStream console, PrintWriter pw) {        
		console.println(msg);
		pw.println(msg);
		//pw.close();
	}
	*/
	
	public static void main(String[] args) throws IOException {
        System.out.println("--- main ---");
        
        File outputFile = createFile();
        System.out.println(outputFile.getPath());
        
       
        
        
        FileWriter fw = new FileWriter(outputFile,true);
  	  	PrintWriter pw = new PrintWriter(fw);
  	  	
	  	// pw.println("343 testing new output, once more");
		 
		  int x = 11;  
		 // it automatically converts x into a string
         //writeToConsoleAndFile("---Main---" + x  , System.out, pw);
        // pw.close();
         
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
        
        inputScreen menu = new inputScreen(pw, outputFile);
        //menu.enterUsernamePassword(user, pass);
       // pw.close();
    
        //tenantRecord tenantList = new tenantRecord();
        tenantRecord tenantList = new tenantRecord(pw, outputFile);
        //rentRecord rentList = new rentRecord();
        rentRecord rentList = new rentRecord(pw, outputFile);
        //expenseRecord expenseList = new expenseRecord();
        expenseRecord expenseList = new expenseRecord(pw, outputFile);
        
        tenantList.addTenant(new Tenant("John Smith", 101 ));
        tenantList.addTenant(new Tenant("Jane Doe", 102) ) ;
        tenantList.addTenant(new Tenant("Sam W"  , 103));
        
        
        
        expenseList.addExpense(new expensePayment(1,1,"Gas Comp",500,"Utilities"));
        expenseList.addExpense(new expensePayment(2,2,"Electric",500,"Utilities"));
        
        
        
        for (int i = 101; i < 121 ; i++) {
        	rentList.addRentRow(new rentRow(i));
        }
        
        annualReport annualSummary = new annualReport(rentList, expenseList);
        
        String continueStr = "y";
        int menuSelect = 0;
        
        String strInput = "";
        int integerInput = 0;
        int integerInput2 = 0;
        int integerInput3 = 0;
        
        while (continueStr.equalsIgnoreCase("y") ) {
        	menu.displayMenu();
        	// pw.close();
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
        		  System.out.println("Rent income: " + annualSummary.calculateRent());
        		  annualSummary.calculateExpenses();
        		  annualSummary.displayCalculatedExpenses();
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
        	pw.println("\nReturn to Menu (y/n)");
        	continueStr = inputStr.nextLine();
        	pw.println(continueStr + "\n");
        }
        pw.close();
        //menu.endProcessPW();
        /*
        System.out.println("copystream1");
        PrintStream printStream = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(printStream);
        System.out.println("copystream2");
        */
	}
}
