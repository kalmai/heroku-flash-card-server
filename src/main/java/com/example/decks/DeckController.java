package com.example.decks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
    @ResponseBody
	@RequestMapping(path="/test", method= RequestMethod.GET)
	public String test() {
		return dao.test();
	}
	
}
