package com.poe.crm.api.dto;

import com.poe.crm.business.Client;

public class ClientMapper {

    public static ClientDTO convertToDTO(Client client){
        ClientDTO dto = new ClientDTO();

            dto.setId(client.getId());
            dto.setCompanyName(client.getCompanyName());
            dto.setPrenom(client.getFirstName());
            dto.setLastName(client.getLastName());
            dto.setEmail(client.getEmail());
            dto.setPhone(client.getPhone());
            dto.setAddress(client.getAddress());
            dto.setZipCode(client.getZipCode());
            dto.setCity(client.getCity());
            dto.setCountry(client.getCountry());
            dto.setState(client.getState());
            dto.setOrders(client.getOrders());

            return dto;
        }

    public static Client convertToEntity(ClientDTO dto) {
        Client client = new Client();

        client.setId(dto.getId());
        client.setCompanyName(dto.getCompanyName());
        client.setFirstName(dto.getPrenom());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setAddress(dto.getAddress());
        client.setZipCode(dto.getZipCode());
        client.setCity(dto.getCity());
        client.setCountry(dto.getCountry());

        client.setState(dto.getState());

        client.setOrders(dto.getOrders());

        return client;
    }
}




