package dev.eldhdpswl.auth.entity;

import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
