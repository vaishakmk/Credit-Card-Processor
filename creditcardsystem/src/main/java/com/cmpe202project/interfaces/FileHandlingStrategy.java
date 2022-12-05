package com.cmpe202project.interfaces;

import com.cmpe202project.models.CreditCard;
import com.cmpe202project.models.InputFileObject;

import java.util.List;

public interface FileHandlingStrategy {

    List<InputFileObject> readFile(String inputFilename) throws Exception;
    void writeFile(String outputFilename, List<CreditCard> cardList) throws Exception;

}
