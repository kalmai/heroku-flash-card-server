package com.example.cards;

import java.util.List;

public interface CardDao {
	List<Card> getCards(int deckId);
	void createCard(Card card);
	void deleteCard(int cardId);
	void updateCard(Card card);
}
