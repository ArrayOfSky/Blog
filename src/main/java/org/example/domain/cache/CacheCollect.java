package org.example.domain.cache;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;

@Data
public class CacheCollect implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
    private HashSet<CacheNode> set = new HashSet<>();

    public void add(String articalId,int number){
        set.add(new CacheNode(articalId,number));
    }

    public void addUser(String articalId,String userId){
        for(CacheNode cacheNode:set){
            if(cacheNode.getArticalId().equals(articalId)){
                cacheNode.add(userId);
                set.add(cacheNode);
                break;
            }
        }
    }
    public boolean deleteUser(String articalId,String userId){
        for(CacheNode cacheNode:set){
            if(cacheNode.getArticalId().equals(articalId)){
              return cacheNode.getUserIdList().contains(userId);
            }
        }
        return false;
    }

}

