package ohtu;

import java.util.HashMap;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;
    private HashMap<Integer, String> scoreCalls;
    
    
    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        setScoreCalls();
    }
    
    private void setScoreCalls() {
        this.scoreCalls = new HashMap<>();
        scoreCalls.put(0, "Love");
        scoreCalls.put(1, "Fifteen");
        scoreCalls.put(2, "Thirty");
        scoreCalls.put(3, "Forty");
    }

    public void wonPoint(String name) {
        if (name.equals(player1Name)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }  
    }
    
    public String getScore() { 
        if (player1Score==player2Score) {
            return getScoreCallForEvenPoints();  
        } else if (player1Score >= scoreCalls.size() || player2Score >= scoreCalls.size()) {
            return getAdvantageScoringCall(player1Score-player2Score);
        }

        return scoreCalls.get(player1Score) + "-" + scoreCalls.get(player2Score);
    }
    
    private String getScoreCallForEvenPoints() {
        if (player1Score >= scoreCalls.size()) {
            return "Deuce";
        }
        
        return scoreCalls.get(player1Score)+ "-All";
    }
    
    private String getAdvantageScoringCall(int difference) {
        int win = 2;
        int advantage = 1;
        
        if (difference == advantage) {
            return "Advantage player1";
        } else if (difference == -advantage) {
            return "Advantage player2";
        } else if (difference >= win) {
            return "Win for player1";
        }
        
        return "Win for player2";
    }
}