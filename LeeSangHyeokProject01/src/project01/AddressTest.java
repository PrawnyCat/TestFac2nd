package project01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import common.util.CommonUtilities;


public class AddressTest {
	
	Map<Character,List<Address>> address = 
			new HashMap<Character,List<Address>>();
	List<Address> newAddr = null;
	//생성자
	public AddressTest() {
		newAddr = new Vector<Address>();
		loadInfo();
	}
	public void loadInfo() {
		BufferedReader br=null;
		try {
			br=new BufferedReader(
					new FileReader("src/project01/AddressList.txt"));
			String data=br.readLine();
			System.out.println(data);
			String name;
			int age;
			String addr;
			String phNum;
			Character key = data.charAt(1);
			while((data)!=null) {
				System.out.println(key);
				data=br.readLine();
				System.out.println(data);
				while(true) {
					
					if(!data.contains("[")) {
						
						
						System.out.println(data);
						String[] comma = data.split(",");
						System.out.println(comma[0]);
						name = comma[0].split(":")[1].trim();
						System.out.println(CommonUtilities.getFirstChar(name));
						age = Integer.parseInt(
							comma[1].split(":")[1].split("세")[0].trim());
						addr = comma[2].split(":")[1].trim();
						phNum = comma[3].split(":")[1].trim();
						newAddr.add(new Address(name, age, addr, phNum));
						address.put(key,newAddr);
						System.out.println(String.format("%s %s", key,CommonUtilities.getFirstChar(name)));
						data=br.readLine();
					}
					else { 
						newAddr = new Vector<Address>();
						key=data.charAt(1);
						System.out.println(key);
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
			}
			catch(Exception e) {}
		}
	}
	
	
	public void printInfo() {
		System.out.println();
		if(!address.isEmpty()) {
			Set<Character> keys = address.keySet();
			for(Character key:keys) {
				System.out.println("["+key+"으로 시작하는 명단]");
				List<Address> values = address.get(key);
				Collections.sort(values);
				for(Address value : values) value.print();
			}
		}
		else System.out.println("주소록에 저장된 정보가 없습니다.");
	}
	
	
	
}
