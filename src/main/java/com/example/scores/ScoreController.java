package com.example.scores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ScoreController {
	
	@Autowired
	private ScoreDao dao;
	
	public ScoreController(ScoreDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(path="/leaderboard/{deckId}",method=RequestMethod.GET)
	public List<Score> getTopTen(@PathVariable int deckId){
		return dao.getScores(deckId);
	}
	
	@RequestMapping(path="/user-scores/{userName}",method=RequestMethod.GET)
	public List<Score> getUserScores(@PathVariable String userName){
		return dao.getUserScores(userName);
	}
	
	@RequestMapping(path="/create-score",method=RequestMethod.POST)
	public void createScore(@RequestBody Score score) {
		dao.createScore(score.getDeck_id(), score.getUser_name(), score.getScore(),score.getDate_inserted());
	}
}
