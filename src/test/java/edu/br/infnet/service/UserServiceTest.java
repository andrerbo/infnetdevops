package edu.br.infnet.service;

import edu.br.infnet.infrastructure.UserMapper;
import edu.br.infnet.infrastructure.UserMapperRepository;
import edu.br.infnet.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapperRepository repository;

    @Test
    public void userCreateMethod_success() throws Exception {
        User user = new User();
        user.setCpf(999_999_999_90L);
        user.setName("Anderson");
        user.setLastName("Ribeiro");
        user.setAge(23);
        user.setEmail("andersonribeiro@gmail.com");

        service.create(user);
        Optional<UserMapper> userMapperOpt = repository.findByCpf(user.getCpf());
        Assert.assertTrue(userMapperOpt.isPresent());
        Assert.assertNotNull(userMapperOpt.get());
        Assert.assertEquals("Anderson", userMapperOpt.get().getName());
    }

    @Test
    public void userCreateMethod_exception() throws Exception {
        User user = new User();
        user.setCpf(999_999_999_90L);
        user.setName("Anderson");
        user.setLastName("Ribeiro");
        user.setAge(23);
        user.setEmail("andersonribeiro-gmail.com");

        Assert.assertThrows(Exception.class ,() -> service.create(user));
        
    }

}
