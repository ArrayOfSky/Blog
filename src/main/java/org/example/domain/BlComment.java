package org.example.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
/**
 * (BlComment)表实体类
 *
 * @author makejava
 * @since 2023-03-31 08:22:33
 */
@SuppressWarnings("serial")
@Data
public class BlComment implements Serializable {

 private static final long serialVersionUID = -4990810027542971546L;

    
    private String id;
    
    private String userId;
    
    private String articalId;
    
    private String status;
    
    private String content;

    private Date date;

    public BlComment(String articalId,String userId){
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

  
    }

