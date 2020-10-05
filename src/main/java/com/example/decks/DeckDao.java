package com.example.decks;

import java.util.List;

public interface DeckDao {
	List<Deck> getDecks(long userId);
	void createDeck(long userId, String deckName, boolean isPublic);
	void deleteDeck(int deckId);
	void editDeckName(String deckName, boolean isPublic, long deckId);
}
