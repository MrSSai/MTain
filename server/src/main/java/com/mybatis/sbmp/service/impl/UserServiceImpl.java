package com.mybatis.sbmp.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatis.sbmp.bo.Ms;
import com.mybatis.sbmp.entity.Users;
import com.mybatis.sbmp.mapper.UserMapper;
import com.mybatis.sbmp.service.UserService;
import com.mybatis.sbmp.utils.ServiceException;
import org.springframework.stereotype.Service;

/**
 * @author zss
 * @date 2023年06月26日 15:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {

    @Override
    public SaTokenInfo loginMs(Ms user) {
        // 判断用户是否存在
        Users m = selectByUserName(user.getUserName());
        if (ObjectUtil.isNull(m)){
            throw new ServiceException("用户不存在");
        }
        if (!m.getPassword().equals(user.getPassword())){
            throw new ServiceException("用户名密码不正确");
        }
        StpUtil.login(user.getUserName());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo;
    }

    private Users selectByUserName(String userName){
        LambdaQueryWrapper<Users> qw = Wrappers.lambdaQuery();
        qw.eq(Users::getPhoneNumber,userName);
        return baseMapper.selectOne(qw);
    }
}
