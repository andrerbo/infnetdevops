package edu.br.infnet.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMapperRepository extends CrudRepository<UserMapper, Integer> {

    boolean existsByCpf(Long cpf);

    Optional<UserMapper> findByCpf(Long cpf);

}
