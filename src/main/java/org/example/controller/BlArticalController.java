package org.example.controller;

import org.example.domain.BlArtical;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.entity.Result;
import org.example.service.BlArticalService;
import javax.annotation.Resource;
import org.example.entity.ResultCode;
/**
 * (BlArtical)控制层
 *
 * @author makejava
 * @since 2023-03-31 08:22:28
 */@CrossOrigin
@RestController
@RequestMapping("/blArtical")
public class BlArticalController {
    /**
     * 服务对象
     */
    @Resource
    private BlArticalService blArticalService;

    /**
     * 点赞
     */
    @RequestMapping(value = "/addLike/{articalId}/{userId}",method = RequestMethod.POST)
    public Result addLike(@PathVariable("articalId")String articalId,@PathVariable("userId")String userId){
        blArticalService.addLike(articalId,userId);
        return new Result(ResultCode.SUCCESS,null);
    }

    /**
     * 取消点赞
     */
    @RequestMapping(value = "/deleteLike/{articalId}/{userId}",method = RequestMethod.POST)
    public Result deleteLike(@PathVariable("articalId")String articalId,@PathVariable("userId")String userId){
        blArticalService.deleteLike(articalId,userId);
        return new Result(ResultCode.SUCCESS,null);
    }
    /**
     * 收藏
     */
    @RequestMapping(value = "/addCollect/{articalId}/{userId}",method = RequestMethod.POST)
    public Result addCollect(@PathVariable("articalId")String articalId,@PathVariable("userId")String userId){
        blArticalService.addCollect(articalId,userId);
        return new Result(ResultCode.SUCCESS,null);
    }
    /**
     * 取消收藏
     */
    @RequestMapping(value = "/deleteCollect/{articalId}/{userId}",method = RequestMethod.POST)
    public Result deleteCollect(@PathVariable("articalId")String articalId,@PathVariable("userId")String userId){
        blArticalService.deleteCollect(articalId,userId);
        return new Result(ResultCode.SUCCESS,null);
    }
    /**
     * 浏览文章
     */
    @RequestMapping(value = "/browse/{id}",method = RequestMethod.GET)
    public Result browse(@PathVariable("id") String id){
        return new Result(ResultCode.SUCCESS,blArticalService.selectArtical(id));
    }


    /**
     * 获取文章列表
     * @return
     */
    @RequestMapping(value = "/selectList",method = RequestMethod.GET)
    public Result selectList(){
        return new Result(ResultCode.SUCCESS,blArticalService.selectList());
    }


    /**
     * 通过主键查询单条数据
     *
     * @param blArtical 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result selectOne(BlArtical blArtical) {
        BlArtical result = blArticalService.selectById(blArtical.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     * @param blArtical 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(@RequestBody BlArtical blArtical) {
        int result = blArticalService.insert(blArtical);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     * @param blArtical 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlArtical blArtical) {
        BlArtical result = blArticalService.update(blArtical);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     * @param blArtical 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Result delete(BlArtical blArtical) {
        int result = blArticalService.deleteById(blArtical.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<BlArtical> result = blArticalService.selectAll();
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "selectPage", method = RequestMethod.GET)
    public Result selectPage(Integer start, Integer limit) {
        return new Result(ResultCode.SUCCESS,blArticalService.selectPage(start, limit));
    }
    
}

