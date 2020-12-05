package com.entegman.radio.linkfixer.persist;

import com.entegman.radio.linkfixer.model.Radio;
import com.entegman.radio.linkfixer.model.RadioForGrid;
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
public class FoundLinksDAO {

    private JdbcTemplate jdbcTemplate;

    public FoundLinksDAO(JdbcTemplate jt){
        jdbcTemplate = jt;
    }

    public boolean insertRadioLink(Stringsss onlineRadioBox, Stringsss canliradyodinle, Stringsss radionet, Radio r){
        String sql = "INSERT INTO found_links (" +
                " radio_id, onlineradiobox_stream, onlineradiobox_url, onlineradiobox_img, " +
                " canliradyodinle_stream, canliradyodinle_url, canliradyodinle_img, " +
                " radionet_stream, radionet_url, radionet_img" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?)";

        int[] types = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] parameters = {r.getId(), onlineRadioBox.getNewLink(), onlineRadioBox.getFoundLink(), onlineRadioBox.getNewImage(),
                canliradyodinle.getNewLink(), canliradyodinle.getFoundLink(), canliradyodinle.getNewImage(),
                radionet.getNewLink(), radionet.getFoundLink(), radionet.getNewImage()};

        int rt = jdbcTemplate.update(sql, parameters, types);

        return rt > 0;
    }


    public boolean exists(int radioId) {

        String sql = "select count(1) from found_links where radio_id = ?";

        Object[] parameters={radioId};
        int[] types={Types.INTEGER};

        Integer count = jdbcTemplate.queryForObject(sql, parameters, types, Integer.class);

        return count > 0;
    }


    public boolean updateRadioLink(Stringsss onlineRadioBox, Stringsss canliradyodinle, Stringsss radionet, Radio r){
        String sql = "UPDATE found_links set onlineradiobox_stream = ?, onlineradiobox_url = ?, onlineradiobox_img = ?, " +
                " canliradyodinle_stream = ? , canliradyodinle_url = ?, canliradyodinle_img = ?," +
                " radionet_stream = ? , radionet_url = ?, radionet_img = ?  where radio_id = ?";

        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        Object[] parameters = {onlineRadioBox.getNewLink(), onlineRadioBox.getFoundLink(), onlineRadioBox.getNewImage(),
                canliradyodinle.getNewLink(), canliradyodinle.getFoundLink(), canliradyodinle.getNewImage(),
                radionet.getNewLink(), radionet.getFoundLink(), radionet.getNewImage(), r.getId()};

        int rt = jdbcTemplate.update(sql, parameters, types);

        return rt > 0;
    }
}
