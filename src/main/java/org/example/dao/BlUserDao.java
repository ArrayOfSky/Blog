package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.domain.BlUser;
import org.apache.ibatis.annotations.Mapper;
/**
 * (BlUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-31 08:22:37
 */
@Mapper
public interface BlUserDao extends BaseMapper<BlUser> {

}

