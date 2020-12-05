package com.entegman.radio.linkfixer.scrapper;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.Stringsss;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Component
public class OnlineRadioBoxLinkFinderConponent {

    public Stringsss findLinks(String r){
        Stringsss s = new Stringsss();

        try {
            s = scrapNewLink(r);
        } catch (IOException e) {
            System.out.println(r + " link alinamadi");
        }

        if (s != null) {
            if (s.getNewLink().contains("play?platform=web")) {
                try {
                    s.setNewLink(getRedirectUrl(s.getNewLink()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return s;
        }

        return null;
    }

    public  String getRedirectUrl(String url) throws Exception{
        String u = lookFirst(url);
        if(u == null){
            u = lookSecond(url);
        }

        return u;
    }

    private  String lookFirst(String url) throws Exception{

        URLConnection con = new URL( url ).openConnection();
        con.connect();
        InputStream is = con.getInputStream();
        if(url.equals(con.getURL().toString())){
            return null;
        } else{
            String u = con.getURL().toString();
            is.close();

            return u;
        }
    }

    public  String lookSecond(String url) throws Exception{

        HttpURLConnection con = (HttpURLConnection)(new URL( url ).openConnection());
        con.setInstanceFollowRedirects( false );
        con.connect();
        String location = con.getHeaderField( "Location" );
        return location;
    }

    public Stringsss scrapNewLink(String r) throws IOException {

        Stringsss s = null;


        Connection conn = Jsoup.connect("https://onlineradiobox.com/search?q=" + URLEncoder.encode(r.trim(), "UTF-8") + "&c=tr")
                .header("Host", "onlineradiobox.com")
                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:81.0) Gecko/20100101 Firefox/81.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");

        Document doc = conn.get();
        Elements els = doc.select("li.stations__station");

        String url = null;

        if (els.size() > 0) {
            for(int i = 0; i<els.size(); i++) {
                Element el = els.get(i);
                if (el.select(".station__title__name").html().toLowerCase().equals(r.trim().toLowerCase())) {
                    s = new Stringsss();

                    url = el.select("button").attr("stream");
                    s.setNewLink(url);

                    s.setNewImage(el.select(".station__title__logo").attr("src").substring(2));

                    s.setFoundLink("https://onlineradiobox.com" + el.select(".stations__station__title").select("a").attr("href"));

                    return s;
                }
            }

        }

        return null;
    }
}
