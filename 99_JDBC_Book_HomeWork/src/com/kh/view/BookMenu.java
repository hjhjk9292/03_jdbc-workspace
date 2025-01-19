package com.kh.view;

import java.util.Scanner;

import com.kh.controller.BookController;

public class BookMenu {
	 private BookController bc = new BookController();
	    private Scanner sc = new Scanner(System.in);

	    public void mainMenu() {
	        System.out.println("\nKH 우리반전용 도서관 관리 페이지입니다.");

	        while (true) {
	            System.out.println("===============================");
	            System.out.println("1. 조회하기");
	            System.out.println("2. 추가하기");
	            System.out.println("3. 책 찾기");
	            System.out.println("4. 전체책 가격 합계 및 평균 조회");
	            System.out.println("5. 프로그램 종료");
	            System.out.println("===============================");

	            System.out.print("메뉴번호를 입력해주세요 : ");
	            int menu = sc.nextInt();
	            sc.nextLine();

	            switch (menu) {
	                case 1: viewBooks(); break;
	                case 2: addBook(); break;
	                case 3: searchBook(); break;
	                case 4: showTotalAndAverage(); break;
	                case 5: System.out.println("프로그램을 종료합니다."); return;
	                default: System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
	            }
	        }
	    }

	    private void viewBooks() {
	        System.out.println("\n어떤 방법으로 책을 조회하겠습니까?");
	        System.out.println("1. 도서관 전체 소장책 조회하기");
	        System.out.println("2. 일반도서만 조회하기(잡지 제외)");
	        System.out.println("3. 잡지만 조회하기(일반도서 제외)");
	        System.out.println("4. 이전으로");

	        System.out.print("메뉴번호를 입력해주세요 : ");
	        int choice = sc.nextInt();
	        sc.nextLine();

	        switch (choice) {
	            case 1: System.out.println(bc.getAllBook()); break;
	            case 2: System.out.println(bc.onlySearchBook()); break;
	            case 3: System.out.println(bc.onlySearchMagazine()); break;
	            case 4: return;
	            default: System.out.println("잘못 입력하셨습니다.");
	        }
	    }

	    private void addBook() {
	        System.out.print("bNo을 입력하세요 : ");
	        String bNo = sc.nextLine();

	        System.out.print("책 제목을 입력하세요 : ");
	        String title = sc.nextLine();

	        System.out.print("작가를 입력하세요 : ");
	        String author = sc.nextLine();

	        System.out.print("출판사를 입력하세요 : ");
	        String publisher = sc.nextLine();

	        System.out.print("가격을 입력하세요 : ");
	        int price = sc.nextInt();
	        sc.nextLine();

	        System.out.print("간단한 설명을 입력하세요 : ");
	        String description = sc.nextLine();

	        System.out.print("일반도서이면 true, 잡지이면 false를 입력하세요 : ");
	        boolean isBook = sc.nextBoolean();
	        sc.nextLine();

	        if (isBook) {
	            if (!bc.addBook(new Book(bNo, title, author, publisher, price, description))) {
	                System.out.println("이미 존재하는 책입니다.");
	            } else {
	                System.out.println("도서가 성공적으로 추가되었습니다.");
	            }
	        } else {
	            System.out.print("출간연도를 입력하세요 : ");
	            int year = sc.nextInt();

	            System.out.print("출간월을 입력하세요 : ");
	            int month = sc.nextInt();
	            sc.nextLine();

	            if (!bc.addBook(new Magazine(bNo, title, author, publisher, price, description, year, month))) {
	                System.out.println("이미 존재하는 잡지입니다.");
	            } else {
	                System.out.println("잡지가 성공적으로 추가되었습니다.");
	            }
	        }
	    }

	    private void searchBook() {
	        System.out.println("\n어떤 방법으로 책을 찾겠습니까?");
	        System.out.println("1. bNo으로 책 찾기");
	        System.out.println("2. 책 제목으로 책 찾기");
	        System.out.println("3. 출간연도로 잡지 찾기");
	        System.out.println("4. 출판사로 책 찾기");
	        System.out.println("5. 특정 가격 밑으로 책 찾기");
	        System.out.println("6. 이전으로");

	        System.out.print("메뉴번호를 입력해주세요 : ");
	        int choice = sc.nextInt();
	        sc.nextLine();

	        switch (choice) {
	            case 1:
	                System.out.print("bNo을 입력하세요 : ");
	                String bNo = sc.nextLine();
	                System.out.println(bc.searchBookBybNo(bNo));
	                break;

	            case 2:
	                System.out.print("책 제목을 입력하세요 : ");
	                String title = sc.nextLine();
	                System.out.println(bc.searchBookByTitle(title));
	                break;

	            case 3:
	                System.out.print("출간연도를 입력하세요 : ");
	                int year = sc.nextInt();
	                sc.nextLine();
	                System.out.println(bc.magazineOfThisYearInfo(year));
	                break;

	            case 4:
	                System.out.print("출판사를 입력하세요 : ");
	                String publisher = sc.nextLine();
	                System.out.println(bc.searchBookByPublisher(publisher));
	                break;

	            case 5:
	                System.out.print("가격을 입력하세요 : ");
	                int price = sc.nextInt();
	                sc.nextLine();
	                System.out.println(bc.searchBookByPrice(price));
	                break;

	            case 6:
	                return;

	            default:
	                System.out.println("잘못 입력하셨습니다.");
	        }
	    }

	    private void showTotalAndAverage() {
	        System.out.println("전체책 가격 합계 : " + bc.getTotalPrice() + "원");
	        System.out.println("전체책 가격 평균 : " + bc.getAvgPrice() + "원");
	    }
	}


	
	
	

