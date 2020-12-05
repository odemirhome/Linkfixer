package com.entegman.radio.linkfixer.scheduled;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.Stringsss;
import com.entegman.radio.linkfixer.persist.RadioDao;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class LinkFinderScheduledTasks {

    @Autowired
    private RadioDao radioDao;

    private static final Logger log = LoggerFactory.getLogger(LinkFinderScheduledTasks.class);


//    @Scheduled(fixedRate = 86400000)
//    public void findLinks() {
//
//        List<Radio> radioList = radioDao.listAllRadiosByCountryId(52);
//        int i = 1;
//        for (Radio r : radioList) {
//            System.out.println(i + "/" + radioList.size());
//            i++;
//            Stringsss s = new Stringsss();
//
//            try {
//                s = scrapNewLink(r);
//            } catch (IOException e) {
//                System.out.println(r.getName() + " link alinamadi");
//            }
//
//            if(r.getId() == 33221){
//                System.out.println(1);
//            }
//
//            if (s != null) {
//                if (s.getNewLink().contains("play?platform=web")) {
//                    try {
//                        s.setNewLink(getRedirectUrl(s.getNewLink()));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                if (s != null)
//                    radioDao.updateNewLink(r.getId(), s);
//            }
//        }
//    }




}

