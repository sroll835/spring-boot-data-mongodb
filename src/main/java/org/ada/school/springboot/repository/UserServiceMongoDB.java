package org.ada.school.springboot.repository;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class UserServiceMongoDB implements UserRepository
{
    private HashMap<User,String> mongoRepository = new HashMap<User,String>();
    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user)
    {
        Integer id = mongoRepository.size();
        user.setId(id.toString());
        mongoRepository.put(user,id.toString());
        return user;
    }

    @Override
    public Optional<User> findById(String id)
    {
        User u = null;
        Set<User> users = mongoRepository.keySet();
        for(User user:users){
            String us = mongoRepository.get(user);
            if(us.equals(id)){
                u =user;
            }
        }
        return Optional.ofNullable(u);
    }

    @Override
    public List<User> all()
    {
        List<User> userss = new ArrayList<>();
        Set<User> users = mongoRepository.keySet();
        for(User user:users){
            userss.add(user);
        }
        return userss;
    }

    @Override
    public void deleteById(String id)
    {
        Set<User> users = mongoRepository.keySet();
        for(User user:users){
            String us = mongoRepository.get(user);
            if(us.equals(id)){
                mongoRepository.remove(user);
            }
        }

    }

    @Override
    public User update(User user, String id)
    {
        mongoRepository.replace(user,id);
        return user;
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        List<User> userss = new ArrayList<User>();
        Set<User> users = mongoRepository.keySet();
        for(User user:users){
            if(user.getName().equals(queryText)){
                userss.add(user);
            }
        }
        return userss;
    }

    @Override
    public List<User> findUsersCreatedAfter(Date startDate) {
        List<User> userss = new ArrayList<User>();
        Set<User> users = mongoRepository.keySet();
        for(User user:users){
            if(user.getCreatedAt().after(startDate)){
                userss.add(user);
            }
        }
        return userss;
    }

}