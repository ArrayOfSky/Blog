package org.example.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;

import lombok.Data;
/**
 * (BlArtical)表实体类
 *
 * @author makejava
 * @since 2023-03-31 08:22:29
 */
@SuppressWarnings("serial")
@Data
public class BlArtical implements Serializable {

 private static final long serialVersionUID = -4990810027542971546L;

    
    private String id;
    
    private String title;
    
    private String text;

    private String kind;

    private Date date;

    private int like;

    private int comment;

    private int collect;

    private int browser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

  
    }

