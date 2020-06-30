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
		if (!user.equals("username") || !pass.equals("password")) {
			System.out.println("oops wrong username password; end");
			System.exit(0);
		}
		else if (user.equals("username") && pass.equals("password")) {
			System.out.println("logged in successfully");
			
		}
	}
	
	public void displayMenu() {
		writeToConsoleAndFile("----------------------------\n(1) Display Tenant List \n"
						+ "(2) Display Rent Record \n"
						+ "(3) Display Expense Record \n"
						+ "(4) Display Annual Summary \n"
						+ "(5) Add New Tenant \n"
						+ "(6) Add Rental Payment \n"
						+ "(7) Add Expense Payment \n"
						+ "----------------------------\n  ", System.out, inputPW);
		
	}
	
	
	public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.println(string);
		pw2.println(string);
	}

	public void selectOption(int insertNum) {
		switch (insertNum) {
			case 1:
				writeToConsoleAndFile("\nyou chose to display tenant list \n", System.out, inputPW);
				break;
			case 2:
				writeToConsoleAndFile("\nyou chose to display rent records\" \n", System.out, inputPW);
				break;
			case 3:
				writeToConsoleAndFile("\nyou chose to display expense records \n", System.out, inputPW);
				break;
			case 4:
				writeToConsoleAndFile("\nyou chose to display annual summary \n", System.out, inputPW);
				break;
			case 5:
				writeToConsoleAndFile("\nyou chose to add new tenant \n", System.out, inputPW);
				break;
			case 6:
				writeToConsoleAndFile("\nyou chose to add new rent paymen \n", System.out, inputPW);
				break;
			case 7:
				writeToConsoleAndFile("\nyou chose to add new expense payment \n", System.out, inputPW);
				break;
		}
	}
	
	public void specifySecondInputs(int insertNum) {
		switch (insertNum) {
			case 5:
				writeToConsoleAndFile("\nEnter a name and room number \n", System.out, inputPW);
				break;
			case 6:
				writeToConsoleAndFile("\nEnter a name, room, month, amt (in that order) \n", System.out, inputPW);
				break;
			case 7:
				writeToConsoleAndFile("\nEnter a month, date, payee, amt, category (in that order) \n", System.out, inputPW);
				break;
		}
	}
	
	public void enterTenantInfo(String insertName, int insertRoom, tenantRecord insertTList) {
		//check if room is empty and exists
		if (insertTList.verifyRoom(insertRoom)) {
			insertTList.addTenant(new Tenant(insertName, insertRoom));
			writeToConsoleAndFile("\nnew tenant added successfully\n", System.out, inputPW);
		}
	}
	
	public void enterRentPaymentInfo(String insertName, int insertRoom, int insertMonth, int insertAmt, rentRecord insertRList, tenantRecord insertTList) {
		if (insertMonth > 12 || insertMonth < 1) {
			writeToConsoleAndFile("\nInvalid month\n", System.out, inputPW);
		}
		else if( insertTList.verifyTenantAndRoom(insertName, insertRoom) ) {
			for (int i = 0; i < insertRList.getSize(); i++) {
				if (insertRList.rentData.get(i).rentArray[0] == insertRoom) {
					insertRList.recieveInfo(i, insertMonth, insertAmt);
					
					writeToConsoleAndFile("\nrent payment added successfully\n", System.out, inputPW);
				}
			}
		}
		
	}
	
	public void enterExpensePaymentInfo(int insertMonth, int insertDate, String insertPayee, int insertAmt, String insertCat, expenseRecord insertEList) {
		insertEList.addExpense(new expensePayment(  insertMonth,  insertDate,  insertPayee,  insertAmt,  insertCat     ));
		writeToConsoleAndFile("\nexpense payment added successfully\n", System.out, inputPW);
	}
}


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
    
    
    // for unit test
    tenantRecord () {
		
	}
    
    tenantRecord (PrintWriter insertPW, File outputFile) {
		this.tenantRecordPW = insertPW;
		this.file = outputFile;
	}
      
    public void addTenant(Tenant insertTenant) {
    	tenantS.add(insertTenant);
    }
    
    public List<Tenant> getTenantList(){ 
       return tenantS;   
    } 
    
    public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.println(string);
		pw2.println(string);
	}
    
    public void displayAllTenantInfo() {
    	writeToConsoleAndFile("\nTenant Record display\n", System.out, tenantRecordPW  );
    	for (int i = 0; i < tenantS.size() ; i++) {
    		writeToConsoleAndFile(tenantS.get(i).getTenantRoom()  + " - " + tenantS.get(i).getName(), System.out, tenantRecordPW  );
    	}
    	System.out.println();
    }
    
    public boolean verifyRoom(int insertNum) {
    	if (insertNum < 101 || insertNum > 120) {
    		writeToConsoleAndFile("\nroom doesnt exist\n"   , System.out, tenantRecordPW  );
    		return false;
    	}
    	else 
    		for (int i = 0; i < tenantS.size() ; i++) {
    			if (tenantS.get(i).getTenantRoom() == insertNum) {
    				writeToConsoleAndFile("\nroom occupied\n"   , System.out, tenantRecordPW  );
    				return false;
    			}
    		}
    	return true;
    	
    }
    
    
    public boolean verifyTenantAndRoom(String insertName, int insertRoom) {
    	for (int i = 0; i < tenantS.size(); i++) {
    		if(tenantS.get(i).getName().equals(insertName) && tenantS.get(i).getTenantRoom() == insertRoom) {
    			writeToConsoleAndFile("\nRoom-Tenant found... returning true\n"   , System.out, tenantRecordPW  );
    			return true;
    		}
    	}
    	
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
    
    public ArrayList<rentRow> getRentData(){
    	return rentData;
    }
    
    public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.print(string);
		pw2.print(string);
	}
    
    public void addRentRow(rentRow insertRow) {
    	// verify funct for tenant and room//      if(verfiyRoomAndTenent) {   -->  chaecked at inputScreen enterTenantInfo  name room match-> add at loop for room num
    	rentData.add(insertRow);
    }
    
    public void displayRentPayments() {
    	writeToConsoleAndFile("Apt\tJan\tFeb\tMar\tApr\tMay\tJun\tJly\tAug\tSept\tOct\tNov\tDec \n", System.out, rentRecordPW);
    	for (int i = 0; i < rentData.size(); i++) {
    		for (int j = 0; j < 13; j++) {
    			writeToConsoleAndFile( Integer.toString(rentData.get(i).getIntAtIndex(j)) + "\t" , System.out, rentRecordPW);
    		}
    		writeToConsoleAndFile("\n", System.out, rentRecordPW);
    	}
    }
    
    public boolean verifyTenantAndRoom(String insertName, int insertRoom) {
    	for (int i = 0; i < rentData.size(); i++) {
    		if(rentData.get(i).name.equals(insertName) && rentData.get(i).roomNum == insertRoom) {
    			writeToConsoleAndFile("\nRoom-Tenant found... returning true\n" , System.out, rentRecordPW);
    			return true;
    		}
    	}
    	
    	writeToConsoleAndFile("\ninvalid: Tenant or Room doesnt exist\n" , System.out, rentRecordPW);
    	return false;
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
    	writeToConsoleAndFile("Expense Record display \n", System.out, expRecordPW);
    	writeToConsoleAndFile("Date\tPayee\t\tAmt\tCategory \n", System.out, expRecordPW);
    	for (int i = 0; i < expenseRecordList.size() ; i++) {
    		writeToConsoleAndFile(expenseRecordList.get(i).getMonth() + "/" + expenseRecordList.get(i).getDate() + "\t" + expenseRecordList.get(i).getPayee() + "\t\t" + expenseRecordList.get(i).getAmt() + "\t" + expenseRecordList.get(i).getCategory(), System.out, expRecordPW);
    		writeToConsoleAndFile("\n", System.out, expRecordPW);
    	}
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
	PrintWriter annualPW;
	File file;
	
	public rentRecord RentRecords;
	public ArrayList<rentRow> rentRecordList = new ArrayList<rentRow>();
	public expenseRecord Expenses;
	public ArrayList<expensePayment> expenseRecordList = new ArrayList<expensePayment>(); 
	public HashMap<String, Integer> allExpenses = new HashMap<String, Integer>();
	
	public int totalRent;
	public int totalExpense;
	public int balance;
	
	public void writeToConsoleAndFile(String string, PrintStream out, PrintWriter pw2) {
		out.print(string);
		pw2.print(string);
	}
	
	annualReport(PrintWriter insertPW, File outputFile, rentRecord insertrentRecord, expenseRecord insertExpenseRecord){
		this.RentRecords=insertrentRecord;
		this.Expenses=insertExpenseRecord;
		this.expenseRecordList=insertExpenseRecord.getexpenseRecordList();
		this.rentRecordList=insertrentRecord.getRentData();
		this.annualPW = insertPW;
		this.file = outputFile;
	}
	
	public void calculateExpenses(){
		for (int i = 0; i < expenseRecordList.size(); i++) {
			// doesn't contain the key yet
			if (!allExpenses.containsKey(expenseRecordList.get(i).category)) {
				allExpenses.put(expenseRecordList.get(i).category, expenseRecordList.get(i).amt);
				totalExpense += expenseRecordList.get(i).amt;
			}
			// it already exist in key
			else {
				int prevKeyAmt = allExpenses.get(expenseRecordList.get(i).category);
				allExpenses.put(expenseRecordList.get(i).category, expenseRecordList.get(i).amt + prevKeyAmt);
				totalExpense += expenseRecordList.get(i).amt;
			}
		}
	}
	
	
	public void calculateRent(){
        for(rentRow r : rentRecordList){
            for(int i = 1; i < 13; i++){
                int value = r.rentArray[i];
                totalRent += value;
            }
        }
        //System.out.print(totalRent);
        //writeToConsoleAndFile(Integer.toString(totalRent), System.out, annualPW);
    }
	
	
	public void displayCalculatedExpenses(){
		calculateRent();
		writeToConsoleAndFile("---RENT---     total:   " + totalRent + "\n", System.out, annualPW);
		calculateExpenses();
		writeToConsoleAndFile("---EXPENSES--- total:   "  + totalExpense + "\n", System.out, annualPW);
		for (String name: allExpenses.keySet()){
            String key = name.toString();
            String value = allExpenses.get(name).toString();  
            writeToConsoleAndFile("  - " + key + " " + value + "\n", System.out, annualPW);
		} 
		balance = totalRent - totalExpense;
		writeToConsoleAndFile("---BALANCE---  total:   "   +  balance + " \n", System.out, annualPW);
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
  	  	
        Scanner inputStr = new Scanner(System.in); 
        Scanner inputInt = new Scanner(System.in); 
        
        
        String user = "username";
        String pass = "password";
        System.out.println("Enter 'username': ");
        String userStr = inputStr.nextLine();
        System.out.println("Enter 'password': ");
        String passStr = inputStr.nextLine();
        
        
        inputScreen menu = new inputScreen(pw, outputFile);
        
        menu.enterUsernamePassword(userStr, passStr);
    
       
        tenantRecord tenantList = new tenantRecord(pw, outputFile);
        rentRecord rentList = new rentRecord(pw, outputFile);
        expenseRecord expenseList = new expenseRecord(pw, outputFile);
        
        /*
        tenantList.addTenant(new Tenant("John Smith", 101 ));
        tenantList.addTenant(new Tenant("Jane Doe", 102) ) ;
        tenantList.addTenant(new Tenant("Sam W"  , 103));
        
        expenseList.addExpense(new expensePayment(1,1,"Gas Comp",500,"Utilities"));
        expenseList.addExpense(new expensePayment(2,2,"ElectricComp",500,"Electricity"));
        */
        
        
        for (int i = 101; i < 121 ; i++) {
        	rentList.addRentRow(new rentRow(i));
        }
        
        annualReport annualSummary = new annualReport(pw, outputFile, rentList, expenseList);
        
        String continueStr = "y";
        int menuSelect = 0;
        
        String strInput = "";
        String strInput2 = "";
        int integerInput = 0;
        int integerInput2 = 0;
        int integerInput3 = 0;
        int integerInput4 = 0;
        int integerInput5 = 0;
        
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
        		  
        		  integerInput = inputInt.nextInt();
        		  integerInput2 = inputInt.nextInt();
        		  strInput = inputStr.nextLine();
        		  integerInput4 = inputInt.nextInt();
        		  strInput2  = inputStr.nextLine();
        		  
        		  menu.enterExpensePaymentInfo(integerInput, integerInput2, strInput, integerInput4, strInput2, expenseList);
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
       
	}
}
