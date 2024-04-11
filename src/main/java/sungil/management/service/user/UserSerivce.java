package sungil.management.service.user;

import sungil.management.domain.Role;
import sungil.management.domain.User;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.form.LoginForm;
import sungil.management.test.PageVO;

import java.util.List;
import java.util.Map;

public interface UserSerivce {
    PageVO<User> getAllUsers(int currentPage);
    List<User> getAllAdmins();
    Map<String, ?> login(LoginForm loginForm) throws NotFoundUserExecption;
    Map<String, String> register(User user) throws DuplicateUserExecption;
    Map<String, ?> getUserById(String id);
    Map<String, Object> addRole(Role role);
    boolean isDuplicateUser(String userId);

    List<Integer> getPageNumbers(String type);

    List<User> getUserByPageNumber(int pageNumber);
    PageVO<User> search(String type, String content, int currentPage);

    Map<String, String> updateUser(User user);

    List<User> getAdminByPageNumber(int pageNumber);
}
