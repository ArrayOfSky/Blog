package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.domain.BlComment;
import org.example.dao.BlCommentDao;
import org.example.utils.IdWorker;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (BlComment表)服务实现类
 *
 * @author makejava
 * @since 2023-03-31 08:22:33
 */
@Service("blCommentService")
public class BlCommentService {
    @Resource
    private BlCommentDao blCommentDao;

    @Resource
    private IdWorker idWorker;

    /**
     * 根据文章id查询
     */
    public List<BlComment> selectByArticalId(String id){
        LambdaQueryWrapper<BlComment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BlComment::getArticalId,id);
        List<BlComment> list = blCommentDao.selectList(lambdaQueryWrapper);
        return list;
    }



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlComment selectById(String id) {
        return this.blCommentDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<BlComment> selectPage(int start, int limit) {
        return this.blCommentDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<BlComment> selectAll() {
        return this.blCommentDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param blComment 实例对象
     * @return 实例对象
     */
    public int insert(BlComment blComment) {
        blComment.setId(idWorker.nextId().toString());
        return this.blCommentDao.insert(blComment);
    }


    /**
     * 修改数据
     *
     * @param blComment 实例对象
     * @return 实例对象
     */
    public BlComment update(BlComment blComment) {
        this.blCommentDao.updateById(blComment);
        return this.selectById(blComment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.blCommentDao.deleteById(id);
    }
    
}

