package test;

import java.util.Arrays;

public class Word {
    private Tile[] tiles;
    private int row;
    private int col;
    private boolean vertical;


    public Word(Tile[] init_tiles, int init_row, int init_col, boolean init_vertical){
        row = init_row;
        col = init_col;
        vertical = init_vertical;
        tiles = new Tile[init_tiles.length];
        System.arraycopy(init_tiles, 0, tiles, 0, tiles.length);
    }
    public boolean equals(Word other) {
        if (other == null)
            return false;
        if (this.col != other.col || this.row != other.row || this.vertical != other.vertical) {
            return false;
        }
        if(this.tiles.length != other.tiles.length) {
            return false;
        }
        for (int i = 0; i < tiles.length; i++) {
            if (this.tiles[i]!=null && other.tiles[i]!=null)
                if (!(this.tiles[i].equals(other.tiles[i])))
                    return false;
        }
        return true;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }
    public Tile[] getTiles() {
        return tiles;
    }
    public void setTiles(Tile[] tilesArr) {
        this.tiles = (Tile[])tilesArr.clone();
    }
}
