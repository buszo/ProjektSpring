package dev.projekt.students.courses.controller;

import dev.projekt.students.courses.dto.UserDTO;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.service.UserDetailsServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserDetailsServiceImp userService;

    public UserController(UserDetailsServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

//    @GetMapping("/admin_only")
//    public ResponseEntity<String> adminOnly() {
//        return ResponseEntity.ok("Hello from admin only url");
//    }

    //ToDo przenieś mapowanie do seriwsu
    @GetMapping("/self")
    public ResponseEntity<UserDTO> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        UserDTO userDTO = new UserDTO(
                currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getFirstName(),
                currentUser.getLastName(),
                currentUser.getRole()
        );

        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/admin_only")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        List<UserDTO> userDTOs = users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

}
