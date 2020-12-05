package com.entegman.radio.linkfixer.util;

import com.entegman.radio.linkfixer.model.RadioForGrid;
import com.entegman.radio.linkfixer.persist.RadioDao;

import java.util.List;
import java.util.concurrent.Callable;

public class LinkCheckerThread implements Callable <Void>{

    List<RadioForGrid> radioList;
    private RadioDao radioDao;

    public LinkCheckerThread(List<RadioForGrid> rl, RadioDao d){
        this.radioList = rl;
        this.radioDao = d;
    }

    @Override
    public Void call() throws Exception {
//        for (int i = 0; i<radioList.size(); i++){
//            RadioForGrid r =radioList.get(i);
//
//            if(!LinkChecker.isLive(r.getCurrentLink())){
//                radioDao.updateLinkAsFailure2(r.getId());
//            }
//            System.out.print("\r Doing "+i+"/"+radioList.size());
//        }
//
        return null;
    }
}
