package com.kh.controller;

import java.util.ArrayList;
import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;
import static com.kh.common.JDBCTemplate.*;

public class BookService {

    private BookDao bd = new BookDao();

    // 전체 도서 조회
    public ArrayList<Book> getAllBooks() {
        return bd.getAllBooks(getConnection());
    }

    // 일반 도서만 조회
    public ArrayList<Book> onlySearchBooks() {
        return bd.onlySearchBooks(getConnection());
    }

    // 잡지만 조회
    public ArrayList<Magazine> onlySearchMagazines() {
        return bd.onlySearchMagazines(getConnection());
    }

    // 도서 추가
    public int addBook(Book book) {
        return bd.addBook(getConnection(), book);
    }

    public int addMagazine(Magazine magazine) {
        return bd.addMagazine(getConnection(), magazine);
    }

    // 도서 번호로 조회
    public Book searchBookBybNo(String bNo) {
        return bd.searchBookBybNo(getConnection(), bNo);
    }

    // 도서 제목으로 조회
    public ArrayList<Book> searchBookByTitle(String title) {
        return bd.searchBookByTitle(getConnection(), title);
    }

    // 특정 연도의 잡지 조회
    public ArrayList<Magazine> magazineOfThisYearInfo(int year) {
        return bd.magazineOfThisYearInfo(getConnection(), year);
    }

    // 출판사로 도서 조회
    public ArrayList<Book> searchBookByPublisher(String publisher) {
        return bd.searchBookByPublisher(getConnection(), publisher);
    }

    // 특정 가격 이하의 도서 조회
    public ArrayList<Book> searchBookByPrice(int price) {
        return bd.searchBookByPrice(getConnection(), price);
    }

    // 도서 가격 합계 및 평균 조회
    public int getTotalPrice() {
        return bd.getTotalPrice(getConnection());
    }

    public double getAvgPrice() {
        return bd.getAvgPrice(getConnection());
    }
}
