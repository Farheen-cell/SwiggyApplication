package com.masai.controller;

import com.masai.exception.DeliveryException;
import com.masai.model.DeliveryPartner;
import com.masai.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/deliverypartners")
@Validated
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryPartner> addDeliveryPartner(@Valid @RequestBody DeliveryPartner deliveryPartner) {
        try {
            DeliveryPartner addedDeliveryPartner = deliveryService.addDeliveryPartner(deliveryPartner);
            return new ResponseEntity<>(addedDeliveryPartner, HttpStatus.CREATED);
        } catch (DeliveryException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryPartner> getDeliveryPartner(@PathVariable int id) {
        try {
            DeliveryPartner deliveryPartner = deliveryService.getDeliveryPartner(id);
            return new ResponseEntity<>(deliveryPartner, HttpStatus.OK);
        } catch (DeliveryException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryPartner> updateDeliveryPartner(@PathVariable int id, @RequestBody DeliveryPartner deliveryPartner) {
        try {
            DeliveryPartner updatedDeliveryPartner = deliveryService.updateDeliveryPartner(id, deliveryPartner);
            return new ResponseEntity<>(updatedDeliveryPartner, HttpStatus.OK);
        } catch (DeliveryException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeliveryPartner> deleteDeliveryPartner(@PathVariable int id) {
        try {
            DeliveryPartner deletedDeliveryPartner = deliveryService.deleteDeliveryPartner(id);
            return new ResponseEntity<>(deletedDeliveryPartner, HttpStatus.OK);
        } catch (DeliveryException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
