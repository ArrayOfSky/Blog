package org.example.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
/**
 * (BlUser)表实体类
 *
 * @author makejava
 * @since 2023-03-31 08:22:37
 */
@SuppressWarnings("serial")
@Data
public class BlUser implements Serializable {

 private static final long serialVersionUID = -4990810027542971546L;

    
    private String id;
    
    private String name;
    
    private String email;
    
    private String password;

    private String level;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  
    }

