package com.scm.SmartContactManager.services.Impl;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.helpers.AppConstants;
import com.scm.SmartContactManager.helpers.ResourceNotFoundException;
import com.scm.SmartContactManager.repository.UserRepo;
import com.scm.SmartContactManager.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

//        User id random generation
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
//        password Encoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));


//        set role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info(user.getProvider().toString());

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
            return  userRepo.findById(id);
     }

    @Override
    public Optional<User> updateUser(User user) {


        User user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
            user2.setName(user.getName());
            user2.setEmail(user.getEmail());
            user2.setPassword(user.getPassword());
            user2.setAbout(user.getAbout());
            user2.setPhoneNumber(user.getPhoneNumber());
            user2.setProfilePic(user.getProfilePic());
            user2.setEnabled(user.isEnabled());
            user2.setEmailVarified(user.isEmailVarified());
            user2.setPhoneVarified(user.isPhoneVarified());
            user2.setProvider(user.getProvider());
            user2.setProviderUserId(user.getProviderUserId());

        User save = userRepo.save(user2);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        userRepo.delete(user2);

    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2!=null ? true : false;
     }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user!=null ? true : false;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
}
