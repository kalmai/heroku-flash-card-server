package com.example.scores;

import java.time.LocalDate;
import java.time.LocalTime;
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
		
		String sql = "select scores.deck_id, scores.user_name, avg(scores.score) as score from scores where deck_id = ? group by scores.user_name, scores.deck_id order by score desc limit 10";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,deckId);
		
		while(rows.next()) {
			Score score = new Score();
			score.setDeck_id(rows.getInt("deck_id"));
			score.setUser_name(rows.getString("user_name"));
			score.setScore(rows.getDouble("score"));
			score.setDate_inserted(rows.getDate("date_inserted").toLocalDate());
			scores.add(score);
		}
		return scores;
	}
	
	public List<Score> getUserScores(String userName){
		List<Score> scores = new ArrayList<Score>();
		
		String sql = "select score_id, deck_id, user_name, score, date_inserted::date FROM scores where user_name = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,userName);
		
		while(rows.next()) {
			Score score = new Score();
			score.setScore_id(rows.getInt("score_id"));
			score.setDeck_id(rows.getInt("deck_id"));
			score.setUser_name(rows.getString("user_name"));
			score.setScore(rows.getDouble("score"));
			score.setDate_inserted(rows.getDate("date_inserted").toLocalDate());
			scores.add(score);
		}
		return scores;
	}
	
	public void createScore(int deckId, String userName, double score, LocalDate insertDate) {
		String sql = "insert into scores values (default, ? , ?, ? ,?)";
		jdbcTemplate.update(sql,deckId,userName,score,insertDate);
	}
}
