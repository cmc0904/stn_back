package sungil.management.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.Result;
import sungil.management.domain.Role;
import sungil.management.domain.User;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.form.LoginForm;
import sungil.management.jwt.JwtTokenProvider;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.repository.users.UserRepository;
import sungil.management.test.PageVO;

import java.util.*;

@Service
public class UserServiceImpl implements UserSerivce {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, JwtTokenValidator jwtTokenValidator) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenValidator = jwtTokenValidator;
    }

    public PageVO<User> getAllUsers(int currentPage) {
        List<User> currentPageUsers = userRepository.getAllUsers((currentPage - 1) * 5);
        int length = userRepository.getAllUserTotalLength();

        return new PageVO<>(length, currentPageUsers);
    }

    @Override
    public List<User> getAllAdmins() {
        return userRepository.getAllAdmins();
    }

    public Map<String, ?> login(LoginForm loginForm) throws NotFoundUserExecption {
        Optional<User> findUser = userRepository.getUserByUserIdAndPassword(loginForm.getUserId(), loginForm.getPassword());
        Map<String, Object> map = new HashMap<>();

        if(findUser.isPresent()) {
            User user = findUser.get();
            String tk = jwtTokenProvider.generateToken(user, userRepository.getRoleByUserId(user.getUserId()));
            map.put("token", tk);
            map.put("roles", jwtTokenValidator.getUserRolesFromToken(tk));

            return map;
        } else {
            throw new NotFoundUserExecption();
        }
    }

    public Result register(User user) throws DuplicateUserExecption {
        Optional<User> findUser = userRepository.getUserByUserId(user.getUserId());


        if (findUser.isEmpty()) {
            userRepository.insertUser(user);
            return new Result("REGISTER_COMPLETE");
        } else {
            throw new DuplicateUserExecption();
        }


    }

    @Override
    public Map<String, ?> getUserById(String id) {
        Optional<User> findUser = userRepository.getUserByUserId(id);

        Map<String, Object> map = new HashMap<>();
        map.put("result" , findUser.isPresent() ? findUser.get() : "CAN_NOT_FOUND_USER");

        return map;
    }

    @Override
    public Result addRole(Role role) {

        try {
            userRepository.addRole(role);
            return new Result("ADD_ROLE_COMPLETE");
        } catch (Exception e) {
            return new Result("CAN_NOT_ADD_ROLE");
        }

    }

    @Override
    public boolean isDuplicateUser(String userId) {
        Optional<User> user = userRepository.getUserByUserId(userId);
        System.out.println(user);
        return user.isPresent();
    }

    @Override
    public List<Integer> getPageNumbers(String type) {

        return new ArrayList<>();

    }

    @Override
    public List<User> getUserByPageNumber(int pageNumber) {
        List<User> limitData = userRepository.getUsersLimit(pageNumber * 5);
        return limitData.subList(pageNumber * 5 - 5, limitData.size());
    }

    @Override
    public PageVO<User> search(String type, String content, int currentPage) {
        try {
            List<User> searchedUser = userRepository.searchUserBy(type, content, (currentPage - 1) * 5);
            System.out.println(searchedUser);
            int totalData = userRepository.searchUserTotalLength(type, content);
            return new PageVO<>(totalData, searchedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageVO<>(0, new ArrayList<>());
        }

    }

    @Override
    public Result updateUser(User user) {


        try {
            userRepository.updateUser(user);
            return new Result("UPDATE");
        } catch (Exception e ) {
            return new Result("FAILED");
        }

    }

    @Override
    public List<User> getAdminByPageNumber(int pageNumber) {
        List<User> limitData = userRepository.getAdminsLimit(pageNumber * 5);
        System.out.println(limitData);
        return limitData.subList(pageNumber * 5 - 5, limitData.size());
    }


}
