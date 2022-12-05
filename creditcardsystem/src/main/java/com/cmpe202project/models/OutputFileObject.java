package com.cmpe202project.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author vaishak
 */
@Data
@AllArgsConstructor
public class OutputFileObject {

    String CardNumber;
    String CardType;


    @Override
    public String toString() {
        return "OutputFileObject{" +
                "CardNumber='" + CardNumber + '\'' +
                ", CardType='" + CardType + '\'' +
                '}';
    }
}
