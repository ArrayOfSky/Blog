package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.domain.BlLike;
import org.example.dao.BlLikeDao;
import org.example.utils.IdWorker;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (BlLike表)服务实现类
 *
 * @author makejava
 * @since 2023-04-02 23:03:46
 */
@Service("blLikeService")
public class BlLikeService {
    @Resource
    private BlLikeDao blLikeDao;

    @Resource
    private IdWorker idWorker;

/**
 * 根据userId和articalId删除
 */
public void deleteByArticalIdAndUserId(String articalId,String userId){
    HashMap<String, Object> map = new HashMap<>();
    map.put("articalId",articalId);
    map.put("userId",userId);
    blLikeDao.deleteByMap(map);
}

    /**
     * 根据文章id查询
     * @param articalId
     * @return
     */
    public List<BlLike> selectByArticalId(String articalId){
        LambdaQueryWrapper<BlLike> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BlLike::getArticalId,articalId);
        return blLikeDao.selectList(lambdaQueryWrapper);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlLike selectById(String id) {
        return this.blLikeDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<BlLike> selectPage(int start, int limit) {
        return this.blLikeDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<BlLike> selectAll() {
        return this.blLikeDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param blLike 实例对象
     * @return 实例对象
     */
    public int insert(BlLike blLike) {
        blLike.setId(idWorker.nextId().toString());
        return this.blLikeDao.insert(blLike);
    }


    /**
     * 修改数据
     *
     * @param blLike 实例对象
     * @return 实例对象
     */
    public BlLike update(BlLike blLike) {
        this.blLikeDao.updateById(blLike);
        return this.selectById(blLike.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.blLikeDao.deleteById(id);
    }
    
}

