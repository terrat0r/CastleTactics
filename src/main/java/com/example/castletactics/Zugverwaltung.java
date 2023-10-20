package com.example.castletactics;

import javafx.stage.Stage;

import java.util.ArrayList;

public class Zugverwaltung {
    int runde = 0;
    boolean whitePlays = true;
    public Figur zumSchmeißen;
    public Figur derSchmeißende;
    public Figur enPassantKandidat = null;
    Figur[][] figuren;
    ArrayList<Figur> geschmissen;
    private Spielverwaltung spv;
    Netzwerk netzwerk = new Netzwerk();

    public Zugverwaltung(Spielverwaltung spv){
        figuren = new Figur[8][8];
        geschmissen = new ArrayList<>();
        this.spv = spv;
    }

    public boolean enPassantPrüfen(Bauer b, int destRow, int destCol)
    {
        if(enPassantKandidat != null && enPassantKandidat.col == destCol && Math.abs(enPassantKandidat.row-destRow) == 1
                && enPassantKandidat.row == b.row && Math.abs(b.col - enPassantKandidat.col) == 1 && enPassantKandidat.lastMoveRows == 2){
            enPassantKandidat.schmeißen(spv.brett.pane);
            //System.out.println("en Passant!");
            return true;
        }
        return false;
    }

    public boolean rochadePrüfen() {
        //TODO: zugvorhersage & schach
        return false;
    }

    /**
     * This function is intended to only be called once per play-draw
     * @return
     */
    public boolean zugPrüfen(){
        if(derSchmeißende.zugErlaubt(derSchmeißende.row, derSchmeißende.col, zumSchmeißen.row, zumSchmeißen.col) && derSchmeißende.isWhite != zumSchmeißen.isWhite && whitePlays == derSchmeißende.isWhite) {

            return true;
        }
        return false;
    }

    public boolean zugPrüfen(int rowDest, int colDest) {
        if(derSchmeißende.zugErlaubt(derSchmeißende.row, derSchmeißende.col, rowDest, colDest) && whitePlays == derSchmeißende.isWhite) {

            return true;
        }
        return false;
    }

    public boolean move(int row, int col, int rowDest, int colDest) {
        derSchmeißende = figuren[row][col];
        zumSchmeißen = figuren[rowDest][colDest];
        boolean success = false;
        if (zumSchmeißen != null && zugPrüfen()) {
            derSchmeißende.move(rowDest, colDest);
            success = true;
        }
        else if (zumSchmeißen == null && zugPrüfen(rowDest, colDest)) {
            derSchmeißende.move(rowDest, colDest);
            success = true;
        }

        if (derSchmeißende instanceof Bauer) {
            if(enPassantPrüfen((Bauer) derSchmeißende, rowDest, colDest)) {
                derSchmeißende.move(rowDest, colDest);
                success = true;
            }
        }
        if(!derSchmeißende.isWhite) {

        }
        //TODO: Rochade
        return success;
    }

    public void zugende() {
        whitePlays = !whitePlays;
        if (whitePlays)
            runde++;
    }

    public void schmeißen(int row, int col) {
        if (figuren[row][col] != null) {
            figuren[row][col].schmeißen(spv.brett.pane);
            figuren[row][col] = null;
        }
    }
}
