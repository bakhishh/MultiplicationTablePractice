package bakhishProject;

public class Main {

	public static void main(String[] args) {
		MySQL myDb = new MySQL();
		myDb.createTablesAndInsertData();
		Menu menu = new Menu() ;
		
	}

}
