package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.domain.BlComment;
import org.apache.ibatis.annotations.Mapper;
/**
 * (BlComment)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-31 08:22:33
 */
@Mapper
public interface BlCommentDao extends BaseMapper<BlComment> {

}

