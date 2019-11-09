/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myCheckers;

/**
 *
 * @author Cliff
 */
public class Checker {

    private String player;
    private Boolean crowned;


    public Checker(String player) {
        this.player = player;
        crowned = false;
    }

    public String GetPlayer() {
        return player;
    }

    public Boolean IsCrowned() {
        return crowned;
    }

    public void SetCrowned() {
        crowned = true;
    }

}
