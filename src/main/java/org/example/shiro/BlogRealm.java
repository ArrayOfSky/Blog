package org.example.shiro;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.domain.BlUser;
import org.example.service.BlUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;



/**
 * @author 22449
 */
@Component
public class BlogRealm extends AuthorizingRealm {

    @Resource
    BlUserService blUserService;

    @Override
    public void setName(String name) {
        super.setName("blogRealm");
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new  SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String email = upToken.getUsername();
        String password = new String( upToken.getPassword());
        if(email.equals("7777777")&&password.equals(new Md5Hash("7777777",email,3).toString())){
            BlUser user = new BlUser();
            user.setLevel("manager");
            return new SimpleAuthenticationInfo(user,password, this.getName());
        }
        BlUser user = blUserService.selectByEmail(email);
        if(user.getPassword().equals(password)){
            user.setLevel("user");
            return new SimpleAuthenticationInfo(user,user.getPassword(), this.getName());
        }
        return null;

    }


}
