package sungil.management.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sungil.management.dto.user.RoleDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.Result;

import sungil.management.dto.user.LoginDTO;
import sungil.management.dto.user.UserDTO;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.jwt.JwtTokenProvider;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.repository.users.UserRepository;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.user.UserVO;

import java.util.*;

@Service
public class UserServiceImpl implements UserSerivce {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, JwtTokenValidator jwtTokenValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenValidator = jwtTokenValidator;
        this.passwordEncoder = passwordEncoder;
    }

    public PageVO<UserVO> getAllUsers(int currentPage) {
        List<UserVO> currentPageUsers = userRepository.getAllUsers((currentPage - 1) * 5);
        int length = userRepository.getAllUserTotalLength();

        return new PageVO<>(length, currentPageUsers);
    }

    @Override
    public List<UserVO> getAllAdmins() {
        return userRepository.getAllAdmins();
    }

    @Override
    public Map<String, Object> login(LoginDTO loginDTO) throws NotFoundUserExecption {
        Optional<UserVO> findUser = userRepository.getUserByUserId(loginDTO.getUserId());
        Map<String, Object> map = new HashMap<>();

        if(findUser.isEmpty()) {
            throw new NotFoundUserExecption();
        }

        UserVO user = findUser.get();

        if(passwordEncoder.matches(loginDTO.getPassword(), user.getUserPassword())) {
            String tk = jwtTokenProvider.generateToken(user, userRepository.getRoleByUserId(user.getUserId()));
            map.put("token", tk);
            map.put("roles", jwtTokenValidator.getUserRolesFromToken(tk));

            return map;
        } else {
            throw new NotFoundUserExecption();

        }
    }

    public Result register(UserDTO user) throws DuplicateUserExecption {
        Optional<UserVO> findUser = userRepository.getUserByUserId(user.getUserId());

        if (findUser.isEmpty()) {
            user.encodePassword();
            userRepository.insertUser(user);
            return new Result("REGISTER_COMPLETE");
        } else {
            throw new DuplicateUserExecption();
        }


    }

    @Override
    public Map<String, ?> getUserById(String id) {
        Optional<UserVO> findUser = userRepository.getUserByUserId(id);

        Map<String, Object> map = new HashMap<>();
        map.put("result" , findUser.isPresent() ? findUser.get() : "CAN_NOT_FOUND_USER");

        return map;
    }

    @Override
    public Result addRole(RoleDTO role) throws CreateFailedExecption {

        try {
            userRepository.addRole(role);
            return new Result("ADD_ROLE_COMPLETE");
        } catch (Exception e) {
            throw new CreateFailedExecption();
        }

    }

    @Override
    public boolean isDuplicateUser(String userId) {
        Optional<UserVO> user = userRepository.getUserByUserId(userId);
        return user.isPresent();
    }

    @Override
    public PageVO<UserVO> search(String type, String content, int currentPage) {
        try {
            List<UserVO> searchedUser = userRepository.searchUserBy(type, content, (currentPage - 1) * 5);
            int totalData = userRepository.searchUserTotalLength(type, content);
            return new PageVO<>(totalData, searchedUser);
        } catch (Exception e) {
            return new PageVO<>(0, new ArrayList<>());
        }

    }

    @Override
    public Result updateUser(UserDTO user) throws UpdateFailedExecption {
        try {
            userRepository.updateUser(user);
            return new Result("UPDATE");
        } catch (Exception e ) {
            throw new UpdateFailedExecption();
        }

    }

    @Override
    public PageVO<UserVO> getAdminsByPage(int page) {
        return new PageVO<>(
            userRepository.getAdminTotalLength()
            ,userRepository.getAdminByPage((page - 1) * 5)
        );
    }


}
