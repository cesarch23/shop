package com.edu.shop.service;

import com.edu.shop.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientInterface {
    public ClientDTO save(ClientDTO clientDTO) ;
    public Optional<ClientDTO> update(ClientDTO clientDTO);
    public List<ClientDTO> getAllClients();
    public Optional<ClientDTO> getByMail(String mail);
}
