package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;

public class BookDao {

    // 전체 도서 조회
    public ArrayList<Book> getAllBooks(Connection conn) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM BOOK";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Book(
                    rs.getString("BNO"),
                    rs.getString("TITLE"),
                    rs.getString("AUTHOR"),
                    rs.getString("PUBLISHER"),
                    rs.getInt("PRICE"),
                    rs.getString("DESCRIPTION")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 일반 도서 조회
    public ArrayList<Book> onlySearchBooks(Connection conn) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM BOOK WHERE TYPE = 'BOOK'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Book(
                    rs.getString("BNO"),
                    rs.getString("TITLE"),
                    rs.getString("AUTHOR"),
                    rs.getString("PUBLISHER"),
                    rs.getInt("PRICE"),
                    rs.getString("DESCRIPTION")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 잡지만 조회
    public ArrayList<Magazine> onlySearchMagazines(Connection conn) {
        ArrayList<Magazine> list = new ArrayList<>();
        String sql = "SELECT * FROM MAGAZINE";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Magazine(
                    rs.getString("BNO"),
                    rs.getString("TITLE"),
                    rs.getString("AUTHOR"),
                    rs.getString("PUBLISHER"),
                    rs.getInt("PRICE"),
                    rs.getString("DESCRIPTION"),
                    rs.getInt("YEAR"),
                    rs.getInt("MONTH")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 도서 추가
    public int addBook(Connection conn, Book book) {
        int result = 0;
        String sql = "INSERT INTO BOOK VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getbNo());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getPrice());
            pstmt.setString(6, book.getDescription());
            
            result = pstmt.executeUpdate(); // 실행 결과를 result에 저장
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 잡지 추가
    public int addMagazine(Connection conn, Magazine magazine) {
        int result = 0;
        String sql = "INSERT INTO MAGAZINE VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, magazine.getbNo());
            pstmt.setString(2, magazine.getTitle());
            pstmt.setString(3, magazine.getAuthor());
            pstmt.setString(4, magazine.getPublisher());
            pstmt.setInt(5, magazine.getPrice());
            pstmt.setString(6, magazine.getDescription());
            pstmt.setInt(7, magazine.getYear());
            pstmt.setInt(8, magazine.getMonth());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 도서 번호로 조회
    public Book searchBookBybNo(Connection conn, String bNo) {
        Book book = null;
        String sql = "SELECT * FROM BOOK WHERE BNO = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    book = new Book(
                        rs.getString("BNO"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getString("PUBLISHER"),
                        rs.getInt("PRICE"),
                        rs.getString("DESCRIPTION")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    // 도서 제목으로 조회
    public ArrayList<Book> searchBookByTitle(Connection conn, String title) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM BOOK WHERE TITLE LIKE ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + title + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Book(
                        rs.getString("BNO"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getString("PUBLISHER"),
                        rs.getInt("PRICE"),
                        rs.getString("DESCRIPTION")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 특정 연도의 잡지 조회
    public ArrayList<Magazine> magazineOfThisYearInfo(Connection conn, int year) {
        ArrayList<Magazine> list = new ArrayList<>();
        String sql = "SELECT * FROM MAGAZINE WHERE YEAR = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, year);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Magazine(
                        rs.getString("BNO"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getString("PUBLISHER"),
                        rs.getInt("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getInt("YEAR"),
                        rs.getInt("MONTH")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 출판사로 도서 조회
    public ArrayList<Book> searchBookByPublisher(Connection conn, String publisher) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM BOOK WHERE PUBLISHER = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, publisher);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Book(
                        rs.getString("BNO"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getString("PUBLISHER"),
                        rs.getInt("PRICE"),
                        rs.getString("DESCRIPTION")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 특정 가격 이하의 도서 조회
    public ArrayList<Book> searchBookByPrice(Connection conn, int price) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM BOOK WHERE PRICE <= ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, price);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Book(
                        rs.getString("BNO"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getString("PUBLISHER"),
                        rs.getInt("PRICE"),
                        rs.getString("DESCRIPTION")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 도서 가격 합계
    public int getTotalPrice(Connection conn) {
        int result = 0;
        String sql = "SELECT SUM(PRICE) AS TOTAL_PRICE FROM BOOK";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                result = rs.getInt("TOTAL_PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 도서 가격 평균
    public double getAvgPrice(Connection conn) {
        double result = 0.0;
        String sql = "SELECT AVG(PRICE) AS AVG_PRICE FROM BOOK";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                result = rs.getDouble("AVG_PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
