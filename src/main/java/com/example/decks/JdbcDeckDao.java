package com.example.decks;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcDeckDao implements DeckDao{
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcDeckDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

	public List<Deck> getDecks(long userId) {
		List<Deck> decks = new ArrayList<Deck>();
		String sql = "";
		SqlRowSet rows;
		if(userId > 0) {
			sql = "select * from decks where is_public = true union select * from decks where is_public = false and user_id = ? order by deck_id ASC";
			rows = jdbcTemplate.queryForRowSet(sql,userId);
		}else {
			sql = "select * from decks where is_public = true order by deck_id ASC";
			rows = jdbcTemplate.queryForRowSet(sql);
		}
		
		while(rows.next()) {
			Deck deck = new Deck();
			deck.setUser_id(rows.getLong("user_id"));
			deck.setDeck_id(rows.getLong("deck_id"));
			deck.setDeck_name(rows.getString("deck_name"));
			deck.setIs_public(rows.getBoolean("is_public"));
			decks.add(deck);
			}
		return decks;
	}
	
	public void createDeck(long userId, String deckName, boolean isPublic) {		
		String sql = "insert into decks values (?,default,?,?)";
		jdbcTemplate.update(sql, userId,deckName,isPublic);
	}
	
	public void deleteDeck(int deckId) {
		String sql = "delete from decks where (deck_id) = ?";
		jdbcTemplate.update(sql,deckId);	
	}
	
	public void editDeckName(String deckName, boolean isPublic, long deckId) {
		String sql = "update decks set (deck_name, is_public) = (?,?) where deck_id = ?";
		jdbcTemplate.update(sql, deckName, isPublic,deckId);
	}

}
