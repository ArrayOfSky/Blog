package org.example.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
/**
 * (BlKind)表实体类
 *
 * @author makejava
 * @since 2023-03-31 09:40:14
 */
@SuppressWarnings("serial")
@Data
public class BlKind implements Serializable {

 private static final long serialVersionUID = -4990810027542971546L;

    
    private String id;
    private String pid;
    
    private String name;

    private int level;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    }

