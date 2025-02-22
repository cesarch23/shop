package com.edu.shop.serviceImpls;

import com.edu.shop.dto.ClientDTO;
import com.edu.shop.entity.Client;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.mapper.ClientMapper;
import com.edu.shop.repository.ClientRepository;
import com.edu.shop.service.ClientInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService implements ClientInterface {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;


    @Override
    @Transactional
    public ClientDTO save(ClientDTO clientDTO)
    {
        if(this.exitByEmail(clientDTO.getMail())){
            throw new BusinessException(BusinessExceptionReason.CLIENT_WITH_EMAIL_EXITS, clientDTO.getMail());
        }
        Client client = clientRepository.save(clientMapper.toEntity(clientDTO));
        return clientMapper.toDTO(client);

    }

    @Override
    public Optional<ClientDTO> update(ClientDTO clientDTO) {
        if(this.exitByEmail( clientDTO.getMail() )){
            int updatedRows = clientRepository.updateClient(
                    clientDTO.getName(),
                    clientDTO.getLastname(),
                    clientDTO.getTelephoneNumber(),
                    clientDTO.getAddress(),
                    clientDTO.getMail()
            );
            return updatedRows > 0  ?
                    clientRepository.findClientByEmail(clientDTO.getMail()).map(clientMapper::toDTO) :
                    Optional.empty();
        }
        throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"El cliente con el email: "+clientDTO.getMail());
    }

    @Secured("ROLE_ADMIN")
    @Override
    public List<ClientDTO> getAllClients() {
        List <Client> clients = clientRepository.findAll();
        return  clientMapper.toListDTO(clients);
    }

    @Override
    public Optional<ClientDTO> getByMail(String mail) {
        return null;
        /*return clientRepository.findClientByEmail(mail)
                .map(clientMapper::toDTO)//// .map(client->this.clientMapper.toDTO(client));
                .orElseThrow()*/

    }
    public boolean exitByEmail(String email){
        return clientRepository.existsClientByEmail(email);
    }
    public boolean exitById(UUID clientId){
        return clientRepository.existsById(clientId);
    }

}
