package org.example.utils;

import java.util.Random;

public class RamdomUtils {

    /**
     * 获取指定位数的随机数
     * @param num
     * @return
     */
    public String getRandom(int num){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < num; i++){
            sb.append(String.valueOf(random.nextInt(10)));
        }
        return sb.toString();
    }

    /**
     * 获得 区间随机数
     * @param start
     * @param end
     * @return
     */
    public Integer getRandom(int start,int end){
        int count=(int)(start+Math.random()*(end-start+1));
        return count;
    }



}
