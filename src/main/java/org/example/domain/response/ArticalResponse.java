package org.example.domain.response;

import lombok.Data;
import org.example.domain.BlArtical;
import org.example.domain.BlComment;
import org.example.domain.BlKind;
import org.redisson.misc.Hash;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
public class ArticalResponse {

    //文章
    private String id;

    private String title;

    private String text;

    private Date date;

    private int like;
    private int comment;
    private int collect;

    private int browser;

    //评论
    ArrayList<BlComment> arrayList = new ArrayList<>();
    //点赞用户
    private HashSet<String> likeUserId = new HashSet<>();
    //收藏用户
    private HashSet<String> collectUserId = new HashSet<>();
    public ArticalResponse(BlArtical blArtical){
        this.id = blArtical.getId();
        this.title = blArtical.getTitle();
        this.text = blArtical.getText();
        this.date = blArtical.getDate();
        this.like = blArtical.getLike();
        this.comment = blArtical.getComment();
        this.collect = blArtical.getCollect();
        this.browser = blArtical.getBrowser();
    }

    public void addComment(List<BlComment> list){
        arrayList.addAll(list);
        this.comment = list.size();
    }

    public void addLike(List<String> likeUserId){
        this.likeUserId.addAll(likeUserId);
        this.like = likeUserId.size();
    }

    public void addCollect(List<String> collectUserId){
        this.collectUserId.addAll(collectUserId);
        this.collect = collectUserId.size();
    }

}
