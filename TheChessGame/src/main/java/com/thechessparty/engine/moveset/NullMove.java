package com.thechessparty.engine.moveset;

import com.thechessparty.engine.board.GameBoard;

public class NullMove extends Move {

    public NullMove() {
        super(null, -1);
    }

    @Override
    public int getCurrent() {
        return -1;
    }

    @Override
    public int getDestination() {
        return -1;
    }

    @Override
    public GameBoard execute() {
        throw new RuntimeException("cannot execute null move!");
    }

    @Override
    public String toString() {
        return "Null Move";
    }
}