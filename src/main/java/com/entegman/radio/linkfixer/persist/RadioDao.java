package com.entegman.radio.linkfixer.persist;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.RadioForGrid;
import com.entegman.radio.linkfixer.model.SITEENUM;
import com.entegman.radio.linkfixer.model.Stringsss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Component
public class RadioDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Radio> listAllRadiosByCountryId(int countryId) {
        String sql = "select * from radio where countryId = ? order by id";

        Object[] parameters={countryId};
        int[] types={Types.INTEGER};

        List<Radio> radioList = jdbcTemplate.query(sql, parameters, types, new RowMapper<Radio>() {
            @Override
            public Radio mapRow(ResultSet rs, int i) throws SQLException {
                return populateRadio(rs);
            }
        });

        return radioList;
    }

    public List<RadioForGrid> listAllRadiosForGridByCountryId(int countryId) {
        String sql = "select " +
                " r.id as id, r.name as name, r.logo175 as currentImage, l.url as currentLink, " +
                " fl.onlineradiobox_stream ostream, fl.onlineradiobox_url ourl, fl.onlineradiobox_img oimg, " +
                " fl.canliradyodinle_stream cstream, fl.canliradyodinle_url curl, fl.canliradyodinle_img cimg, " +
                " fl.radionet_stream rstream, fl.radionet_url rurl, fl.radionet_img rimg, r.link_copied copied " +
                " from radio r " +
                " left join radio_link rl on rl.radio_id =r.id " +
                " left join link l on l.id =rl.link_id " +
                " left join found_links fl on fl.radio_id = r.id " +
                " where r.countryId = ? " +
                " order by r.id";

        Object[] parameters={countryId};
        int[] types={Types.INTEGER};

        List<RadioForGrid> radioList = jdbcTemplate.query(sql, parameters, types, new RowMapper<RadioForGrid>() {
            int j = 1;

            @Override
            public RadioForGrid mapRow(ResultSet rs, int i) throws SQLException {
                RadioForGrid r = populateRadioForGrid(rs, j);
                j++;

                return r;
            }
        });

        return radioList;
    }

//    public List<RadioForGrid> listBrokenRadioForGridByCountry(int countryId) {
//
//        String sql = "select r.id as id, r.name as name, r.logo175 as currentImage, l.url as currentLink, r.new_link as newLink, r.found_image as foundImage, r.found_link as foundLink, r.link_copied copied " +
//                "from radio r " +
//                "left join radio_link rl on rl.radio_id =r.id " +
//                "left join link l on l.id =rl.link_id " +
//                "where r.countryId = ? " +
//                "AND r.new_link <> l.url order by r.id";
//
//        Object[] parameters={countryId};
//        int[] types={Types.INTEGER};
//
//        List<RadioForGrid> radioList = jdbcTemplate.query(sql, parameters, types, new RowMapper<RadioForGrid>() {
//
//            int j = 1;
//
//            @Override
//            public RadioForGrid mapRow(ResultSet rs, int i) throws SQLException {
//                RadioForGrid r = populateRadioForGrid(rs, j);
//                j++;
//
//                return r;
//            }
//        });
//
//        return radioList;
//    }


//    public List<RadioForGrid> listRadioForGridByCountry(int countryId) {
//
//        String sql = "select r.id as id, r.name as name, r.logo175 as currentImage, l.url as currentLink, r.new_link as newLink, r.found_image as foundImage, r.found_link as foundLink, r.link_copied copied " +
//                "from radio r " +
//                "left join radio_link rl on rl.radio_id =r.id " +
//                "left join link l on l.id =rl.link_id " +
//                "where r.countryId = ? order by r.id";
//
//        Object[] parameters={countryId};
//        int[] types={Types.INTEGER};
//
//        List<RadioForGrid> radioList = jdbcTemplate.query(sql, parameters, types, new RowMapper<RadioForGrid>() {
//
//            int j = 1;
//
//            @Override
//            public RadioForGrid mapRow(ResultSet rs, int i) throws SQLException {
//                RadioForGrid r = populateRadioForGrid(rs, j);
//                j++;
//
//                return r;
//            }
//        });
//
//        return radioList;
//    }

//    public List<Radio> listBrokenLinkRadios() {
//        String sql = "SELECT * from radio where link_failure2 = 1 order by id";
//
//        return jdbcTemplate.query(sql, new RowMapper<Radio>() {
//
//            @Override
//            public Radio mapRow(ResultSet rs, int i) throws SQLException {
//
//                return populateRadio(rs);
//
//            }
//        });
//    }


    private Radio populateRadio(ResultSet rs) throws SQLException {
        Radio r = new Radio();
        r.setId(rs.getInt("id"));
        r.setName(rs.getString("name"));
        r.setCountryId(rs.getInt("countryId"));
        r.setCity(rs.getString("city"));
        r.setContinent(rs.getString("continent"));
        r.setLogo300(rs.getString("logo300"));
        r.setDescription(rs.getString("description"));
        r.setWebsite(rs.getString("website"));
        r.setLogo175(rs.getString("logo175"));
        r.setShort_description(rs.getString("short_description"));
        r.setRank(rs.getInt("rank"));

        return r;
    }

    private RadioForGrid populateRadioForGrid(ResultSet rs, int i) throws SQLException {
        RadioForGrid r = new RadioForGrid();
        r.setIndex(i);
        r.setId(rs.getInt("id"));
        r.setName(rs.getString("name"));
        r.setCurrentImage(rs.getString("currentImage"));
        r.setCurrentLink(rs.getString("currentLink"));

        r.setOstream(rs.getString("ostream"));
        r.setOurl(rs.getString("ourl"));
        r.setOimg(rs.getString("oimg"));

        r.setCstream(rs.getString("cstream"));
        r.setCurl(rs.getString("curl"));
        r.setCimg(rs.getString("cimg"));

        r.setRstream(rs.getString("rstream"));
        r.setRurl(rs.getString("rurl"));
        r.setRimg(rs.getString("rimg"));

        r.setLinkCopied(rs.getInt("copied"));

        return r;
    }


