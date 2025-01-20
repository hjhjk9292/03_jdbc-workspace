package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {
		
		// Properties 복습
		/*
		 * * Properties 특징
		 * - Map 계열 컬렉션 (key + value 세트로 담는 특징)
		 * - key, value 모두 String(문자열)으로 담기
		 * 	 setProperty(String key, String value)
		 * 	 getProperty(String key) : String(value)
		 * 
		 * - 주로 외부파일(.properties / .xml)로 입출력 할 때 사용
		 * 
		 */
		
		Properties prop = new Properties();
		
		/*
		prop.setProperty("C", "INSERT"); 
		prop.setProperty("R", "SELECT");
		prop.setProperty("U", "UPDATE");
		prop.setProperty("D", "DELETE");
		
		try {
			prop.store(new FileOutputStream("resources/test.properties"), "properties Test"); // surround with multi-catch // prop.store(파일출력용스트림("경로/파일이름"), null);
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "properties Test");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		*/
		
		try {
			prop.load(new FileInputStream("resources/driver.properties")); // prop.load(파일입력스트림 생성(파일이름));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("pasword")); // 존재하지 않는 키값 제시시 null 반환
		
	}
}
