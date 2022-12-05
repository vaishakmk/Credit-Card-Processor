package com.cmpe202project.implementations.strategy;

import com.cmpe202project.models.CreditCardType;
import com.cmpe202project.models.CreditCard;
import com.cmpe202project.implementations.util.Utilities;
import com.cmpe202project.interfaces.FileHandlingStrategy;
import com.cmpe202project.models.InputFileObject;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlParser implements FileHandlingStrategy {

    @Override
    public List<InputFileObject> readFile(String filePath) throws Exception {
        String cardNumber = "";
        Date expirationDate = new Date();
        String nameOfCardholder = "";
        InputFileObject inputfileobject;
        List<InputFileObject> list;
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;


        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("CARD");
            list = new ArrayList<>();
            System.out.println("XmlParser Read xml input file : " +filePath);
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;

                    if(null != eElement.getElementsByTagName("CARD_NUMBER").item(0))
                        cardNumber = eElement.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();

                    if(null != eElement.getElementsByTagName("EXPIRATION_DATE").item(0))
                        expirationDate = Utilities.StringToDate(eElement.getElementsByTagName("EXPIRATION_DATE").item(0).getTextContent());

                    if(null != eElement.getElementsByTagName("CARD_HOLDER_NAME").item(0))
                        nameOfCardholder = eElement.getElementsByTagName("CARD_HOLDER_NAME").item(0).getTextContent();

                    inputfileobject = new InputFileObject(cardNumber.trim(),expirationDate,nameOfCardholder.trim());
                    list.add(inputfileobject);
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            throw e;
        }
        return list;
    }

    @Override
    public void writeFile(String filePath, List<CreditCard> cardList) throws Exception {
        String error;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("CARDS");
            doc.appendChild(rootElement);
            System.out.println("XmlParser Write xml output file : "+filePath);
            for (CreditCard i : cardList) {

                if (i.getCardType() != CreditCardType.Invalid)
                    error = "";
                else if(!StringUtils.isNotEmpty(i.getCardNumber()))
                    error = ": empty/null card number";
                else if(i.getCardNumber().length()>19)
                    error = ": more than 19 digits";
                else if(!StringUtils.isNumericSpace(i.getCardNumber()))
                    error = ": non numeric characters";
                else
                    error = ": not a possible card number";

                Element row = doc.createElement("CARD");
                rootElement.appendChild(row);

                //CardNumber Element
                Element cardNumber = doc.createElement("CARD_NUMBER");
                cardNumber.appendChild(doc.createTextNode(i.getCardNumber()));
                row.appendChild(cardNumber);

                //CardType Element
                Element cardType = doc.createElement("CARD_TYPE");
                cardType.appendChild(doc.createTextNode(i.getCardType().toString()+error));
                row.appendChild(cardType);

                //Error Element
//                Element errortag = doc.createElement("Error");
//                errortag.appendChild(doc.createTextNode(error));
//                row.appendChild(errortag);

            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(filePath);
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            System.out.println("Done creating XML File");

        }catch (Exception e) {
            throw e;
        }

    }
}
