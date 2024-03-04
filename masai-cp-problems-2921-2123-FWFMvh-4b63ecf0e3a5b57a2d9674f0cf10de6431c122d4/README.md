# java-springboot-design-swiggy

# Swiggy

Create a simple Spring Boot application to manage a food delivery system like Swiggy, with Restaurants, Customers, Orders, and Delivery Partners. The application should support all the operations present in service layer of the boilerplate code with using a database:

## Initial Set up
- You have been given zip file of the boilerplate code.
- Download the zip file containing the boilerplate code, unzip it.
- Open the project in STS or IntelliJ or any IDE of your choice.
- Read the complete instructions properly.
- Make sure you are following each and every instruction, such as variable name, method name and proper annotations.
- All the interfaces are given to you containing the methods you need to impliments.
- Don't change pom.xml file.
- Inside application.properties file, you can use your MySQL credentials for internal testing but please make sure while submitting the answer you are commenting the mySQL credentials part and uncommenting h2 database part


## Submission Instructions
- Make sure you are submitting the code before deadline.
- Once you complete the code, please run the **mvn clean test** to check if all the test cases are passing or not
- If your **ControllerLayer and ServiceLayer** is not getting created with all the bean, all the service methods will fail.
- If your project is giving compilation error, you will get 0 marks.
- You need to submit your GitHub link.
- Please make sure you are submitting link, where your project is located.
- If you submit wrong link, you will get 0 marks.
- Submission after deadline will not be accepted.

## Maximum Marks : 32
- `testClassAnnotations`: 1 mark
- `classAttributes`: 1 mark
- `associationMapping`: 2 mark
- `ServiceLayerAnnotations`: 1 mark
- `ControllerLayerAnnotations`: 1 mark
- `addCustomer`: 1 mark
- `getCustomer`: 1 mark
- `updateCustomerName`: 1 mark
- `deleteCustomer`: 1 mark
- `addOrder`: 2 marks
- `getOrder`: 1 mark
- `updateOrder`: 1 mark
- `deleteOrder`: 1 mark
- `addDeliveryPartner`: 1 mark
- `getDeliveryPartner`: 1 mark
- `updateDeliveryPartner`: 1 mark
- `deleteDeliveryPartner`: 1 mark
- `addRestaurant`: 1 mark
- `getRestaurant`: 1 mark
- `updateRestaurantDetails`: 1 mark
- `deleteRestaurant`: 1 mark
- `customerByName`: 1 mark
- `restaurantByRestaurantNameAndAddress`: 1 mark
- `OrderByCustomerId`: 1 mark
- `CustomerValidation`: 1 mark
- `RestaurantValidation`: 1 mark
- `OrdersValidation`: 1 mark
- `DeliveryPartnerValidation`: 1 mark
- `GlobalExceptionHandler`: 1 mark
- `methodExceptionHandler`: 1 mark


## Description of Methods

- `testClassAnnotations`:
    - Make sure you are giving proper annotations for all the entities
      
- `classAttributes`:
    - Each Entity class should have same variable name and number of variable with same datatype and required annotations
      
- `associationMapping`: 
    - Association Mapping should be same as mentioned in the Entity classes description
      
- `ServiceLayerAnnotations`:
    - Service layer interface should be implimented properly with requred class name and depencencies
    - Service layer classes should have proper annotations
      
- `ControllerLayerAnnotations`:
    - Controller layer should have requrired dependencies
    - Controller layer classes should have proper annotations

- `addCustomer`:
    - add a new customer in the database.
    - if null value is provided you method should throw CustomerException
    - if customer is already present with same customerId, method should throw Customer Exception
    - return the customer after saving in database.
- `getCustomer`: 
    - find and return the customer based on customerId.
    - if customer is not present with given customerId, throw CustomerException.
      
- `updateCustomerName`:
  - find the customer based on given customerId.
  - update the name of the customer.
  - if no customer present with the given customerId, throw Customer Exception
  - after updating name of the customer in database, return the updated customer.
    
