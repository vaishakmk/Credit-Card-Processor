package com.cmpe202project;

import com.cmpe202project.models.CreditCard;
import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.implementations.chainofresponsibility.AmExCardHandler;
import com.cmpe202project.implementations.chainofresponsibility.DiscoverCardHandler;
import com.cmpe202project.implementations.chainofresponsibility.MasterCardHandler;
import com.cmpe202project.implementations.chainofresponsibility.VisaCardHandler;
import com.cmpe202project.implementations.strategy.FileContext;
import com.cmpe202project.interfaces.CreditCardHandler;
import com.cmpe202project.models.InputFileObject;
import com.cmpe202project.models.*;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main( String[] args ) {

        CreditCardType cardType;
        CreditCard outputcard;
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
        outputcard = new InvalidCC();
        FileContext filecontext = new FileContext();
        List<InputFileObject> list =filecontext.doReadFile(inputFile);
        if(null != list) {
            for (InputFileObject inputfileobject : list) {

                System.out.println("Check validity of CardNumber : " + inputfileobject.getCardNumber());
                cardType = masterCardHandler.validateCard(inputfileobject.getCardNumber());
                System.out.println("CardType is : " + cardType.name());

                switch (cardType) {
                case AmericanExpress:
                    outputcard = new AmExCC(inputfileobject.getCardNumber(),inputfileobject.getExpirationDate(),inputfileobject.getNameOfCardholder());
                    break;
                case Discover:
                    outputcard = new DiscoverCC(inputfileobject.getCardNumber(),inputfileobject.getExpirationDate(),inputfileobject.getNameOfCardholder());
                    break;
                case MasterCard:
                    outputcard = new MasterCC(inputfileobject.getCardNumber(),inputfileobject.getExpirationDate(),inputfileobject.getNameOfCardholder());
                    break;
                case Visa:
                    outputcard = new VisaCC(inputfileobject.getCardNumber(),inputfileobject.getExpirationDate(),inputfileobject.getNameOfCardholder());
                    break;
                case Invalid:
                    outputcard = new InvalidCC(inputfileobject.getCardNumber(),inputfileobject.getExpirationDate(),inputfileobject.getNameOfCardholder());
                    break;
                default:
                    System.out.println("Invalid Credit Card. Keeping it as InvalidCardCreator");
                }

//                CreditCard card = cardCreator.createCard(
//                        inputfileobject.getCardNumber(),
//                        inputfileobject.getExpirationDate(),
//                        inputfileobject.getNameOfCardholder());
                cardList.add(outputcard);
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
