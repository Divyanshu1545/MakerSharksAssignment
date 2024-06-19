package com.divyanshu.assignment.repositories;

import com.divyanshu.assignment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByEmail(String email);
    public boolean existsByPhoneNumber(String phoneNumber);
}
