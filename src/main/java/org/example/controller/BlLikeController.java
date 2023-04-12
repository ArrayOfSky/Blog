package org.example.controller;

import org.example.domain.BlLike;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.entity.Result;
import org.example.service.BlLikeService;
import javax.annotation.Resource;
import org.example.entity.ResultCode;
/**
 * (BlLike)控制层
 *
 * @author makejava
 * @since 2023-04-02 23:03:46
 */@CrossOrigin
@RestController
@RequestMapping("/blLike")
public class BlLikeController {
    /**
     * 服务对象
     */
    @Resource
    private BlLikeService blLikeService;

    /**
     * 通过主键查询单条数据
     *
     * @param blLike 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result selectOne(BlLike blLike) {
        BlLike result = blLikeService.selectById(blLike.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     * @param blLike 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(@RequestBody BlLike blLike) {
        int result = blLikeService.insert(blLike);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     * @param blLike 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlLike blLike) {
        BlLike result = blLikeService.update(blLike);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     * @param blLike 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Result delete(BlLike blLike) {
        int result = blLikeService.deleteById(blLike.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<BlLike> result = blLikeService.selectAll();
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
        return new Result(ResultCode.SUCCESS,blLikeService.selectPage(start, limit));
    }
    
}

