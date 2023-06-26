package com.mybatis.sbmp.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mybatis.sbmp.bo.Ms;
import com.mybatis.sbmp.entity.Users;

/**
 * @author zss
 * @date 2023年06月26日 15:47
 */

public interface UserService extends IService<Users> {

    /**
     * 工程师登录
     * @param user
     * @return
     */
    SaTokenInfo loginMs(Ms user);
}
