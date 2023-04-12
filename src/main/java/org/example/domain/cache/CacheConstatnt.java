package org.example.domain.cache;

import java.io.Serializable;

public class CacheConstatnt  implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
    public static CacheBrowser cacheBrowser = new CacheBrowser();

    public static CacheCollect cacheCollect = new CacheCollect();

    public static CacheLike cacheLike = new CacheLike();

}
