package bootcamp.edu.crm.controller;

import bootcamp.edu.crm.dto.request.OrderRequest;
import bootcamp.edu.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity post(@RequestBody OrderRequest orderRequest) {
        return userService.create(orderRequest);

    }

    @GetMapping("/by_id/{userId}")
    public ResponseEntity retrieveUserById(@PathVariable Long userId) {
        return userService.retrieveUserById(userId);
    }


    @PutMapping("/by_id/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody OrderRequest orderRequest) {
        return userService.update(userId, orderRequest);
    }

    @DeleteMapping("/by_id/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}
