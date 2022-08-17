package com.poe.crm.api.dto;

import com.poe.crm.business.Client;
import com.poe.crm.business.OrderState;


public class OrderDTO {



        private Long id;
        private String typePresta;
        private String designation;
        private int nbDays;
        private float unitPrice;
        private OrderState state;
        private Client client;


        public OrderDTO() {

        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public int getNbDays() {
            return nbDays;
        }

        public void setNbDays(int nbDays) {
            this.nbDays = nbDays;
        }

        public float getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(float unitPrice) {
            this.unitPrice = unitPrice;
        }

        public OrderState getState() {
            return state;
        }

        public void setState(OrderState state) {
            this.state = state;
        }

        public String getTypePresta() {
            return typePresta;
        }

        public void setTypePresta(String typePresta) {
            this.typePresta = typePresta;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", typePresta='" + typePresta + '\'' +
                    ", designation='" + designation + '\'' +
                    ", nbDays=" + nbDays +
                    ", unitPrice=" + unitPrice +
                    ", state=" + state +
                    ", client=" + client +
                    '}';
        }
    }

