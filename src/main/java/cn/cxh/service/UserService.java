package cn.cxh.service;

import cn.cxh.entry.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    boolean login(User user,HttpSession session);
    int getUserCount(String userName,int role);
    List<User> getUserList(String userName,int role,int currentPageNo,int pageSize);
    User getUserById(int id);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean delUserById(int id);
    boolean cheackUserCode(String userCode);
    boolean updatePwd(String pwd,int id);

}
