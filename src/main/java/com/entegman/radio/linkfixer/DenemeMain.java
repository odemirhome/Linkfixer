package com.entegman.radio.linkfixer;

import com.entegman.radio.linkfixer.model.Stringsss;
import com.entegman.radio.linkfixer.scrapper.CanliRadyoDinleLinkFinderConponent;
import com.entegman.radio.linkfixer.scrapper.OnlineRadioBoxLinkFinderConponent;
import com.entegman.radio.linkfixer.scrapper.RadionetLinkFinderConponent;
import com.entegman.radio.linkfixer.util.LinkChecker;
import org.springframework.beans.factory.annotation.Autowired;

public class DenemeMain {


    public static void main(String args[]){
        String name = "Radio Mevlana";

        Stringsss s = new RadionetLinkFinderConponent().findLinks(name);

        System.out.println(1);
    }
}
