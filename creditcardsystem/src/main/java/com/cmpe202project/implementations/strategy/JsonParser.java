package com.cmpe202project.implementations.strategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.models.CreditCard;
import com.cmpe202project.implementations.util.Utilities;
import com.cmpe202project.interfaces.FileHandlingStrategy;
import com.cmpe202project.models.InputFileObject;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonParser implements FileHandlingStrategy {

    @Override
    public List<InputFileObject> readFile(String inputFilename) throws Exception {
        String cardNumber = "invalid";
        Date expirationDate = new Date();
        String nameOfCardholder = "";
        List<InputFileObject> list ;
        InputFileObject inputfileobject;

        //JSON parser object to parse read file
        System.out.println("JsonParser Read json input file : " + inputFilename);
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(inputFilename)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray jsonList = (JSONArray) obj;
            System.out.println(jsonList);
            //System.out.println(jsonList);

            //Iterate over array
            list = new ArrayList<>();
            for(Object o:jsonList){
                JSONObject row= (JSONObject)o;
                if(null != row.get("CardNumber")) {
                    if (row.get("CardNumber") instanceof String)
                        cardNumber = (String) row.get("CardNumber");
                    else if (row.get("CardNumber") instanceof Long)
                        cardNumber = "" + (Long) row.get("CardNumber");
                }

                if(null != row.get("ExpirationDate"))
                    expirationDate = Utilities.StringToDate((String)row.get("ExpirationDate"));

                if(null != row.get("NameOfCardholder"))
                    nameOfCardholder = (String)row.get("NameOfCardholder");

                inputfileobject = new InputFileObject(cardNumber.trim(),expirationDate,nameOfCardholder.trim());
                System.out.println(inputFilename);
                list.add(inputfileobject);
            }
        }
        catch (IOException e) {
            throw new IOException("JsonParser.readFile():Error in reading file", e);
        }
        return list;
    }

    @Override
    public void writeFile(String filePath, List<CreditCard> cardList) throws Exception {
        String error;
        JSONArray list;

        System.out.println("JsonParser Write json output file : "+filePath);
        list = new JSONArray();
        for (CreditCard i : cardList) {
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

            JSONObject details = new JSONObject();
            if(StringUtils.isNumericSpace(i.getCardNumber()))
                details.put("CardNumber",Long.parseLong(i.getCardNumber()));
            else
                details.put("CardNumber",i.getCardNumber());
            details.put("CardType",i.getCardType().toString());
            details.put("Error",error);

            //Add Credit Card details to list
            list.add(details);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(filePath)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(list);
            // System.out.println(jsonOutput);

            file.write(jsonOutput);
            file.flush();

            System.out.println("Done creating JSON File");

        }catch (IOException e) {
            throw new IOException("JsonParser.writeFile():Error in write file", e);
        }

    }
}
