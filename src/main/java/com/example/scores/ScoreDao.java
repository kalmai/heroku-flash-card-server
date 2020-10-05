package com.example.scores;

import java.time.LocalDate;
import java.util.List;

public interface ScoreDao {
	public List<Score> getScores(int deckId);
	public List<Score> getUserScores(int userId);
	public void createScore(int deckId, int userId, double score, LocalDate insertDate);
}
