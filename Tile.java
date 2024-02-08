package test;

import java.util.Arrays;

public class Tile {
    final char letter;final int score;

    public boolean equals(Tile other) {
        return this.letter == other.letter && this.score == other.score;
    }

    private Tile(char init_letter, int init_score) {
        this.letter = init_letter;
        this.score = init_score;
    }

    public int hashCode() {
        char temp_letter = letter;int code;
        code = temp_letter - 'A' + 1;
        return code;
    }

    public static class Bag {
        int[] numOfLetters = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        int[] lettersScore = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        Tile[] begTiles;
        private Bag(){
            begTiles = new Tile[26];
            for (int i = 0; i < 26; i++) {
                char init_letter = (char) ('A' + i);
                int init_score = lettersScore[i];
                begTiles[i] = new Tile(init_letter, init_score);
            }
        }
        private int lotteryLetter() {
                int min = 1;int max = 25;int randomNumber;
                int range = max - min + 1;
                randomNumber = (int)(Math.random() * range) + min;
                return randomNumber;
        }
        public Tile getRand(){
            int randomNumber = lotteryLetter();Tile return_tile;
            if (Arrays.stream(this.numOfLetters).sum() == 0)
                return null;
            if (this.numOfLetters[randomNumber] == 0)
                return getRand();
            if (this.numOfLetters[randomNumber] != 0){
                this.numOfLetters[randomNumber]--;
                return_tile = this.begTiles[randomNumber];
                return return_tile;
            }
            return null;
        }
        public Tile getTile(char letter){
            Tile return_tile;
            if (letter >= 'A' && letter <= 'Z') {
                int letter_location = letter - 'A' + 1 - 1;
                if (this.numOfLetters[letter_location] != 0) {
                    this.numOfLetters[letter_location]--;
                    return_tile = this.begTiles[letter_location];
                    return return_tile;
                }
            }
            return null;
        }
        void put(Tile recall){
            int[] maxOfLetters = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
            int recallNum = recall.letter - 'A' + 1 -1;
            if (this.numOfLetters[recallNum] < maxOfLetters[recallNum])
                this.numOfLetters[recallNum]++;
        }
        int size(){
            return Arrays.stream(numOfLetters).sum();
        }
        int[] getQuantities(){
            int[] numOfLettersCopy = new int[26];
            System.arraycopy(numOfLetters, 0, numOfLettersCopy, 0, numOfLetters.length);
            return numOfLettersCopy;
        }
        private static Bag myBeg = null;
        public static Bag getBag(){
            if (myBeg == null)
                return myBeg = new Bag();
            return myBeg;
        }
    }
}
