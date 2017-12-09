package co.com.jetprogramming.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.jetprogramming.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}