- `deleteCustomer`: 
  - find the customer based on the given customerId.
  - if customer is not present with given customerId, throw CustomerException.
  - delete the Customer from the database, and return the deleted Customer.
    
- `addOrder`:
    - find the customer, restaurant and deliveryPartner based on the given ids.
    - if any entity is not found then throw exception according to the entity.
    - make the requred relationship between orders andd other entities.
    - add a new Orders in the database.
    - if null value is provided you method should throw OrderException
    - if Orders is already present with same orderId, method should throw OrderException
    - return the order after saving in database.
      
- `getOrder`:
  - find the Orders based on the given orderId.
  - if order is not present with given orderId, throw OrderException.
  - delete the orders from the database, and return the deleted orders.
    
- `updateOrder`:
  - find the Orders based on given orderId.
  - update the status of the order based on given status.
  - if no order present with the given orderId, throw OrderException
  - after updating status of the orders in database, return the updated orders.
    
- `deleteOrder`:
  - find the orders based on the given orderId.
  - if orders is not present with given orderId, throw OrderException.
  - delete the orders from the database, and return the deleted orders.
    
- `addDeliveryPartner`:
    - add new deliveryPartner in the database.
    - if null value if provided, throw the DeliveryException.
    - if deliveryPartner is already present with the given deliveryId, throw DeliveryException.
    - return the deliveryPartner after saving in database.
      
- `getDeliveryPartner`:
  - find and return the deliveryPartner based on given deliveryId.
  - if no deliveryPartner found with the given deliveryId, throw DeliveryException.
    
- `updateDeliveryPartner`:
  - find the deliveryPartner based ont the deliveryId.
  - if no deliveryPartner found with the given deliveryId, throw DeliveryException.
  - assign all the value of deliveryPartner that is provided in the method to the deliveryPartner that you have found from database except deliveryId.
  - save and return the updated deliveryPartner.
    
- `deleteDeliveryPartner`:
  - find the deliveryPartner based on the deliveryId.
  - if no deliveryPartner found with given deliveryId, throw DeliveryException.
  - delete and return the deliveryPartner.
    
- `addRestaurant`: 
    - add new restaurant in the database.
    - if null value if provided, throw the RestaurantException.
    - if restaurant is already present with the given restaurantId, throw RestaurantException.
    - return the restaurant after saving in database.
   
- `getRestaurant`:
  - find and return the restaurant based on given restaurantId.
  - if no restaurant found with the given restaurantId, throw RestaurantException.
    
- `updateRestaurantDetails`: 
  - find the restaurant based ont the restaurantId.
  - if no restaurant found with the given restaurantId, throw RestaurantException.
  - assign restaurantId to the restaurant that is provided in the method and save provided restaurant.
  - save and return the updated restaurant.
    
- `deleteRestaurant`:
  - find the restaurant based on the restaurantId.
  - if no deliveryPartner found with given restaurantId, throw RestaurantException.
  - delete and return the restaurant.
 
- `customerByName`
  - find the Customer based on customer username, with the help of Jpa naming convention.
  - if no Customer found with given name, throw CustomerException
  - return the Customer based on customer username.
    
- `restaurantByRestaurantNameAndAddress`
  - find and return the Restaurant based on restaurantName and address, with the help of Jpa naming convention.
  - if no Restaurant found with given name and address, throw RestaurantException.
    
- `OrderByCustomerId`
  - find and return the Order based on customer's customerId with the help of Jpa naming convention. you need to write Jpa method inside OrderRepository without connecting to CustomerRepository.
  - if no Order found with given customerId, throw OrderException.
    
- `CustomerValidation`
  - use jakarta.Validation for validating each attribute
    
- `RestaurantValidation`
  - use jakarta.Validation for validating each attribute
    
- `OrdersValidation`
  - use jakarta.Validation for validating each attribute
    
- `DeliveryPartnerValidation`
  - use jakarta.Validation for validating each attribute
    
