package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.domain.BlCollect;
import org.example.dao.BlCollectDao;
import org.example.utils.IdWorker;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (BlCollect表)服务实现类
 *
 * @author makejava
 * @since 2023-03-31 15:17:40
 */
@Service("blCollectService")
public class BlCollectService {
    @Resource
    private BlCollectDao blCollectDao;

    @Resource
    private IdWorker idWorker;

    /**
     * 根据userId和articalId删除
     */
    public void deleteByArticalIdAndUserId(String articalId,String userId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("articalId",articalId);
        map.put("userId",userId);
        blCollectDao.deleteByMap(map);
    }


    public List<BlCollect> selectByArticalId(String articalId){
        LambdaQueryWrapper<BlCollect> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(BlCollect::getArticalId,articalId);
        return blCollectDao.selectList(lambdaQueryWrapper);
    }



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlCollect selectById(String id) {
        return this.blCollectDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<BlCollect> selectPage(int start, int limit) {
        return this.blCollectDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<BlCollect> selectAll() {
        return this.blCollectDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param blCollect 实例对象
     * @return 实例对象
     */
    public int insert(BlCollect blCollect) {
        blCollect.setId(idWorker.nextId().toString());
        return this.blCollectDao.insert(blCollect);
    }


    /**
     * 修改数据
     *
     * @param blCollect 实例对象
     * @return 实例对象
     */
    public BlCollect update(BlCollect blCollect) {
        this.blCollectDao.updateById(blCollect);
        return this.selectById(blCollect.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.blCollectDao.deleteById(id);
    }
    
}

