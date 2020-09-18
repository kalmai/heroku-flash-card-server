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
	
	public void createDeck(String deckName) {		
		String sql = "insert into decks values (default, ?)";
		jdbcTemplate.update(sql, deckName);
	}
	
	public void deleteDeck(int deckId) {
		String cardNotNull = "select count (card_id) from cards where deck_id = ?";
		Object param = 1;
		int result = jdbcTemplate.queryForObject(cardNotNull, new Object[] {param} ,Integer.class);
		if(result != 0) {
			String sql = "delete from cards where (deck_id) = ?";
			jdbcTemplate.queryForRowSet(sql,deckId);
		}
		
		String sql = "delete from decks where (deck_id) = ?";
		jdbcTemplate.update(sql,deckId);	
	}
	
	public void editDeckName(Deck deck) {
		String sql = "update decks set deck_name = ? where deck_id = ?";
		jdbcTemplate.update(sql,deck.getDeck_name(), deck.getDeck_id());
	}

}
