package org.example.controller;

import org.example.domain.BlComment;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.entity.Result;
import org.example.service.BlCommentService;
import javax.annotation.Resource;
import org.example.entity.ResultCode;
/**
 * (BlComment)控制层
 *
 * @author makejava
 * @since 2023-03-31 08:22:33
 */@CrossOrigin
@RestController
@RequestMapping("/blComment")
public class BlCommentController {
    /**
     * 服务对象
     */
    @Resource
    private BlCommentService blCommentService;



    /**
     * 通过主键查询单条数据
     *
     * @param blComment 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result selectOne(BlComment blComment) {
        BlComment result = blCommentService.selectById(blComment.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     * @param blComment 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(@RequestBody BlComment blComment) {
        int result = blCommentService.insert(blComment);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     * @param blComment 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlComment blComment) {
        BlComment result = blCommentService.update(blComment);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     * @param blComment 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Result delete(BlComment blComment) {
        int result = blCommentService.deleteById(blComment.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<BlComment> result = blCommentService.selectAll();
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
        return new Result(ResultCode.SUCCESS,blCommentService.selectPage(start, limit));
    }
    
}

