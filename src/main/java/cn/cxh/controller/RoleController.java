package cn.cxh.controller;

import cn.cxh.service.RoleService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/role/")
public class RoleController {
    @Resource
    private RoleService roleService;
    @RequestMapping("getRole.html")
    @ResponseBody
    public Object getRole(){

        return JSONArray.toJSON(roleService.getRoles());

    }
}
