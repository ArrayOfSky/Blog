package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.example.domain.BlUser;
import org.example.dao.BlUserDao;
import org.example.utils.*;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.List;

/**
 * (BlUser表)服务实现类
 *
 * @author makejava
 * @since 2023-03-31 08:22:37
 */
@Service("blUserService")
public class BlUserService {
    @Resource
    private BlUserDao blUserDao;

    @Resource
    private IdWorker idWorker;

    @Resource
    private RamdomUtils ramdomUtils;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private MailUtils mailUtils;



    public boolean updatePassword(BlUser user){
        BlUser blUser = selectByEmail(user.getEmail());
        if(blUser==null){
            return false;
        }
        blUser.setPassword(user.getPassword());
        update(blUser);
        return true;
    }

    public boolean findPassword(BlUser user,String code){
        String code1 = redisUtils.get(user.getEmail()+"code");
        if (code1.equals(code)) {
            user.setPassword(new Md5Hash(user.getPassword(), user.getEmail(), 3).toString());
            return updatePassword(user);
        }
        return false;
    }


    public boolean getCode(String email) {
        if(selectByEmail(email)!=null){
            return false;
        }
        String code = ramdomUtils.getRandom(6);
        redisUtils.setEx(email+"code", code,30L);
        mailUtils.sendSimpleMail(email, "lunk", code);
        return true;
    }

    public boolean register(BlUser user,String code)  {
        String code1 = redisUtils.get(user.getEmail()+"code");
        if (code1.equals(code)) {
            insert(user);
            redisUtils.delete(user.getEmail()+"code");
            return true;
        }
        return false;
    }


    public BlUser selectByEmail(String email){
        LambdaQueryWrapper<BlUser> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(BlUser::getEmail,email);
        return blUserDao.selectOne(lambdaQueryWrapper);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlUser selectById(String id) {
        return this.blUserDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<BlUser> selectPage(int start, int limit) {
        return this.blUserDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<BlUser> selectAll() {
        return this.blUserDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param blUser 实例对象
     * @return 实例对象
     */
    public int insert(BlUser blUser) {
        blUser.setId(idWorker.nextId().toString());
        String password = new Md5Hash(blUser.getPassword(), blUser.getEmail(), 3).toString();
        blUser.setPassword(password);
        blUser.setLevel("user");
        blUserDao.insert(blUser);
        return 1;
    }


    /**
     * 修改数据
     *
     * @param blUser 实例对象
     * @return 实例对象
     */
    public BlUser update(BlUser blUser) {
        this.blUserDao.updateById(blUser);
        return this.selectById(blUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.blUserDao.deleteById(id);
    }
    
}

