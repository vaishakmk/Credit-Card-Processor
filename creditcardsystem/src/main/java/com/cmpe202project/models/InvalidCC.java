package com.cmpe202project.models;

import java.util.Date;

/**
 * @author vaishak
 */
public class InvalidCC extends CreditCard {

    public InvalidCC(){
        super();
        System.out.println("Invalid Credit Card ");
    }
    public InvalidCC(String cardNumber, Date expirationDate, String nameOfCardholder) {
        super(CreditCardType.Invalid, cardNumber, expirationDate, nameOfCardholder);
        System.out.println("Invalid Credit Card ");
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
