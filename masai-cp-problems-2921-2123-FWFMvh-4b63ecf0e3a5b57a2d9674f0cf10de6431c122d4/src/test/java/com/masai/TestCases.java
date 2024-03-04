package com.masai;

import com.masai.controller.CustomerController;
import com.masai.controller.DeliveryController;
import com.masai.controller.OrderController;
import com.masai.controller.RestaurantController;
import com.masai.exception.CustomerException;
import com.masai.exception.GlobalExceptionHandler;
import com.masai.model.*;
import com.masai.repository.CustomerRepository;
import com.masai.repository.DeliveryRepository;
import com.masai.repository.OrderRepository;
import com.masai.repository.RestaurantRepository;
import com.masai.service.CustomerServiceImpl;
import com.masai.service.DeliveryServiceImpl;
import com.masai.service.OrderServiceImpl;
import com.masai.service.RestaurantServiceImpl;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

    private static int marks = 0;

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private DeliveryRepository deliveryRepository;
    @InjectMocks
    private RestaurantServiceImpl restaurantService;
    @InjectMocks
    private DeliveryServiceImpl deliveryService;
    @InjectMocks
    private CustomerServiceImpl customerService;
    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @Order(1)
    void testClassAnnotations() {
        String methodName = "EntityAnnotations";
        //arrange
        Class<?> customerClass = Customer.class;
        Class<?> orderClass = Orders.class;
        Class<?> deliveryClass = DeliveryPartner.class;
        Class<?> restaurantClass = Restaurant.class;

        //assert
        //customer
        assertTrue(customerClass.isAnnotationPresent(Entity.class) , () -> String.format("[Failure] %s: -> entity not created properly", methodName));

        //orders
        assertTrue(orderClass.isAnnotationPresent(Entity.class) , () -> String.format("[Failure] %s: -> entity not created properly", methodName));

        //restaurant
        assertTrue(restaurantClass.isAnnotationPresent(Entity.class) , () -> String.format("[Failure] %s: -> entity not created properly", methodName));

        //deliveryPartner
        assertTrue(deliveryClass.isAnnotationPresent(Entity.class) , () -> String.format("[Failure] %s: -> entity not created properly", methodName));
        System.out.printf("[Success] %s: -> entity class annotations are correct",methodName);
        System.out.println();
        marks+=1;
    }

    @Test
    @Order(2)
    void testClassAttributes() {
        String methodName = "classAttributes";
        //arrange
        Class<?> customerClass = Customer.class;
        Class<?> orderClass = Orders.class;
        Class<?> deliveryClass = DeliveryPartner.class;
        Class<?> restaurantClass = Restaurant.class;

        assertEquals(4, customerClass.getDeclaredFields().length , ()-> String.format("[Failure] %s: -> number of attributes are not correct for all the classes", methodName));
        assertEquals(5, deliveryClass.getDeclaredFields().length , ()-> String.format("[Failure] %s: -> number of attributes are not correct for all the classes", methodName));
        assertEquals(7, orderClass.getDeclaredFields().length , ()-> String.format("[Failure] %s: -> number of attributes are not correct for all the classes", methodName));
        assertEquals(5, restaurantClass.getDeclaredFields().length , ()-> String.format("[Failure] %s: -> number of attributes are not correct for all the classes", methodName));

        System.out.printf("[Success] %s: -> method working fine",methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(3)
    void testAssociationMapping(){

        String methodName = "associationMapping";
        Class<?> customerClass = Customer.class;
        Class<?> orderClass = Orders.class;
        Class<?> deliveryClass = DeliveryPartner.class;
        Class<?> restaurantClass = Restaurant.class;
        assertDoesNotThrow(
                ()-> {
                    assertTrue(customerClass.getDeclaredField("orders").isAnnotationPresent(OneToMany.class) , () -> String.format("[Failure] %s: -> association mapping is not correct", methodName));
                    assertTrue(deliveryClass.getDeclaredField("restaurants").isAnnotationPresent(ManyToMany.class), () -> String.format("[Failure] %s: -> association mapping is not correct", methodName) );
                    assertTrue(deliveryClass.getDeclaredField("orders").isAnnotationPresent(OneToMany.class), () -> String.format("[Failure] %s: -> association mapping is not correct", methodName) );
                    assertTrue(orderClass.getDeclaredField("restaurant").isAnnotationPresent(ManyToOne.class), () -> String.format("[Failure] %s: -> association mapping is not correct", methodName) );
                    assertTrue(orderClass.getDeclaredField("customer").isAnnotationPresent(ManyToOne.class), () -> String.format("[Failure] %s: -> association mapping is not correct", methodName) );
                    assertTrue(orderClass.getDeclaredField("deliveryPartner").isAnnotationPresent(ManyToOne.class), () -> String.format("[Failure] %s: -> association mapping is not correct", methodName) );
                    assertTrue(restaurantClass.getDeclaredField("orders").isAnnotationPresent(OneToMany.class), () -> String.format("[Failure] %s: -> association mapping is not correct", methodName) );
                    assertTrue(restaurantClass.getDeclaredField("deliveryPartners").isAnnotationPresent(ManyToMany.class), () -> String.format("[Failure] %s: -> association mapping is not correct", methodName) );
                });
        System.out.printf("[Success] %s: -> mapping is correct",methodName);
        System.out.println();
        marks += 2;
    }

    @Test
    @Order(4)
    void testServiceLayerAnnotations(){
        String methodName = "ServiceLayerAnnotations";
        Class<?> customer = CustomerServiceImpl.class ;
        Class<?> order = OrderServiceImpl.class;
        Class<?> restaurant = RestaurantServiceImpl.class;
        Class<?> delivery = DeliveryServiceImpl.class;

        assertTrue(customer.isAnnotationPresent(Service.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on service layer ", methodName));
        assertTrue(order.isAnnotationPresent(Service.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on service layer ", methodName));
        assertTrue(restaurant.isAnnotationPresent(Service.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on service layer ", methodName));
        assertTrue(delivery.isAnnotationPresent(Service.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on service layer ", methodName));
        System.out.printf("[Success] %s: -> proper stereotype annotations are used", methodName);
        System.out.println();
        marks++;

    }

    @Test
    @Order(5)
    void testControllerLayerAnnotations(){
        String methodName = "ControllerLayerAnnotations";
        Class<?> customer = CustomerController.class ;
        Class<?> order = OrderController.class;
        Class<?> restaurant = RestaurantController.class;
        Class<?> delivery = DeliveryController.class;

        assertTrue(customer.isAnnotationPresent(Controller.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on controller layer ", methodName));
        assertTrue(order.isAnnotationPresent(Controller.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on controller layer ", methodName));
        assertTrue(restaurant.isAnnotationPresent(Controller.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on controller layer ", methodName));
        assertTrue(delivery.isAnnotationPresent(Controller.class), () -> String.format("[Failure] %s: -> stereotype annotation is missing on controller layer ", methodName));
        System.out.printf("[Success] %s: -> proper stereotype annotations are used", methodName);
        System.out.println();
        marks++;

    }


    @Test
    @Order(6)
    void testAddCustomer() {
        String methodName = "addCustomer";
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(1);
        newCustomer.setUsername("Test Name");
        Customer customer = new Customer();
        customer.setCustomerId(2);

        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);
        when(customerRepository.findById(2)).thenReturn(Optional.of(newCustomer));

        assertDoesNotThrow(() -> {
            Customer addedCustomer = customerService.addCustomer(newCustomer);
            assertNotNull(addedCustomer, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(newCustomer, addedCustomer, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(customerRepository, times(1)).save(any(Customer.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        assertThrows(CustomerException.class, () -> customerService.addCustomer(customer) , () -> String.format("[Failure] %s: exception handling is not as expected", methodName));
        assertThrows(CustomerException.class, () -> customerService.addCustomer(null) , () -> String.format("[Failure] %s: exception handling is not as expected", methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }

    @Test
    @Order(7)
    void testGetCustomer() {
        String methodName = "getCustomer";
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(1);
        existingCustomer.setUsername("Test Name");

        when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.findById(2)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            Customer retrievedCustomer = customerService.getCustomer(1);
            assertNotNull(retrievedCustomer, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingCustomer, retrievedCustomer, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        assertThrows(CustomerException.class, () -> customerService.getCustomer(2) , () -> String.format("[Failure] %s: exception handling is not as expected", methodName));
        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }

    @Test
    @Order(8)
    void testUpdateCustomerName() {
        String methodName = "updateCustomerName";
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(1);
        existingCustomer.setUsername("Test Name");
        Customer customer = new Customer();

        when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.findById(2)).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        assertDoesNotThrow(() -> {
            Customer updatedCustomer = customerService.updateCustomerName(1, "New Name");
            assertNotNull(updatedCustomer, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals("New Name", updatedCustomer.getUsername(), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(customerRepository, times(1)).save(any(Customer.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        assertThrows(CustomerException.class , () -> customerService.updateCustomerName(2,"testName") , () -> String.format("[Failure] %s: -> exception not handled properly",methodName));
        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }

    @Test
    @Order(9)
    void testDeleteCustomer() {
        String methodName = "deleteCustomer";
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(1);
        existingCustomer.setUsername("Test Name");

        when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));

        assertDoesNotThrow(() -> {
            Customer deletedCustomer = customerService.deleteCustomer(1);
            assertNotNull(deletedCustomer, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingCustomer, deletedCustomer, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(customerRepository, times(1)).delete(any(Customer.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(10)
    void testAddOrder() {
        String methodName = "addOrder";
        Orders newOrder = new Orders();
        newOrder.setOrderId(1);
        newOrder.setStatus(Status.COOKING);

        Customer customer = new Customer();
        customer.setCustomerId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);

        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setDeliveryId(1);

        when(orderRepository.findById(1)).thenReturn(Optional.empty());
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(deliveryRepository.findById(1)).thenReturn(Optional.of(deliveryPartner));
        when(orderRepository.save(any(Orders.class))).thenReturn(newOrder);

        assertDoesNotThrow(() -> {
            Orders addedOrder = orderService.addOrder(newOrder, 1, 1, 1);
            assertNotNull(addedOrder, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(newOrder, addedOrder, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(customer, addedOrder.getCustomer(), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(restaurant, addedOrder.getRestaurant(), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(deliveryPartner, addedOrder.getDeliveryPartner(), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertTrue(customer.getOrders().contains(addedOrder), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertTrue(restaurant.getOrders().contains(addedOrder), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertTrue(deliveryPartner.getOrders().contains(addedOrder), () -> String.format("[failure] %s: -> method not working as expected",methodName));

            verify(orderRepository, times(1)).findById(1);
            verify(customerRepository, times(1)).findById(1);
            verify(restaurantRepository, times(1)).findById(1);
            verify(deliveryRepository, times(1)).findById(1);
            verify(orderRepository, times(1)).save(any(Orders.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks += 2;
    }


    @Test
    @Order(11)
    void testGetOrder() {
        String methodName = "getOrder";
        Orders existingOrder = new Orders();
        existingOrder.setOrderId(1);
        existingOrder.setStatus(Status.COOKING);

        when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));

        assertDoesNotThrow(() -> {
            Orders retrievedOrder = orderService.getOrder(1);
            assertNotNull(retrievedOrder, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingOrder, retrievedOrder, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(orderRepository, times(1)).findById(1);
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }

    @Test
    @Order(12)
    void testUpdateOrder() {
        String methodName = "updateOrder";
        Orders existingOrder = new Orders();
        existingOrder.setOrderId(1);
        existingOrder.setStatus(Status.COOKING);

        when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Orders.class))).thenReturn(existingOrder);

        assertDoesNotThrow(() -> {
            Orders result = orderService.updateOrder(1, Status.DELIVERED);
            assertNotNull(result, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(Status.DELIVERED, result.getStatus(), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(orderRepository, times(1)).findById(1);
            verify(orderRepository, times(1)).save(any(Orders.class));
        });

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(13)
    void testDeleteOrder() {
        String methodName = "deleteOrder";
        Orders existingOrder = new Orders();
        existingOrder.setOrderId(1);
        existingOrder.setStatus(Status.COOKING);

        when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));

        assertDoesNotThrow(() -> {
            Orders deletedOrder = orderService.deleteOrder(1);
            assertNotNull(deletedOrder, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingOrder, deletedOrder, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(orderRepository, times(1)).findById(1);
            verify(orderRepository, times(1)).delete(any(Orders.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(14)
    void testAddDeliveryPartner() {
        String methodName = "addDeliveryPartner";
        DeliveryPartner newDeliveryPartner = new DeliveryPartner();
        newDeliveryPartner.setDeliveryId(1);
        newDeliveryPartner.setDeliveryName("Delivery Express");

        when(deliveryRepository.findById(1)).thenReturn(Optional.empty());
        when(deliveryRepository.save(any(DeliveryPartner.class))).thenReturn(newDeliveryPartner);

        assertDoesNotThrow(() -> {
            DeliveryPartner addedDeliveryPartner = deliveryService.addDeliveryPartner(newDeliveryPartner);
            assertNotNull(addedDeliveryPartner, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(newDeliveryPartner, addedDeliveryPartner, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(deliveryRepository, times(1)).findById(1);
            verify(deliveryRepository, times(1)).save(any(DeliveryPartner.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(15)
    void testGetDeliveryPartner() {
        String methodName = "getDeliveryPartner";
        DeliveryPartner existingDeliveryPartner = new DeliveryPartner();
        existingDeliveryPartner.setDeliveryId(1);
        existingDeliveryPartner.setDeliveryName("Delivery Express");

        when(deliveryRepository.findById(1)).thenReturn(Optional.of(existingDeliveryPartner));

        assertDoesNotThrow(() -> {
            DeliveryPartner retrievedDeliveryPartner = deliveryService.getDeliveryPartner(1);
            assertNotNull(retrievedDeliveryPartner, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingDeliveryPartner, retrievedDeliveryPartner, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(deliveryRepository, times(1)).findById(1);
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(16)
    void testUpdateDeliveryPartner() {
        String methodName = "updateDeliveryPartner";
        DeliveryPartner existingDeliveryPartner = new DeliveryPartner();
        existingDeliveryPartner.setDeliveryId(1);
        existingDeliveryPartner.setDeliveryName("Delivery Express");

        when(deliveryRepository.findById(1)).thenReturn(Optional.of(existingDeliveryPartner));
        when(deliveryRepository.save(any(DeliveryPartner.class))).thenReturn(existingDeliveryPartner);

        DeliveryPartner updatedDeliveryPartner = new DeliveryPartner();
        updatedDeliveryPartner.setDeliveryName("Updated Express");
        updatedDeliveryPartner.setAddress("Updated Address");

        assertDoesNotThrow(() -> {
            DeliveryPartner result = deliveryService.updateDeliveryPartner(1, updatedDeliveryPartner);
            assertNotNull(result, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals("Updated Express", result.getDeliveryName(), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals("Updated Address", result.getAddress(), () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(deliveryRepository, times(1)).findById(1);
            verify(deliveryRepository, times(1)).save(any(DeliveryPartner.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(17)
    void testDeleteDeliveryPartner() {
        String methodName = "deleteDeliveryPartner";
        DeliveryPartner existingDeliveryPartner = new DeliveryPartner();
        existingDeliveryPartner.setDeliveryId(1);
        existingDeliveryPartner.setDeliveryName("Delivery Express");

        when(deliveryRepository.findById(1)).thenReturn(Optional.of(existingDeliveryPartner));

        assertDoesNotThrow(() -> {
            DeliveryPartner deletedDeliveryPartner = deliveryService.deleteDeliveryPartner(1);
            assertNotNull(deletedDeliveryPartner, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingDeliveryPartner, deletedDeliveryPartner, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(deliveryRepository, times(1)).findById(1);
            verify(deliveryRepository, times(1)).delete(any(DeliveryPartner.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(18)
    void testAddRestaurant() {
        String methodName = "addRestaurant";
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setRestaurantId(1);
        newRestaurant.setRestaurantName("New Restaurant");

        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(newRestaurant);

        assertDoesNotThrow(() -> {
            Restaurant addedRestaurant = restaurantService.addRestaurant(newRestaurant);
            assertNotNull(addedRestaurant, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(newRestaurant, addedRestaurant , () -> String.format("[failure] %s: -> method not working fine",methodName));
            verify(restaurantRepository, times(1)).findById(1);
            verify(restaurantRepository, times(1)).save(any(Restaurant.class));
        }, () -> String.format("[failure] %s: -> method not working fine",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }




    @Test
    @Order(19)
    void testGetRestaurant() {
        String methodName = "getRestaurant";
        Restaurant existingRestaurant = new Restaurant();
        existingRestaurant.setRestaurantId(1);
        existingRestaurant.setRestaurantName("Existing Restaurant");

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(existingRestaurant));

        assertDoesNotThrow(() -> {
            Restaurant retrievedRestaurant = restaurantService.getRestaurant(1);
            assertNotNull(retrievedRestaurant, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingRestaurant, retrievedRestaurant, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(restaurantRepository, times(1)).findById(1);
        });

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(20)
    void testUpdateRestaurantDetails() {
        String methodName = "updateRestaurantDetails";
        Restaurant existingRestaurant = new Restaurant();
        existingRestaurant.setRestaurantId(1);
        existingRestaurant.setRestaurantName("Existing Restaurant");

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(existingRestaurant));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(existingRestaurant);

        Restaurant updatedRestaurant = new Restaurant();
        updatedRestaurant.setRestaurantName("Updated Restaurant");

        assertDoesNotThrow(() -> {
            Restaurant result = restaurantService.updateRestaurantDetails(1, updatedRestaurant);
            assertNotNull(result, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingRestaurant, result, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(restaurantRepository, times(1)).findById(1);
            verify(restaurantRepository, times(1)).save(any(Restaurant.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }


    @Test
    @Order(21)
    void testDeleteRestaurant() {
        String methodName = "deleteRestaurant";
        Restaurant existingRestaurant = new Restaurant();
        existingRestaurant.setRestaurantId(1);
        existingRestaurant.setRestaurantName("Existing Restaurant");

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(existingRestaurant));

        assertDoesNotThrow(() -> {
            Restaurant deletedRestaurant = restaurantService.deleteRestaurant(1);
            assertNotNull(deletedRestaurant, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            assertEquals(existingRestaurant, deletedRestaurant, () -> String.format("[failure] %s: -> method not working as expected",methodName));
            verify(restaurantRepository, times(1)).findById(1);
            verify(restaurantRepository, times(1)).delete(any(Restaurant.class));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));

        System.out.printf("[Success] %s: method working fine", methodName);
        System.out.println();
        marks++;
    }

    @Test
    @Order(22)
    void testCustomerByName(){
        String methodName = "CustomerByName";

        assertDoesNotThrow(() ->{
            Method method = CustomerRepository.class.getDeclaredMethod("findByUsername", String.class) ;
            when(restaurantRepository.findByRestaurantNameAndAddress("name" , "address")).thenReturn(new Restaurant());
            Restaurant restaurant = restaurantService.restaurantByNameAndAddress("name", "address");
            assertNotNull(restaurant , () -> String.format("[failure] %s: -> method not working as expected",methodName));
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        System.out.printf("[Success] %s: -> method working fine\n", methodName);
        marks++;
    }

    @Test
    @Order(23)
    void testRestaurantByRestaurantNameAndAddress(){
        String  methodName = "restaurantByRestaurantNameAndAddress" ;
        assertDoesNotThrow(() -> {
            Method method = RestaurantRepository.class.getDeclaredMethod("findByRestaurantNameAndAddress", String.class, String.class) ;
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        System.out.printf("[Success] %s: -> method working fine\n", methodName);
        marks++;
    }

    @Test
    @Order(24)
    void testOrderByCustomerId(){
        String methodName = "OrderByCustomerId" ;
        assertDoesNotThrow(() ->{
            Method method = OrderRepository.class.getDeclaredMethod("findByCustomer_CustomerId", Integer.class) ;
        }, () -> String.format("[failure] %s: -> method not working as expected",methodName));
        System.out.printf("[Success] %s: -> method working fine\n", methodName);
        marks++;
    }

    @Test
    @Order(25)
    void CustomerValidation(){
        String methodName = "CustomerValidation";
        Class<Customer> customerClass = Customer.class ;
        Field[] fields = customerClass.getDeclaredFields() ;

        assertDoesNotThrow(()->{
            assertTrue(customerClass.getDeclaredField("customerId").isAnnotationPresent(Id.class) , () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(customerClass.getDeclaredField("customerId").isAnnotationPresent(GeneratedValue.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(customerClass.getDeclaredField("username").isAnnotationPresent(NotNull.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(customerClass.getDeclaredField("username").isAnnotationPresent(Size.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        }, () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        System.out.printf("[Success] %s: validation working fine\n" , methodName);
        marks++;
    }

    @Test
    @Order(26)
    void RestaurantValidation(){
        String methodName = "RestaurantValidation";
        Class<Restaurant> restaurantClass = Restaurant.class ;

        assertDoesNotThrow(()->{
            assertTrue(restaurantClass.getDeclaredField("restaurantId").isAnnotationPresent(Id.class) , () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(restaurantClass.getDeclaredField("restaurantId").isAnnotationPresent(GeneratedValue.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(restaurantClass.getDeclaredField("restaurantName").isAnnotationPresent(NotNull.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(restaurantClass.getDeclaredField("address").isAnnotationPresent(NotNull.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        }, () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        System.out.printf("[Success] %s: validation working fine\n" , methodName);
        marks++;
    }

    @Test
    @Order(27)
    void OrdersValidation(){
        String methodName = "OrdersValidation";
        Class<Orders> ordersClass = Orders.class ;

        assertDoesNotThrow(()->{
            assertTrue(ordersClass.getDeclaredField("orderId").isAnnotationPresent(Id.class) , () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(ordersClass.getDeclaredField("orderId").isAnnotationPresent(GeneratedValue.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(ordersClass.getDeclaredField("totalAmount").isAnnotationPresent(PositiveOrZero.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(ordersClass.getDeclaredField("status").isAnnotationPresent(Enumerated.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        }, () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        System.out.printf("[Success] %s: validation working fine\n" , methodName);
        marks++;
    }

    @Test
    @Order(28)
    void DeliveryPartnerValidation(){
        String methodName = "DeliveryPartnerValidation";
        Class<DeliveryPartner> deliveryPartnerClass = DeliveryPartner.class ;

        assertDoesNotThrow(()->{
            assertTrue(deliveryPartnerClass.getDeclaredField("deliveryId").isAnnotationPresent(Id.class) , () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(deliveryPartnerClass.getDeclaredField("deliveryId").isAnnotationPresent(GeneratedValue.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(deliveryPartnerClass.getDeclaredField("deliveryName").isAnnotationPresent(NotNull.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
            assertTrue(deliveryPartnerClass.getDeclaredField("address").isAnnotationPresent(NotNull.class), () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        }, () -> String.format("[Failure] %s: -> validation is not as expected", methodName));
        System.out.printf("[Success] %s: validation working fine\n" , methodName);
        marks++;
    }

    @Test
    @Order(29)
    void testGlobalExceptionHandler(){
        String methodName = "GlobalExceptionHandler";
        Class<GlobalExceptionHandler> exceptionHandlerClass = GlobalExceptionHandler.class;
        Method[] methods = exceptionHandlerClass.getMethods() ;
        assertDoesNotThrow(() ->{
            assertTrue(exceptionHandlerClass.isAnnotationPresent(ControllerAdvice.class), () -> String.format("[Failure] %s: -> Exception Handling is not as expected", methodName));
        }, () -> String.format("[Failure] %s: -> Exception Handling is not as expected", methodName));

        System.out.printf("[Success] %s: -> Exception class is annotated properly\n", methodName);
        marks++;
    }

    @Test
    @Order(30)
    void testExceptionHandlerMethods(){
        String methodName = "methodExceptionHandler" ;
        Class<GlobalExceptionHandler> exceptionHandlerClass = GlobalExceptionHandler.class;
        Method[] methods = exceptionHandlerClass.getDeclaredMethods() ;

        assertDoesNotThrow(() -> {
            for(Method method : methods){
                assertTrue(method.isAnnotationPresent(ExceptionHandler.class), () -> String.format("[Failure] %s: -> Exception Handling is not as expected", methodName));
            }
        }, () -> String.format("[Failure] %s: -> Exception Handling is not as expected", methodName));
        System.out.printf("[Success] %s: -> method level annotations are correct", methodName);
        marks++;
    }

    @AfterAll
    static void printMarks(){
        System.out.println("[MARKS] marks is " + marks);
    }


}
