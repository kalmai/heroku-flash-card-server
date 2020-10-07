package com.example.scores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcScoreDao implements ScoreDao{
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcScoreDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

	public List<Score> getScores(int deckId){
		List<Score> scores = new ArrayList<Score>();
		
		String sql = "select scores.deck_id, users.user_name, avg(scores.score) as score from scores join users on scores.user_id = users.user_id where scores.deck_id = ? group by users.user_name, scores.deck_id order by score desc limit 10";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,deckId);
		
		while(rows.next()) {
			Score score = new Score();
			score.setDeck_id(rows.getInt("deck_id"));
			score.setUser_name(rows.getString("user_name"));
			score.setScore(rows.getDouble("score"));
			scores.add(score);
		}
		return scores;
	}
	
	public List<Score> getUserScores(int userId){
		List<Score> scores = new ArrayList<Score>();
		
		String sql = "select scores.score_id, scores.deck_id, scores.user_id, scores.score, scores.date_inserted::date, users.user_name FROM scores join users on scores.user_id = users.user_id where scores.user_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,userId);
		
		while(rows.next()) {
			Score score = new Score();
			score.setScore_id(rows.getInt("score_id"));
			score.setUser_name(rows.getString("user_name"));
			score.setDeck_id(rows.getInt("deck_id"));
			score.setUser_id(rows.getInt("user_id"));
			score.setScore(rows.getDouble("score"));
			score.setDate_inserted(rows.getDate("date_inserted").toLocalDate());
			scores.add(score);
		}
		return scores;
	}
	
	public void createScore(int deckId, int userId, double score, LocalDate insertDate) {
		String sql = "INSERT INTO scores (score_id, deck_id, user_id, score, date_inserted) VALUES (default, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,deckId,userId,score,insertDate);
	}
	
	public List<Score> getScoresBetweenDates(int userId, int deckId, LocalDate start, LocalDate end){
		List<Score> scores = new ArrayList<Score>();
		
		String sql = "select scores.deck_id, scores.user_id, avg(scores.score)::int as score, scores.date_inserted::date from scores where scores.user_id = ? and scores.deck_id = ? and scores.date_inserted between ? and ? group by scores.deck_id, scores.user_id,scores.date_inserted::date order by date_inserted ASC";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,userId,deckId,start,end);
		
		while(rows.next()) {
			Score score = new Score();
			score.setDeck_id(rows.getInt("deck_id"));
			score.setUser_id(rows.getInt("user_id"));
			score.setScore(rows.getDouble("score"));
			score.setDate_inserted(rows.getDate("date_inserted").toLocalDate());
			scores.add(score);
		}
		return scores;
	}
}
