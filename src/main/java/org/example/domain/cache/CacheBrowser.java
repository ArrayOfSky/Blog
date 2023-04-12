package org.example.domain.cache;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;

@Data
public class CacheBrowser implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
    private HashSet<CacheNode> set = new HashSet<>();

    public boolean add(String articalId,int number){
        return set.add(new CacheNode(articalId,number));
    }

    public void increse(String articalId){
        for(CacheNode node : set){
            if(node.getArticalId().equals(articalId)){
                node.setNumber(node.getNumber()+1);
                break;
            }
        }
    }

    public void decrese(String articalId){
        for(CacheNode node : set){
            if(node.getArticalId().equals(articalId)){
                node.setNumber(node.getNumber()-1);
                break;
            }
        }
    }
}

