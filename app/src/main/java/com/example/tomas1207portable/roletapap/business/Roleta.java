package com.example.tomas1207portable.roletapap.business;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * Main runner for roulette.
 *
 * Sets a random number, calculates the selected player based off the
 * rotation.
 */
public class Roleta {
    private Integer direction;
    private Integer DefDirection;
    private Integer Lapscount;
    private String winningPlayer;
    private ArrayList<String> players;

    public Roleta(ArrayList<String> players) {
        this.players = players;
        this.direction = 0;
    }

    public Roleta run() {
        rotate();
        findWinner();
        return this;
    }

    public Integer getDirection() {
        return direction;
    }
    public String getWinningPlayer() {
        return winningPlayer;
    }
    public ArrayList<String> getPlayers() {
        return players;
    }
    public Integer getLapscount() {
        return Lapscount;
    }
    public void setLapscount(Integer lapscount) {
        Lapscount = lapscount;
    }

    private void rotate() {
        DefDirection = new Random().nextInt(4);
        if (DefDirection == 0){
            direction = 0;
        }
        if (DefDirection == 1){
            direction = 90;
        }
        if (DefDirection == 2){
            direction = 180;
        }
        if (DefDirection == 3){
            direction = 270;
        }
    }


    private void findWinner() {
        float i = (float) getDirection() / 360 * getPlayers().size();
        setWinningPlayer(getPlayers().get((int) i));
    }

    private void setWinningPlayer(String winningPlayer) {
        this.winningPlayer = winningPlayer;
    }


}
