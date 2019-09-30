package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtilities {
	//입력값이 숫자인지 판단하는 메소드
	public static boolean isNumber(String value) {		
		for(int i=0;i< value.length();i++) {
			if(!(value.codePointAt(i)>='0' && value.codePointAt(i)<='9'))
				return false;
		}
		return true;
	}
	//두 날짜 차이를 반환하는 메소드
	//반환타입 : long
	//매개변수 : String타입의 두 날자, 날짜패턴, 구분자(단위)
	public static long getDateDiff(
			String strFDate,
			String strSDate,
			String pattern,
			char delim
			) throws ParseException {
		//1. 매개변수에 전달된 pattern으로 SimpleDateFromat객체 생성
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		//2. String -> Date.parse()
		Date fDate=dateFormat.parse(strFDate);
		Date sDate=dateFormat.parse(strSDate);
		//3. 시간차 구하기 : getTime()
		long fTime=fDate.getTime();
		long sTime=sDate.getTime();
		long diff=Math.abs(fTime-sTime);
		//4. 매개변수 delim에 따른 날짜 차이 반환
		switch(Character.toUpperCase(delim)) {
			case 'D' : return diff/(1000*60*60*24);
			case 'H' : return diff/(1000*60*60);
			case 'M' : return diff/(1000*60);
			default : return diff/(1000);
			
		}
		
	}
	//이름에서 초성을 구해 초성을 반환하는 메소드
	
	public static char getFirstChar(String name) {
		char[] lastName = name.trim().toCharArray();
		//방법 1.
		/*
		char[] startChar = {'가','나','다','라','마','바','사','아','자','차'
				,'카','타','파','하'};
		char[] endChar= {'낗','닣','띻','맇','밓','삫','앃','잏','찧','칳',
				'킿','팋','핗','힣'};
		char[] returnChar = {'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ'
				,'ㅋ','ㅌ','ㅍ','ㅎ'};
		for(int i=0;i<startChar.length;i++) {
			if(lastName[0]>=startChar[i]&&lastName[0]<=endChar[i]) 
				return returnChar[i];
		}
		*/
		//방법 2.
		/*
		if(lastName[0]>='가'&&lastName[0]<'나') return 'ㄱ';
		else if(lastName[0]>='나'&&lastName[0]<'다') return 'ㄴ';
		else if(lastName[0]>='다'&&lastName[0]<'라') return 'ㄷ';
		else if(lastName[0]>='라'&&lastName[0]<'마') return 'ㄹ';
		else if(lastName[0]>='마'&&lastName[0]<'바') return 'ㅁ';
		else if(lastName[0]>='바'&&lastName[0]<'사') return 'ㅂ';
		else if(lastName[0]>='사'&&lastName[0]<'아') return 'ㅅ';
		else if(lastName[0]>='아'&&lastName[0]<'자') return 'ㅇ';
		else if(lastName[0]>='자'&&lastName[0]<'차') return 'ㅈ';
		else if(lastName[0]>='차'&&lastName[0]<'카') return 'ㅊ';
		else if(lastName[0]>='카'&&lastName[0]<'타') return 'ㅋ';
		else if(lastName[0]>='타'&&lastName[0]<'파') return 'ㅌ';
		else if(lastName[0]>='파'&&lastName[0]<'하') return 'ㅍ';
		else if(lastName[0]>='하'&&lastName[0]<='힣') return 'ㅎ';
		*/
		//방법 1+2.
		char[] startChar = {'가','나','다','라','마','바','사','아','자','차'
				,'카','타','파','하'};
		char[] returnChar = {'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ'
				,'ㅋ','ㅌ','ㅍ','ㅎ'};
		for(int i=0;i<startChar.length-1;i++) {
			if(lastName[0]>=startChar[i]&&lastName[0]<startChar[i+1]) 
				return returnChar[i];
			else if(lastName[0]>='하'&&lastName[0]<'힣')
				return 'ㅎ';
		}
		
		
		//초성이 한글이 아닌 경우
		return '0';
	
		
		
	}
	
	
}
