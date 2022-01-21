import java.util.*;
import java.io.*;
/**
 * This class represents a storage facility and calculates the area
 * of the cross-section of the cavern in the storage facility.
 * 
 * @author Maxime Sotsky 3637964
 */
public class Storage {
    /** number of rows in storage facility graph */
    private int numRow;
    /** number of columns in storage facility graph */
    private int numCol;
    /** the storage facility graph */
    private int[][] cross;
    /**
     * Constructor for storage facility
     * @param file - file containing graph to be read by scanner to construct
     * storage facility.
     * @throw - exception if file is not found
     */
    public Storage(File file) throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        numRow = sc.nextInt();
        numCol = sc.nextInt();
        cross = new int[numRow][numCol];

        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                cross[i][j] = sc.nextInt();
            }
        }
        sc.close();
    }

    /** Method to display storage facility in tabular form */
    public void show(){
        for(int[] x : this.cross){
            for(int y : x){
                System.out.print(y + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
     * Method to find the valve/opening location of the storage facility cavern
     * and sets its value to 3, meaning it has been processed in finding the area
     * assuming it will always be found within the first row of the graph.
     * In addition, this method provides a starting point for finding the area
     * of the cavern.
     * @return true if a valve is found, false otherwise (pre-caution)
     */
    public boolean FindValve(){
        for(int i = 0; i < this.cross[0].length; i++){
            if(this.cross[0][i] == 0){
                this.cross[0][i] = 3;
                return true;
            }
        }
        return false;
    }
    /**
     * Method to find the area of the cavern in the storage facility graph.
     * @return the number of zero value neighbors in the graph as an 
     * integer, excluding diagnals.
     * the number of zeroes represents the area of the cavern.
     */
    public int findArea(){
        int count = 0;
        if(!FindValve())
            return count;
        count++;
        for(int i = 1; i < this.cross.length; i++){
            for(int j = 0; j < this.cross[i].length; j++){
                if(this.cross[i][j] == 0 && checkNeighbors(i,j)){
                    this.cross[i][j] = 3;
                    count++;
                    j = 0;
                    i = 0;
                }
            }
        }
        return count;
    }
    /**
     * Method helper for findArea()
     * This method checks each neighbor in terms of 
     * the position provided that is connected to the valve.
     * @param x - represents the row position of the zero element being tested
     * @param y - represents the column position of the zero element being tested
     * @return true if a valid neighbor is found, otherwise false.
     */
    private boolean checkNeighbors(int x, int y){
        if(checkLeft(x,y))
            return true;
        else if(checkRight(x,y))
            return true;
        else if(checkDown(x,y))
            return true;
        else if(checkUp(x,y))
            return true;
        else
            return false;
    }

    /**
     * Method helper for checkNeighbors().
     * This method checks whether the left neighbor of a given index
     * contains the value 3 meaning, that neighbor is connected to the valve.
     * @param x - represents the row position of the zero element being tested
     * @param y - represents the column position of the zero element being tested
     * @return true if the left neighbor is 3, otherwse false.
     */
    private boolean checkLeft(int x, int y){
        if(y-1 >= 0){
            if(this.cross[x][y-1] == 3){
                return true;
            }
        }
        return false;
    }
    /**
     * Method helper for checkNeighbors().
     * This method checks whether the right neighbor of a given index
     * contains the value 3 meaning, that neighbor is connected to the valve.
     * @param x - represents the row position of the zero element being tested
     * @param y - represents the column position of the zero element being tested
     * @return true if the right neighbor is 3, otherwse false.
     */
    private boolean checkRight(int x, int y){ 
        if(y+1 < numCol){
            if(this.cross[x][y+1] == 3){
                return true;
            }
        }
        return false;
    }
    /**
     * Method helper for checkNeighbors().
     * This method checks whether the upper neighbor of a given index
     * contains the value 3 meaning, that neighbor is connected to the valve.
     * @param x - represents the row position of the zero element being tested
     * @param y - represents the column position of the zero element being tested
     * @return true if the upper neighbor is 3, otherwse false.
    */
    private boolean checkUp(int x, int y){ 
        if(x-1 >= 0){
            if(this.cross[x-1][y] == 3){
                return true;
            }
        }
        return false;
    }
    /**
     * Method helper for checkNeighbors().
     * This method checks whether the lower neighbor of a given index
     * contains the value 3 meaning, that neighbor is connected to the valve.
     * @param x - represents the row position of the zero element being tested
     * @param y - represents the column position of the zero element being tested
     * @return true if the lower neighbor is 3, otherwse false.
     */
    private boolean checkDown(int x, int y){  
        if(x+1 < numRow){
            if(this.cross[x+1][y] == 3){
                return true;
            }
        }
        return false;
    }

}
