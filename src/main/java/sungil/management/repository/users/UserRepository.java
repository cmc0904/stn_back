package sungil.management.repository.users;

import org.apache.ibatis.annotations.Mapper;
import sungil.management.domain.Role;
import sungil.management.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {
    List<User> getAllUsers();
    List<User> getAllAdmins();
    Optional<User> getUserByUserId(String userId);
    Optional<User> getUserByUserIdAndPassword(String userId, String password);
    void insertUser(User user);
    List<String> getRoleByUserId(String userId);
    void addRole(Role role);
    void deleteUserByUserId(String userId);
    List<User> getUsersLimit(int limit);

    List<User> getAdminsLimit(int limit);
    List<User> getUserLIKE(String type, String content);

    void updateUser(User user);
}
