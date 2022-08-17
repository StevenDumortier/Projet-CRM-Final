package com.poe.crm.dao;

import com.poe.crm.business.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //composant spring qui va permettre de faire des injections dans des variables une instance de cet objet

public interface OrderRepository extends JpaRepository<Order,Long> {
}
