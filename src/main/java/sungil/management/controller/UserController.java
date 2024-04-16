package sungil.management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sungil.management.dto.user.RoleDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.ResponseDto;
import sungil.management.vo.etc.Result;

import sungil.management.dto.user.LoginDTO;
import sungil.management.dto.user.UserDTO;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;

import sungil.management.service.user.UserSerivce;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.user.UserVO;

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
    public ResponseEntity<PageVO<UserVO>> getAllUsers(int currentPage) {
        return ResponseEntity.ok(userSerivce.getAllUsers(currentPage));
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<UserVO>> getAllAdmins() {
        return ResponseEntity.ok(userSerivce.getAllAdmins());
    }

    @GetMapping("/getUserByUserId")
    public ResponseEntity<?> getUserById(Authentication authentication, String userId) {
        return ResponseEntity.ok(userSerivce.getUserById(userId == null ? authentication.getName() : userId));
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Validated UserDTO userDTO) throws DuplicateUserExecption {
        return ResponseEntity.ok(userSerivce.register(userDTO));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws NotFoundUserExecption {
        return ResponseEntity.ok(new ResponseDto<Map<?, ?>>("Login", userSerivce.login(loginDTO)));
    }

    @PostMapping("/addRole")
    public Result addRole(@RequestBody RoleDTO role) throws CreateFailedExecption {
        return userSerivce.addRole(role);
    }


    @GetMapping("/checkDuplicate")
    public Map<String, String> checkDuplicateUser(String userId) {
        Map<String, String> map = new HashMap<>();

        map.put("result", userSerivce.isDuplicateUser(userId) ? "DUPLICATE_USER" : "NOT_DUPLICATE_USER");
        return map;
    }

    @GetMapping("/search")
    public PageVO<UserVO> liveSearch(String type, String content, Integer currentPage) {
        return userSerivce.search(type, content, currentPage);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Result> updateUser(@RequestBody @Validated UserDTO userDTO) throws UpdateFailedExecption {
        return ResponseEntity.ok(userSerivce.updateUser(userDTO));
    }
}
