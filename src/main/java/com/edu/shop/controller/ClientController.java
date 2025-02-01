package com.edu.shop.controller;

import com.edu.shop.dto.ClientDTO;
import com.edu.shop.serviceImpls.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("add")
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO){
        ClientDTO clientDto2 = clientService.save(clientDTO);
        return new ResponseEntity<>(clientDto2, HttpStatus.CREATED);

    }
    @GetMapping("all")
    public ResponseEntity<List<ClientDTO>> all(){
        return new ResponseEntity<>(clientService.getAllClients(),HttpStatus.OK);
    }
    @PatchMapping(path = "update")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO){
        return new ResponseEntity<>(this.clientService.update(clientDTO).get(),HttpStatus.OK);
    }




}