package com.poe.crm.business.service;

import com.poe.crm.api.dto.OrderDTO;
import com.poe.crm.api.dto.OrderMapper;
import com.poe.crm.business.Client;
import com.poe.crm.business.Order;
import com.poe.crm.dao.ClientRepository;
import com.poe.crm.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrmService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addClient(Client client) {
        System.out.println(client);
        clientRepository.save(client);
    }

    public void addOrder(OrderDTO orderdto) { //boolean true faire  false pas faire
        //verifier que le client existe dans la dtb
        //verifier que le state client est actif
        //value =    pas besoin optionnel
        Order order = OrderMapper.convertToEntity(orderdto);
        orderRepository.save(order);
    }

    public Optional<Client> findClient(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Order> findOrder(Long id) {
        return orderRepository.findById(id);
    }

    public boolean deleteClient(Long id) {
        //Iterator (acces concurentiel) fait pour parcourir notre collection tout en faisant une action

        //Iterator<Client> it = clients.iterator();
        //while(it.hasNext()){
        //Client client = it.next();
        //if(client.getId().equals(id)){
        //it.remove();
        //
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteOrder(Long id) {

        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateClient(Client client) {
        if (clientRepository.existsById(client.getId())) {
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    public boolean updateOrder(Order order) {
        if (orderRepository.existsById(order.getId())) {
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public List<Client> findClientByCompanyName(String companyName) {
        return clientRepository.findAllByCompanyName(companyName);
    }

    public List<Client> findClientByFirstNameAndLastName(String firstName, String lastName) {
        return clientRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }

    public float calculateExpense(Long clientId) {
        float total = 0;
        Optional<Client> op = clientRepository.findById(clientId);
        if (op.isPresent()) {
            Client client = op.get();
            for (Order order : client.getOrders()) {
                total = total + order.getNbDays() * order.getUnitPrice();
            }

        }
        return total;
    }
}
