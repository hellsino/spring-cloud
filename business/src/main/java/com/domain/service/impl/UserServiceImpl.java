package com.domain.service.impl;

import com.domain.entity.User;
import com.domain.mapper.UserMapper;
import com.domain.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hellsino
 * @since 2026-06-10 13:33:41.654
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
