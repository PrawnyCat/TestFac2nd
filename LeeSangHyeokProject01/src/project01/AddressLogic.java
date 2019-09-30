package project01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import common.util.CommonUtilities;

public class AddressLogic {
	Map<Character,List<Address>> address = 
			new TreeMap<Character,List<Address>>();
	List<Address> newAddr = null;

	
	public AddressLogic() {
		newAddr = new Vector<Address>();
		loadInfo();
	}
	
	public void loadInfo() {
		BufferedReader br=null;
		try {
			br=new BufferedReader(
					new FileReader("src/project01/AddressList.txt"));
			String data=br.readLine();
			String name;
			int age;
			String addr;
			String phNum;
			Character key = data.charAt(1);
			while((data)!=null) {
				data=br.readLine();
				while(true) {
					if(!data.contains("[")) {
						String[] comma = data.split(",");
						name = comma[0].split(":")[1].trim();
						age = Integer.parseInt(
							comma[1].split(":")[1].split("세")[0].trim());
						addr = comma[2].split(":")[1].trim();
						phNum = comma[3].split(":")[1].trim();
						newAddr.add(new Address(name, age, addr, phNum));
						address.put(key,newAddr);
						data=br.readLine();
					}
					else { 
						newAddr = new Vector<Address>();
						key=data.charAt(1);
						data=br.readLine();
						continue;
					}
					
				}
				
			}
			
		}
		catch (Exception e){}
		finally {
			try {
				if(br!=null) br.close();
				System.out.println("로딩 완료!");
			}
			catch(Exception e) {}
		}
	}

	public void printMainMenu() {
		System.out.println("=======================메인 메뉴=======================");
		System.out.println("1. 입력  2. 출력  3. 수정  4. 삭제  5. 검색  6. 파일로 저장  9. 종료");
		System.out.println("====================================================");
		System.out.println("실행할 메뉴 번호를 입력하세요.");
	}

	public int getMenuNum() {
		Scanner sc = new Scanner(System.in);
		int menuStr=0;
		while(true) {
			try {
				menuStr = Integer.parseInt(sc.nextLine());
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("메뉴 번호는 숫자로만 입력해주시기 바랍니다.");
				printMainMenu();
				continue;
			}
		}
		return menuStr;
	}

	public void sepMenuNum(int mainMenu) {
		switch (mainMenu) {
		case 1 : 
			inputInfo();
			break;
		case 2 :
			printInfo();
			break;
		case 3 : 
			changeInfo();
			break;
		case 4 : 
			deleteInfo();
			break;
		case 5 : 
			searchInfo();
			break;
		case 6:
			saveInfo();
			break;
		case 9 : 
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
			break;
		default : System.out.println("잘못된 메뉴 번호입니다.");
		
		}
		
	}
	

	

	private void inputInfo() {
		
		Scanner sc = new Scanner(System.in);
		String name = "";
		char firstChar = ' ';
		int age=0;
		while(true) {
			System.out.println("이름을 입력하세요.");
			name = sc.nextLine();
			firstChar = CommonUtilities.getFirstChar(name);
			if(firstChar=='0') {
				System.out.println("한글 이름이 아닙니다.");
				continue;
			}
			else break;
		}
		while(true) {
			System.out.println("나이를 입력하세요.");
			try {
				age = Integer.parseInt(sc.nextLine());
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("나이는 숫자로만 입력해주시기 바랍니다.");
				continue;
			}
		}
		System.out.println("주소를 입력하세요.");
		String addr = sc.nextLine();
		System.out.println("연락처를 입력하세요.");
		String phNum = sc.nextLine();
		if(!address.containsKey(firstChar)) {
			newAddr= new Vector<Address>();
		}
		else {
			newAddr = address.get(firstChar);
		}
		newAddr.add(new Address(name, age, addr, phNum));
		address.put(firstChar,newAddr);
		Collections.sort(newAddr);
	}
	
	private void printInfo() {
		if(!address.isEmpty()) {
			Set<Character> keys = address.keySet();
			for(Character key:keys) {
				System.out.println("["+key+"으로 시작하는 명단]");
				List<Address> values = address.get(key);
				for(Address value : values) value.print();
			}
		}
		else System.out.println("주소록에 저장된 정보가 없습니다.");
	}

	private void searchInfo() {
		
		if(address.isEmpty()) {
			System.out.println("주소록에 저장된 정보가 없습니다.");
			return;
		}
		Address foundInfo = chkName("검색");
		if(foundInfo!=null) {
			System.out.println("[검색 결과]");
			foundInfo.print();
		}
		
	}

	private void deleteInfo() {
		
		if(address.isEmpty()) {
			System.out.println("주소록에 저장된 정보가 없습니다.");
			return;
		}
		Address foundInfo = chkName("삭제");
		Iterator<Character> keys = address.keySet().iterator();
		while(keys.hasNext()){
			Character key = keys.next();
			List<Address> values = address.get(key);
			Iterator<Address> adIt = values.iterator();
			while(adIt.hasNext()) {
				Address delVal = adIt.next();
				if(delVal.equals(foundInfo)) {
					System.out.printf("%s의 정보가 삭제되었습니다.%n",delVal.name);
					adIt.remove();
					break;
				}
			}
			if(values.isEmpty()) {
				address.remove(key);
				break;
			}
			
		}
	}
	
