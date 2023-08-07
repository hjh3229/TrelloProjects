package com.example.trelloprojects.user.repository;

import com.example.trelloprojects.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
