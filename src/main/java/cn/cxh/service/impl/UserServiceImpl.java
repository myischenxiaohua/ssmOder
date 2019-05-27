package cn.cxh.service.impl;

import cn.cxh.dao.UserDao;
import cn.cxh.entry.User;
import cn.cxh.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public boolean login(User user,HttpSession session) {
        boolean flag=false;
        String inputPasswor=user.getUserPassword();
         user=userDao.findByCode(user.getUserCode());
         System.out.println("service---"+user.getUserName());
          if(user.getUserPassword()!=null){
              if(user.getUserPassword().equals(inputPasswor)){
                  session.setAttribute("loginUser",user);
                  flag=true;

              }
          }

        return flag;
    }

    @Override
    public int getUserCount(String userName, int role) {
        return userDao.findUserCount(userName,role);
    }

    @Override
    public List<User> getUserList(String userName,int role,int currentPageNo,int pageSize){
//        处理游标开始的位置
        currentPageNo=(currentPageNo-1)*pageSize;
        return userDao.findAll(userName,role,currentPageNo,pageSize);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.insert(user)>0;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(user)>0;
    }

    @Override
    public boolean delUserById(int id) {
        return userDao.delById(id)>0;
    }

    @Override
    public boolean cheackUserCode(String userCode) {
//        objects.nonNull判断对象不为空
        return Objects.nonNull(userDao.findByCode(userCode)) ;
    }

    @Override
    public boolean updatePwd(String pwd, int id) {

        return userDao.updatePwd(pwd,id)>0;
    }
}
