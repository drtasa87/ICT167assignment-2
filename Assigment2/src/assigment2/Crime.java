/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

/**
 *
 * @author dr_ta
 */
public class Crime extends Book {

    //fine calculation method for Crime
    public int CrimeFine(int day, int fine) {

        if (day < 3) {
            fine = 0;
        } else if (day >= 3 && day < 8) {
            fine = day * 5;
        } else if (day >= 8 && day <= 16) {
            fine = day * 10;
        } else {
            System.out.println("Error");
        }

        return fine;
    } //end of method

} //end of class
