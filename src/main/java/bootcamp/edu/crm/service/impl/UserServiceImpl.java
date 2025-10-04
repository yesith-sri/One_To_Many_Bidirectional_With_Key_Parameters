package bootcamp.edu.crm.service.impl;

import bootcamp.edu.crm.dto.OrderDto;
import bootcamp.edu.crm.dto.request.OrderRequest;
import bootcamp.edu.crm.entity.Orders;
import bootcamp.edu.crm.entity.User;
import bootcamp.edu.crm.repositary.UserRepositary;
import bootcamp.edu.crm.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepositary userRepositary;

    public UserServiceImpl(UserRepositary userRepositary) {
        this.userRepositary = userRepositary;
    }

    @Override
    public ResponseEntity<User> create(OrderRequest orderRequest) {
        User user = new User();
        user.setName(orderRequest.getUser().getName());

        List<OrderDto> ordersList = orderRequest.getOrders();
        List<Orders> orderDtoList = new ArrayList<>();

        for (OrderDto orderDto  : orderRequest.getOrders()) {
            Orders orders = new Orders();
            orders.setProduct(orderDto.getProduct());
            orders.setUser(user);
            orderDtoList.add(orders);
        }
        user.setOrders(orderDtoList);

        User save = userRepositary.save(user);
        return ResponseEntity.ok(save);
    }

    @Override
    public ResponseEntity retrieveUserById(Long userId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        log.info("Successfully found user with id: {}", userId);
        return ResponseEntity.ok(user);
    }

    @Override
    @Transactional // It's good practice to make service methods that modify data transactional
    public ResponseEntity<User> update(Long userId, OrderRequest orderRequest) {

        User existingUser = userRepositary.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Update the user's basic details
        existingUser.setName(orderRequest.getUser().getName());

        //  Clear the old list of orders. orphanRemoval=true will delete them from the DB.
        existingUser.getOrders().clear();

        // Create and add the new list of orders from the request
        for (OrderDto orderDto : orderRequest.getOrders()) {
            Orders newOrder = new Orders();
            newOrder.setProduct(orderDto.getProduct());
            newOrder.setUser(existingUser); // Link the new order back to the user
            existingUser.getOrders().add(newOrder);
        }

        User updatedUser = userRepositary.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(Long userId) {
        if (!userRepositary.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        // If the user exists, delete them.
        // Because of CascadeType settings and orphanRemoval=true on the User entity,
        // all associated orders will also be deleted automatically.
        userRepositary.deleteById(userId);

        log.info("Successfully deleted user with id: {}", userId);

        // Return a 204 No Content response, which is the standard for successful DELETE operations.
        return ResponseEntity.noContent().build();
    }
}

