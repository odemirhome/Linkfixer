package com.entegman.radio.linkfixer.scrapper;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.Stringsss;
import com.entegman.radio.linkfixer.model.radionet.RadioNet;
import com.entegman.radio.linkfixer.model.radionet.Station;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.sql.SQLException;
import java.util.List;

@Component
public class RadionetLinkFinderConponent {

    public Stringsss findLinks(String r){
        Stringsss s = new Stringsss();

        try {
            s = scrapNewLink(r);
        } catch (IOException e) {
            System.out.println(r + " link alinamadi");
        }


        return s;
    }

//    public static void scrapNewLink(String radioName) throws IOException, SQLException {
//        Document doc = Jsoup.connect("https://www.radio.net/search?q="+ URLEncoder.encode(radioName, "UTF-8")).get();

//        Elements els = doc.select("a[data-testid=list-item]");

//        if (els.size() > 0) {
//            if(els.get(0).select(".chXIOx").html().toLowerCase().equals(radioName.toLowerCase())) {
//                Element el = els.get(0).select("a").get(0);
//
//
//                doc = Jsoup.connect("https://www.radio.net" + el.attr("href")).get();
//                Radio r = getRadio(doc);
//                //
//                List<Link> lnks = RadioDAO.getLinksByRadio(radioId, conn);
//
//                for (int i = 0; i < lnks.size(); i++) {
//                    RadioDAO.updateLink(conn, lnks.get(i).getId(), r.getStation().getStreams()[0].getUrl());
//                }
//
//                RadioDAO.updateRadio(conn, radioId,2);
//            }
//        }
//    }



    public Stringsss scrapNewLink(String r) throws IOException {

        Stringsss s = null;


        Connection conn = Jsoup.connect("https://www.radio.net/search?q=" + URLEncoder.encode(r.trim(), "UTF-8"))
                .header("Host", "radio.net/")
                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:81.0) Gecko/20100101 Firefox/81.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");

        Document doc = conn.get();
        Elements els = doc.select("a[data-testid=list-item]");

        String url = null;

        if (els.size() > 0) {
            for(int i = 0; i<els.size(); i++) {
                Element el = els.get(i);
                if (el.select("div").get(5).html().toLowerCase().equals(r.trim().toLowerCase())) {
                    String uy = "https://www.radio.net"+el.attr("href");
                    conn = Jsoup.connect(uy)
                            .header("Host", "radio.net/")
                            .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:81.0) Gecko/20100101 Firefox/81.0")
                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                            .header("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");
                    doc = conn.get();

                    String script = doc.select(".player-inner-wrapper script").get(0).html();

                    String jsonStr = script.substring(script.indexOf("'components/station/stationService':")+36, script.indexOf("'components/player/playerService'")-12);

                    Gson g = new GsonBuilder().create();
                    Station st = g.fromJson(jsonStr, Station.class);

                    s = new Stringsss();

                    s.setNewLink(st.getStation().getStreams().get(0).getUrl());

                    s.setNewImage(st.getStation().getLogo175x175());

                    s.setFoundLink(uy);

                    return s;
                }
            }

        }

        return null;
    }
}
