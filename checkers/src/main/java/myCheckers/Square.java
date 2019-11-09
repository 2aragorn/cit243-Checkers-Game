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
public class Square {
	
    private Boolean isBlack;
    private Boolean occupied;
    public Checker checker;
    private int x;
    private int y;

    public Square(boolean isBlack, int x, int y) {
        this.isBlack = isBlack;
        occupied = false;
        this.x = x;
        this.y = y;
    }

    public Boolean IsBlack() {
        return isBlack;
    }

    public Boolean IsOccupied() {
        return occupied;
    }

    public Checker GetChecker() {
            return checker;
    }

    public int GetX() {
        return x;
    }

    public int GetY() {
        return y;
    }

    public void SetChecker(Checker checker) {
        occupied = true;
        this.checker = checker;
    }

    public void RemoveChecker() {
        occupied = false;
        checker = null;
    }

}
