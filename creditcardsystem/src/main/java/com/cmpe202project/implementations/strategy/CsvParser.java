package com.cmpe202project.implementations.strategy;

import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.models.CreditCard;
import com.cmpe202project.implementations.util.Utilities;
import com.cmpe202project.interfaces.FileHandlingStrategy;
import com.cmpe202project.models.InputFileObject;
import com.cmpe202project.models.OutputFileObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvParser implements FileHandlingStrategy {

    private static final String COMMA_DELIMITER = ",";

    @Override
    public List<InputFileObject> readFile(String inputFilename) throws Exception {
        String line;
        BufferedReader fileReader;
        List<InputFileObject> list;


        try {
            System.out.println("CsvParser Read csv input file : " + inputFilename);

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(inputFilename));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //loop until all lines are read
            list = new ArrayList<>();
            while ((line = fileReader.readLine()) != null) {

                //use string.split to load a string array with the values from each line of the file, using a comma as the delimiter
                String[] attributes = line.split(COMMA_DELIMITER);
                //System.out.println(attributes[0]+"  "+attributes[1]+"   "+attributes[2]);
                if(attributes.length >= 3) {
                    InputFileObject inputfileobject = new InputFileObject(attributes[0].trim(), Utilities.StringToDate(attributes[1]), attributes[2].trim());
                    list.add(inputfileobject);
                }
                else if(attributes.length == 2){
                    InputFileObject inputfileobject = new InputFileObject(attributes[0].trim(), Utilities.StringToDate(attributes[1]), "");
                    list.add(inputfileobject);
                }
                else if(attributes.length == 1){
                    InputFileObject inputfileobject = new InputFileObject(attributes[0].trim(), new Date(), "");
                    list.add(inputfileobject);
                }
                else{
                    InputFileObject inputfileobject = new InputFileObject("", new Date(), "");
                    list.add(inputfileobject);
                }
            }
            fileReader.close();
        }
        catch (IOException e) {
            throw new IOException(" CvsParser.readFile():Error in reading file.", e);
        }
        return list;
    }




    @Override
    public void writeFile(String filePath, List<CreditCard> cardList) throws Exception {

        //Delimiter used in CSV file
        String NEW_LINE_SEPARATOR = "\n";
        String header = "cardNumber,cardType";
        List<OutputFileObject> list;
        String error = "";

        System.out.println("CsvParser Write csv output file : "+filePath);

        // first create file object for file placed at location specified by filepath
        File file = new File(filePath);

        // add data to csv
        list = new ArrayList<>();
        for(CreditCard i: cardList) {
            if(i.getCardType() != CreditCardType.Invalid) {
                error = "";
            } else if(i.getCardNumber().length()>19){
                error = ": more than 19 digits";
            } else if(!StringUtils.isNotEmpty(i.getCardNumber())){
                error = ": empty/null card number";
            } else if(!StringUtils.isNumericSpace(i.getCardNumber())){
                error = ": non numeric characters";
            } else{
                error = ": not a possible card number";
            }



            OutputFileObject details = new OutputFileObject(i.getCardNumber(), (i.getCardType().toString() + error));
            list.add(details);
        }

        try{
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            //Write the CSV file header
            outputfile.append(header.toString());

            //Add a new line separator after the header
            outputfile.append(NEW_LINE_SEPARATOR);

            for(OutputFileObject row :list){
                outputfile.append(row.getCardNumber());
                outputfile.append(COMMA_DELIMITER);
                outputfile.append(row.getCardType());
                outputfile.append(NEW_LINE_SEPARATOR);
            }
            outputfile.flush();
            outputfile.close();
            System.out.println("CSV file was created successfully !!!");
        }
        catch (IOException e) {
            throw new IOException(" CvsParser.writeFile():Error in writing file.", e);
        }

    }
}
