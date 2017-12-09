package co.com.jetprogramming.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.jetprogramming.model.UserLog;

public interface UserLogRepository extends CrudRepository<UserLog, Long> {

}