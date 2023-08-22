package com.msq.CustomErrorAndValidation.service;

import com.msq.CustomErrorAndValidation.dto.UserRequest;
import com.msq.CustomErrorAndValidation.entity.User;
import com.msq.CustomErrorAndValidation.exception.UserNotFound;
import com.msq.CustomErrorAndValidation.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserRequest userRequest){
        User user=User.build(0,userRequest.getName(),
                userRequest.getEmail(),userRequest.getMobile(),
                userRequest.getGender(),userRequest.getAge(),
                userRequest.getNationality());
        return   userRepository.save(user);

    }

    public List<User> getAllUser(){
        List<User> all = userRepository.findAll();
        return all;
    }
   //@CacheEvict is used to empty cache whatever we created using @Cacheable
    //@CacheEvict(value="addresses", allEntries=true)


    // here we apply condition on id Now it will cache only for the IDs less Than 3
   @Cacheable(value = "addresses" , key = "#id" ,condition = "#id<3")
   /*
   CachePut we should use this with update method bcoz in that case we just want to update the
    cache instead of making new entry for that particular id
    @CachePut(value = "addresses" , key = "#id" ,condition = "#id<3")
   */
   public User getUser(int id) throws UserNotFound {
        if ( id!=0) {

            Optional<User> user = userRepository.findByUserId(id);
            if (user.isPresent() == true) {
                return user.get();
            } else {
//                System.out.println(id);
                throw new UserNotFound("User with id= " + id + " not present  in the our database");
            }
        } else {
            throw new UserNotFound("Id should not be zero");
        }
    }
    

}
