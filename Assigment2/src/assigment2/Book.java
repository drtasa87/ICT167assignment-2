/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.util.Date;

/**
 *
 * @author dr_ta
 */
public class Book extends Client {

    /**
     * @param args the command line arguments
     */
    private boolean type;
    private String title;
    private int id;
    private long isbn;
    private String fauthor;
    private String lauthor;
    private int pages;
    private String date;
    public int day;
    private String borrow;
    public int fine;

    // default constructor
    public Book() {
        type = false;
        title = "No name yet.";
        id = 0;
        isbn = 0;
        fauthor = "No First Name";
        lauthor = "No Last Name";
        pages = 0;
        day = 0;
        date = null;
        borrow = null;
        fine = 0;

    }

    //constructor with seven arg
    public Book(int temid, String temtitle, String temfauthor, String temlauthor, long temisbn, int tempages, String temdate, String temborrow, boolean ftype) {
        type = ftype;
        title = temtitle;
        id = temid;
        isbn = temisbn;
        fauthor = temfauthor;
        lauthor = temlauthor;
        pages = tempages;
        day = 0;
        date = temdate;
        borrow = temborrow;
        fine = 0;
    }

    //sort method
    public void sort(Book[] books) {                                            
        Book temp = new Book();
        for (int i = 0; i < books.length; i++) {
            for (int j = 1; j < (books.length - i); j++) {
                if (books[j - 1].getId() > books[j].getId()) {
                    temp = books[j - 1];
                    books[j - 1] = books[j];
                    books[j] = temp;
                }
            }
        }
    }

    //day calculation method
    public void day(Book[] books) {
        Date current = new Date();
        String s1;
        for (int i = 0; i < books.length; i++) {
            s1 = books[i].getDate();
            Date record = new Date(s1);
            books[i].setDay((int) (current.getTime() - record.getTime()) / (1000 * 60 * 60 * 24));
        }
    }

    //calculate fines for genre
    public void calculateFine(Book[] books) {
        if (type == true) {
            Crime book = new Crime();
            fine = book.CrimeFine(day, fine);
        } else {
            Drama book = new Drama();
            fine = book.DramaFine(day, fine);
        }

    }

    //GETTERS
    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getFauthor() {
        return fauthor;
    }

    public String getLauthor() {
        return lauthor;
    }

    public int getPages() {
        return pages;
    }

    public String getDate() {
        return date;
    }

    public String getBorrow() {
        return borrow;
    }

    public int getDay() {
        return day;
    }

    public int getFine() {
        return fine;
    }

    public boolean isType() {
        return type;
    }

    //SETTERS
    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setFauthor(String author) {
        this.fauthor = author;
    }

    public void setLauthor(String lauthor) {
        this.lauthor = lauthor;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBorrow(String borrow) {
        this.borrow = borrow;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
