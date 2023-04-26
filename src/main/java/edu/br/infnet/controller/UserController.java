package edu.br.infnet.controller;

import edu.br.infnet.infrastructure.UserMapper;
import edu.br.infnet.model.User;
import edu.br.infnet.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{cpf}")
    public ResponseEntity<ResponseModel> get(@PathVariable Long cpf){
        try {
            UserMapper userMapper = service.fetch(cpf);
            return new ResponseEntity<>(
                    ResponseModel.success(200, "OK", userMapper),
                    HttpStatus.OK
            );
        } catch (Exception error) {
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
            service.create(user);
            return new ResponseEntity<>(
                    ResponseModel.success(201, "CREATED", null),
                    HttpStatus.CREATED
            );
        } catch (Exception error) {
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
}
