package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.MemberUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员用户：(MemberUser)Mapper接口
 *
 */
@Mapper
public interface MemberUserMapper extends BaseMapper<MemberUser>{

}
