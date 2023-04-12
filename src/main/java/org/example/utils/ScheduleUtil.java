package org.example.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.example.domain.BlCollect;
import org.example.domain.BlLike;
import org.example.domain.cache.*;
import org.example.service.BlArticalService;
import org.example.service.BlCollectService;
import org.example.service.BlLikeService;
import org.example.service.BlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static org.example.utils.RedisConstants.*;

@Component
public class ScheduleUtil {
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private BlArticalService articalService;

    @Resource
    private BlCollectService collectService;

    @Resource
    private BlLikeService likeService;

    @Scheduled(cron = "0/30 * *  * * ? ")   //每5秒执行一次
    public void execute() {
        // 1.从redis查询缓存
        String browser = redisUtils.get(CACHE_BROWSER_KEY);
        String collect = redisUtils.get(RedisConstants.CACHE_COLLECT_KEY);
        String like = redisUtils.get(CACHE_LIKE_KEY);
        // 2.判断是否存在
        if (!StrUtil.isBlank(browser)) {
            CacheBrowser browser1 = JSONUtil.toBean(browser, CacheBrowser.class);
            HashSet<CacheNode> set = browser1.getSet();
            for(CacheNode node : set){
                String articalId = node.getArticalId();
                int num = node.getNumber();
                articalService.update(articalId,num);
            }
        }
        if (!StrUtil.isBlank(collect)) {
            CacheCollect collect1 = JSONUtil.toBean(collect, CacheCollect.class);
            HashSet<CacheNode> set = collect1.getSet();
            for(CacheNode node : set){
                String articalId = node.getArticalId();
                ArrayList<String> array = node.getUserIdList();
                for(String userId:array){
                    collectService.insert(new BlCollect(articalId,userId));
                }
            }
        }
        if (!StrUtil.isBlank(like)) {
            CacheLike like1 = JSONUtil.toBean(like, CacheLike.class);
            HashSet<CacheNode> set = like1.getSet();
            for(CacheNode node : set){
                String articalId = node.getArticalId();
                ArrayList<String> array = node.getUserIdList();
                for(String userId:array){
                    likeService.insert(new BlLike(articalId,userId));
                }
            }
        }
        redisUtils.delete(CACHE_BROWSER_KEY);
        redisUtils.delete(RedisConstants.CACHE_COLLECT_KEY);
        redisUtils.delete(CACHE_LIKE_KEY);

        redisUtils.set(CACHE_BROWSER_KEY,JSONUtil.toJsonStr(CacheConstatnt.cacheBrowser));
        redisUtils.set(RedisConstants.CACHE_COLLECT_KEY,JSONUtil.toJsonStr(CacheConstatnt.cacheCollect));
        redisUtils.set(CACHE_LIKE_KEY,JSONUtil.toJsonStr(CacheConstatnt.cacheLike));

        CacheConstatnt.cacheBrowser = new CacheBrowser();
        CacheConstatnt.cacheCollect = new CacheCollect();
        CacheConstatnt.cacheLike = new CacheLike();
    }

}
