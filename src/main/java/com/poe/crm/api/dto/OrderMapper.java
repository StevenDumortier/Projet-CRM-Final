package com.poe.crm.api.dto;

import com.poe.crm.business.Client;
import com.poe.crm.business.Order;

public class OrderMapper {
    public static OrderDTO convertToDTO(Order order){
        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());
        dto.setTypePresta(order.getTypePresta());
        dto.setDesignation(order.getDesignation());
        dto.setNbDays(order.getNbDays());
        dto.setUnitPrice(order.getUnitPrice());
        dto.setState(order.getState());
        dto.setClient(order.getClient());


        return dto;
    }

    public static Order convertToEntity(OrderDTO dto) {
        Order order = new Order();

        order.setId(dto.getId());
        order.setTypePresta(dto.getTypePresta());
        order.setDesignation(dto.getDesignation());
        order.setNbDays(dto.getNbDays());
        order.setUnitPrice(dto.getUnitPrice());
        order.setState(dto.getState());
        order.setClient(dto.getClient());

        return order;
    }
}
