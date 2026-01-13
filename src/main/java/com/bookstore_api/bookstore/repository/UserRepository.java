package com.bookstore_api.bookstore.repository;

import com.bookstore_api.bookstore.dto.LoginRequestDTO;
import com.bookstore_api.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findOneByEmailIdIgnoreCase(String emailId);

}
