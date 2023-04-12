package org.example.service;

import org.example.domain.BlKind;
import org.example.dao.BlKindDao;
import org.example.utils.IdWorker;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (BlKind表)服务实现类
 *
 * @author makejava
 * @since 2023-03-31 09:40:14
 */
@Service("blKindService")
public class BlKindService {
    @Resource
    private BlKindDao blKindDao;

    @Resource
    private IdWorker idWorker;




    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlKind selectById(String id) {
        return this.blKindDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<BlKind> selectPage(int start, int limit) {
        return this.blKindDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<BlKind> selectAll() {
        return this.blKindDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param blKind 实例对象
     * @return 实例对象
     */
    public int insert(BlKind blKind) {
    
        return this.blKindDao.insert(blKind);
    }


    /**
     * 修改数据
     *
     * @param blKind 实例对象
     * @return 实例对象
     */
    public BlKind update(BlKind blKind) {
        this.blKindDao.updateById(blKind);
        return this.selectById(blKind.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.blKindDao.deleteById(id);
    }
    
}

