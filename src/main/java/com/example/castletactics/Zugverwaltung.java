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

    public Zugverwaltung(Spielverwaltung spv){
        figuren = new Figur[8][8];
        geschmissen = new ArrayList<>();
        this.spv = spv;
    }

    public boolean enPassantPrüfen(Bauer b, int destRow, int destCol)
    {
        if(enPassantKandidat != null && enPassantKandidat.col == destCol && Math.abs(enPassantKandidat.row-destRow) == 1
                && enPassantKandidat.row == b.row && Math.abs(b.col - enPassantKandidat.col) == 1){
            enPassantKandidat.schmeißen(spv.brett.pane);
            //System.out.println("en Passant!");
            return true;
        }
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
