package org.example.domain.cache;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class CacheNode implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
    private String articalId;
    private int number;
    private ArrayList<String> userIdList = new ArrayList<>();

    public CacheNode(String articalId, int number) {
        this.articalId = articalId;
        this.number = number;
    }

    public CacheNode(String articalId){
        this.articalId = articalId;
    }

    public void add(String userId){
        userIdList.add(userId);
    }


}
