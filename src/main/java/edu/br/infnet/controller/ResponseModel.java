package edu.br.infnet.controller;

import edu.br.infnet.infrastructure.UserMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseModel {

    private Integer statusCode;

    private String message;

    private List<UserMapper> users;

    public static ResponseModel success(Integer statusCode, String message, UserMapper user) {
        return new ResponseModel(statusCode, message, Arrays.asList(user));
    }

    public static ResponseModel fail(Integer statusCode, String error) {
        return new ResponseModel(statusCode, error, List.of());
    }

}