	private void changeMenu() {
		System.out.println("=======================수정 메뉴=======================");
		System.out.println("1. 일괄 수정  2. 개별 항목별 수정  3. 메인 메뉴로 돌아가기");
		System.out.println("====================================================");
		System.out.println("실행할 메뉴 번호를 입력하세요.");
	}
	private void changeSubMenu() {
		System.out.println("=======================수정 메뉴=======================");
		System.out.println("1. 이름  2. 나이  3. 주소  4. 연락처  5. 메인 메뉴로 돌아가기");
		System.out.println("====================================================");
		System.out.println("실행할 메뉴 번호를 입력하세요.");
	}
	
	private void changeInfo() {
		
		if(address.isEmpty()) {
			System.out.println("저장된 정보가 없습니다.");
			return;
		}
		Scanner sc = new Scanner(System.in);
		changeMenu();
		int chMnNum;
		while(true) {
			try {
				chMnNum = Integer.parseInt(sc.nextLine());
				if(chMnNum<1||chMnNum>3) {
					System.out.println("잘못된 메뉴 번호입니다.");
					changeMenu();
					continue;
				}
				else break;
			}
			catch(NumberFormatException e) {
				System.out.println("메뉴 번호는 숫자로만 입력해주시기 바랍니다.");
				changeMenu();
				continue;
			}
		}
		
			switch(chMnNum) {
			 case 1 : 
				 Address foundInfo = chkName("일괄 수정");
				 if(foundInfo!=null) {
					System.out.println("수정할 나이를 입력하세요.");
					int inputAge;
					while(true) {
						try {
							inputAge = Integer.parseInt(sc.nextLine());
							break;
						}
						catch(NumberFormatException e) {
							System.out.println("나이는 숫자로만 입력해주시기 바랍니다.");
							continue;
						}
					}
					foundInfo.age=inputAge;
					System.out.println("수정할 주소를 입력하세요.");
					foundInfo.addr=sc.nextLine();
					System.out.println("수정할 연락처를 입력하세요.");
					foundInfo.phNum=sc.nextLine();
					System.out.println(String.format("[%s의 정보가 수정되었습니다.]",foundInfo.name));
					System.out.print("수정 후 정보 >>>> ");
					foundInfo.print();
				}
				break;
			 case 2 : 
				 foundInfo = chkName("개별 항목별 수정");
				 if(foundInfo!=null) {
					 changeSubMenu();
					 int chSbNum;
						while(true) {
							try {
								chSbNum = Integer.parseInt(sc.nextLine());
								if(chSbNum<1||chSbNum>5) {
									System.out.println("잘못된 메뉴 번호입니다.");
									changeMenu();
									continue;
								}
								else break;
							}
							catch(NumberFormatException e) {
								System.out.println("메뉴 번호는 숫자로만 입력해주시기 바랍니다.");
								changeMenu();
								continue;
							}
						}
						switch(chSbNum) {
							case 1 : 
								 System.out.println("수정할 이름을 입력하세요.");
								 foundInfo.name=sc.nextLine();
							 break;
							case 2 :
								System.out.println("수정할 나이를 입력하세요.");
								int inputAge;
								while(true) {
									try {
										inputAge = Integer.parseInt(sc.nextLine());
										break;
									}
									catch(NumberFormatException e) {
										System.out.println("나이는 숫자로만 입력해주시기 바랍니다.");
										continue;
									}
								}
								foundInfo.age=inputAge;
							 break;
							case 3 :
								 System.out.println("수정할 주소를 입력하세요.");
								 foundInfo.addr=sc.nextLine();
							 break;
							case 4 :
								 System.out.println("수정할 연락처를 입력하세요.");
								 foundInfo.phNum=sc.nextLine();
							 break;
							default : return;
							
						}
				 
					System.out.println(String.format("[%s의 정보가 수정되었습니다.]",foundInfo.name));
					System.out.print("수정 후 정보 >>>> ");
					foundInfo.print();
				 }
				 break;
			 default:return;
					 
			
			}
		
		
	}
	
	
	private Address chkName(String title) {
		
		System.out.println(title+"할 인물의 이름을 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String findName = sc.nextLine();
		char findKey = CommonUtilities.getFirstChar(findName);
		if(address.containsKey(findKey)) {
			List<Address> values =address.get(findKey);
			for(Address value:values) {
				if(value.name.equals(findName)) {
					return value;
				}
				
			}
			System.out.println("주소록에 해당 이름이 존재하지 않습니다.");
		}
		
		return null;
	}	

	private void saveInfo() {
		if(address.isEmpty()) {
			System.out.println("파일로 저장할 정보가 없습니다.");
			return;
		}
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(
					new FileWriter("src/project01/AddressList.txt"));
			Set<Character> keys = address.keySet();
			for(Character key:keys) {
				List<Address> values = address.get(key);
				pw.println(String.format("[%s으로 시작하는 명단]",key));
				for(Address value : values) 
					pw.println(value.getInfo());
			}
			System.out.println("파일 저장 완료.");
		} catch(Exception e) {
			System.out.println("파일 저장 중 오류가 발생했습니다. : "+e.getMessage());
		}
		finally {
			if(pw!=null) pw.close();
		}
	}
	
}
