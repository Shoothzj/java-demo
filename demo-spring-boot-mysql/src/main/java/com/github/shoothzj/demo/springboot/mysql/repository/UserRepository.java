package com.github.shoothzj.demo.springboot.mysql.repository;

import com.github.shoothzj.demo.springboot.mysql.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
