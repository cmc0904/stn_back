package sungil.management.repository.users;

import org.apache.ibatis.annotations.Mapper;

import sungil.management.dto.user.RoleDTO;
import sungil.management.dto.user.UserDTO;
import sungil.management.vo.user.UserVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {
    List<UserVO> getAllUsers(int offset);
    Integer getAllUserTotalLength();
    List<UserVO> getAllAdmins();
    Optional<UserVO> getUserByUserId(String userId);
    void insertUser(UserDTO user);
    List<String> getRoleByUserId(String userId);
    void addRole(RoleDTO role);
    List<UserVO> searchUserBy(String type, String content, int offset);
    Integer searchUserTotalLength(String type, String content);

    List<UserVO> getAdminByPage(int offset);
    Integer getAdminTotalLength();

    void updateUser(UserDTO userDTO);
}
