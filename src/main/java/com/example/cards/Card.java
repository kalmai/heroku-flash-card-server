package com.example.cards;

public class Card {
	long deck_id;
	long card_id;
	String question;
	String answer;
	String example;
	long user_id;
	
	public Card() {
		
	}
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getDeck_id() {
		return deck_id;
	}

	public void setDeck_id(long deck_id) {
		this.deck_id = deck_id;
	}

	public long getCard_id() {
		return card_id;
	}

	public void setCard_id(long card_id) {
		this.card_id = card_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

}
