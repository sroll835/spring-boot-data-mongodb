package org.ada.school.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>
{


    User create(User user);

    List<User> all();

    User update(User userDto, String id);

    List<User> findUsersWithNameOrLastNameLike(String queryText);

    List<User> findUsersCreatedAfter(Date startDate);
}
