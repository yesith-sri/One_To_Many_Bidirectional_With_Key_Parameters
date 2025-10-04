package bootcamp.edu.crm.service;

import bootcamp.edu.crm.dto.request.OrderRequest;
import bootcamp.edu.crm.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<User> create(OrderRequest orderRequest);

    ResponseEntity retrieveUserById(Long userId);

    ResponseEntity<?> update(Long userId, OrderRequest orderRequest);

    ResponseEntity<Void> deleteUser(Long userId);
}
