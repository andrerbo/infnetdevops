package edu.br.infnet.infrastructure;

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
public class UserMapperTest {

    @Autowired
    private UserMapperRepository repository;

    @Test
    public void testUserMapper_save(){
        User user = new User();
        user.setCpf(999_999_999_99L);
        user.setName("Andre");
        user.setLastName("Ribeiro");
        user.setEmail("andreribeiro@gmail.com");
        user.setAge(30);

        UserMapper userMapper = UserMapper.toDTO(user);
        repository.save(userMapper);

        Optional<UserMapper> userMapperOpt = repository.findById(1);
        Assert.assertTrue(userMapperOpt.isPresent());
        Assert.assertNotNull(userMapperOpt.get());
        Assert.assertEquals(userMapper, userMapperOpt.get());

    }

}
