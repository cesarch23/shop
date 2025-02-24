package com.edu.shop.serviceImpls;

import com.edu.shop.dto.UserDTO;
import com.edu.shop.dto.UserRolDTO;
import com.edu.shop.entity.User;
import com.edu.shop.entity.UserRol;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.repository.UserRepository;
import com.edu.shop.repository.UserRolRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserRolRepository userRolRepository;
    private  final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRolRepository userRolRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRolRepository = userRolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO register(UserDTO userDTO){
        validarEmailUnico(userDTO.getEmail());
        User newUser=new User();

        newUser.setName(userDTO.getName());
        newUser.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        newUser.setUsername(userDTO.getEmail());
        newUser.setLocked(false);
        newUser.setDisabled(false);
        User user =  this.userRepository.save(newUser);

        UserRol role = new UserRol();
        role.setUserId(user.getId());
        List<UserRolDTO> userRolDTOS= new ArrayList<>();
        userDTO.getRoles().forEach(
                rolDTO-> {
                    role.setRole(rolDTO.getRole());
                    role.setGrantedDate(LocalDateTime.now());
                    UserRol rolInserted = userRolRepository.save(role);
                    userRolDTOS.add(new UserRolDTO(rolInserted.getRole()));
                }
        );
        return new UserDTO(user.getId(),user.getUsername(),user.getName(),userRolDTOS);
    }
    private void validarEmailUnico(String email){
        if(userRepository.existsByUsername(email))
            throw new BusinessException(BusinessExceptionReason.ENTITY_EXITS,"El usuario con el email: ");
    }
}
