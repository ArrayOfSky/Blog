package org.example.domain.response;

import lombok.Data;
import org.example.domain.BlArtical;
import org.example.domain.BlKind;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class KindListResponse implements Serializable {

    private static final long serialVersionUID = -4990810027542971546L;

    private String id;
    private String pid;

    private String name;

    private int level;

    ArrayList<BlArtical> list = new ArrayList<>();

    public KindListResponse(BlKind kind){
        this.id = kind.getId();
        this.pid = kind.getPid();
        this.level = kind.getLevel();
        this.name = kind.getName();
    }

    public void add(BlArtical artical){
        artical.setText(null);
        list.add(artical);
    }


}
