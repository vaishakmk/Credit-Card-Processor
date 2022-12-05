package com.cmpe202project.implementations.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author vaishak
 */
public class Utilities {
    public static Date StringToDate(String stringDate) {

        //System.out.println("The Date in String format is : "+ stringDate);
        Date date= null;
        try {
            date = new SimpleDateFormat("MM/yy").parse(stringDate);
        } catch (ParseException e) {
            System.out.println("Error in parsing date, setting to default date");
            date = new Date();
        }
        return date;
    }
}
