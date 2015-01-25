package pizza;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class CommandLine {

    // 'sqlMngr' is the object which interacts directly with MySQL
	private SQLController sqlMngr = null;
    // 'sc' is needed in order to scan the inputs provided by the user
	private Scanner sc = null;
	//private Statement st = null;
	//private Connection conn = null;
	//Public functions - CommandLine State Functions
	private float TAX = (float) 1.13;
	private int numPeople = 11;
	//calculate location
	private duration calD = new duration();
    /* Function used for initializing an istance of current
     * class
     */
	public boolean startSession() {
		boolean success = true;
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		if (sqlMngr == null) {
			sqlMngr = new SQLController();
		}
		try {
			success = sqlMngr.connect(this.getCredentials());
		} catch (ClassNotFoundException e) {
			success = false;
			System.err.println("Establishing connection triggered an exception!");
			e.printStackTrace();
			sc = null;
			sqlMngr = null;
		}
		return success;
	}
	
    /* Function that acts as destructor of an instance of this class.
     * Performs some housekeeping setting instance's private field
     * to null
     */
	public void endSession() {
		if (sqlMngr != null)
			sqlMngr.disconnect();
		if (sc != null) {
			sc.close();
		}
		sqlMngr = null;
		sc = null;
	}

    /* Function that executes an infinite loop and activates the respective 
     * functionality according to user's choice. At each time it also outputs
     * the menu of core functionalities supported from our application.
     */
	public boolean execute() throws SQLException {
		if (sc != null && sqlMngr != null) {
			System.out.println("");
			System.out.println("***************************");
			System.out.println("******ACCESS GRANTED*******");
			System.out.println("***************************");
			System.out.println("");
			
			String input = "";
			int choice = -1;
			do {
				menu(); //Print Menu
				input = sc.nextLine();
				try {
					choice = Integer.parseInt(input);
					switch (choice) { //Activate the desired functionality
					case 1:
						this.insertCustomer();
						break;
					case 2:
						this.updateCustomer();
						break;
					case 3:
						this.ordermenu();
						break;
					case 4:
						this.quemenu();
						break;
					case 5:
						this.deactivate();
						break;
					case 6:
						this.putOnHold();
						break;
					case 7:
						this.deliverInfo();
						break;
					case 8:
						this.reActivate();
						break;
					case 9:
						//this.fulillOrder();
						break;
					case 10:
						this.doSpecial();
						break;
					case 11:
						this.updatePay();
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {
					input = "-1";
				}
			} while (input.compareTo("0") != 0);
			
			return true;
		} else {
			System.out.println("");
			System.out.println("Connection could not been established! Bye!");
			System.out.println("");
			return false;
		}
	}
	
	private void doSpecial() throws SQLException {
		
		//special's setup
		System.out.println("Hello Boss, welcome back !");
		System.out.println("Enter special's catch name");
		String catName = sc.nextLine();
		System.out.println("Enter the price you will charge for this deal");
		int price = parseInHelper();
		System.out.println("Enter a date u want to start this special:");
		String startDate = checkDate();
		System.out.println("Enter a date u want to end this special:");
		String endDate = checkDate();
		String specialQue = "insert into specials(sName,sPrice,startDate,endDate) " +
				"values('" + catName + "'," + price + ",'" + startDate + "','" +
				endDate + "')";
		sqlMngr.insertOp(specialQue);
		int sid = sqlMngr.getID("SELECT LAST_INSERT_ID()");
		
		//select the special's items
		while(true){
			sqlMngr.selectOp("select * from products where category='pizza'");
			System.out.println("please enter the pizzaID you want");
			int pizzaID = parseInHelper();
			sqlMngr.selectOp("select * from products where category='topping'");
			System.out.println("Please enter the topingID for your first topping ");
			int top1 = parseInHelper();
			sqlMngr.selectOp("select * from products where category='topping'");
			System.out.println("Please enter the toppingID for your second topping.");
			System.out.println("Or enter 0 if you would not like a 2nd topping");
			int top2 = parseInHelper();
			sqlMngr.selectOp("select * from products where category='topping'");
			System.out.println("please enter the toppingID for your third topping");
			System.out.println("Or enter 0 if you would not like a 3rd topping");
			int top3 = parseInHelper();
			System.out.println("please enter the quantity of this pizza in the combo");
			int pizzaQuan = parseInHelper();
			
			sqlMngr.selectOp("select * from products where category='side'");
			System.out.println("please enter the sideID you want");
			int side = parseInHelper();
			System.out.println("please enter the quantity of this side in the combo");
			int sideQuan = parseInHelper();
			
			sqlMngr.selectOp("select * from products where category='drink'");
			System.out.println("please enter the drinkID you want");
			int drink = parseInHelper();
			System.out.println("please enter the quantity of this drink in the combo");
			int drinkQuan = parseInHelper();
			
			String que = "insert into sitems Values(" +sid +","+ pizzaID+","+ 
					top1 +","+ top2+"," + top3 +","+ pizzaQuan +","+ side +
					","+ sideQuan +","+ drink +","+ drinkQuan+ ")";
			sqlMngr.insertOp(que);
			
			System.out.println("enter 0 to exit or 1 to add more items into this combo");
			int exitStat = parseInHelper();
			if(exitStat ==0)
				break;
		}
		
	}

	private static void menu() {
		System.out.println("=========MENU=========");
		System.out.println("0. Exit.");
		System.out.println("1. Customer set up.");
		System.out.println("2. Update customer accout.");
		System.out.println("3. Order menu.");
		System.out.println("4. Que Menu");
		System.out.println("5. Discontinue service.");
		System.out.println("6. Put on Hold");
		System.out.println("===Options for Employees===");
		System.out.println("7. Delivery Info.");
		System.out.println("8. Re-activate a de-activated customer");
		System.out.println("9. Order fulfillment");
		System.out.println("10. Do Specials!!!");
		System.out.println("11. update payment");
		System.out.print("Choose one of the previous options [0-8]: ");
		
	}

	private void quemenu() throws SQLException {
		// TODO Auto-generated method stub
		boolean exit = false;
		while(!exit){
			//System.out.println("Let's bun it out");
			System.out.println("=========QUERYMENU=========");
		    System.out.println("0. Exit.");
		    System.out.println("1. product query.");
		    System.out.println("2. OnSale query.");
		    System.out.println("3. Order query.");
		    System.out.println("4. Balance query");
		    System.out.println("5. Transction.");
		    String input = sc.nextLine();
		    try{
				int choice = Integer.parseInt(input);
				switch (choice) { //Activate the desired functionality
				case 0:
				    exit = true;	
					break;
				case 1:
					int pIntChoice = 0;
					String product="";
					while(pIntChoice <=0){
					  System.out.println("enter 1 for pizza, 2 for topping, 3 for side, 4 for drink.");
					  String pChoice = sc.nextLine();
					  pIntChoice = Integer.parseInt(pChoice);
					  if(pIntChoice == 1){
						  product = "pizza";
					  }else if(pIntChoice == 2){
						  product = "topping";
					  }else if(pIntChoice == 3){
						  product = "side";
				      }else if(pIntChoice == 4){
						 product = "drink";
					  }else{
						 System.out.println("Enter an option between 1 and 4");
						 pIntChoice = -1;
					  }
					}
					String queProd = "select pid, description, price from products where category='"+ product +"';";
					sqlMngr.selectOp(queProd); 
					break;
				case 2:
					System.out.println("Our Specials");
					String query = "select sid, sname,sprice from specials where now()<=endDate" +
							" and now()>= startDate";
					sqlMngr.selectOp(query);
					System.out.println("Items in our special:");
					//System.out.println("Pizzas:");
					String pizzaQuery = "select s.sid, p.description, si.pquantity from products p, sItems si," +
							" specials s where now()<=endDate and now()>=startDate and" +
							" (p.pid=si.pizzaid OR p.pid=si.top1 OR p.pid=si.top2 OR p.pid=si.top3" +
							" OR p.pid=side OR p.pid=drink) and si.sid=s.sid";
					sqlMngr.selectOp(pizzaQuery);
					
					break;
				case 3:
					int cid = login();
					System.out.println("This is your order:");
					String findOrder =("select o.oid, o.deliverTime from orders o where o.cid="
							+cid+";");
					sqlMngr.selectOp(findOrder);
					break;
				case 4:
					int cid1 = login();
					System.out.println("This is your balance:");
					String findBalance =("select c.balance from customers c where c.cid="
							+cid1+"");
					sqlMngr.selectOp(findBalance);
					break;
				case 5:
					int cid2 = login();
					String date = "";
					System.out.println("Please enter the date (YYYY-MM-DD) " +
							"which you would like to see all your transactions from.");
					date = checkDate();
					System.out.println("These are your transactions:");
					String transaction = "select o.oid, o.ordertime, o.total from orders o" +
							 " where o.cid="+cid2+" and o.orderTime>='" + date+"'";
						
					sqlMngr.selectOp(transaction);

					break;
				
				default:
					break;
				}
			} catch (NumberFormatException e) {
				input = "-1";
			}
			
		}
	}


	private void ordermenu() throws SQLException {
		int cid = login();
		System.out.println("Welcome back to pizza order!!!");
		
		//make order and get oid
		 String query = "INSERT INTO orders (cid, orderTime) VALUES("+ cid +",now());";
		 sqlMngr.insertOp(query);
		 int oid = sqlMngr.getID("select LAST_INSERT_ID()");
		 System.out.println(oid);
		 orderHelper(oid, cid);
		
	}

	private void updateCustomer() throws SQLException {
		// TODO Auto-generated method stub
		int rowsAff = 0;
		String updatestr = "update customers set ";
		
		// we made assumption that customer has unique combination of first and last name
		int cid = login();
		System.out.println("Enter field to update");
		System.out.println("1) address");
		System.out.println("2) credit card");
		System.out.println("3) check");
		System.out.println("4) first name");
		System.out.println("5) last name");
		System.out.println("6) password");
		System.out.println("7) exit");
		String input = "";
		int choice = -1;
		input = sc.nextLine();
			try {
				choice = Integer.parseInt(input);
				switch (choice) { //Activate the desired functionality
				case 1:
					System.out.println("Enter new address");
					String newAddress = sc.nextLine();
					updatestr = updatestr + "address='" + newAddress + "' where login='"+cid + "';";
					//remember postal code
					
					break;
				case 2:
					System.out.println("Enter new credit card");
					String newCc = sc.nextLine();
					updatestr = updatestr + "credcard=" + newCc + "' where cid='"+cid + "';";
				case 3:
					System.out.println("Enter new check");
					String newCheck = sc.nextLine();
					updatestr = updatestr + "check=" + newCheck + "' where cid='"+cid + "';";
					break;
				case 4:
					System.out.println("Enter new first name");
					String newFname = sc.nextLine();
					updatestr = updatestr + "fname=" + newFname + "' where cid='"+cid + "';";
					break;
				case 5:
					System.out.println("Enter new last name");
					String newLname = sc.nextLine();
					updatestr = updatestr + "lname=" + newLname + "' where cid='"+cid + "';";
					break;
				case 6:
					System.out.println("Enter new password");
					String newPwd = sc.nextLine();
					updatestr = updatestr + "password=" + newPwd + "' where cid='"+cid + "';";
					break;
				case 7:
					execute();
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				input = "-1";
			}
			System.out.println(updatestr);
			rowsAff = sqlMngr.insertOp(updatestr);
			System.out.println("");
			System.out.println("Rows affected: " + rowsAff);
			System.out.println("");
	}
		

	@SuppressWarnings("null")
	private void insertCustomer() throws SQLException {
		// TODO Auto-generated method stub
		// login, password, fname, lname, age, address, sex, balance, payment,
		int rowsAff = 0;
		int counter = 0;
		String query = "";
		String val;
		System.out.print("enter your login: ");
		String login = sc.nextLine();
		String checklogin = "select * from customers where login='" + login +"';";
		while(sqlMngr.checkExist(checklogin)){
			System.out.println("The login name exists, try another.");
			System.out.print("enter your login: ");
			login = sc.nextLine();
			checklogin = "select * from customers where login='" + login +"';";
		}
		val = "'" + login + "',";
		System.out.print("enter your password: "); 
		String password = sc.nextLine();
		val = val + "'" + password + "',";
		System.out.print("enter your first name: ");
		String fname = sc.nextLine();
		val = val + "'" + fname + "',";
		System.out.print("enter your last name: ");
		String lname = sc.nextLine();
		val = val + "'"+ lname + "',";
		System.out.print("enter your address: ");
		String address = sc.nextLine();
		val = val + "'" + address + "',";
		System.out.print("enter your gender M or F: ");// check it
		String sex = sc.nextLine(); 
		val = val + "'" + sex + "',";
		System.out.print("enter your age: ");
		String age = sc.nextLine();
		val = val + age + ",";
		
		//enter default balance of 0
		val = val + "0, ";
		System.out.println("enter your payment method:");
		
		// change start
		System.out.println("1) cash");
		System.out.println("2) credit card");
		System.out.println("3) check");
		String input = "";
		int choice = -1;
		input = sc.nextLine();
			try {
				choice = Integer.parseInt(input);
				switch (choice) { //Activate the desired functionality
				case 1:
					System.out.println("Enter amount of cash");
					String newCash = sc.nextLine();
					val = val + newCash + ",null, null, 1);";
					break;
				case 2:
					System.out.println("Enter new credit card");
					String newCc = sc.nextLine();
					val = val + "null, " + newCc + ", null, 1);";
					break;
				case 3:
					System.out.println("Enter new check");
					String newCheck = sc.nextLine();
					val = val + "null, null, " + newCheck + ", 1);";
					break;
				case 4:
				
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				input = "-1";
			}
		//String payment = sc.nextLine(); // do case later
		//val = val + "80, 80" + ");";
				
		//change end
		//transform the user input into a valid SQL insert statement
	    // need to change later
		query = "INSERT INTO customers" + " (login, password,fname, lname,address,gender,age,balance, cash, credit, checklimit, active) VALUES(";
		query = query + val;
	    
		
		//query = query.concat("'" + val[counter] + "');");
		System.out.println(query);
		rowsAff = sqlMngr.insertOp(query);
		System.out.println("");
		System.out.println("Rows affected: " + rowsAff);
		System.out.println("");
		
	}
	
    // Called during the initialization of an instance of the current class
    // in order to retrieve from the user the credentials with which our program
    // is going to establish a connection with MySQL
	private String[] getCredentials() {
		String[] cred = new String[3];
		System.out.print("Username: ");
		cred[0] = sc.nextLine();
		System.out.print("Password: ");
		cred[1] = sc.nextLine();
		System.out.print("Database: ");
		cred[2] = sc.nextLine();
		return cred;
	}
	
	private int login() throws SQLException{
		
		System.out.print("enter your login: ");
		String login = sc.nextLine();
		System.out.print("enter your password: ");
		String password = sc.nextLine();
		String checklogin = "select * from customers where login='" + login +"' and " +
	    		"password='" + password + "';";
		int attempts = 1;
		while(!sqlMngr.checkExist(checklogin) && attempts <5){
				System.out.println("The account doesn't exist or wrong password.");
				System.out.print("enter your login: ");
				login = sc.nextLine();
				System.out.print("enter your password: ");
				password = sc.nextLine();
			    checklogin = "select * from customers where login='" + login +"' and " +
			    		"password='" + password + "';";
			    attempts ++;
		}
		if(attempts >= 5){
			System.out.println("Too many invalid login attempts.");
			execute();
			
		}
		int cid;
		cid = sqlMngr.getID("select cid from customers where login='" + login +"' and " +
			    		"password='" + password + "';");
		//System.out.println(cid);
		return cid;
	}
	
	private void orderHelper(int oid, int cid) throws SQLException{

		     System.out.println("Enter field to you want to order today: ");
		     System.out.println("1) pizza");
		     System.out.println("2) side");
		     System.out.println("3) drink");
		     System.out.println("4) confirm order");
		     System.out.println("5) exit");
		     String input = sc.nextLine();
			 try {
				int choice = Integer.parseInt(input);
				switch (choice) { //Activate the desired functionality
				case 1:
					 pizzaOrder(oid,cid);
					 break;
				case 2:
					sideAndDrinkOrder(oid, 2, cid); // 2 for side
					    break;
						
				case 3:
					sideAndDrinkOrder(oid, 3, cid); // 3 for drink
						break;
				case 4:
					confirmOrder(oid, cid);
						break;
				case 5:
					
				default:
					break;
				}
				} catch (NumberFormatException e) {
					input = "-1";
				}
		     execute();
		
		
	}
	
	private void confirmOrder(int oid, int cid) throws SQLException {
		// TODO Auto-generated method stub
		float total =0;
        System.out.println("The following is your order:");
		
		// print ordered pizzas
		System.out.println("Your ordered pizzas:");
		String getPizza = "select po.pizzaID, p.description, po.quantity, po.subtotal " +
				"from products p, pizzaorder po where po.oID=" +
				oid + " and po.pid=p.pid;";
		sqlMngr.selectOp(getPizza);
		
		//print corresponding pizza toppings
		System.out.println("Your pizza with corresponding topping matched by ID");
		String getTop = "select po.pizzaID, p.description " +
				"from products p, pizzaorder po, toppings t"+
				" where po.oID="+oid+" and po.pizzaID=t.pizzaID and t.pid=p.pid;";
		sqlMngr.selectOp(getTop);	
		//print sides & drinks
		System.out.println("Your ordered sides and drinks");
		String getSidesAndDrinks = "select p.description,n.quantity,n.subtotal" +
				" from nonPizza n, products p"+
				" where n.oid="+oid+" and p.pid=n.pid;";
		sqlMngr.selectOp(getSidesAndDrinks);
		
		//ask if customer wants to make the order standing or not
		System.out.println("Do u want to make it a standing order?");
		System.out.println("0: no, 1: daily, 2: weekly, 3: monthly.");
		int stand;
		String updateStand;
		while(true){
			String standing = sc.nextLine();
			stand = Integer.parseInt(standing);
			if(stand==0){
				break;
			}else if(stand == 1){
				updateStand = "UPDATE order SET standing=daily WHERE oid="+oid+";";
				sqlMngr.insertOp(updateStand);
				break;
			}else if(stand == 2){
				updateStand = "UPDATE order SET standing=weekly WHERE oid="+oid+";";
				sqlMngr.insertOp(updateStand);
				break;
			}else if(stand == 3){
				updateStand = "UPDATE order SET standing=monthly WHERE oid="+oid+";";
				sqlMngr.insertOp(updateStand);
				break;
			}else{
			System.out.println("Enter a number between 0 and 3 inclusive.");
	    	}
		}
		//asks if customer wants to deliver or not
		System.out.println("Would you like your order delivered?");
		System.out.println("0: no, 1: yes");
		String yesNo, updateYesNo; 
		int ouiNon;
		while(true){
			yesNo = sc.nextLine();
			ouiNon = Integer.parseInt(yesNo);
			if(ouiNon ==1){
				updateYesNo= "UPDATE orders SET deliverTime=now()"+"WHERE oid="+oid+";";
				break;
			}
			else if(ouiNon ==0){
				updateYesNo= "UPDATE orders SET delivery=0 WHERE oid="+oid+";";
				break;
			}
			System.out.println("Enter 0 for no, 1 for yes");
		}
		//calculate time
		int did = oid % numPeople;
		String getAddress = "select address from customers where cid=" + cid + ";";
		String cusAddress = sqlMngr.getString(getAddress);
		String storeAddress = "select address from delivpeople where did=" + did+";";
		String delAddress = sqlMngr.getString(storeAddress);
		int time = calTime(cusAddress, delAddress);
		System.out.println("your order have to cook 10 mins + " +time + " mins deliver time");
		time += 10;
		
		//computing total
		String getPriceQuery = "select sum(po.subtotal) from pizzaorder po" +
				" where po.oid=" +oid+";";
		float pizzaPrice = sqlMngr.getPrice(getPriceQuery);
		total += pizzaPrice;
		getPriceQuery = "select sum(subtotal) from nonpizza where oid=" +oid+";";
		total += sqlMngr.getPrice(getPriceQuery); 
		total += delivPrice(time-10, did);
		total = total*TAX;
		String updateTotal = "Update orders o SET o.total="+total+", " +
				"o.deliverTime=now() + Interval " + time +" minute WHERE o.oid="+oid+";";
		sqlMngr.insertOp(updateTotal);
	}
	

	private void pizzaOrder(int oid, int cid) throws SQLException{
		   float subtotal=0;
			sqlMngr.selectOp("select pid, description, price " +
		   		"from products where category='pizza';");
		   System.out.println("select your pizza(select the id):");
		   int pid = parseInHelper();
		   
		   String insertPizza = "INSERT pizzaorder (pid, oID) VALUES ("+ pid + ","
				   + oid + ");";
		   sqlMngr.insertOp(insertPizza);
		   String getPriceQuery = "select price from products where pid=" +pid + ";";
		   float pizzaPrice = sqlMngr.getPrice(getPriceQuery);
		   subtotal += pizzaPrice;
		   int pizzaID = sqlMngr.getID("SELECT LAST_INSERT_ID()");
		   System.out.println(pizzaID);
		   int toppings = 0;
		   while(true){
			   System.out.println("How many toppings would u like?");
			   toppings = parseInHelper();
			   
			   // you can have 0 toppings because some people like DIY
			   if(toppings > 3 || toppings <0)
				   System.out.println("Maximum three toppings allowerd");
			   else
				   break;
		   }
		  
		   //System.out.println(oid);
		   	   
		   for(int i = 0; i< toppings; i++){
			   sqlMngr.selectOp("select pid, description, price " +
			   		"from products where category='topping';");
			   System.out.println("select your topping(select the id):");
			   pid = parseInHelper();
			   getPriceQuery = "select price from products where pid=" +pid + ";";
			   float toppingPrice = sqlMngr.getPrice(getPriceQuery);
			   subtotal += toppingPrice;
			   String insertTop = "INSERT toppings (pizzaID, pid, subtotal) VALUES ("+ pizzaID+ ","
					   + pid + "," + toppingPrice + ");" ;
			   sqlMngr.insertOp(insertTop);
		   	}
		   //enter quantity of pizzas
		   System.out.println("Enter quantity of this pizza");
		   
		   int pizzaQuantity = parseInHelper();
		   subtotal = subtotal * pizzaQuantity;
		   
		   String updatePizzaOrder = "UPDATE pizzaorder SET quantity=" + pizzaQuantity +
				   ", subtotal="+subtotal+ " WHERE pizzaID=" + pizzaID + ";";
		   sqlMngr.insertOp(updatePizzaOrder);
		   orderHelper(oid,cid); 
	}
	
	private void sideAndDrinkOrder(int oid, int choice, int cid) throws SQLException{
		String query = "select pid, description, price " +
		   		"from products where category='";
		float subtotal =0;
		String sideOrDrink = "";
		if (choice==2){
			sideOrDrink = "side";
			query += "side';";
		}
		else if (choice==3){
			query += "drink';";
			sideOrDrink = "drink";
		}

		while(true){
			sqlMngr.selectOp(query);
			System.out.println("select your " + sideOrDrink + " (select the ID):");		
			String pid = sc.nextLine();
			System.out.println("enter quantity");
			int strQuantity = parseInHelper();
			int quantity = 0;
			while(quantity<=0){
				if(strQuantity<=0){
					System.out.println("enter quantity(quantity must be greater than 0!)");
					strQuantity = parseInHelper();
				}else{
					quantity = strQuantity;
				}
				
			}
			String getPriceQuery = "select price from products where pid=" +pid + ";";
			float productPrice = sqlMngr.getPrice(getPriceQuery);
			subtotal = productPrice*quantity;
			String insertSideOrDrink = "INSERT nonPizza (oid, pid, quantity,subtotal) VALUES ("+ oid+ ","
					   + pid + "," + quantity + ","+subtotal +");" ;
			sqlMngr.insertOp(insertSideOrDrink);
			System.out.println("Make another order?");
			String yesNo = "lol";
			while(!yesNo.equals("n") && !yesNo.equals("y")){
				System.out.println("Enter 'y' for yes, 'n' for no");
				yesNo = sc.nextLine();
			}
			if(yesNo.equals("n")){
				break;
			}
		
		}
		orderHelper(oid,cid);	
		
	}


private String checkDate(){
	String date ="";
	while(!date.matches("\\d{4}-\\d{2}-\\d{2}")){
        System.out.println("Please enter the date in form of (YYYY-MM-DD) ");
       date = sc.nextLine();
    }
	return date;
  
}

private void deactivate() throws SQLException{
	int cid = login();
	System.out.println("Do you really wish to discontinue our service?");
	System.out.println("Enter 1 to confirm deactivation or any other key to exit.");
	String input = sc.nextLine();
	if(input.equals("1")){
		String deac = "Update customers c SET c.active=0" +
			" WHERE c.cid ="+cid;
		sqlMngr.insertOp(deac);
		System.out.println("Customer successfully deactivated.");
	}
	else
		System.out.println("Customer not deactivated.");
	
}

private void putOnHold() throws SQLException {
	int cid = login();
	System.out.println("Do you really wish to put off your standing order?");
	System.out.println("Enter 1 to put off or any other key to exit.");
	String input = sc.nextLine();
	if(input.equals("1")){
		String offHold = "Update orders SET standing=NULL" +
			" WHERE cid ="+cid;
		sqlMngr.insertOp(offHold);
		System.out.println("Standing orders successfully put on hold.");
	}
	else
		System.out.println("Standing orders not put on hold.");
}

private void updatePay(){
	System.out.println("The following are unconfirmed orders:");
	String unpaid = "SELECT o.oid ,o.cid, o.orderTime FROM orders o" +
			" WHERE o.paid = 0";
	sqlMngr.selectOp(unpaid);
	System.out.println("Enter order number to");
	int oid = parseInHelper();
	String query = "update orders set paid=1 where oid=" + oid + ";";
	sqlMngr.insertOp(query);
	
}

private int parseInHelper(){
	String line = "";
	int val;
	while (true) {
		line = sc.nextLine();
		if(line.equals("")){
			 System.out.println("Please input a number");
			 continue;
		}
	    try {
	        val = Integer.parseInt(line);
	        break;
	    } catch (NumberFormatException e) {
	    	 System.out.println("Input a number");
	    }
	}
	return val;
	
}

private int calTime(String addFrom, String addTo){
	addFrom = addFrom.replaceAll(" ", "+");
	addTo = addTo.replaceAll(" ", "+");
	String time = calD.calculateDuration(addFrom, addTo);
	if(time==null){
		return -1;
	}else{
		return Integer.parseInt(time);
	  }
  }

private float delivPrice(int time, int did) throws SQLException{
	
	//we assume they all drive at 0.7km per min
	float distance = (float) (time*0.7);
	String distType = "";
	if(distance>=1 && distance<4){
		distType = " shortD ";
	} else if (distance>=4 && distance<10){
		distType = " medD ";
	} else if (distance>=10){
		distType = " longD ";
	}
	
	String priceStr = "select"+ distType + "from delivPeople where did="+did+";";
	float price = sqlMngr.getPrice(priceStr);
	return price;
}

private void deliverInfo(){
	String query = "select did, name, shortD, medD, longD from " +
			"delivpeople;";
	sqlMngr.selectOp(query);
		}

private void reActivate(){
    String query = "select * from customers where active=0";
    sqlMngr.selectOp(query);
    System.out.println("Enter the cid of customer who you want to reactivate.");
    int custNum = parseInHelper();
    String updateActive = "UPDATE customers SET active=1 WHERE cid="+custNum+";";
    sqlMngr.insertOp(updateActive);
 }

}
