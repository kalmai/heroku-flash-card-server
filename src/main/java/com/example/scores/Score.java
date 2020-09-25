package com.example.scores;

import java.time.LocalDate;

public class Score {

	int score_id;
	int deck_id;
	String user_name;
	double score;
	LocalDate date_inserted;
	
	public Score () {
		
	}

	public int getDeck_id() {
		return deck_id;
	}

	public void setDeck_id(int deck_id) {
		this.deck_id = deck_id;
	}

	public int getScore_id() {
		return score_id;
	}

	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public LocalDate getDate_inserted() {
		return date_inserted;
	}

	public void setDate_inserted(LocalDate date_inserted) {
		this.date_inserted = date_inserted;
	}

}
