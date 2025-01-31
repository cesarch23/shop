package com.edu.shop.mapper;

import com.edu.shop.dto.ClientDTO;
import com.edu.shop.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "id",target = "clientId"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "surname",target = "lastname"),
            @Mapping(source = "phoneNumber", target = "telephoneNumber"),
            @Mapping(source = "address", target="address"),
            @Mapping(source = "email", target="mail")
    })
    ClientDTO toDTO (Client client);
    //    private List<Shopping> shopping; omitido

    List<ClientDTO> toListDTO (List<Client> clients);

    @Mappings({
            @Mapping(source = "clientId",target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "lastname",target = "surname"),
            @Mapping(source = "telephoneNumber", target = "phoneNumber"),
            @Mapping(source = "address", target="address"),
            @Mapping(source = "mail", target="email"),
            @Mapping(target = "shopping", ignore = true)
    })
    Client toEntity(ClientDTO clientDTO);

}