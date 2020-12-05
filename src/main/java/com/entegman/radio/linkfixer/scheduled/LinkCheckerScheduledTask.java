package com.entegman.radio.linkfixer.scheduled;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.RadioForGrid;
import com.entegman.radio.linkfixer.model.Stringsss;
import com.entegman.radio.linkfixer.persist.RadioDao;
import com.entegman.radio.linkfixer.util.LinkChecker;
import com.entegman.radio.linkfixer.util.LinkCheckerThread;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class LinkCheckerScheduledTask {

    @Autowired
    private RadioDao radioDao;

    private static final Logger log = LoggerFactory.getLogger(LinkFinderScheduledTasks.class);

    @Scheduled(fixedRate = 86400000)
    public void checkLinks() throws IOException {

//        int threadSize = 8;
//
//        List<RadioForGrid> radioList = radioDao.listRadioForGridByCountry(52);
//
//        final List<List<RadioForGrid>> subset = ListUtils.partition(radioList, ((int)radioList.size()/threadSize)+1);
//
//        ExecutorService executer = Executors.newFixedThreadPool(threadSize, r-> {
//            Thread t = new Thread(r);
//            t.setDaemon(true);
//            return t;
//        });
//
//        List<LinkCheckerThread> threads = new ArrayList<>();
//
//        for(int i = 0; i<threadSize; i++){
//            threads.add(new LinkCheckerThread(subset.get(i), radioDao));
//        }
//
//        long startTime = System.currentTimeMillis();
//
//        for(int i = 0; i<threadSize; i++){
//            executer.submit(threads.get(i));
//        }
//
//        executer.shutdown();
//
//        while(!executer.isTerminated());
//
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("Total time : "+ (endTime-startTime) / 60000 +" minutes.");

    }

}
