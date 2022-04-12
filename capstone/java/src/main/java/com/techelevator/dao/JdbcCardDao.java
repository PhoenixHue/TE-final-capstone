package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
public class JdbcCardDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCardDao(DataSource datasource) { this.jdbcTemplate = new JdbcTemplate(datasource);}

    public boolean createCard(int module, String creator, String tag, String deck) {

        String sql= "BEGIN; " +
                "INSERT INTO card_table" +
                "(id, module, creator, tag, question, answer, deck)" +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);" +
                "COMMIT;";

                return true;}




                private Card mapRowToCard(SqlRowSet rowSetResults) {

                Card card = new Card();
                card.setId(rowSetResults.getInt("id"));
                card.setModule(rowSetResults.getInt("module"));
                card.setCreator(rowSetResults.getString("creator"));
                card.setTag(rowSetResults.getString("tag"));
                card.setQuestion(rowSetResults.getString("question"));
                card.setQuestion(rowSetResults.getString("answer"));
                card.setDeck(rowSetResults.getString("deck"));

                return card;
                }


    public Card findCardByTag(String tag) {
        String sql = "SELECT module, creator, question, answer, deck" +
                "FROM card_table"+
                "WHERE tag =?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tag);
        if (rowSet.next()) {
            return mapRowToCard(rowSet);
        } return null;
    }


        public Card findCardByModule(int module) {
            String moduleSql = "SELECT creator, tag, question, answer, deck" +
                    "FROM card_table"+
                    "WHERE module =?;";
            SqlRowSet moduleRowSet = jdbcTemplate.queryForRowSet(moduleSql, module);
            if (moduleRowSet.next()) {
                return mapRowToCard(moduleRowSet);
            } return null;
    }


            public Card findCardByCreator(String creator){
                String creatorSql = "SELECT module, tag, question, answer, deck" +
                        "FROM card_table" +
                        "WHERE creator =?;";
                SqlRowSet creatorRowSet = jdbcTemplate.queryForRowSet(creatorSql, creator);
                if (creatorRowSet.next()) {
                    return mapRowToCard(creatorRowSet);
                } return null;
            }


                public Card findCardByDeck(String deck){
                String deckSql = "SELECT module, creator, tag, question, answer" +
                        "FROM card_table" +
                        "WHERE deck =?;";
                SqlRowSet deckRowSet = jdbcTemplate.queryForRowSet(deckSql, deck);
                if (deckRowSet.next()) {
                    return mapRowToCard(deckRowSet);
                } return null;
            }








                }






