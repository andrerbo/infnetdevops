package edu.br.infnet.controller;

import edu.br.infnet.infrastructure.UserMapper;
import edu.br.infnet.model.User;
import edu.br.infnet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RestTemplate restTemplate;

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{cpf}")
    public ResponseEntity<ResponseModel> get(@PathVariable Long cpf){
        try {
            UserMapper userMapper = service.fetch(cpf);
            logger.info("User fetched: " + cpf);
            return new ResponseEntity<>(
                    ResponseModel.success(200, "OK", userMapper),
                    HttpStatus.OK
            );
        } catch (Exception error) {
            logger.error("Error getting user with cpf: " + cpf);
            return new ResponseEntity<>(
                    ResponseModel.fail(404, error.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/users/{cpf}")
    public ResponseEntity<ResponseModel> delete(@PathVariable  Long cpf) {
        try {
            service.delete(cpf);
            return new ResponseEntity<>(
                    ResponseModel.success(200, "OK", null),
                    HttpStatus.OK
            );
        } catch (Exception error) {
            return new ResponseEntity<>(
                    ResponseModel.fail(404, error.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseModel> post(@RequestBody User user) {
        try {
            logger.info("User created: " + user.getCpf());
            service.create(user);
            return new ResponseEntity<>(
                    ResponseModel.success(201, "CREATED", null),
                    HttpStatus.CREATED
            );
        } catch (Exception error) {
            logger.error("Error creating user with cpf: " + user.getCpf());
            return new ResponseEntity<>(
                    ResponseModel.fail(422, error.getMessage()),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );
        }
    }

    @PutMapping("/users")
    public ResponseEntity<ResponseModel> put(@RequestBody User user) {
        try {
            service.update(user);
            return new ResponseEntity<>(
                    ResponseModel.success(200, "OK", null),
                    HttpStatus.OK
            );
        } catch (Exception error) {
            return new ResponseEntity<>(
                    ResponseModel.fail(404, error.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }
    @PostMapping("/zusers")
    public void postForUsers(@RequestBody UserMapper user) {
        try {
            logger.info("start transaction");
            restTemplate.postForLocation("http://localhost:8080/users", user);
        } catch (Exception error) {
            logger.error("error transaction");
        }
    }
}
