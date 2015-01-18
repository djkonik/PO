package uaiContacts.repository;

import org.springframework.data.repository.CrudRepository;
import uaiContacts.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findById(int id);
}
