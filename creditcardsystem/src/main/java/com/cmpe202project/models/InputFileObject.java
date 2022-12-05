package com.cmpe202project.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author vaishak
 */
@Data
@AllArgsConstructor
public class InputFileObject {

    String cardNumber;
    Date expirationDate;
    String nameOfCardholder;

    public InputFileObject(){
        cardNumber = null;
    }

    @Override
    public String toString() {
        return "InputFileObject{" +
                "CardNumber='" + cardNumber + '\'' +
                ", ExpirationDate=" + expirationDate +
                ", NameOfCardholder='" + nameOfCardholder + '\'' +
                '}';
    }
}
