package com.masai.repository;

import com.masai.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryPartner,Integer> {

}
