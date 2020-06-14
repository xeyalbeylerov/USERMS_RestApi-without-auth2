package com.company.repo;
import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findByNameContainingAndSurnameContaining(String name, String surname);
    User findByEmailAndPassword(String email, String password);

//    User getById(int id);// default getOne metodu var.

    User findByEmail(String email);
    boolean existsUserByEmail(String email);
    boolean existsUserById(Integer id);

}



