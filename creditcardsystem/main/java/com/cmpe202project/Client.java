package com.cmpe202project;

//import com.sjsu.designpattern.model.CreditCard.class;
//import com.sjsu.designpattern.model.CreditCardType.java;
//import com.sjsu.designpattern.implementation.chainofresponsibility.AmExCardHandler;
//import com.sjsu.designpattern.implementation.chainofresponsibility.DiscoverCardHandler;
//import com.sjsu.designpattern.implementation.chainofresponsibility.MasterCardHandler;
//import com.sjsu.designpattern.implementation.chainofresponsibility.VisaCardHandler;
//import com.sjsu.designpattern.implementation.factory.*;
//import com.sjsu.designpattern.implementation.strategy.FileContext;
//import com.sjsu.designpattern.interfaces.CreditCardHandler;
//import com.sjsu.designpattern.model.InputFileObject;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main( String[] args ) {

        CreditCardType cardType;
        CardIssuer cardIssuer;
        List<CreditCard> cardList;

        if (args.length == 0)
            throw new IllegalArgumentException("Mandatory file name missing in argument list");
        String inputFile = args[0];
        String outputFile = args[1];
        System.out.println("The Input File is : "+inputFile);
        System.out.println("The Output File is : "+outputFile);

        CreditCardHandler masterCardHandler = new MasterCardHandler();
        CreditCardHandler visaCardHandler = new VisaCardHandler();
        CreditCardHandler amExCardHandler = new AmExCardHandler();
        CreditCardHandler discoverCardHandler = new DiscoverCardHandler();



        // Chain of responsibility from MasterCard->Visa->AmEx->Discover
        masterCardHandler.setSuccessor(visaCardHandler);
        visaCardHandler.setSuccessor(amExCardHandler);
        amExCardHandler.setSuccessor(discoverCardHandler);




        // Pass the input file to the file context
        cardList = new ArrayList<>();
        cardCreator = new InvalidCardCreator();
        FileContext filecontext = new FileContext();
        List<InputFileObject> list =filecontext.doReadFile(inputFile);
        if(null != list) {
            for (InputFileObject inputfileobject : list) {

                System.out.println("Check validity of CardNumber : " + inputfileobject.getCardNumber());
                cardType = amExCardHandler.validateCard(inputfileobject.getCardNumber());
                System.out.println("CardType is : " + cardType.name());

                switch (cardType) {
                case AmericanExpress:
                    cardCreator = new AmExCardCreator();
                    break;
                case Discover:
                    cardCreator = new DiscoverCardCreator();
                    break;
                case MasterCard:
                    cardCreator = new MasterCardCreator();
                    break;
                case Visa:
                    cardCreator = new VisaCardCreator();
                    break;
                case Invalid:
                    cardCreator = new InvalidCardCreator();
                    break;
                default:
                    System.out.println("Invalid Credit Card. Keeping it as InvalidCardCreator");
                }

                CreditCard card = cardCreator.createCard(
                        inputfileobject.getCardNumber(),
                        inputfileobject.getExpirationDate(),
                        inputfileobject.getNameOfCardholder());
                cardList.add(card);
            }

            System.out.println("Printing List of Credit Cards ");
            for (CreditCard cc : cardList) {
                System.out.println(cc);
            }
            // Write Output to File
            filecontext.doWriteFile(outputFile, cardList);
        }
        else {
            System.out.println("File Format is not supported or Input data is not correct.");
        }
    }
}
