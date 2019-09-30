package project01;


public class AddressApp {

	public static void main(String[] args) {
		
		AddressLogic logic = new AddressLogic();
		
		while(true) {
			
			logic.printMainMenu();
			
			int mainMenu = logic.getMenuNum();
			
			logic.sepMenuNum(mainMenu);
			
		}
	}

}
