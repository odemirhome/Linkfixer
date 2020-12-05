package com.entegman.radio.linkfixer.model;

import java.util.List;

public class Radio {
    int id;
    String name;
    int countryId;
    String country;
    String city;
    String continent;
    String logo300;
    String description;
    String website;
    String logo175;
    String short_description;
    int rank;
    String genreStr;
    String languageStr;
    List<String> links;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLogo300() {
        return logo300;
    }

    public void setLogo300(String logo300) {
        this.logo300 = logo300;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo175() {
        return logo175;
    }

    public void setLogo175(String logo175) {
        this.logo175 = logo175;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public List<String> getGenre() {
//        return genre;
//    }
//
//    public void setGenre(List<String> genre) {
//        this.genre = genre;
//    }
//
//    public List<String> getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(List<String> language) {
//        this.language = language;
//    }

    public String getGenreStr() {
        return genreStr;
    }

    public void setGenreStr(String genreStr) {
        this.genreStr = genreStr;
    }

    public String getLanguageStr() {
        return languageStr;
    }

    public void setLanguageStr(String languageStr) {
        this.languageStr = languageStr;
    }
}
