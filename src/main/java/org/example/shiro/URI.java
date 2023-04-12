package org.example.shiro;

import java.util.HashSet;
import java.util.Set;

public class URI {
    public static Set<String> anonUri = new HashSet();

    public static Set<String> authcUri = new HashSet<>();
    static{
        anonUri.add("/**");

//        authcUri.add("/");
    }


}
