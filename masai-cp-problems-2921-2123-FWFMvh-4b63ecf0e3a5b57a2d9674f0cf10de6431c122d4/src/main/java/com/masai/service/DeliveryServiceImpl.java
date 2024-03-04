package com.masai.service;

import com.masai.exception.DeliveryException;
import com.masai.model.DeliveryPartner;
import com.masai.repository.DeliveryRepository;
import com.masai.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) throws DeliveryException {
        try {
            if (deliveryRepository.findById(deliveryPartner.getDeliveryId()).isPresent()) {
                throw new DeliveryException("Delivery partner with the same ID already exists");
            }

            return deliveryRepository.save(deliveryPartner);
        } catch (DataIntegrityViolationException e) {
            throw new DeliveryException("Data integrity violation occurred while saving delivery partner");
        } catch (Exception e) {
            throw new DeliveryException("An unexpected error occurred");
        }
    }

    @Override
    public DeliveryPartner getDeliveryPartner(int deliveryPartnerId) throws DeliveryException {
        try {
            return deliveryRepository.findById(deliveryPartnerId)
                    .orElseThrow(() -> new DeliveryException("Delivery partner not found"));
        } catch (Exception e) {
            throw new DeliveryException("An unexpected error occurred");
        }
    }

    @Override
    public DeliveryPartner updateDeliveryPartner(int deliveryPartnerId, DeliveryPartner deliveryPartner) throws DeliveryException {
        try {
            DeliveryPartner existingDeliveryPartner = deliveryRepository.findById(deliveryPartnerId)
                    .orElseThrow(() -> new DeliveryException("Delivery partner not found"));

            existingDeliveryPartner.setDeliveryName(deliveryPartner.getDeliveryName());
            existingDeliveryPartner.setAddress(deliveryPartner.getAddress());

            return deliveryRepository.save(existingDeliveryPartner);
        } catch (Exception e) {
            throw new DeliveryException("An unexpected error occurred");
        }
    }

    @Override
    public DeliveryPartner deleteDeliveryPartner(int deliveryPartnerId) throws DeliveryException {
        try {
            DeliveryPartner existingDeliveryPartner = deliveryRepository.findById(deliveryPartnerId)
                    .orElseThrow(() -> new DeliveryException("Delivery partner not found"));

            deliveryRepository.delete(existingDeliveryPartner);

            return existingDeliveryPartner;
        } catch (Exception e) {
            throw new DeliveryException("An unexpected error occurred");
        }
    }
}
