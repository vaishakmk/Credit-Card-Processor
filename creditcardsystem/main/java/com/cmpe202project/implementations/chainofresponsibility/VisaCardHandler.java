package com.cmpe202project.implementations.chainofresponsibility;

import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.interfaces.CreditCardHandler;
import org.apache.commons.lang3.StringUtils;

public class VisaCardHandler implements CreditCardHandler {

    private CreditCardHandler successor = null;
    private CreditCardType cardType = null;

    @Override
    public CreditCardType description() {
        return CreditCardType.Visa;
    }

    @Override
    public CreditCardType validateCard(String cardNumber) {

        System.out.println("VisaCardHandler got the request...");
        if(isValid(cardNumber)){
            System.out.println(cardNumber + " => This is Visa Card!!!!");
            cardType = CreditCardType.Visa;
        }
        else{
            if(successor != null)
                System.out.println("Request forwarded to"+ successor.description()+ "handler ...");
                cardType = successor.validateCard(cardNumber);
            else {
                System.out.println("Invalid Credit Card");
                cardType = CreditCardType.Invalid;
            }
        }
        return cardType;
    }

    @Override
    public void setSuccessor(CreditCardHandler next) {
        this.successor = next;
    }

    public boolean isValid(String cardNumber){

        return  StringUtils.isNotEmpty(cardNumber) &&
                StringUtils.isNumericSpace(cardNumber) &&
                cardNumber.charAt(0)=='4' &&
                (cardNumber.length()==13 || cardNumber.length()==16);
    }
}
