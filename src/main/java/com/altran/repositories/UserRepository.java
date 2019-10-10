package com.altran.repositories;

import com.altran.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    @Override
    void delete(User deleted);
}
