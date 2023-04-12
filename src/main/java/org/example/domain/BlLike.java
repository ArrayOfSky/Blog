package org.example.domain;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
/**
 * (BlLike)表实体类
 *
 * @author makejava
 * @since 2023-04-02 23:03:46
 */
@SuppressWarnings("serial")
@Data
public class BlLike implements Serializable {

 private static final long serialVersionUID = -4990810027542971546L;

    
    private String id;
    
    private String userId;
    
    private String articalId;
    
    private Date date;

    public BlLike(String articalId,String userId){
        this.articalId = articalId;
        this.userId = userId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticalId() {
        return articalId;
    }

    public void setArticalId(String articalId) {
        this.articalId = articalId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

  
    }

