package com.example.scores;

import java.time.LocalDate;
import java.util.List;

public interface ScoreDao {
	public List<Score> getScores(int deckId);
	public List<Score> getUserScores(String userName);
	public void createScore(int deckId, String userName, double score, LocalDate insertDate);
}
