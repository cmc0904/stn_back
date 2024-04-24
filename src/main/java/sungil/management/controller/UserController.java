package sungil.management.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
import java.util.Objects;

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
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response) throws NotFoundUserExecption {
        Map<String, ?> login = userSerivce.login(loginDTO);

        Cookie idCookie = new Cookie(
                "token"
                , String.format("%s", (String) login.get("token"))
        ); ;
        idCookie.setHttpOnly(true);
        idCookie.setMaxAge(3600000);
        idCookie.setPath("/");
        response.addCookie(idCookie);

        return ResponseEntity.ok()
                .body(new ResponseDto<Map<?, ?>>("Login", login));
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

    @GetMapping("/logout")
    public void checkDuplicateUser(HttpServletResponse res) {
        System.out.println(1);
        Cookie cookie = new Cookie("token", null); // 삭제할 쿠키에 대한 값을 null로 지정
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        cookie.setMaxAge(0); // 유효시간을 0으로 설정해서 바로 만료시킨다.
        res.addCookie(cookie); // 응답에 추가해서 없어지도록 함

    }

    @GetMapping("/search")
    public PageVO<UserVO> liveSearch(String type, String content, Integer currentPage) {
        return userSerivce.search(type, content, currentPage);
    }


    @GetMapping("/getAdminsByPage")
    public PageVO<UserVO> liveSearch(@RequestParam(defaultValue = "1") int currentPage) {
        return userSerivce.getAdminsByPage(currentPage);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Result> updateUser(@RequestBody @Validated UserDTO userDTO) throws UpdateFailedExecption {
        return ResponseEntity.ok(userSerivce.updateUser(userDTO));
    }


    @GetMapping("/checkVaildJWT")
    public Result check() {
        return new Result("JWT_CHECKED");
    }

    @GetMapping("/getJoinDataForChart")
    public ResponseEntity<PageVO> getMyJoins(Integer currentPage){
        System.out.println(userSerivce.getJoinDataForChart(currentPage));
        return ResponseEntity.ok(userSerivce.getJoinDataForChart(currentPage));
    }
}
