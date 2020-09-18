package com.example.decks;

import java.util.List;

public interface DeckDao {
	List<Deck> getDecks();
	void createDeck(String deckName);
	void deleteDeck(int deckId);
	void editDeckName(Deck deck);
}
