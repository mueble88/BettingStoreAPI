package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    List<User> findById(long id);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.name = :name, user.lastName = :lastName, user.email = :email, user.phone = :phone, user.dateOfBirth = :dateOfBirth, user.createdAt = :createdAt, user.lastUpdatedAt = :lastUpdatedAt  WHERE user.id = :id")
    int updatePublicFields( @Param("id") long id,
                            @Param("name")String name,
                            @Param("lastName")String lastName,
                            @Param("email")String email,
                            @Param("phone")String phone,
                            @Param("dateOfBirth")Date dateOfBirth,
                            @Param("createdAt")long createdAt,
                            @Param("lastUpdatedAt")long lastUpdatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.password = :password WHERE user.email = :email")
    int updatePasswordField(@Param("email")String email,
                            @Param("password") String password);

}


