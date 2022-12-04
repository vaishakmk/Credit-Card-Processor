package com.cmpe202project.models;
import com.cmpe202project.models.CreditCardType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public abstract class CreditCard {

    private CreditCardType cardType;
    private String cardNumber;
    private Date expirationDate;
    private String nameOfCardholder;


    @Override
    public String toString() {
        return "CreditCard{" +
                "cardType=" + cardType +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", nameOfCardholder='" + nameOfCardholder + '\'' +
                '}';
    }
}
