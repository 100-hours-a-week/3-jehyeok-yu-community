package com.kakaotechbootcamp.community.user.repository;

import com.kakaotechbootcamp.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
