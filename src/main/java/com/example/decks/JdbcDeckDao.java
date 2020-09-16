package com.example.decks;

import com.example.decks.*;

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

	public List<Deck> getDecks() {
		List<Deck> decks = new ArrayList<Deck>();
		
		String sql = "SELECT deck_id, deck_name FROM decks";		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while(rows.next()) {
			Deck deck = new Deck();
			deck.setDeck_id(rows.getLong("deck_id"));
			deck.setDeck_name(rows.getString("deck_name"));
			decks.add(deck);
			}
		return decks;
	}
	
	public String test() {
		String str = "this is a test";
		return str;
	}

}
