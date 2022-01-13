//package cred.firstProject.repository;
//
//public class UserRepository {
//}

package cred.firstProject.repository;
import org.springframework.data.repository.CrudRepository;
import cred.firstProject.model.User;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByEmailId(String emailId);
    List<User> findByMobile(Integer mobile);
    List<User> findAllByOrderByDobAsc();
}