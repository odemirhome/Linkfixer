package com.entegman.radio.linkfixer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteException;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MyEventListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        createTable1();

        addColumn("radionet_stream", "TEXT", "found_links");
        addColumn("radionet_url", "TEXT", "found_links");
        addColumn("radionet_img", "TEXT", "found_links");

//        dropColumn("new_link", "radio");
//        dropColumn("found_link", "radio");
//        dropColumn("found_image", "radio");
//        dropColumn("link_failure2", "radio");
        addColumn("link_copied", "INTEGER", "radio");

    }

    public void createTable1(){
        String s = "CREATE TABLE IF NOT EXISTS found_links ( "+
                    "radio_id	INTEGER NOT NULL, "+
                    "onlineradiobox_stream	TEXT, "+
                    "onlineradiobox_url	TEXT, "+
                    "onlineradiobox_img	TEXT, "+
                    "canliradyodinle_stream	TEXT, "+
                    "canliradyodinle_url	TEXT, "+
                    "canliradyodinle_img	TEXT, "+
                "PRIMARY KEY(radio_id) )";

        jdbcTemplate.execute(s);

    }

    public void addColumn(String columnName, String type, String tableName){
        if(!exists(columnName, tableName)) {
            String sql = "ALTER TABLE "+tableName+" ADD COLUMN "+columnName+" "+type;
            jdbcTemplate.execute(sql);
        }
    }

    public void dropColumn(String columnName, String tableName){
        if(exists(columnName, tableName)) {
            String sql = "ALTER TABLE "+tableName+" DROP COLUMN "+columnName;
            jdbcTemplate.execute(sql);
        }
    }

    public boolean exists(String columnName, String tableName){
        String sql = "select count(*) from pragma_table_info('"+tableName+"') where name = '"+columnName+"'";
        int count = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return new Integer(resultSet.getInt(1));
            }
        });

        return count == 1;
    }
}
