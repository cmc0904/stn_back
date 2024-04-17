package sungil.management.service.user;

import sungil.management.dto.user.RoleDTO;
import sungil.management.execption.CreateFailedExecption;
import sungil.management.execption.UpdateFailedExecption;
import sungil.management.vo.etc.Result;

import sungil.management.dto.user.LoginDTO;
import sungil.management.dto.user.UserDTO;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.vo.etc.PageVO;
import sungil.management.vo.user.UserVO;

import java.util.List;
import java.util.Map;

public interface UserSerivce {
    PageVO<UserVO> getAllUsers(int currentPage);
    List<UserVO> getAllAdmins();
    Map<String, ?> login(LoginDTO loginDTO) throws NotFoundUserExecption;
    Result register(UserDTO userDTO) throws DuplicateUserExecption;
    Map<String, ?> getUserById(String id);
    Result addRole(RoleDTO role) throws CreateFailedExecption;
    boolean isDuplicateUser(String userId);
    PageVO<UserVO> search(String type, String content, int currentPage);
    Result updateUser(UserDTO userDTO) throws UpdateFailedExecption;
    PageVO<UserVO> getAdminsByPage(int page);

}
