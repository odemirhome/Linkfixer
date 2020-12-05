package com.entegman.radio.linkfixer.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.*;

public class LinkChecker {

    public static boolean isLive(String link){

        HttpURLConnection urlconn = null;
        int res = -1;
        String msg = null;
        try{

            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

            URL url = new URL(myEncode(link));
            urlconn = (HttpURLConnection)url.openConnection();

            urlconn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:82.0) Gecko/20100101 Firefox/82.0");
            urlconn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            urlconn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            urlconn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            urlconn.setRequestProperty("Connection", "keep-alive");
            urlconn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            urlconn.setRequestProperty("Cache-Control", "max-age=0");
            urlconn.setRequestProperty("Referer", link);


            http://radyo.reklamsizradyo.org:9862/mp3/1/
            urlconn.setRequestProperty("Host", "radyo.reklamsizradyo.org:9862");



//            GET / HTTP/1.0

            urlconn.setConnectTimeout(10000);
            urlconn.setRequestMethod("GET");
            urlconn.connect();
//            String redirlink = urlconn.getHeaderField("Location");
//            if(redirlink != null && !url.toExternalForm().equals(redirlink))
//                return isLive(redirlink);
//            else
                return urlconn.getResponseCode()==HttpURLConnection.HTTP_OK;

        }catch(Exception e){
            return false;

        }finally{

            if(urlconn != null)
                urlconn.disconnect();

        }


    }

    public static String myEncode(String s){
        int index = StringUtils.ordinalIndexOf(s,"/", 3);

        String part1 = s.substring(0, index+1);
        String part2 = s.substring(index+1, s.length());

        String []parts = part2.split("/");

        String newPart2 = "";
        for(int i = 0; i < parts.length; i++){
            newPart2 += URLEncoder.encode(parts[i])+"/";
        }

        newPart2 = newPart2.substring(0, newPart2.length()-1);

        return part1+newPart2;
    }
}
