package cn.cxh.service.impl;

import cn.cxh.dao.RoleDao;
import cn.cxh.entry.Role;
import cn.cxh.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleDao roleDao;
    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }
}
