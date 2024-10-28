package com.stray.cat.util;

import java.security.MessageDigest;
import java.util.Random;

public class TitleUtil {
    //生成MD5
    public static String getUrl() {
        Random rand = new Random();
        int i=rand.nextInt(7);
        if(i==0){
            return "/images/title/title1.jpg";
        }if(i==1){
            return "/images/title/title2.jpg";
        }if(i==2){
            return "/images/title/title3.jpg";
        }if(i==3){
            return "/images/title/title4.jpg";
        }if(i==4){
            return "/images/title/title5.jpg";
        }if(i==5){
            return "/images/title/title6.jpg";
        }else{
            return "/images/title/title7.jpg";
        }
    }
}
