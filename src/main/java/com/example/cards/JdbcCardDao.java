package com.example.cards;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcCardDao implements CardDao{
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcCardDao(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public List<Card> getCards(int deckId){
		List<Card> cards = new ArrayList<Card>();
		
		String sql = "SELECT deck_id, card_id, question, answer, example, user_id FROM cards where deck_id = ? ORDER BY deck_id ASC";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, deckId);
		
		while(rows.next()) {
			Card card = new Card();
			card.setDeck_id(rows.getLong("deck_id"));
			card.setCard_id(rows.getLong("card_id"));
			card.setQuestion(rows.getString("question"));
			card.setAnswer(rows.getString("answer"));
			card.setExample(rows.getString("example"));
			card.setUser_id(rows.getLong("user_id"));
			cards.add(card);
		}
		return cards;
	}
	
	public void createCard(Card card) {
		String sql = "INSERT INTO cards (deck_id, card_id, user_id, question, answer, example) VALUES (?, default, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,card.getDeck_id(),card.getUser_id(), card.getQuestion(),card.getAnswer(),card.getExample());
	}
	
	public void deleteCard(int cardId) {
		String sql = "delete from cards where (card_id) = ?";
		jdbcTemplate.update(sql,cardId);
	}
	
	public void updateCard(Card card) {
		String sql = "update cards set question = ?, answer = ?, example = ? where card_id =?";
		jdbcTemplate.update(sql,card.getQuestion(),card.getAnswer(),card.getExample(), card.getCard_id());
	}
	
}
