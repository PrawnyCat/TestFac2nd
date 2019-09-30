package project01;

public class Address implements Comparable<Address> {
	
	String name;
	int age;
	String addr;
	String phNum;
	public Address(String name, int age, String addr, String phNum) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.phNum = phNum;
	}
	@Override
	public int compareTo(Address target) {
		return this.name.compareTo(target.name);
		
	}
	String getInfo() {
		return String.format("이름 : %s, 나이 : %d세, 주소 : %s, 연락처 : %s",name,age,addr,phNum);
	}
	void print() {
		System.out.println(getInfo());
	}
	
	
}