//    public boolean updateNewLink(int id, Stringsss s) {
//        String sql = "update radio set new_link = ?, found_link = ?, found_image = ? where id = ?";
//
//        Object[] parameters = {s.getNewLink(), s.getFoundLink(), s.getNewImage(), id};
//        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
//
//        int r = jdbcTemplate.update(sql, parameters, types);
//
//        return r > 0;
//    }
//
//    public boolean updateLinkAsFailure(int radioId) {
//        String sql = "update radio set link_failure = 1 where id = ?";
//
//        Object[] parameters={radioId};
//        int[] types={Types.INTEGER};
//
//        int r = jdbcTemplate.update(sql, parameters, types);
//
//        return r > 0;
//    }

//    public boolean updateLinkAsFailure2(int radioId) {
//        String sql = "update radio set link_failure2 = 1 where id = ?";
//
//        Object[] parameters={radioId};
//        int[] types={Types.INTEGER};
//
//        int r = jdbcTemplate.update(sql, parameters, types);
//
//        return r > 0;
//    }

    public boolean updateFixedLink(int radioId, SITEENUM sitenum) {

        String column = "";

        if(sitenum.equals(SITEENUM.CANLIRADYO)){
            column = "canliradyodinle_stream";
        } else if(sitenum.equals(SITEENUM.RADIONET)){
            column = "radionet_stream";
        } else if(sitenum.equals(SITEENUM.ONLINERADIO)){
            column = "onlineradiobox_stream";
        }

        String sql = "update link set url = (select "+column+" from found_links where radio_id = ?) where id = (select link_id from radio_link where radio_id = ?)";

        Object[] parameters={radioId, radioId};
        int[] types={Types.INTEGER, Types.INTEGER};

        int r = jdbcTemplate.update(sql, parameters, types);

        if(r > 0){
            String sql2 = "update radio set link_copied = 1 where id = ?";

            Object[] parameters2={radioId};
            int[] types2={Types.INTEGER};

            int r2 = jdbcTemplate.update(sql2, parameters2, types2);

            return r2 > 0 ;

        } else{
            return false;
        }

    }


    public boolean justCheckFixed(int radioId) {

        String sql2 = "update radio set link_copied = 1 where id = ?";

        Object[] parameters2={radioId};
        int[] types2={Types.INTEGER};

        int r2 = jdbcTemplate.update(sql2, parameters2, types2);

        return r2 > 0 ;
    }


    public boolean updateLinkOfRadio(int radioId, String url) {
        String sql = "update link set url = ? where id in (select link_id from radio_link where radio_id = ?)";

        Object[] parameters={url, radioId};
        int[] types={Types.VARCHAR, Types.INTEGER};

        int r = jdbcTemplate.update(sql, parameters, types);

        return r > 0;
    }

    public List<RadioForGrid> listAllLinkNull(int countryId) {

        String sql = "select " +
                " r.id as id, r.name as name, r.logo175 as currentImage, l.url as currentLink, " +
                " fl.onlineradiobox_stream ostream, fl.onlineradiobox_url ourl, fl.onlineradiobox_img oimg, " +
                " fl.canliradyodinle_stream cstream, fl.canliradyodinle_url curl, fl.canliradyodinle_img cimg, " +
                " fl.radionet_stream rstream, fl.radionet_url rurl, fl.radionet_img rimg, r.link_copied copied " +
                " from radio r " +
                " left join radio_link rl on rl.radio_id =r.id " +
                " left join link l on l.id =rl.link_id " +
                " left join found_links fl on fl.radio_id = r.id " +
                " where r.countryId = ? " +
                " and fl.onlineradiobox_stream is null AND fl.canliradyodinle_stream is null AND fl.radionet_stream is null " +
                " order by r.id";

        Object[] parameters={countryId};
        int[] types={Types.INTEGER};

        List<RadioForGrid> radioList = jdbcTemplate.query(sql, parameters, types, new RowMapper<RadioForGrid>() {

            int j = 1;

            @Override
            public RadioForGrid mapRow(ResultSet rs, int i) throws SQLException {
                RadioForGrid r = populateRadioForGrid(rs, j);
                j++;

                return r;
            }
        });

        return radioList;

    }

    public boolean resetLinksCopied() {

        String sql = "update radio set link_copied = 0 ";

        int r = jdbcTemplate.update(sql);

        return r > 0;
    }

//    public List<RadioForGrid> listRadiosWithTheSameLink(int countryId) {
//
//        String sql = "select r.id as id, r.name as name, r.logo175 as currentImage, l.url as currentLink, r.new_link as newLink, r.found_image as foundImage, r.found_link as foundLink, r.link_copied copied " +
//                "from radio r " +
//                "left join radio_link rl on rl.radio_id =r.id " +
//                "left join link l on l.id =rl.link_id " +
//                "where r.countryId = ? " +
//                "AND r.new_link = l.url order by r.id";
//
//        Object[] parameters={countryId};
//        int[] types={Types.INTEGER};
//
//        List<RadioForGrid> radioList = jdbcTemplate.query(sql, parameters, types, new RowMapper<RadioForGrid>() {
//
//            int j = 1;
//
//            @Override
//            public RadioForGrid mapRow(ResultSet rs, int i) throws SQLException {
//                RadioForGrid r = populateRadioForGrid(rs, j);
//                j++;
//
//                return r;
//            }
//        });
//
//        return radioList;
//    }
}
