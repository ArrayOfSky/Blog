package org.example.controller;

import org.example.domain.BlKind;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.entity.Result;
import org.example.service.BlKindService;
import javax.annotation.Resource;
import org.example.entity.ResultCode;
/**
 * (BlKind)控制层
 *
 * @author makejava
 * @since 2023-03-31 09:40:14
 */@CrossOrigin
@RestController
@RequestMapping("/blKind")
public class BlKindController {
    /**
     * 服务对象
     */
    @Resource
    private BlKindService blKindService;

    /**
     * 通过主键查询单条数据
     *
     * @param blKind 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result selectOne(BlKind blKind) {
        BlKind result = blKindService.selectById(blKind.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     * @param blKind 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(@RequestBody BlKind blKind) {
        int result = blKindService.insert(blKind);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     * @param blKind 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlKind blKind) {
        BlKind result = blKindService.update(blKind);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     * @param blKind 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Result delete(BlKind blKind) {
        int result = blKindService.deleteById(blKind.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<BlKind> result = blKindService.selectAll();
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
        return new Result(ResultCode.SUCCESS,blKindService.selectPage(start, limit));
    }
    
}

