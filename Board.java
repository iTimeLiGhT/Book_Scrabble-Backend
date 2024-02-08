package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    static int[][] bonusBoard = {
            //1 = Triple Word Score
            //2 = Double Word Score
            //3 = Triple Letter Score
            //4 = Double Letter Score
            {1, 0, 0, 4, 0, 0, 0, 1, 0, 0, 0, 4, 0, 0, 1},
            {0, 2, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 2, 0},
            {0, 0, 2, 0, 0, 0, 4, 0, 4, 0, 0, 0, 2, 0, 0},
            {4, 0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 2, 0, 0, 4},
            {0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0},
            {0, 0, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 0, 0},
            {1, 0, 0, 4, 0, 0, 0, 2, 0, 0, 0, 4, 0, 0, 1},
            {0, 0, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 0, 0},
            {0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0},
            {0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
            {4, 0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 2, 0, 0, 4},
            {0, 0, 2, 0, 0, 0, 4, 0, 4, 0, 0, 0, 2, 0, 0},
            {0, 2, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 2, 0},
            {1, 0, 0, 4, 0, 0, 0, 1, 0, 0, 0, 4, 0, 0, 1}
    };
    Tile[][] mainBoard = new Tile[15][15];
    private static Board myBoard = null;
    private Word inUse[] = new Word[30];
    private int inUseCount =0;
    public static Board getBoard() {
        if (myBoard == null)
            return myBoard = new Board();
        return myBoard;
    }
        public Board(){
            for (int i=0;i<15;i++)
                Arrays.fill(mainBoard[i], null);
        }
        public Tile[][] getTiles(){
        Tile[][] mainBoardCopy = new Tile[15][15];
            for (int i=0;i<15;i++)
                System.arraycopy(mainBoard[i], 0, mainBoardCopy[i], 0, 15);
            return mainBoardCopy;
        }
    public boolean boardLegal(Word temp_word){
        boolean flag = false;
        //Default
        if(temp_word.getCol() < 0 || temp_word.getCol() > 14 || temp_word.getRow() < 0 || temp_word.getRow()  > 14)
            return false;
        //Vertical - מאונך
        if (temp_word.isVertical()) {
            if (temp_word.getRow() + temp_word.getTiles().length > 14)
                return false;
            //StartMove
            if (mainBoard[7][7] == null){
                if (temp_word.getCol() != 7)
                    return false;
                if (temp_word.getCol() + temp_word.getTiles().length < 7 || temp_word.getRow() > 7)
                    return false;
            }
            else {
                for (int i=0;i<temp_word.getTiles().length;i++){
                    if (mainBoard[temp_word.getRow() + i][temp_word.getCol()] != null
                            || (temp_word.getCol() + 1 < 15 && mainBoard[temp_word.getRow() + i][temp_word.getCol() + 1] != null)
                            || (temp_word.getCol() - 1 > -1 && mainBoard[temp_word.getRow() + i][temp_word.getCol() - 1] != null)
                            || (temp_word.getRow() + i + 1 < 15 && mainBoard[temp_word.getRow() + i + 1][temp_word.getCol()] != null)
                            || (temp_word.getRow() + i - 1 > -1 && mainBoard[temp_word.getRow() + i - 1][temp_word.getCol()] != null))
                        flag = true;
                    if (mainBoard[temp_word.getRow()+i][temp_word.getCol()]!=null && temp_word.getTiles()[i]!=null)
                        if (mainBoard[temp_word.getRow()+i][temp_word.getCol()].letter!=temp_word.getTiles()[i].letter)
                            return false;
                }
                if (!flag)
                    return false;
            }
        }
        //Horizontal - מאוזן
        if (!temp_word.isVertical()){
            if(temp_word.getCol() + temp_word.getTiles().length > 14)
                return false;
            //StartMove
            if( mainBoard[7][7]==null){
                if(temp_word.getRow()!=7)
                    return false;
                if(temp_word.getCol()+temp_word.getTiles().length<7 || temp_word.getCol()>7)
                    return false;
            }
            else{
                for(int i=0;i<temp_word.getTiles().length;i++){
                    if((mainBoard[temp_word.getRow()][temp_word.getCol()+i]!=null
                            || (temp_word.getRow()+1<15 && mainBoard[temp_word.getRow()+1][temp_word.getCol()+i]!=null)
                            || (temp_word.getRow()-1>-1 && mainBoard[temp_word.getRow()-1][temp_word.getCol()+i]!=null)
                            || (temp_word.getCol()+i+1 < 15 && mainBoard[temp_word.getRow()][temp_word.getCol()+i+1]!=null)
                            || (temp_word.getCol()+i-1>-1 && mainBoard[temp_word.getRow()][temp_word.getCol()+i-1]!=null)))
                        flag = true;
                    if(mainBoard[temp_word.getRow()][temp_word.getCol()+i]!=null && temp_word.getTiles()[i]!=null)
                        if (mainBoard[temp_word.getRow()][temp_word.getCol()+i].letter!=temp_word.getTiles()[i].letter)
                            return false;
                }
                if (!flag)
                    return false;
            }
        }
        return true;
    }

        public boolean dictionaryLegal(Word temp_word){return true;}

        public ArrayList<Word> getWords(Word temp_word){
            ArrayList<Word> wordsArr = new ArrayList<Word>();
            ArrayList<Tile> temp = new ArrayList<Tile>();
            Tile[] tiles = temp_word.getTiles();
            wordsArr.add(temp_word);
            int startPoint;
            int endPoint;
            for(int i=0; i<tiles.length;i++){
                if(temp_word.isVertical()){
                    startPoint = temp_word.getCol();
                    endPoint = temp_word.getCol();
                    while(startPoint-1>-1 && mainBoard[temp_word.getRow()+i][startPoint-1]!=null){
                        startPoint--;
                    }
                    while(endPoint+1<15 && mainBoard[temp_word.getRow()+i][endPoint+1]!=null){
                        endPoint++;
                    }
                    if(startPoint != temp_word.getCol() || endPoint != temp_word.getCol()){
                        temp.addAll(Arrays.asList(mainBoard[temp_word.getRow() + i]).subList(startPoint, endPoint + 1));

                        Word word = new Word(temp.toArray(new Tile[temp.size()]), temp_word.getRow()+i, startPoint, false);
                        for (int j=0;j<inUseCount;j++){
                            if (inUse[j].equals(word))
                                word = null;
                        }
                        if (word!=null) {
                            wordsArr.add(word);
                        }
                    }
                }
                if(!(temp_word.isVertical())){
                    startPoint = temp_word.getRow();
                    endPoint = temp_word.getRow();
                    while(startPoint-1>-1 && mainBoard[startPoint-1][temp_word.getCol()+i]!=null){
                        startPoint--;
                    }
                    while(endPoint+1<15 && mainBoard[endPoint+1][temp_word.getCol()+i]!=null){
                        endPoint++;
                    }
                    if(startPoint != temp_word.getRow() || endPoint != temp_word.getRow()){
                        temp.clear();
                        for(int j = startPoint; j <= endPoint; j++)
                            temp.add(mainBoard[j][temp_word.getCol()+i]);
                        if(temp.size()>0){
                            Word word = new Word(temp.toArray(new Tile[temp.size()]), startPoint, temp_word.getCol()+i, true);
                            for (int j=0;j<inUseCount;j++){
                                if (inUse[j].equals(word))
                                    word = null;
                            }
                            if (word!=null) {
                                wordsArr.add(word);
                            }
                        }
                    }

                }
            }
            return wordsArr;
        }
        public int getScore(Word temp_word){
            int totalScore = 0;
            int tileLocation = 0;
            boolean tripleFlag = false;boolean doubleFlag = false;
            //Vertical - מאונך
            if (temp_word.isVertical()){
                for (int i=temp_word.getRow();i<=(temp_word.getRow()+temp_word.getTiles().length-1);i++){
                    if (temp_word.getTiles()[tileLocation] != null){
                        if (bonusBoard[i][temp_word.getCol()] == 0)
                            totalScore += temp_word.getTiles()[tileLocation].score;
                        if (bonusBoard[i][temp_word.getCol()] == 1){//1 = Triple Word Score
                            tripleFlag = true;
                            totalScore += temp_word.getTiles()[tileLocation].score;
                        }
                        if (bonusBoard[i][temp_word.getCol()] == 2){//2 = Double Word Score
                            doubleFlag = true;
                            totalScore += temp_word.getTiles()[tileLocation].score;
                        }
                        if (bonusBoard[i][temp_word.getCol()] == 3)//3 = Triple Letter Score
                            totalScore += 3*(temp_word.getTiles()[tileLocation].score);
                        if (bonusBoard[i][temp_word.getCol()] == 4)//4 = Double Letter Score
                            totalScore += 2*(temp_word.getTiles()[tileLocation].score);

                    }
                    else{
                        if (temp_word.getTiles()[tileLocation] == null && mainBoard[i][temp_word.getCol()] != null){
                            if (bonusBoard[i][temp_word.getCol()] == 0)
                                totalScore +=mainBoard[i][temp_word.getCol()].score;
                            if (bonusBoard[i][temp_word.getCol()] == 1){//1 = Triple Word Score
                                tripleFlag = true;
                                totalScore +=mainBoard[i][temp_word.getCol()].score;
                            }
                            if (bonusBoard[i][temp_word.getCol()] == 2){//2 = Double Word Score
                                doubleFlag = true;
                                totalScore +=mainBoard[i][temp_word.getCol()].score;
                            }
                            if (bonusBoard[i][temp_word.getCol()] == 3)//3 = Triple Letter Score
                                totalScore += 3*(mainBoard[i][temp_word.getCol()].score);
                            if (bonusBoard[i][temp_word.getCol()] == 4)//4 = Double Letter Score
                                totalScore += 2*(mainBoard[i][temp_word.getCol()].score);
                        }
                    }
                    tileLocation++;
                }
            }
            //Horizontal - מאוזן
            if (!temp_word.isVertical()){
                //First Move Bonus
                if(inUseCount>0)
                    bonusBoard[7][7]=0;
                for (int i=temp_word.getCol();i<=(temp_word.getCol()+temp_word.getTiles().length-1);i++){
                    if (temp_word.getTiles()[tileLocation] != null){
                        if (bonusBoard[temp_word.getRow()][i] == 0)
                            totalScore += temp_word.getTiles()[tileLocation].score;
                        if (bonusBoard[temp_word.getRow()][i] == 1){//1 = Triple Word Score
                            tripleFlag = true;
                            totalScore += temp_word.getTiles()[tileLocation].score;
                        }
                        if (bonusBoard[temp_word.getRow()][i] == 2){//2 = Double Word Score
                            doubleFlag = true;
                            totalScore += temp_word.getTiles()[tileLocation].score;
                        }
                        if (bonusBoard[temp_word.getRow()][i] == 3)//3 = Triple Letter Score
                            totalScore += 3*(temp_word.getTiles()[tileLocation].score);
                        if (bonusBoard[temp_word.getRow()][i] == 4)//4 = Double Letter Score
                            totalScore += 2*(temp_word.getTiles()[tileLocation].score);
                    }
                    else{
                        if (temp_word.getTiles()[tileLocation] == null && mainBoard[temp_word.getRow()][i] != null){
                            if (bonusBoard[temp_word.getRow()][i] == 0)
                                totalScore +=mainBoard[temp_word.getRow()][i].score;
                            if (bonusBoard[temp_word.getRow()][i] == 1){//1 = Triple Word Score
                                tripleFlag = true;
                                totalScore +=mainBoard[temp_word.getRow()][i].score;
                            }
                            if (bonusBoard[temp_word.getRow()][i] == 2){//2 = Double Word Score
                                doubleFlag = true;
                                totalScore +=mainBoard[temp_word.getRow()][i].score;
                            }
                            if (bonusBoard[temp_word.getRow()][i] == 3)//3 = Triple Letter Score
                                totalScore += 3*(mainBoard[temp_word.getRow()][i].score);
                            if (bonusBoard[temp_word.getRow()][i] == 4)//4 = Double Letter Score
                                totalScore += 2*(mainBoard[temp_word.getRow()][i].score);
                        }

                    }
                    tileLocation++;
                }
            }
			
            if (tripleFlag)
                return totalScore*3;
            if (doubleFlag)
                return totalScore*2;
            else return totalScore;
        }
        public boolean searchIfUse(Word temp_word){
        for (int i=0;i<inUseCount;i++) {
            if (inUse[i].equals(temp_word)) {
                return false;
            }
        }
            return true;
        }
        public int tryPlaceWord(Word temp_word){
            int total_score=0;
            int tileLocation = 0;
//-----------------------------------------------------------------//
        if (boardLegal(temp_word) && dictionaryLegal(temp_word)){
            //Vertical
            if (temp_word.isVertical()){
                for (int i=temp_word.getRow();i<=(temp_word.getRow()+temp_word.getTiles().length-1);i++){
                    if (temp_word.getTiles()[tileLocation]!=null) {
                        mainBoard[i][temp_word.getCol()] = temp_word.getTiles()[tileLocation];
                    }
                    tileLocation++;
                }
            }
            //Horizontal
            if (!temp_word.isVertical()){
                for (int i=temp_word.getCol();i<=(temp_word.getCol()+temp_word.getTiles().length-1);i++){
                    if (temp_word.getTiles()[tileLocation]!=null){
                        mainBoard[temp_word.getRow()][i] = temp_word.getTiles()[tileLocation];
                    }
                    tileLocation++;
                }
            }
//-----------------------------------------------------------------//
            total_score+=getScore(temp_word);
            inUse[inUseCount] = temp_word;
            inUseCount++;

            ArrayList<Word> neighborWords = getWords(temp_word);
            for (int i=0;i<neighborWords.size();i++){
                boolean isInUse = false;
                for (int j=0; j<inUseCount; j++){
                    if (neighborWords.get(i).equals(inUse[j])){
                        isInUse = true;
                        break;
                    }
                }
                if (!isInUse){
                    total_score += getScore(neighborWords.get(i));
                    inUse[inUseCount] = neighborWords.get(i);
                    inUseCount++;
                }
            }
            if(inUseCount>0)
                bonusBoard[7][7]=0;
            return total_score;
        }
        else
            return 0;
        }

}
