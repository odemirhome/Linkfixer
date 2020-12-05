package com.entegman.radio.linkfixer.scrapper;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.Stringsss;
import org.apache.commons.compress.compressors.brotli.BrotliCompressorInputStream;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;

@Component
public class CanliRadyoDinleLinkFinderConponent {

    public Stringsss findLinks(String r){
        Stringsss s = new Stringsss();

        try {
            s = scrapNewLink(r);
        } catch (IOException e) {
            System.out.println(r + " link alinamadi");
        }

        return s;
    }



    public Stringsss scrapNewLink(String r) throws IOException {

        Stringsss s = null;

        Connection conn = Jsoup.connect("https://www.canliradyodinle.fm/?s=" + URLEncoder.encode(r.trim(), "UTF-8"))
                .header("Host", "onlineradiobox.com")
                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:81.0) Gecko/20100101 Firefox/81.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");

        Document doc = conn.get();
        Elements els = doc.select(".canli-radyo-listesi li");

        String url = null;

        if (els.size() > 0) {
            Element el = els.get(0);
            if (el.select("a strong").html().toLowerCase().equals(r.trim().toLowerCase())) {

                String link = el.select("a").attr("href");

                conn = Jsoup.connect(link)
                    .header("Host", "onlineradiobox.com")
                    .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:81.0) Gecko/20100101 Firefox/81.0")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");

                doc = conn.get();
                els = doc.select("input[name=radyoid]");
                String cookie = conn.response().header("Set-Cookie");
                if(els.size() > 0){

                    s = new Stringsss();

                    String radioId = els.get(0).attr("value");

                    String urr = doAjax("https://www.canliradyodinle.fm/wp-admin/admin-ajax.php?action=radyocal&radyo="+radioId, cookie, link);


                    conn = Jsoup.connect(urr)
                            .header("Host", "onlineradiobox.com")
                            .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:81.0) Gecko/20100101 Firefox/81.0")
                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                            .header("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");

                    doc = conn.get();
                    els = doc.select("audio source");

                    url = els.get(0).attr("src");

                    s.setNewLink(url);
                    s.setNewImage(doc.select(".radyo-oynatici-logo").attr("src"));
                    s.setFoundLink(link);

                    return s;
                }

            }

        }

        return null;
    }

    public String doAjax(String u, String cookie, String mainUrl) {

        String uu = "";

        cookie = "__gads=ID=7c5db93bc1492221-22b79fc75ca600b5:T=1603954305:RT=1603954305:S=ALNI_MaWuqHWfVAFpXWkVXblIfAGyU7Tnw; __cfduid=d66f4b149b0aad98414ab752dac03baed1603954303; canliradyolastradio=%5B181%2C1634%5D";

        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:82.0) Gecko/20100101 Firefox/82.0");
            conn.setRequestProperty("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("DNT", "1");
            conn.setRequestProperty("Host", "www.canliradyodinle.fm");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Referer", mainUrl);
            conn.setRequestProperty("TE", "Trailers");
            conn.setRequestProperty("Content-Type", "application/json");


            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }


            String json = IOUtils.readLines(new BrotliCompressorInputStream(conn.getInputStream())).get(0);

            uu = json.substring(json.indexOf("src=\\\"")+6, json.indexOf("\\\"><\\/iframe>\"}"));
            conn.disconnect();

            uu = uu.replace("\\/","/");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uu;
    }
}
