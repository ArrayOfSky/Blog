package org.example.controller;

import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.domain.BlUser;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.entity.Result;
import org.example.service.BlUserService;
import javax.annotation.Resource;
import org.example.entity.ResultCode;
/**
 * (BlUser)控制层
 *
 * @author makejava
 * @since 2023-03-31 08:22:37
 */
@RestController
@RequestMapping("/blUser")
@CrossOrigin
public class BlUserController {
    /**
     * 服务对象
     */
    @Resource
    private BlUserService blUserService;



    /**
     * 忘记密码
     */
    @RequestMapping(value = "/forget/{code}")
    public Result findPassword(@RequestBody BlUser user,@PathVariable("code") String code){
        return new Result(ResultCode.SUCCESS,blUserService.findPassword(user,code));
    }


    /**
     * 通过主键查询单条数据
     *
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result selectOne(BlUser blUser) {
        BlUser result = blUserService.selectById(blUser.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(@RequestBody BlUser blUser) {
        int result = blUserService.insert(blUser);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlUser blUser) {
        BlUser result = blUserService.update(blUser);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Result delete(BlUser blUser) {
        int result = blUserService.deleteById(blUser.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<BlUser> result = blUserService.selectAll();
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 分页查询
     *
     */
    @RequestMapping(value = "selectPage", method = RequestMethod.GET)
    public Result selectPage(Integer start, Integer limit) {
        return new Result(ResultCode.SUCCESS,blUserService.selectPage(start, limit));
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody BlUser user){
        String email = user.getEmail();
        String password = user.getPassword();
        try {
            password = new Md5Hash(password,email,3).toString();  //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(email,password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(upToken);
            String sessionId = (String)subject.getSession().getId();
            return new Result(ResultCode.SUCCESS,sessionId);
        }catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR,null);
        }
    }

    /**
     *  用户注册
     */
    @RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
    public Result register(@RequestBody BlUser user,@PathVariable("code")String code){
        blUserService.register(user,code);
        return new Result(ResultCode.SUCCESS,null);
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/getCode/{email}",method = RequestMethod.POST)
    public Result getCode(@PathVariable("email")String email){
        blUserService.getCode(email);
        return new Result(ResultCode.SUCCESS,null);
    }

    
}

