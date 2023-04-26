package edu.br.infnet.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long cpf;

    private String name;

    private String lastName;

    private Integer age;

    private String email;

    public boolean isValidEmail() {
        if(!this.email.contains("@")) {
            return false;
        }
        return true;
    }

    public boolean isValidCpf() {
        return true;
    }

}
