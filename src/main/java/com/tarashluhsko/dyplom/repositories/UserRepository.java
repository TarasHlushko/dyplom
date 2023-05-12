package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T , Long> {
    T findByEmail(String email);
}
