package com.example.springbootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootdemo.dao.TbUserDao;
import com.example.springbootdemo.entity.TbUserEntity;
import com.example.springbootdemo.service.TbUserService;
import org.springframework.stereotype.Service;

/**
 * @author ex_jingjintao
 */
@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUserEntity> implements TbUserService {
}
