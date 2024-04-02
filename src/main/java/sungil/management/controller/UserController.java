package sungil.management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.ResponseDto;
import sungil.management.domain.Role;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.domain.User;
import sungil.management.form.LoginForm;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.service.user.UserSerivce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserSerivce userSerivce;

    @Autowired
    public UserController(UserSerivce userSerivce) {
        this.userSerivce = userSerivce;
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userSerivce.getAllUsers());
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<User>> getAllAdmins() {
        return ResponseEntity.ok(userSerivce.getAllAdmins());
    }

    @GetMapping("/getUserByUserId")
    public ResponseEntity<?> getUserById(Authentication authentication, String userId) {
        return ResponseEntity.ok(userSerivce.getUserById(userId == null ? authentication.getName() : userId));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Validated User user) {
        System.out.println(2);
        try {
            return ResponseEntity.ok(userSerivce.register(user));
        } catch (DuplicateUserExecption e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Validated LoginForm loginForm) {
        try {
            return ResponseEntity.ok(new ResponseDto<Map<?, ?>>("Login", userSerivce.login(loginForm)));
        } catch (NotFoundUserExecption e) {
            return ResponseEntity.ok(new ResponseDto<String>(e.getMessage(), null));
        }
    }

    @PostMapping("/addRole")
    public Map<String, Object> addRole(@RequestBody Role role) {
        return userSerivce.addRole(role);
    }


    @GetMapping("/checkDuplicate")
    public Map<String, String> checkDuplicateUser(String userId) {
        Map<String, String> map = new HashMap<>();

        map.put("result", userSerivce.isDuplicateUser(userId) ? "DUPLICATE_USER" : "NOT_DUPLICATE_USER");
        return map;
    }

    @GetMapping("/pageNumbers")
    public List<Integer> getUserListPageNumbers() {
        return userSerivce.getPageNumbers();
    }

    @GetMapping("/getUsersByPage")
    public List<User> getUsersByPage(int page) {
        return userSerivce.getUserByPageNumber(page);
    }

    @GetMapping("/search")
    public List<User> liveSearch(String type, String content) {
        return userSerivce.search(type, content);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody @Validated User user){
        return ResponseEntity.ok(userSerivce.updateUser(user));
    }
}
