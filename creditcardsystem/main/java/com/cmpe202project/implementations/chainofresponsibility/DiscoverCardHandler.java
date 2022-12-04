package com.cmpe202project.implementations.chainofresponsibility;

import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.interfaces.CreditCardHandler;
import org.apache.commons.lang3.StringUtils;

public class DiscoverCardHandler implements CreditCardHandler {

    private CreditCardHandler successor;
    private CreditCardType cardType;

    @Override
    public CreditCardType description() {
        return CreditCardType.Discover;
    }

    @Override
    public CreditCardType validateCard(String cardNumber) {

        System.out.println("DiscoverCardHandler got the request...");
        if(isValid(cardNumber)) {
            System.out.println(cardNumber + " => This is DiscoverCard !!");
            cardType = CreditCardType.Discover;
        }
        else{
            if(successor !=null)
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

        return  StringUtils.isNotEmpty(cardNumber) &&
                StringUtils.isNumericSpace(cardNumber) &&
                cardNumber.startsWith("6011") &&
                cardNumber.length()==16;
    }
}
