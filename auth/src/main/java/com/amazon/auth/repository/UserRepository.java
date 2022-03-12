package com.amazon.auth.repository;

import com.amazon.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findUserByUserNameAndPassword(String username,String password);
}
