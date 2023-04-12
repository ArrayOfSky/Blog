package org.example.service;

import org.example.domain.*;
import org.example.dao.BlArticalDao;
import org.example.domain.cache.CacheConstatnt;
import org.example.domain.response.ArticalResponse;
import org.example.domain.response.KindListResponse;
import org.example.utils.CacheClient;
import org.example.utils.IdWorker;
import org.example.utils.RedisConstants;
import org.example.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (BlArtical表)服务实现类
 *
 * @author makejava
 * @since 2023-03-31 08:22:29
 */
@Service("blArticalService")
public class BlArticalService {
    @Resource
    private BlArticalDao blArticalDao;

    @Resource
    private IdWorker idWorker;

    @Resource
    private BlKindService blKindService;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private BlCommentService blCommentService;

    @Resource
    private BlCollectService blCollectService;

    @Resource
    private CacheClient cacheClient;

    @Resource
    private BlLikeService blLikeService;

    /**
     * 浏览
     */
    public ArticalResponse selectArtical(String id){
        ArticalResponse response = cacheClient.queryWithMutex(RedisConstants.CACHE_ARTICAL_KEY,
                id,ArticalResponse.class,this::selectArticalFallBack,
                RedisConstants.CACHE_ARTICAL_TTL, TimeUnit.MINUTES,RedisConstants.LOCK_ARTICAL_KEY+id);
        browser(response.getId(),response.getBrowser());
        return response;
    }

    public ArticalResponse selectArticalFallBack(String id){
            BlArtical artical = blArticalDao.selectById(id);
            ArticalResponse response = new ArticalResponse(artical);
            List<BlComment> listComment = blCommentService.selectByArticalId(id);
            List<BlCollect> listCollect = blCollectService.selectByArticalId(id);
            List<BlLike> listLike = blLikeService.selectByArticalId(id);
            List<String> listCollectId = new ArrayList<>();
            for(BlCollect blCollect : listCollect){
                listCollectId.add(blCollect.getUserId());
            }
            List<String> listLikeId = new ArrayList<>();
            for(BlLike blLike : listLike){
                listLikeId.add(blLike.getUserId());
            }
            response.addComment(listComment);
            response.addCollect(listCollectId);
            response.addLike(listLikeId);
            return response;
    }

    /**
     * 获取文章列表
     */
    public List selectList(){
        List list = cacheClient.queryWithMutexWithoutId(RedisConstants.CACHE_ARTICAL_LIST_KEY,
                List.class,this::selectListFallBack,RedisConstants.CACHE_ARTICAL_TTL,
                TimeUnit.MINUTES,RedisConstants.LOCK_ARTICAL_LIST_KEY);
        return list;
    }

    public List selectListFallBack(){
        List<BlKind> list = blKindService.selectAll();
        int maxLevel = 0;
        ArrayList<ArrayList<KindListResponse>> kindList = new ArrayList();
        for(BlKind blKind : list){
            int level = blKind.getLevel();
            if(level>maxLevel){
                maxLevel = level;
                for(int i = kindList.size();i<maxLevel;i++){
                    kindList.add(new ArrayList<>());
                }
            }
            kindList.get(level-1).add(new KindListResponse(blKind));
        }
        List<BlArtical> articalList = selectAll();
      for(BlArtical artical : articalList){
          String kind = artical.getKind();
          for(ArrayList<KindListResponse> list1 : kindList){
              for(KindListResponse response : list1){
                  if (kind.equals(response.getName())){
                      response.add(artical);
                  }
              }
          }

      }
        return kindList;
    }

    /**
     * 浏览
     */
    public void browser(String articalId,int num){
        if(!CacheConstatnt.cacheBrowser.add(articalId,num)){
            CacheConstatnt.cacheBrowser.increse(articalId);
        }
    }

    /**
     * 添加点赞
     */
    public void addLike(String articalId,String userId){
       CacheConstatnt.cacheLike.addUser(articalId,userId);
    }

    /**
     * 取消点赞
     */
    public void deleteLike(String articalId,String userId){
        if(!CacheConstatnt.cacheLike.deleteUser(articalId,userId)){
            blLikeService.deleteByArticalIdAndUserId(articalId,userId);
        }
    }

    /**
     * 添加收藏
     */
    public void addCollect(String articalId,String userId){
        CacheConstatnt.cacheCollect.addUser(articalId,userId);
    }

    /**
     * 取消收藏
     */
    public void deleteCollect(String articalId,String userId){
        if(!CacheConstatnt.cacheCollect.deleteUser(articalId,userId)){
            blCollectService.deleteByArticalIdAndUserId(articalId, userId);
        }
    }

    /**
     * 新增数据
     *
     * @param blArtical 实例对象
     * @return 实例对象
     */
    public int insert(BlArtical blArtical) {
        blArtical.setId(idWorker.nextId().toString());
        redisUtils.delete(RedisConstants.CACHE_ARTICAL_LIST_KEY);
        return this.blArticalDao.insert(blArtical);
    }


    /**
     * 修改浏览量
     */
    public void update(String articalId,int num){
        BlArtical artical = blArticalDao.selectById(articalId);
        if(artical==null){
            return;
        }
        artical.setBrowser(num);
        this.blArticalDao.updateById(artical);
        redisUtils.delete(RedisConstants.CACHE_ARTICAL_KEY+articalId);
        redisUtils.delete(RedisConstants.CACHE_ARTICAL_LIST_KEY);
    }



    /**
     * 修改数据
     *
     * @param blArtical 实例对象
     * @return 实例对象
     */
    public BlArtical update(BlArtical blArtical) {
        this.blArticalDao.updateById(blArtical);
        //删除缓存
        redisUtils.delete(RedisConstants.CACHE_ARTICAL_KEY+blArtical.getId());
        redisUtils.delete(RedisConstants.CACHE_ARTICAL_LIST_KEY);
        return this.selectById(blArtical.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        BlArtical artical = blArticalDao.selectById(id);
        if(artical==null){
            return -1;
        }
        //删除缓存
        redisUtils.delete(RedisConstants.CACHE_ARTICAL_KEY+id);
        redisUtils.delete(RedisConstants.CACHE_ARTICAL_LIST_KEY);
        blArticalDao.deleteById(id);
        return 1;
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlArtical selectById(String id) {
        return this.blArticalDao.selectById(id);
    }

    /**
     * 分页查询
     * @param start 第几页
     * @param limit  长度
     * @return 文章列表
     */
    public IPage<BlArtical> selectPage(int start, int limit) {
        return blArticalDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<BlArtical> selectAll() {
        return this.blArticalDao.selectList(null);
     }

    
}

