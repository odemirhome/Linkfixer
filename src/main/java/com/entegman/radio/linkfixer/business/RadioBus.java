package com.entegman.radio.linkfixer.business;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.Stringsss;
import com.entegman.radio.linkfixer.persist.FoundLinksDAO;
import com.entegman.radio.linkfixer.scrapper.CanliRadyoDinleLinkFinderConponent;
import com.entegman.radio.linkfixer.scrapper.OnlineRadioBoxLinkFinderConponent;
import com.entegman.radio.linkfixer.scrapper.RadionetLinkFinderConponent;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.concurrent.Callable;

public class RadioBus implements Callable<Void> {

    List<Radio> radioList;

    public RadioBus(List<Radio> rl, JdbcTemplate temp) {
        this.radioList = rl;
        foundLinksDAO = new FoundLinksDAO(temp);
        olinkFinder = new OnlineRadioBoxLinkFinderConponent();
        clinkFinder = new CanliRadyoDinleLinkFinderConponent();
        rlinkFinder = new RadionetLinkFinderConponent();
    }

    FoundLinksDAO foundLinksDAO;

    private OnlineRadioBoxLinkFinderConponent olinkFinder;

    private CanliRadyoDinleLinkFinderConponent clinkFinder;

    private RadionetLinkFinderConponent rlinkFinder;


    @Override
    public Void call() throws Exception {

        int i = 1;
        for (Radio r : radioList) {
            System.out.println(i + "/" + radioList.size());
            i++;

            Stringsss onlineRadioBoxLinks = olinkFinder.findLinks(r.getName());
            Stringsss canliRadyoDinleLinks = clinkFinder.findLinks(r.getName());
            Stringsss radionetRadyoDinleLinks = rlinkFinder.findLinks(r.getName());

            if (onlineRadioBoxLinks == null) {
                onlineRadioBoxLinks = new Stringsss();
                onlineRadioBoxLinks.setNewLink("");
                onlineRadioBoxLinks.setFoundLink("");
                onlineRadioBoxLinks.setNewImage("");
            }

            if (canliRadyoDinleLinks == null) {
                canliRadyoDinleLinks = new Stringsss();
                canliRadyoDinleLinks.setNewLink("");
                canliRadyoDinleLinks.setFoundLink("");
                canliRadyoDinleLinks.setNewImage("");
            }

            if (radionetRadyoDinleLinks == null) {
                radionetRadyoDinleLinks = new Stringsss();
                radionetRadyoDinleLinks.setNewLink("");
                radionetRadyoDinleLinks.setFoundLink("");
                radionetRadyoDinleLinks.setNewImage("");
            }

            if (foundLinksDAO.exists(r.getId())) {
                foundLinksDAO.updateRadioLink(onlineRadioBoxLinks, canliRadyoDinleLinks, radionetRadyoDinleLinks, r);
            } else {
                foundLinksDAO.insertRadioLink(onlineRadioBoxLinks, canliRadyoDinleLinks, radionetRadyoDinleLinks, r);
            }
        }

        return null;
    }

}