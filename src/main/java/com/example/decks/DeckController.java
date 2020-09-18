package com.example.decks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DeckController {

	@Autowired
	private DeckDao dao;
	
	public DeckController(DeckDao dao) {
		this.dao = dao;
	}

	@RequestMapping(path="/decks", method= RequestMethod.GET)
	public List<Deck> getDecks(){
		return dao.getDecks();
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(path="/create-deck/{deckName}", method=RequestMethod.POST)
	public void createDeck(@PathVariable String deckName) {
		dao.createDeck(deckName);
	}
	
	@RequestMapping(path="/delete-deck/{deckId}", method=RequestMethod.DELETE)
	public void deleteDeck(@PathVariable int deckId) {
		dao.deleteDeck(deckId);
	}
	
	@RequestMapping(path="/update-deck",method=RequestMethod.POST)
	public void updateDeck(@RequestBody Deck deck) {
		dao.editDeckName(deck);
	}
}
