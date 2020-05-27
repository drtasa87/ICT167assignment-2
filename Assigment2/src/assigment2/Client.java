/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.io.File;                        //libraries used
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author dr_ta
 */
public class Client {                                                           //start of client class

    public static void inputfile(Book[] book) throws FileNotFoundException {        //inputfile method
        Scanner file = new Scanner(new File("book.txt"));                        //open file book.txt
        try {                                                                   
            for (int i = 0; i < book.length; i++) {                                 //for loop
                while (file.hasNextLine()) {
                    String line = file.nextLine();                                  //reads the lines
                    String[] parts = line.split(",");
                    if (parts[8].equalsIgnoreCase("Crime")) {                       //if last word is crime, put in array
                        book[i] = new Book(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Long.parseLong(parts[4]), Integer.parseInt(parts[5]), parts[6], parts[7], true);
                    } else if (parts[8].equalsIgnoreCase("Drama")) {                //if drama put in array
                        book[i] = new Book(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Long.parseLong(parts[4]), Integer.parseInt(parts[5]), parts[6], parts[7], false);
                    }                                                               
                    break;
                }                                                               //end of while
            }                                                                   // end of for loop
        } catch (Exception e) {                                                 //end of try
            System.out.println("Error in the input file books.txt");            //display
        }

    }                                                                           //end of method