- `GlobalExceptionHandler`
  - GlobalException class is given, needs to give appropriate 
    
- `methodExceptionHandler`
  - create exceptionHandler methods for all the Custom exception.
  - create exceptionHandler method for invalid endpoints.
  - create exceptionHandler method for invalid arguments.
    


**Note: if some functionalities are dependent on each other, and one functionality is effecting other functionality then your score may affect**
**Note: You must have instance variable of repositoryLayer inside serviceLayer to perform all the Database operations and You must have instance variable of serviceLayer inside ControllerLayer**


## Features to Build

**application.properties credentials are given if you want to use MySQL credentials, you can use, but while submitting the answer, please make sure you are usign h2 database credentials only**
- You need to create implimentation of all the service classes `CustomerServiceImpl` ,  `DeliveryServiceImpl` , `OrderServiceImpl`, `RestaurantServiceImpl`)


## Models
### Customer class:
- The `Customer` class is already given.
- create required constructors.
- create getter and setter
- override toString method.
- override hashcode and equals method.
- Create the following fields inside the class.
    - `customerId` (primary key, int , id should be auto generated)
    - `username` (String , username should not be null, blank and empty are allowed. username should be of minimum 3 character and maximum 40 character)
    - `email` (String, proper annotation for validation without using pattern)
    - `orders` (List of Orders, one customer can have multiple orders)


### Restaurant class:
- The `Restaurant` class is already given.
- create required constructors.
- create getter and setter
- override toString method.
- override hashcode and equals method.
- Create the following fields inside the class.
    - `restaurantId` ( primary key, int, id should be auto generated)
    - `restaurantName`(String , value should not be null, empty and blank are allowed)
    - `address` (String, value should not be null, empty and blank are allowed)
    - `orders` (List of Orders, one restaurant can have multiple orders)
    - `deliveryPartners` (List of Delivery Partners, one restaurant can have multiple DeliveryPartner)
 
  ### Orders class:
- The `Orders` class is already given.
- create required constructors.
- create getter and setter
- override toString method.
- override hashcode and equals method.
- Create the following fields inside the class.
    - `orderId` ( primary key, int, id should be auto generated)
    - `items`(List of String)
    - `totalAmount` (double, value should be a positive number including 0)
    - `status` (Status Enum)
    - `customer` (Customer, one order will have single customer)
    - `restaurant` (Restaurant, each order will have single restaurant)
    - `deliveryPartner` (DeliveryPartner, each order will have single deliveryPartner)


  ### DeliveryPartner class:
- The `DeliveryPartner` class is already given.
- create required constructors.
- create getter and setter
- override toString method.
- override hashcode and equals method.
- Create the following fields inside the class.
    - `deliveryId` ( primary key, int, id should be auto generated)
    - `deliveryName`(String, value should not be null, empty and blank are allowed)
    - `address` (String, value should not be null, empty and blank are allowed)
    - `orders` (List of Orders, one DeliveryPartner will have multiple Orders)
    - `restaurant` (Restaurant, each DeliveryPartner will have multiple Restaurant)
 
      

### Repository Layer
- **CustomerRepository**
  - interface is given, you need to extends required interfaces and classes.
  - Write your own method if needed

- **DeliveryRepository**
  - interface is given, you need to extends required interfaces and classes.
  - Write your own method if needed

- **OrderRepository**
  - interface is given, you need to extends required interfaces and classes.
  - Write your own method if needed

- **RestaurantRepository**
  - interface is given, you need to extends required interfaces and classes.
  - Write your own method if needed


### Service Layer

- **CustomerService**
  - interface is given, you need to provide the implimentation of this interface.
  - `CustomerServiceImpl` should have dependency of `CustomerRepository`.
  - Dependencies should be Autowired
  - implimentated class name should be `CustomerServiceImpl`.
  - You need to provide implimentation of all the methods available inside the interface according to the requirement.
  - Needs to handle exception accordingly.

