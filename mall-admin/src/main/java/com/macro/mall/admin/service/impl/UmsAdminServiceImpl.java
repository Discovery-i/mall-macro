package com.macro.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.github.pagehelper.PageHelper;
import com.macro.mall.admin.dao.UmsAdminRoleRelationDao;
import com.macro.mall.admin.dto.UmsAdminParam;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAdminExample;
import com.macro.mall.model.UmsRole;
import com.macro.mall.admin.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Autowired
    UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        //条件查询内添加用户输入的名字
        umsAdminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        //查表获取用户
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        //有用户
        if (umsAdmins.size() > 0) {
            return null;
        }
        //  ↓  无用户则可注册
        //完善用户信息
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //明文 -> 密文 / 不加盐值
        String encodePassword = BCrypt.hashpw(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);

        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public List<UmsAdmin> list(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        UmsAdminExample.Criteria userNameCriteria = umsAdminExample.createCriteria();
        UmsAdminExample.Criteria nickNameCriteria = umsAdminExample.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            userNameCriteria.andUsernameLike("%" + keyword + "%");
            nickNameCriteria.andNickNameLike("%" + keyword + "%");
            umsAdminExample.or(nickNameCriteria);
        }
        return umsAdminMapper.selectByExample(umsAdminExample);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return umsAdminMapper.selectByPrimaryKey(id);
    }



    @Override
    public int update(Long id, UmsAdmin umsAdmin) {

        UmsAdmin rawAdmin = umsAdminMapper.selectByPrimaryKey(id);

        if (StrUtil.isEmpty(umsAdmin.getPassword())) {
//            用户无输入密码
            umsAdmin.setPassword(null);
        }else {
            if (umsAdmin.getPassword().equals(rawAdmin.getPassword())) {
//                两者密码相同 -> 查询时后端传到前端而用户没有修改时的默认值
                umsAdmin.setPassword(null);
            }else {
//                数据加密
                umsAdmin.setPassword(BCrypt.hashpw(umsAdmin.getPassword()));
            }
        }
        return umsAdminMapper.updateByPrimaryKeySelective(umsAdmin);
    }

    @Override
    public int delete(Long id) {
        return umsAdminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsRole> getRoleByAdminId(Long id) {
        return umsAdminRoleRelationDao.getRoleByAdminId(id);
    }


}
