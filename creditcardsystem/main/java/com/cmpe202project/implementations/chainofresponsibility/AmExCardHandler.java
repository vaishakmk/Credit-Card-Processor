package com.cmpe202project.implementations.chainofresponsibility;

import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.interfaces.CreditCardHandler;
import org.apache.commons.lang3.StringUtils;

public class AmExCardHandler implements CreditCardHandler {

    private CreditCardType cardType;
    private CreditCardHandler successor;

    @Override
    public CreditCardType description() {
        return CreditCardType.AmericanExpress;
    }

    @Override
    public CreditCardType validateCard(String cardNumber) {

        System.out.println("AmExCardHandler got the request...");
        if(isValid(cardNumber)){
            System.out.println(cardNumber + " => This is AmEx Card !!");
            cardType = CreditCardType.AmericanExpress;
        }
        else{
            if(successor != null)
                System.out.println("Request forwarded to"+ successor.description()+ "handler ...");
                cardType = successor.validateCard(cardNumber);
        }
        return cardType;
    }

    @Override
    public void setSuccessor(CreditCardHandler next) {
        this.successor = next;
    }

    public boolean isValid(String cardNumber){

        return StringUtils.isNotEmpty(cardNumber) &&
                StringUtils.isNumericSpace(cardNumber) &&
                cardNumber.charAt(0)=='3' &&
                (cardNumber.charAt(1)=='4' || cardNumber.charAt(1)=='7') &&
                cardNumber.length()==15;
    }
}
