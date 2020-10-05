package com.example.decks;

public class Deck {
	long user_id;
	long deck_id;
	String deck_name;
	boolean is_public;
	
	public Deck() {
		
	}

	public long getDeck_id() {
		return deck_id;
	}

	public void setDeck_id(long deck_id) {
		this.deck_id = deck_id;
	}

	public String getDeck_name() {
		return deck_name;
	}

	public void setDeck_name(String deck_name) {
		this.deck_name = deck_name;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public boolean isIs_public() {
		return is_public;
	}

	public void setIs_public(boolean is_public) {
		this.is_public = is_public;
	}
	
	
}
