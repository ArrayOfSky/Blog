package org.example.controller;

import org.example.domain.BlCollect;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.entity.Result;
import org.example.service.BlCollectService;
import javax.annotation.Resource;
import org.example.entity.ResultCode;
/**
 * (BlCollect)控制层
 *
 * @author makejava
 * @since 2023-03-31 15:17:40
 */@CrossOrigin
@RestController
@RequestMapping("/blCollect")
public class BlCollectController {
    /**
     * 服务对象
     */
    @Resource
    private BlCollectService blCollectService;

    /**
     * 通过主键查询单条数据
     *
     * @param blCollect 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result selectOne(BlCollect blCollect) {
        BlCollect result = blCollectService.selectById(blCollect.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     * @param blCollect 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(@RequestBody BlCollect blCollect) {
        int result = blCollectService.insert(blCollect);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     * @param blCollect 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlCollect blCollect) {
        BlCollect result = blCollectService.update(blCollect);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     * @param blCollect 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Result delete(BlCollect blCollect) {
        int result = blCollectService.deleteById(blCollect.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<BlCollect> result = blCollectService.selectAll();
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
        return new Result(ResultCode.SUCCESS,blCollectService.selectPage(start, limit));
    }
    
}

