package test;

public class SaveForLeter {};

    public static void printBoard(Tile[][] mainBoard) {
        System.out.println("Board:");
        for(int i = 0; i < mainBoard.length; i++) {
            for(int j = 0; j < mainBoard[i].length; j++) {
                if(mainBoard[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(mainBoard[i][j].letter + " ");
                }
            }
            System.out.println();
        }
    }

public Tile[] wordCompletion(Word word){
        int tileLocation = 0;
        Tile[] temp_word = word.getTiles();
        //Vertical
        if (word.isVertical()){
        for (int i=0;i<word.getTiles().length;i++){
        if (word.getTiles()[i]==null)
        temp_word[i] = mainBoard[word.getRow()+i][word.getCol()];
        }
        }
        //Horizontal
        if (!word.isVertical()){
        for (int i=0;i<word.getTiles().length;i++){
        if (word.getTiles()[i]==null)
        temp_word[i] = mainBoard[word.getRow()][word.getCol()+i];
        }
        }
        return temp_word;
        }

Word Compilation - completed null spots
            Tile[] word = wordCompletion(temp_word);
            temp_word.setTiles(word);
            for (int i=0;i<neighborWords.size();i++){
                    if (!neighborWords.get(i).equals(temp_word))
                        total_score+=getScore(neighborWords.get(i),false);
            }

            for (int i=0;i<neighborWords.size();i++){
                if (neighborWords.get(i) != temp_word || neighborWords.get(i) != inUse[i])
                    total_score+=getScore(neighborWords.get(i),false);
            }
printBoard(mainBoard);