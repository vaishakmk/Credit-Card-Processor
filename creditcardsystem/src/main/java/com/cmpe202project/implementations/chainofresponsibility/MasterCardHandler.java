package com.cmpe202project.implementations.chainofresponsibility;

import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.interfaces.CreditCardHandler;
import org.apache.commons.lang3.StringUtils;

public class MasterCardHandler implements CreditCardHandler {

    private CreditCardHandler successor = null;
    private CreditCardType cardType;

    @Override
    public CreditCardType description() {
        return CreditCardType.MasterCard;
    }

    @Override
    public CreditCardType validateCard(String cardNumber) {

        System.out.println("MasterCardHandler got the request...");
        if(isValid(cardNumber)){
            System.out.println(cardNumber + " => This is MasterCard !!");
            cardType = CreditCardType.MasterCard;
        }
        else{
            if(successor != null)
                cardType = successor.validateCard(cardNumber);
            else {
                System.out.println("Invalid Credit Card");
                cardType = CreditCardType.Invalid;
            }
        }
        return cardType;
    }

    @Override
    public void setSuccessor(CreditCardHandler next) {this.successor = next;
    }

    public boolean isValid(String cardNumber){

        return  StringUtils.isNotEmpty(cardNumber) &&
                StringUtils.isNumericSpace(cardNumber) &&
                cardNumber.charAt(0)=='5' &&
                (cardNumber.charAt(1)>='1' && cardNumber.charAt(1)<='5') &&
                cardNumber.length()==16;
    }
}
