package bootcamp.edu.crm.repositary;

import bootcamp.edu.crm.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositary extends CrudRepository<User ,Long> {
}
