package com.poe.crm.api;

import com.poe.crm.api.dto.ClientDTO;
import com.poe.crm.api.dto.ClientMapper;
import com.poe.crm.business.Client;
import com.poe.crm.business.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //retourne du json
//@Controller retourne une page html
//@RequestMapping("api")  Pour modifier l'uri comme api
public class ClientController {
    @Autowired
    CrmService crmService;

    @GetMapping("clients")
    public List<Client> getAll(){
       List<ClientDTO>json= new ArrayList<>();
        for (Client client : crmService.getAllClients()){
            ClientDTO clientDTO = ClientMapper.convertToDTO(client);
            clientDTO.setTotalExpense(crmService.calculateExpense(client.getId()));
            json.add(clientDTO);

        }
        return crmService.getAllClients();
    }

    @PostMapping("clients")
    public void createClient(@RequestBody Client client){
        crmService.addClient(client);
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable("id") Long id){
        Optional<Client> o = crmService.findClient(id);
        if (o.isPresent()){
            Client client =o.get();
            ClientDTO clientDTO = ClientMapper.convertToDTO(client);
            clientDTO.setTotalExpense(crmService.calculateExpense(client.getId()));
            return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }
    @DeleteMapping("clients/{id}")
    public ResponseEntity<String> delete (@PathVariable long id){
        boolean hasDeleted = crmService.deleteClient(id);
        if (hasDeleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND). body("L'identifiant ne correspond à aucun Client");
        }
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Client client){

        if(! id.equals( client.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("L'identifiant dans URL ne correspond pas à identifiant dans body");
        } else {
            if(crmService.updateClient(client)){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body("L'identifiant ne correspond à aucun Client");
            }
        }
    }
    @GetMapping("clients/company")
public List<Client> searchByCompanyName(@RequestParam(value="companyname", required = true , defaultValue = "") String companyName){
        return crmService.findClientByCompanyName(companyName);
    }
    @GetMapping("clients/firstnameandlastname")
    public List<Client> searchByFirstNameAndLastName(
            @RequestParam(value="firstName", required = true , defaultValue = "") String firstName,
            @RequestParam(value="lastName", required = true , defaultValue = "") String lastName){
        return crmService.findClientByFirstNameAndLastName(firstName,lastName);
    }

}