- **DeliveryService**
  - interface is given, you need to provide the implimentation of this interface.
  - `DeliveryServiceImpl` should have dependency of `DeliveryRepository`.
  - Dependencies should be Autowired
  - implimentated class name should be `DeliveryServiceImpl`.
  - You need to provide implimentation of all the methods available inside the interface according to the requirement.
  - Needs to handle exception accordingly.

- **OrderService**
  - interface is given, you need to provide the implimentation of this interface.
  - `OrderServiceImpl` should have dependencies of `OrderRepository` , `RestaurantRepository` , `CustomerRepository` and `DeliveryRepository`.
  - Dependencies should be Autowired
  - implimentated class name should be `OrderServiceImpl`.
  - You need to provide implimentation of all the methods available inside the interface according to the requirement.
  - Needs to handle exception accordingly.
    
- **RestaurantService**
  - interface is given, you need to provide the implimentation of this interface.
  - `RestaurantServiceImpl` should have dependency of `RestaurantRepository`.
  - Dependencies should be Autowired
  - implimentated class name should be `RestaurantServiceImpl`.
  - You need to provide implimentation of all the methods available inside the interface according to the requirement.
  - Needs to handle exception accordingly.

**Note: If any of the implimentation class is not found or not implimentated properly then all test cases will fail and you will get 0 marks.**


### Controller Layer


- **CustomerController**
  - `CustomerController` is given, you need to write all the costomer related API here.
  - `CustomerController` should have dependency of `CustomerService`
  - Dependencies should be Autowired
  - Provide implimentation of all the method available inside service layer.

- **DeliveryController**
  - interface is given, you need to provide the implimentation of this interface.
  - implimentated class name should be `DeliveryServiceImpl`.
  - Dependencies should be Autowired
  - You need to provide implimentation of all the methods available inside the interface according to the requirement.
  - Needs to handle exception accordingly.

- **OrderController**
  - interface is given, you need to provide the implimentation of this interface.
  - implimentated class name should be `OrderServiceImpl`.
  - Dependencies should be Autowired
  - You need to provide implimentation of all the methods available inside the interface according to the requirement.
  - Needs to handle exception accordingly.
    
- **RestaurantController**
  - interface is given, you need to provide the implimentation of this interface.
  - implimentated class name should be `RestaurantServiceImpl`.
  - Dependencies should be Autowired
  - You need to provide implimentation of all the methods available inside the interface according to the requirement.
  - Needs to handle exception accordingly.




- Creating Classes: Design based on attributes.
- Maintain Relationship:
- Three-Tier Architecture: Implement a proper folder structure following the three-tier architecture.
- Adding, Deleting, Updating and Viewing Functionalities: Implement functionalities to add, delete, and view for all Entity .


### Constraints:
- Use appropriate Soring annotations (e.g., @Service, @Repository, and others) for creating beans and dependency injection.
- Implement exception handling by throwing and catching the appropriate exceptions (UserException and ProfileException) for error scenarios during data access operations.
- Note: All implemented classes should have a default constructor.

### Common instructions for pom.xml and application.properties
- Both pom.xml & application.properties files are given along with boilerplate code.
- Do not make any changes to the existing content of the pom.xml file.
- You can make changes in the application.properties file but at the time of submission, make sure you are use h2 database credentials and not of MySQL
- Test your application’s methods on your local system.
- From the main method of the Runner class, you can test your application’s methods in your local system. can create an object if you want and call the methods.
- Check your application for a few sample test cases:

### Steps to test your application:

- Run the maven project (refer to the below steps).
- Right-click on the project → Run As → Maven test.
### General guidelines
- The system on cp.masaischool.com may take between 1-20 minutes for responding.
- So, we request you to read the problem carefully and debug it before itself.
- We also request you not just submit it last minute.
- Try to keep one submission at a time.
- Use the template provided to write your code (Mandatory).

Note: Properly design and implement the entity classes, establish relationships, and ensure the application's functionality aligns with the specified points distribution.

