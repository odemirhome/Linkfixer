
package com.entegman.radio.linkfixer.model.radionet;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RadioNet {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastModified")
    @Expose
    private Long lastModified;
    @SerializedName("logo44x44")
    @Expose
    private String logo44x44;
    @SerializedName("logo100x100")
    @Expose
    private String logo100x100;
    @SerializedName("logo175x175")
    @Expose
    private String logo175x175;
    @SerializedName("logo300x300")
    @Expose
    private String logo300x300;
    @SerializedName("logo630x630")
    @Expose
    private String logo630x630;
    @SerializedName("logo1200x1200")
    @Expose
    private String logo1200x1200;
    @SerializedName("playable")
    @Expose
    private Boolean playable;
    @SerializedName("streams")
    @Expose
    private List<Stream> streams = null;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("topics")
    @Expose
    private List<String> topics = null;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("homepageUrl")
    @Expose
    private String homepageUrl;
    @SerializedName("adParams")
    @Expose
    private String adParams;
    @SerializedName("hideReferer")
    @Expose
    private Boolean hideReferer;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("topicTags")
    @Expose
    private List<TopicTag> topicTags = null;
    @SerializedName("genreTags")
    @Expose
    private List<GenreTag> genreTags = null;
    @SerializedName("cityTag")
    @Expose
    private CityTag cityTag;
    @SerializedName("countryTag")
    @Expose
    private CountryTag countryTag;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public String getLogo44x44() {
        return logo44x44;
    }

    public void setLogo44x44(String logo44x44) {
        this.logo44x44 = logo44x44;
    }

    public String getLogo100x100() {
        return logo100x100;
    }

    public void setLogo100x100(String logo100x100) {
        this.logo100x100 = logo100x100;
    }

    public String getLogo175x175() {
        return logo175x175;
    }

    public void setLogo175x175(String logo175x175) {
        this.logo175x175 = logo175x175;
    }

    public String getLogo300x300() {
        return logo300x300;
    }

    public void setLogo300x300(String logo300x300) {
        this.logo300x300 = logo300x300;
    }

    public String getLogo630x630() {
        return logo630x630;
    }

    public void setLogo630x630(String logo630x630) {
        this.logo630x630 = logo630x630;
    }

    public String getLogo1200x1200() {
        return logo1200x1200;
    }

    public void setLogo1200x1200(String logo1200x1200) {
        this.logo1200x1200 = logo1200x1200;
    }

    public Boolean getPlayable() {
        return playable;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getAdParams() {
        return adParams;
    }

    public void setAdParams(String adParams) {
        this.adParams = adParams;
    }

    public Boolean getHideReferer() {
        return hideReferer;
    }

    public void setHideReferer(Boolean hideReferer) {
        this.hideReferer = hideReferer;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<TopicTag> getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(List<TopicTag> topicTags) {
        this.topicTags = topicTags;
    }

    public List<GenreTag> getGenreTags() {
        return genreTags;
    }

    public void setGenreTags(List<GenreTag> genreTags) {
        this.genreTags = genreTags;
    }

    public CityTag getCityTag() {
        return cityTag;
    }

    public void setCityTag(CityTag cityTag) {
        this.cityTag = cityTag;
    }

    public CountryTag getCountryTag() {
        return countryTag;
    }

    public void setCountryTag(CountryTag countryTag) {
        this.countryTag = countryTag;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
