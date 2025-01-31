package com.edu.shop.serviceImpls;

import com.edu.shop.dto.ClientDTO;
import com.edu.shop.entity.Client;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.mapper.ClientMapper;
import com.edu.shop.repository.ClientRepository;
import com.edu.shop.service.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientInterface {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;


    @Override
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
        throw new BusinessException(BusinessExceptionReason.CLIENT_NOT_FOUND,clientDTO.getMail());
    }

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


}
