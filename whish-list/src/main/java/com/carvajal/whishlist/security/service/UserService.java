package com.carvajal.whishlist.security.service;

import com.carvajal.whishlist.security.entity.User;
import com.carvajal.whishlist.security.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    RepositoryUser repositoryUser;

    public Optional<User> getByUserName(String userName){
        return repositoryUser.findByUserName(userName);
    }

    public boolean existsByUserName(String userName){
        return repositoryUser.existsByUserName(userName);
    }

    public boolean existsByEmail(String email){
        return repositoryUser.existsByEmail(email);
    }

    public int save(User user){
        User u= repositoryUser.save(user);
        return u.getId();
    }
}