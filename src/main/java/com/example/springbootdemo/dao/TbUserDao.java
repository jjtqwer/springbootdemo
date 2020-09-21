package com.example.springbootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.entity.TbUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserDao extends BaseMapper<TbUserEntity> {
}
