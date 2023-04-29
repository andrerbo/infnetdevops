package edu.br.infnet.service;

import edu.br.infnet.infrastructure.UserMapper;
import edu.br.infnet.infrastructure.UserMapperRepository;
import edu.br.infnet.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapperRepository repository;

    public UserService(UserMapperRepository repository) {
        this.repository = repository;
    }

    public void create(User user) throws Exception {
        if(!user.isValidCpf()){
            throw new Exception("CPF INVALID");
        }

        if(!user.isValidEmail()){
            throw new Exception("EMAIL INVALID");
        }

        UserMapper userMapper = UserMapper.toDTO(user);
        repository.save(userMapper);
    }

    public UserMapper fetch(Long cpf) throws Exception {
        if(!repository.existsByCpf(cpf)){
            throw new Exception("USER NOT FOUND");
        }

        Optional<UserMapper> userMapperOpt = repository.findByCpf(cpf);
        if(!userMapperOpt.isPresent()){
            throw new Exception("GENERIC ERROR");
        }

        UserMapper userMapper = userMapperOpt.get();
        return userMapper;
    }

    public void delete(Long cpf) throws Exception {
        if(!repository.existsByCpf(cpf)) {
            throw new Exception("USER NOT FOUND");
        }

        UserMapper userMapper = repository.findByCpf(cpf).get();
        repository.delete(userMapper);
    }

    public void update(User user) throws Exception {
        if(!repository.existsByCpf(user.getCpf())) {
            throw new Exception("USER NOT FOUND");
        }

        Optional<UserMapper> userMapperOpt = repository.findByCpf(user.getCpf());
        if(!userMapperOpt.isPresent()){
            throw new Exception("GENERIC ERROR");
        }
        UserMapper userMapper = userMapperOpt.get();
        userMapper.setName(user.getName());
        userMapper.setLastName(user.getLastName());
        userMapper.setAge(user.getAge());
        userMapper.setEmail(user.getEmail());
        repository.save(userMapper);
    }

}
