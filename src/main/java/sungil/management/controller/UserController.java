package sungil.management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.ResponseDto;
import sungil.management.domain.Result;
import sungil.management.domain.Role;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.domain.User;
import sungil.management.form.LoginForm;
import sungil.management.service.user.UserSerivce;
import sungil.management.test.PageVO;

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
    public ResponseEntity<PageVO<User>> getAllUsers(int currentPage) {
        return ResponseEntity.ok(userSerivce.getAllUsers(currentPage));
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
        try {
            return ResponseEntity.ok(userSerivce.register(user));
        } catch (DuplicateUserExecption e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginForm loginForm) {
        try {
            return ResponseEntity.ok(new ResponseDto<Map<?, ?>>("Login", userSerivce.login(loginForm)));
        } catch (NotFoundUserExecption e) {
            return ResponseEntity.ok(new ResponseDto<String>(e.getMessage(), null));
        }
    }

    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role) {
        return userSerivce.addRole(role);
    }


    @GetMapping("/checkDuplicate")
    public Map<String, String> checkDuplicateUser(String userId) {
        Map<String, String> map = new HashMap<>();

        map.put("result", userSerivce.isDuplicateUser(userId) ? "DUPLICATE_USER" : "NOT_DUPLICATE_USER");
        return map;
    }

    @GetMapping("/pageNumbers")
    public List<Integer> getUserListPageNumbers(String type) {
        return userSerivce.getPageNumbers(type);
    }

    @GetMapping("/getUsersByPage")
    public List<User> getUsersByPage(int page) {
        return userSerivce.getUserByPageNumber(page);
    }

    @GetMapping("/getAdminsByPage")
    public List<User> getAdminByPage(int page) {
        System.out.println(userSerivce.getAdminByPageNumber(page));
        return userSerivce.getAdminByPageNumber(page);
    }

    @GetMapping("/search")
    public PageVO<User> liveSearch(String type, String content, Integer currentPage) {
        System.out.println(type);
        System.out.println(content);
        System.out.println(currentPage);
        return userSerivce.search(type, content, currentPage);

    }

    @PutMapping("/updateUser")
    public ResponseEntity<Result> updateUser(@RequestBody @Validated User user){
        return ResponseEntity.ok(userSerivce.updateUser(user));
    }
}
