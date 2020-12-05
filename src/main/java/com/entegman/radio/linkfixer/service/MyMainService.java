package com.entegman.radio.linkfixer.service;


import com.entegman.radio.linkfixer.business.RadioBus;
import com.entegman.radio.linkfixer.model.Stringsss;
import com.entegman.radio.linkfixer.scrapper.OnlineRadioBoxLinkFinderConponent;
import com.entegman.radio.linkfixer.persist.RadioDao;
import com.entegman.radio.linkfixer.service.model.*;
import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.RadioForGrid;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MyMainService {

    @Autowired
    private RadioDao radioDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    boolean linkFinderWorking = false;


    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello my %s!", name);
    }

//    @PostMapping("/listRadiosDifferentLinks")
//    public ResponseEntity<ListRadioResult> listRadiosDifferentLinks(@RequestBody ListAllRadiosParam param) {
//
//
//        List<RadioForGrid> radioList = radioDao.listBrokenRadioForGridByCountry(52);
//
//        ListRadioResult response = new ListRadioResult();
//        response.setResultObject(radioList);
//        response.setSuccess(true);
//        response.setTotal(radioList.size());
//
//        return new ResponseEntity<ListRadioResult>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/listRadiosSameLinks")
//    public ResponseEntity<ListRadioResult> listRadiosSameLinks(@RequestBody ListAllRadiosParam param) {
//
//
//        List<RadioForGrid> radioList = radioDao.listRadiosWithTheSameLink(52);
//
//        ListRadioResult response = new ListRadioResult();
//        response.setResultObject(radioList);
//        response.setSuccess(true);
//        response.setTotal(radioList.size());
//
//        return new ResponseEntity<ListRadioResult>(response, HttpStatus.OK);
//    }

    @PostMapping("/listAllRadios")
    public ResponseEntity<List<RadioForGrid>> listAllRadios(@RequestBody ListAllRadiosParam param) {

        List<RadioForGrid>radioList = radioDao.listAllRadiosForGridByCountryId(52);
        return new ResponseEntity<List<RadioForGrid>>(radioList, HttpStatus.OK);
    }

    @PostMapping("/listAllLinkNull")
    public ResponseEntity<List<RadioForGrid>> listAllLinkNull(@RequestBody ListAllRadiosParam param) {

        List<RadioForGrid>radioList = radioDao.listAllLinkNull(52);
        return new ResponseEntity<List<RadioForGrid>>(radioList, HttpStatus.OK);
    }

//    @PostMapping("/updateAsLinkFailure")
//    public ResponseEntity<Boolean> updateAsLinkFailure(@RequestBody UpdateLinlkFailureParam param) {
//
//        if(radioDao.updateLinkAsFailure(param.getRadioId()))
//            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//        else
//            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
//    }

    @PostMapping("/updateLinkOfRadio")
    public ResponseEntity<Boolean> updateAsLinkOfRadio(@RequestBody UpdateLinkOfRadio param) {

        if(radioDao.updateLinkOfRadio(param.getRadioId(), param.getUrl()))
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }

    @PostMapping("/updateFixedLink")
    public ResponseEntity<Boolean> updateFixedLink(@RequestBody UpdateLinlkFailureParam param) {

        if(radioDao.updateFixedLink(param.getRadioId(), param.getSitenum()))
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);

    }

    @PostMapping("/justCheckFixed")
    public ResponseEntity<Boolean> justCheckFixed(@RequestBody JustCheckParam param) {

        if(radioDao.justCheckFixed(param.getRadioId()))
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);

    }

    @PostMapping("/findLinks")
    public ResponseEntity<Boolean> findOnlineRadioBoxLinks() {

        if(!linkFinderWorking) {
            linkFinderWorking = true;

            List<Radio> radioList = radioDao.listAllRadiosByCountryId(52);
            List<List<Radio>> subset = ListUtils.partition(radioList, ((int)radioList.size()/8)+1);

            ExecutorService executer = Executors.newFixedThreadPool(8, r-> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

            List<RadioBus> threads = new ArrayList<>();

            for(int i = 0; i<8; i++){
                threads.add(new RadioBus(subset.get(i), jdbcTemplate));
            }

            long startTime = System.currentTimeMillis();

            for(int i = 0; i<8; i++){
                executer.submit(threads.get(i));
            }

            executer.shutdown();

            while(!executer.isTerminated());

        }

        linkFinderWorking = false;
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/resetLinksCopied")
    public ResponseEntity<Boolean> resetLinksCopied() {

        if(radioDao.resetLinksCopied())
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);

    }


    @PostMapping("/isLinkFinderWorking")
    public ResponseEntity<Boolean> isLinkFiderWorking() {
        return new ResponseEntity<Boolean>(linkFinderWorking, HttpStatus.OK);
    }

}
