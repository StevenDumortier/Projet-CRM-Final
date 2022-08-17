package com.poe.crm.api;


import com.poe.crm.business.Order;
import com.poe.crm.business.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //retourne du json
//@Controller retourne une page html
//@RequestMapping("api")  Pour modifier l'uri comme api
public class OrderController {
    @Autowired
    CrmService crmService;

    @GetMapping("orders")
    public List<Order> getAll(){
        return crmService.getAllOrders();
    }

    @PostMapping("orders")
    public void createOrder(@RequestBody Order order){
        crmService.addOrder(order);
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id") Long id){
        Optional<Order> o = crmService.findOrder(id);
        if (o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }
    @DeleteMapping("orders/{id}")
    public ResponseEntity<String> delete (@PathVariable long id){
        boolean hasDeleted = crmService.deleteOrder(id);
        if (hasDeleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND). body("L'identifiant ne correspond à aucun Order");
        }
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Order order){

        if(! id.equals( order.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("L'identifiant dans URL ne correspond à identifiant dans body");
        } else {
            if(crmService.updateOrder(order)){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body("L'identifiant ne correspond à aucun Order");
            }
        }
    }
}