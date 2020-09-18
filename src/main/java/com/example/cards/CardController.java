package com.example.cards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CardController {
	
	@Autowired
	private CardDao dao;
	
	public CardController(CardDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(path="/cards/{deckId}", method= RequestMethod.GET)
	public List<Card> getCards(@PathVariable int deckId){
		return dao.getCards(deckId);
	}
	
	@RequestMapping(path="/create-card", method = RequestMethod.POST)
	public void createCard(@RequestBody Card card) {
		dao.createCard(card);
	}

	@RequestMapping(path="/delete-card/{cardId}", method=RequestMethod.DELETE)
	public void deleteCard(@PathVariable int cardId) {
		dao.deleteCard(cardId);
	}
	
	@RequestMapping(path="/update-card", method=RequestMethod.POST)
	public void updateCard(@RequestBody Card card) {
		dao.updateCard(card);
	}
	
}
