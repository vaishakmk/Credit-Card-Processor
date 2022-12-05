# individual-project-cmpe202-03-vaishakmk

## Part - 1
### Primary Problem we are trying to solve:

•	In this problem, the primary problem statement is to identify what type of Credit Card does each record of the input file represent i.e., whether it’s a MasterCard, Visa, American Express or Discover.
•	For verifying this we must parse the given file for each record and verify that the corresponding credit card number is a valid account number or not. If the Credit Card number is invalid, handle it accordingly.
•	If the card is a valid card, it needs to be processed by one of the four objects. Since the client doesn’t know which object is the right one responsible for handling the request, we can use the Chain of Responsibility behavioral design pattern to issue a request to identify the type of Credit Card to one of the several concrete handlers without specifying the receiver explicitly.


### Secondary Problem we are trying to solve:

The secondary problem is to create the appropriate objects of the Credit Card class once the type of card is identified after reading the record from the file. We can use Factory Method creational design pattern by defining an interface for creating Credit Card object and allowing the subclasses to create object of the identified Credit Card. This allows us to create the appropriate Credit Card objects at runtime.


## Part - 2

We need to extend our application to parse different file formats. For this we are using Strategy pattern. We check the extension of the input file and based on the input file context we define the strategy to parse the given document.
This  will allow us to add more strategies in future as well. We just need to create a concrete strategy class like CsvFileParser, XmlFileParser and JsonFileParser.
