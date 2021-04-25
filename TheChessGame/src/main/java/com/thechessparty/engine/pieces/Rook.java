package com.thechessparty.engine.pieces;

import com.google.common.collect.ImmutableList;
import com.thechessparty.engine.Team;
import com.thechessparty.engine.board.BoardUtilites;
import com.thechessparty.engine.board.GameBoard;
import com.thechessparty.engine.board.Tile;
import com.thechessparty.engine.moveset.AttackMove;
import com.thechessparty.engine.moveset.Move;
import com.thechessparty.engine.moveset.NormalMove;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
//class variables
    // offsets relative to the Rook's position on the board
    private final static int[] ROOK_MOVES = {-8, -1, 1, 8};
    public Rook(int position, final Team team){
        super(PieceIdentifiers.ROOK, position,team);
    }

       @Override
    public List<Move> listLegalMoves(final GameBoard board) {
       
        final List<Move> legalMoves = new ArrayList<>();
       // return null;
        for (final int current : ROOK_MOVES) {
            int destination = getPosition();
              while (BoardUtilites.isValidMove(destination)) {
                   if (isFirstColumn(destination, current) 
                    || isEighthColumn(destination, current)) {
                    break;
                }
                  destination += current;
               
                if (BoardUtilites.isValidMove(destination)) {
                   final Tile destinationTile = board.getTile(destination);

                // if destination Tile is not occupied get NormalMove
                if (!destinationTile.isTileOccupied()) {
                    legalMoves.add(new NormalMove(board, this, destination));
                } else {
                    final Piece occupyingPiece = destinationTile.getPiece();
                    final Team team = occupyingPiece.getTeam();

                    // if the Tile is occupied get AttackMove
                    if (getTeam() != team) {
                        legalMoves.add(new AttackMove(board, this, destination, occupyingPiece));
                    }
                    break; 
                }
            }
        }
    }

        return ImmutableList.copyOf(legalMoves);
}

//----------- private helper methods ---------------------

    /**
     * Utilizes the constant boolean array that tracks the
     *
     * @param currentPosition current coordinates of the Bishop
     * @param offset          the offset position to be qualified
     * @return true if the move can be made false if there is edge case exclusion
     */
    private static boolean isFirstColumn(final int currentPosition, final int offset) {
        return BoardUtilites.FIRST_COLUMN[currentPosition] && ((offset == -1));
    }


    /**
     * Utilizes the constant boolean array that tracks the
     *
     * @param currentPosition current coordinates of the Bishop
     * @param offset          the offset position to be qualified
     * @return true if the move can be made false if there is edge case exclusion
     */
    private static boolean isEighthColumn(final int currentPosition, final int offset) {
        return BoardUtilites.EIGHTH_COLUMN[currentPosition] && ((offset == 1));
    }

    /**
     * Creates a new Rook with updated position of Move
     * @param m the next Move of the Bishop
     * @return a new Bishop with position of Move
     */
    @Override
    public Rook movePiece(Move m) {
        return new Rook(m.getDestination(), m.getMovedPosition().getTeam());
    }

    @Override
    public String toString(){
        return PieceIdentifiers.ROOK.toString();
    }
}
