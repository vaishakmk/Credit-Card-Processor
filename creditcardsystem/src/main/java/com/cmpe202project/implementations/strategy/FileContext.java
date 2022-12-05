package com.cmpe202project.implementations.strategy;

import com.cmpe202project.models.CreditCard;
import com.cmpe202project.interfaces.FileHandlingStrategy;
import com.cmpe202project.models.InputFileObject;
import org.apache.commons.io.FilenameUtils;

import java.util.List;

/**
 * @author vaishak
 */
public class FileContext {
    private FileHandlingStrategy strategy;


    public FileContext(){
        this.strategy = new CsvParser();
    }

    public List<InputFileObject> doReadFile(String inputFilename){
        List<InputFileObject> list = null;
        try{

            String extension = FilenameUtils.getExtension(inputFilename);
            System.out.println("The Input File Extension is: " + extension);

            switch (extension) {
                case "csv":
                    System.out.println("File is handled by a Csv parser");
                    changeStrategy(new CsvParser());
                    list = strategy.readFile(inputFilename);
                    break;

                case "json":
                    System.out.println("File is handled by a Json parser");
                    changeStrategy(new JsonParser());
                    list = strategy.readFile(inputFilename);
                    break;

                case "xml":
                    System.out.println("File is handled by a Xml parser");
                    changeStrategy(new XmlParser());
                    list = strategy.readFile(inputFilename);
            }
        }
        catch(Exception e){
            System.out.println("Exception in doReadFile "+ e.getMessage());
        }
        return list;
    }

    public void doWriteFile(String filePath, List<CreditCard>cardList){
        try{
            //String extension = FilenameUtils.getExtension(filePath);
            //System.out.println("The Output File Extension is: " + extension);
            strategy.writeFile(filePath,cardList);
        }
        catch(Exception e){
            System.out.println("Exception in doWriteFile "+ e.getMessage());
        }

    }

    public void changeStrategy(FileHandlingStrategy s){
        strategy = s;
    }

}
