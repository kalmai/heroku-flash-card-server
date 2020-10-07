package com.example.scores;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ScoreDao {
	public List<Score> getScores(int deckId);
	public List<Score> getUserScores(int userId);
	public void createScore(int deckId, int userId, double score, LocalDate insertDate);
	public List<Score> getScoresBetweenDates(int userId, int deckId, LocalDate start, LocalDate end);
}
