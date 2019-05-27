package cn.cxh.controller;

import cn.cxh.entry.Role;
import cn.cxh.entry.User;
import cn.cxh.service.RoleService;
import cn.cxh.service.UserService;
import cn.cxh.tool.Config;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;


    //最原始的写法
//    public ModelAndView list2(){
//        ModelAndView  mv=new ModelAndView();
//        mv.setViewName("admin/userList");   //视图（界面）
//        mv.addObject("userList",userService.getUserList());  //模型（数据）
//        return mv;
//    }

    @RequestMapping("list.html")
    public String list(Model model, @RequestParam(value = "userName",required = false) String userName, @RequestParam(value = "role",required = false,defaultValue ="0") int role, @RequestParam(value = "page",required = false,defaultValue = "0") int page){

//        初始化查询参数
//      设置每一页显示记录数量
        int pageSize= Config.pageSize;
//        设置当前页吗，开始都是第一页
        int currentPageNo=1;
//        有页码就赋给当前页
        System.out.println(page);
        if (page>0){
            currentPageNo=page;
        }

//        查询表的总记录数  19
        int count=userService.getUserCount(userName,role);
        System.out.println("查询总数"+count);
        //总页数
//       PageSupport pages=new PageSupport();
//        pages.setTotalCount(count);
//        pages.setPageSize(pageSize);
//        pages.setCurrentPageNo(currentPageNo);
//        总分页数
        int totalPageCount=0;

        if(count%pageSize==0){
            totalPageCount=count/pageSize;
        }else {
            totalPageCount=count/pageSize+1;
        }

        System.out.println("总分页数:"+totalPageCount);
//        控制首页和尾页
        if(currentPageNo<1){
            currentPageNo=1;
        }else if(currentPageNo>totalPageCount){
            currentPageNo=totalPageCount;
        }

       List<User> userList= userService.getUserList(userName,role,currentPageNo,pageSize);

        model.addAttribute("userList",userList);
        model.addAttribute("roleList",roleService.getRoles());
        model.addAttribute("totalPageCount",totalPageCount);
        model.addAttribute("count",count);
        model.addAttribute("currentPageNo",currentPageNo);
       return "admin/userList";


    }



    @RequestMapping("ajaxlist.html")
    @ResponseBody
    public Object ajaxlist(@RequestParam(value = "userName",required = false) String userName, @RequestParam(value = "role",required = false,defaultValue ="0") int role, @RequestParam(value = "page",required = false,defaultValue = "0") int page){

//        初始化查询参数
//      设置每一页显示记录数量
        int pageSize= Config.pageSize;
//        设置当前页吗，开始都是第一页
        int currentPageNo=1;
//        有页码就赋给当前页
        System.out.println(page);
        if (page>0){
            currentPageNo=page;
        }

//        查询表的总记录数  19
        int count=userService.getUserCount(userName,role);
        System.out.println("查询总数"+count);
        //总页数
//       PageSupport pages=new PageSupport();
//        pages.setTotalCount(count);
//        pages.setPageSize(pageSize);
//        pages.setCurrentPageNo(currentPageNo);
//        总分页数
        int totalPageCount=0;

        if(count%pageSize==0){
            totalPageCount=count/pageSize;
        }else {
            totalPageCount=count/pageSize+1;
        }

        System.out.println("总分页数:"+totalPageCount);
//        控制首页和尾页
        if(currentPageNo<1){
            currentPageNo=1;
        }else if(currentPageNo>totalPageCount){
            currentPageNo=totalPageCount;
        }

        List<User> userList= userService.getUserList(userName,role,currentPageNo,pageSize);

        Map map=new HashMap();
        map.put("userList",userList);
        map.put("roleList",roleService.getRoles());
        map.put("totalPageCount",totalPageCount);
        map.put("count",count);
        map.put("currentPageNo",currentPageNo);
        return JSONArray.toJSON(userList);


    }




    @RequestMapping("showUser.html/{id}")
    public String showUser(@PathVariable int id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "admin/showUser";
    }


    @RequestMapping(value = "add.html")
    public String add(@ModelAttribute("user") User user){
       // model.addAttribute("roleList",roleService.getRoles());
        return "admin/addUser";
    }

    @RequestMapping(value = "saveUser.html",method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, HttpSession session, @RequestParam(value = "a_idPicPath",required = false) MultipartFile[] upFiles,Model model){
        String idPicPath="";
        String workPicPath="";

        for (int i=0;i<upFiles.length;i++){
            MultipartFile upFile=upFiles[i]; //从数组中取上传的文件
            //上传的文件不为空
            if(!upFile.isEmpty()){
                //            设置文件存储路径 或存储的绝对路径
                String path=session.getServletContext().getRealPath("static"+ File.separator+"upload");
                System.out.println(path);
//            获取原文件名
                String oldFileName=  upFile.getOriginalFilename();
                System.out.println(oldFileName);
//            获取原文件名后缀
                String prefix= FilenameUtils.getExtension(oldFileName);
                System.out.println(prefix);
//            判断文件大小
                int fileSize=5000000;
                if(upFile.getSize()>fileSize){
//                上传的文件超过了规定文件大小 跳转到页面提示上传超过规定大小
                    model.addAttribute("msg","上传超过500k");
                    model.addAttribute("url","/user/add.html");

                    return "common/error";

                }else if(prefix.equalsIgnoreCase("jpg")||prefix.equalsIgnoreCase("png")){
                    //满足格式进行上传
//                定义存储在服务器磁盘里的文件名
                    String fileName=System.currentTimeMillis()+ new Random().nextInt(1000000)+"."+prefix;
//                创建一个文件
                    File file=new File(path,fileName);
                    if(!file.exists()){ //判断文件不存，再创建
                        file.mkdirs();
                    }
                    //保存 讲页面提交的文件保存到创建的空文件里面
                    try {
                        upFile.transferTo(file);
                        if(i==0){
                            idPicPath=fileName;
                        }else {
                            workPicPath=fileName;
                        }

                        System.out.println("图片地址:"+idPicPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                        model.addAttribute("msg","上传失败");
                        model.addAttribute("url","/user/add.html");

                        return "common/error";

                    }


                }else {
                    //上传图片格式错误
                    model.addAttribute("msg","上传图片格式错误，是不允许的图片格式，只能上传jpg,png");
                    model.addAttribute("url","/user/add.html");

                    return "common/error";
                }
            }

        }



        user.setCreatedBy(((User)session.getAttribute("loginUser")).getId());
        user.setCreationDate(new Date());
        user.setIdPicPath(idPicPath);
        user.setWorkPicPath(workPicPath);
        System.out.println(user.getUserName());

        if (bindingResult.hasErrors()){
            return "admin/addUser";
        }

        if (userService.addUser(user)) {
            return "redirect:/user/list.html";
        }

        return "redirect:/user/add.html";
    }

    @RequestMapping(value = "fm.html",method = RequestMethod.GET)
    public String addfm(@ModelAttribute("user") User user){
        return "admin/fm";
    }

    @RequestMapping(value = "fm.html",method = RequestMethod.POST)
    public String saveFm(@Valid User user,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  "admin/fm";
        }
        return "redirect:/user/list.html";
    }


    @RequestMapping("update.html")
    public String update(@RequestParam int id,Model model){
    model.addAttribute("user",userService.getUserById(id));
    model.addAttribute("roleList",roleService.getRoles());
        return "admin/updateUser";
    }

    @RequestMapping(value = "updateSave.html",method = RequestMethod.POST)
    public String updateSave(User user){
        if(userService.updateUser(user)){
            return "redirect:/user/list.html";
        }
        return "redirect:/user/update.html?id="+user.getId();

    }

    @RequestMapping("del.html")
    @ResponseBody
    public Object del(@RequestParam int id,Model model){
        String msg="删除失败";
        boolean status=false;
        Map map=new HashMap();

        User user=  userService.getUserById(id);
        if (user.getUserRole()==1){
            msg="管理员不可以删除";
        }else {
            if (userService.delUserById(id)){
                msg="删除成功";
                status=true;
            }
        }

        map.put("msg",msg);
        map.put("status",status);


        return  JSONArray.toJSON(map);
    }

    @RequestMapping( "check.html")
    @ResponseBody
    public Object userCodeExit(@RequestParam String userCode){

        Map rData=new HashMap();
         if (userService.cheackUserCode(userCode))  {
             rData.put("msg","改用户已存在");
             rData.put("status",false);
         }else {
             rData.put("msg","恭喜你可以注册");
             rData.put("status",true);
         }

         return JSONArray.toJSON(rData);
    }

    @RequestMapping("ajax.html")
    public String ajax(){
        return "admin/ajax";
    }


    @RequestMapping("updatePwd.html")
    public String updatePwd(){
        return "admin/updatePwd";
    }

    @RequestMapping("checkPwd.html")
    @ResponseBody
    public Object checkPwd(@RequestParam String pwd,HttpSession session){
       int userId= ((User)session.getAttribute("loginUser")) .getId();
     User user= userService.getUserById(userId);
     Map map=new HashMap();
     if(user.getUserPassword().equals(pwd)){
//         比对密码成功
         map.put("msg","密码验证成功");
         map.put("status",true);

     }else {
//         不成功
         map.put("msg","密码验证失败");
         map.put("status",false);
     }

        return JSONArray.toJSON(map);
    }

    @RequestMapping("updatePwdSave.html")
    @ResponseBody
    public Object updatePwdSave(@RequestParam String pwd,HttpSession session){
        int userId= ((User)session.getAttribute("loginUser")) .getId();
        Map map=new HashMap();
       if (userService.updatePwd(pwd,userId)){
           map.put("msg","修改成功");
       }else {
           map.put("msg","修改失败");
       }

       return JSONArray.toJSON(map);

    }

    @RequestMapping("vie")
    @ResponseBody
    public String vie(HttpSession session){
        int userId= ((User)session.getAttribute("loginUser")) .getId();
        User user=userService.getUserById(userId);
        //System.out.println(JSON.toJSONString(user));


       return JSON.toJSONString(user);
    }
//    视图
    @RequestMapping("vie/{id}")
    public Object vie(HttpSession session,@PathVariable int id){
        int userId= ((User)session.getAttribute("loginUser")) .getId();
        System.out.println(userId);
        return "admin/addUser";
    }



}
