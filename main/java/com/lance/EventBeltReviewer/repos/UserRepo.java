package com.lance.EventBeltReviewer.repos;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.lance.EventBeltReviewer.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);
    
    boolean existsByEmail(String email);
}
