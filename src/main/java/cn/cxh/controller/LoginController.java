package cn.cxh.controller;

import cn.cxh.entry.User;
import cn.cxh.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/login.html")
    public String index(HttpServletRequest request){
        String view="login";
        //判断是否有请求
        if(request.getParameter("userCode")!=null){
            User user=new User();
            user.setUserCode(request.getParameter("userCode"));
            user.setUserPassword(request.getParameter("userPassword"));
            if (userService.login(user, request.getSession())){
                System.out.println(((User)request.getSession().getAttribute("loginUser")).getUserName());
                view="redirect:admin/index.html"; //redirect:重定向
            }else {
                throw new RuntimeException("用户名或密码错误");
            }

        }
        //接收页面提交的用户名和密码
        //调登录服务查询
        //成功就跳转后台
        //失败跳转登录页面

        return view;

    }
    @RequestMapping("/admin/index.html")
    public String admin(){


        return "admin/index";

    }
//局部异常
//    @ExceptionHandler(RuntimeException.class)
//    public String hand(RuntimeException e,HttpServletRequest request){
//        request.setAttribute("e",e);
//        return "login";
//    }

    @RequestMapping("loginOut.html")
    public String loinOut(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "redirect:/login.html";
    }

}