    public static void main(String[] args) throws FileNotFoundException {           //main method

        Book[] books = new Book[9];                                             // new object of books
        inputfile(books);                                                       //call method
        //This line calculates the days
        books[0].day(books);                                                    //call method
        //THIS LINE CALCULATE FINES
        for (int i = 0; i < books.length; i++) {                                //for loop  
            books[i].calculateFine(books);                                      //calculate fine for each book
        }

        //JOPTIONPANE
        while (true) {
            //display Menu
            try {       
                String OspiLibrary = JOptionPane.showInputDialog(null, "Hello and Welcome" + "\nType number: \n 1. To Quit (exit the program)"
                        + "\n 2. Compute the overall fine" + "\n 3. Output outstanding fine per book" + "\n 4. Find the top three books"
                        + "\n 5. Number of books over-due more than 10 days" + "\n 6. Most and least popular book" + "\n 7. Last borrower of a book"
                        + "\n 8. Search for book by ID" + "\n 9. Search the book by their author" + "\n 10. Sort the books by ID number"
                        + "\n 11. Output sorted list to a CSV file", "Ospi Park Library", JOptionPane.INFORMATION_MESSAGE);
                
                switch (Integer.parseInt(OspiLibrary)) {                        //returns an intiger

                    case 1:                                                     //case 1 start
                        JOptionPane.showMessageDialog(null, "Thank you and Goodbye");  //displayes window with message
                        System.exit(0);                                           //returning o so will stop while loop and close the program
                        break;                                                  //brak of case 1

                    case 2://genre overall fine
                        int totalcrime = 0;
                        int totaldrama = 0;
                        for (int i = 0; i < books.length; i++) {                    //inside for loop
                            if (books[i].isType()) {
                                totalcrime = totalcrime + books[i].getFine();       //adding total fines of crime
                            } else {
                                totaldrama = totaldrama + books[i].getFine();        //adding total fines of drama
                            }

                        }
                        //Display case2
                        JOptionPane.showMessageDialog(null, "DRAMA: " + totaldrama + " AUD" + "\n " + "CRIME: " + totalcrime + " AUD", "Overall fine", JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 3:
                        //Books details with fines
                        String display = "";
                        for (int i = 0; i < books.length; i++) {
                            display = display + books[i].getId() + ", " + books[i].getTitle() + ", " + books[i].getFauthor() + ", " + books[i].getLauthor() + ", "
                                    + books[i].getIsbn() + ", " + books[i].getPages() + ", "
                                    + books[i].getDate() + ", " + books[i].getBorrow() + ", " + books[i].getFine() + " AUD" + "\n";

                        }
                        //Display case3
                        JOptionPane.showMessageDialog(null, display, "Outstanding fine per book", JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 4://not available
                        //Display case4
                        JOptionPane.showMessageDialog(null, "Sorry this option is not available!");
                        break;

                    case 5:
                        int counter = 0;
                        for (int i = 0; i < books.length; i++) {
                            if (books[i].getDay() > 10) {
                                counter++;
                            }
                        }
                       //Display case5
                        JOptionPane.showMessageDialog(null, "There are " + counter + " books that are over-due more than 10 days");
                        break;

                    case 6://not available
                        //Display case6
                        JOptionPane.showMessageDialog(null, "Sorry this option is not available!");
                        break;

                    case 7:

                        int last;
                        last = books[0].getDay();
                        for (int i = 0; i < books.length; i++) {
                            if (books[i].getDay() < last) {
                                last = i;
                            }
                        }
                        //what will Display
                        String disp = "LAST BORROWER \n";
                        disp = disp + books[last].getId() + ", " + books[last].getTitle() + ", " + books[last].getFauthor() + ", " + books[last].getLauthor() + ", "
                                + books[last].getIsbn() + ", " + books[last].getPages() + ", "
                                + books[last].getDate() + ", " + books[last].getBorrow() + ", " + books[last].getFine() + " AUD" + "\n";
                        //Display case7
                        JOptionPane.showMessageDialog(null, disp);
                        break;

                    case 8://search by ID
                         //asks for input in new dialog window
                        String option = JOptionPane.showInputDialog("Please Enter the Book ID: ");
                        int id = Integer.parseInt(option);
                        for (int i = 0; i < books.length; i++) {
                            if (books[i].getId() == id) {
                                //Display case8
                                JOptionPane.showMessageDialog(null, "Book Found!" + "\n" + books[i].getId() + ", " + books[i].getTitle() + ", " + books[i].getFauthor() + ", " + books[i].getLauthor() + ", "
                                        + books[i].getIsbn() + ", " + books[i].getPages() + ", "
                                        + books[i].getDate() + ", " + books[i].getBorrow() + ", " + books[i].getFine() + " AUD" + "\n", "Search ID", JOptionPane.INFORMATION_MESSAGE);

                                //printing(books[i]);
                                break;
                            } else {
                                if (i == books.length - 1 && books[i].getId() != id) {
                                    //Display error for case 8
                                    JOptionPane.showMessageDialog(null, "No results!");
                                }
                            }
                        }
                        break;

                    case 9://Search by author name
                         //asks for input in new dialog window
                        String namesearch = JOptionPane.showInputDialog("Please Enter First or Last name of the Author ");
                        String fauthor = namesearch;
                        for (int i = 0; i < books.length; i++) {
                            if (books[i].getFauthor().equalsIgnoreCase(fauthor) || books[i].getLauthor().equalsIgnoreCase(fauthor)) {
                                //Display case9
                                JOptionPane.showMessageDialog(null, "Book Found!" + "\n" + books[i].getId() + ", " + books[i].getTitle() + ", " + books[i].getFauthor() + ", " + books[i].getLauthor() + ", "
                                        + books[i].getIsbn() + ", " + books[i].getPages() + ", "
                                        + books[i].getDate() + ", " + books[i].getBorrow() + ", " + books[i].getFine() + " AUD" + "\n", "Search Name", JOptionPane.INFORMATION_MESSAGE);

                                break;
                            } else {
                                if (i == books.length - 1 && (!books[i].getFauthor().equalsIgnoreCase(fauthor) || !books[i].getLauthor().equalsIgnoreCase(fauthor))) {
                                     //Display error for case 9
                                    JOptionPane.showMessageDialog(null, "No results!");
                                }
                            }
                        }

                        break;

                    case 10://sorting by ID
                        String sort = "";
                        for (int i = 0; i < books.length; i++) {
                            books[i].sort(books);                               //call sort method for all books
                        }
                        for (int i = 0; i < books.length; i++) {

                           //inside for loop to Display whole list of books
                            sort = sort + books[i].getId() + ", " + books[i].getTitle() + ", " + books[i].getFauthor() + ", " + books[i].getLauthor() + ", "
                                    + books[i].getIsbn() + ", " + books[i].getPages() + ", "
                                    + books[i].getDate() + ", " + books[i].getBorrow() + ", " + books[i].getFine() + " AUD" + "\n";

                        }
                        //Display case10
                        JOptionPane.showMessageDialog(null, sort, "Sorted by ID number", JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 11://print to .csv file
                        String fileName = "SortedList.csv";
                        PrintWriter outputStream = null;
                        try {
                            outputStream = new PrintWriter(fileName);
                            //header
                            outputStream.println("Book ID,Title,Author F,Author L,ISBN,Pages,Rent Date,Rented to, Fine");

                            for (int i = 0; i < books.length; i++) {
                               //inside for loop to print whole list with following details
                                outputStream.print(books[i].getId());
                                outputStream.append(',');
                                outputStream.print(books[i].getTitle());
                                outputStream.append(',');
                                outputStream.print(books[i].getFauthor());
                                outputStream.append(',');
                                outputStream.print(books[i].getLauthor());
                                outputStream.append(',');
                                outputStream.print(books[i].getIsbn());
                                outputStream.append(',');
                                outputStream.print(books[i].getPages());
                                outputStream.append(',');
                                outputStream.print(books[i].getDate());
                                outputStream.append(',');
                                outputStream.print(books[i].getBorrow());
                                outputStream.append(',');
                                outputStream.print(books[i].getFine());
                                outputStream.append('\n');

                            }
                            outputStream.close();
                            //mesage about output
                            JOptionPane.showMessageDialog(null, "Sorted list printed Succesfully"); //confirm printing to .csv file
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Unexspected ERROR!");
                        }

                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Error, wrong input!");
                        break;
                }//end of switch
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error, wrong input");   //pop out message if input for menu is wrong
            }
        }//end of while loop set up to repeat until type: 1

    }//end of Menu
}//end of Client class
