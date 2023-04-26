package edu.br.infnet.infrastructure;

import edu.br.infnet.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEVOPS_USER")
public class UserMapper {

    @Id
    private Long cpf;

    private String name;

    private String lastName;

    private Integer age;

    private String email;

    private LocalDate createdAt;


    public static UserMapper toDTO(User user) {
        return new UserMapper(
                user.getCpf(),
                user.getName(),
                user.getLastName(),
                user.getAge(),
                user.getEmail(),
                LocalDate.now()
        );

    }

}
