package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T , Long> {
    T findByEmail(String email);
}