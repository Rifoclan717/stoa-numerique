package com.example.stoa_backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.stoa_backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
