package com.example.scores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(path="/user-scores/{userId}",method=RequestMethod.GET)
	public List<Score> getUserScores(@PathVariable int userId){
		return dao.getUserScores(userId);
	}
	
	@RequestMapping(path="/create-score",method=RequestMethod.POST)
	public void createScore(@RequestBody Score score) {
		dao.createScore(score.getDeck_id(), score.getUser_id(), score.getScore(),score.getDate_inserted());
	}
	
	@RequestMapping(path="/score-range", method=RequestMethod.GET)
	public List<Score> scoreDateRange(@RequestParam int userId,int deckId, String start, String end){
		LocalDate convertedStart = LocalDate.parse(start);
		LocalDate convertedEnd = LocalDate.parse(end);
		return dao.getScoresBetweenDates(userId, deckId, convertedStart,convertedEnd);
	}
}
