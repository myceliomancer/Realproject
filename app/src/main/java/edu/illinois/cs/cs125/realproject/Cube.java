package edu.illinois.cs.cs125.realproject;

//the best code u ever seen
public class Cube {
    public static void info() {
        System.out.println("A class that implements a functional rubik's cube into Java");
        System.out.println(" - .info(): Prints this list");
        System.out.println(" - new Cube(): Creates a cube in solved state");
        System.out.println(" - new Cube(int[][] layout): Creates a cube with a custom layout");
        System.out.println(" - .vtest: Vertical layout for testing");
        System.out.println(" - .htest: Horizontal layout for testing");
        System.out.println(" - .turn(String move): Turns a face on a cube. Can parse double and reverse moves. ");
        System.out.println(" - .turn(String[] algorithm): Executes an algorithm from String array on a cube");
        System.out.println(" - .scramble(int n): Generates , executes, and returns a random scramble with 'n' terms.");
        System.out.println(" - .toString(String[] scram): Static method for turning String arrays into a single String");
    }

    public int[][] layout = {
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5},
            {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5},
            {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5},
            {0, 0, 0, 6, 6, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 6, 6, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 6, 6, 6, 0, 0, 0, 0, 0, 0}
    };

    public static int[][] vtest = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
    };

    public static int[][] htest = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
    };

    public Cube(int[][] array) {
        this.layout = array;
    }
    public Cube() {}


    //Prints a cube
    public void pront() {
        for (int i = 0; i < this.layout.length; i++) {
            for (int j = 0; j < this.layout[0].length; j++) {
                switch (layout[i][j]) {
                    case 0: System.out.print("."); break; //.
                    case 1: System.out.print("|"); break; //|
                    case 2: System.out.print("@"); break; //@
                    case 3: System.out.print("<"); break; //<
                    case 4: System.out.print("#"); break; //#
                    case 5: System.out.print("+"); break; //+
                    case 6: System.out.print("%"); break; //%
                    default: System.out.print(layout[i][j]); break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    //To make coding easier, we do all the magic turny stuff here.
    private void turnFace(char input) {
        int face = (int)input;
        int height = -1;
        int width = -1;
        switch (face) {
            case 70: //F (front)
                height = 4; width = 4; break;
            case 66: //B (back)
                height = 4; width = 10; break;
            case 85: //U (up)
                height = 1; width = 4; break;
            case 68: //D (down)
                height = 7; width = 4; break;
            case 76: //L (left)
                height = 4; width = 1; break;
            case 82: //R (right)
                height = 4; width = 7; break;
        }
        //ugly manual switch - corners
        int temp = this.layout[height-1][width-1];
        this.layout[height-1][width-1] = this.layout[height+1][width-1];
        this.layout[height+1][width-1] = this.layout[height+1][width+1];
        this.layout[height+1][width+1] = this.layout[height-1][width+1];
        this.layout[height-1][width+1] = temp;
        //ugly manual switch - edges
        temp = this.layout[height-1][width];
        this.layout[height-1][width] = this.layout[height][width-1];
        this.layout[height][width-1] = this.layout[height+1][width];
        this.layout[height+1][width] = this.layout[height][width+1];
        this.layout[height][width+1] = temp;
    }
    //Turn: U
    public void turnU() {
        int t1 = this.layout[3][0];
        int t2 = this.layout[3][1];
        int t3 = this.layout[3][2];
        for (int j = 3; j != 12; j++) {
            this.layout[3][j-3] = this.layout[3][j];
        }
        this.layout[3][9] = t1;
        this.layout[3][10] = t2;
        this.layout[3][11] = t3;
        turnFace('U');
    }
    //Turn: D
    public void turnD() {
        int t1 = this.layout[5][9];
        int t2 = this.layout[5][10];
        int t3 = this.layout[5][11];
        for (int j = 11; j != 2; j--) {
            this.layout[5][j] = this.layout[5][j-3];
        }
        this.layout[5][0] = t1;
        this.layout[5][1] = t2;
        this.layout[5][2] = t3;
        turnFace('D');
    }
    //Turn: F
    public void turnF() {
        //ugly corner switch 1
        int temp = layout[2][3];
        layout[2][3] = layout[5][2];
        layout[5][2] = layout[6][5];
        layout[6][5] = layout[3][6];
        layout[3][6] = temp;
        //Equally ugly corner switch 2
        temp = layout[2][5];
        layout[2][5] = layout[3][2];
        layout[3][2] = layout[6][3];
        layout[6][3] = layout[5][6];
        layout[5][6] = temp;
        //manual edge piece switch
        temp = layout[2][4];
        layout[2][4] = layout[4][2];
        layout[4][2] = layout[6][4];
        layout[6][4] = layout[4][6];
        layout[4][6] = temp;

        turnFace('F');
    }
    //Turn: L
    public void turnL() {
        int t1 = layout[6][3];
        int t2 = layout[7][3];
        int t3 = layout[8][3];
        for (int i = 8; i != 2; i--) { layout[i][3] = layout[i-3][3]; }
        layout[0][3] = layout[5][11];
        layout[1][3] = layout[4][11];
        layout[2][3] = layout[3][11];
        layout[5][11] = t1;
        layout[4][11] = t2;
        layout[3][11] = t3;

        turnFace('L');
    }
    //Turn: R
    public void turnR() {
        int t1 = layout[0][5];
        int t2 = layout[1][5];
        int t3 = layout[2][5];
        for (int i = 0; i != 6; i++) { layout[i][5] = layout[i+3][5]; }
        layout[6][5] = layout[5][9];
        layout[7][5] = layout[4][9];
        layout[8][5] = layout[3][9];
        layout[5][9] = t1;
        layout[4][9] = t2;
        layout[3][9] = t3;

        turnFace('R');
    }
    //Turn: B
    public void turnB() {
        int temp;

        //ugly corner switch 1
        temp = layout[0][3];
        layout[0][3] = layout[3][8];
        layout[3][8] = layout[8][5];
        layout[8][5] = layout[5][0];
        layout[5][0] = temp;
        //Equally ugly corner switch 2
        temp = layout[0][5];
        layout[0][5] = layout[5][8];
        layout[5][8] = layout[8][3];
        layout[8][3] = layout[3][0];
        layout[3][0] = temp;
        //manual edge piece switch
        temp = layout[0][4];
        layout[0][4] = layout[4][8];
        layout[4][8] = layout[8][4];
        layout[8][4] = layout[4][0];
        layout[4][0] = temp;

        turnFace('B');
    }


    public void turn(String input) {
        int multiplier = 1;
        int face = (int)input.charAt(0);
        if (input.contains("2")) { multiplier = 2; }
        if (input.contains("'")) { multiplier = 3; }
        for (int i = 0; i < multiplier; i++) { //Turns the face the correct number of times
            switch (face) {
                case 70: //F (front)
                    this.turnF(); break;
                case 66: //B (back)
                    this.turnB(); break;
                case 85: //U (up)
                    this.turnU(); break;
                case 68: //D (down)
                    this.turnD(); break;
                case 76: //L (left)
                    this.turnL(); break;
                case 82: //R (right)
                    this.turnR(); break;
            }
        }
    }

    public void turn(String[] array) {
        for (int i = 0; i < array.length; i++) {
            this.turn(array[i]);
        }
    }

    //Generates a string array with the scramble, and also performes the scramble on the cube.
    public String[] scramble(int n) {
        if (n < 1) { System.out.println("no"); return new String[1]; }
        String[] array = new String[n];

        int prev = (int)(6*Math.random());
        for (int i = 0; i < n; i++) {
            int face = (int)(6*Math.random());
            int degree = (int)(3*Math.random());
            //System.out.print(prev + ", " + face);
            if (face == prev) { i--; continue; } //Makes sure consecutive moves are not on same face

            String current = "";
            switch (face) {
                case 0: //Up
                    current += "U"; break;
                case 1: //Down
                    current += "D"; break;
                case 2: //Left
                    current += "L"; break;
                case 3: //Right
                    current += "R"; break;
                case 4: //Front
                    current += "F"; break;
                case 5: //Back
                    current += "B"; break;
            }
            prev = face;
            switch (degree) {
                case 0: //Normal. actually does nothing, but I'm still putting it here
                    current += ""; break; //n oone can stop me >:)
                case 1: //Double
                    current += "2"; break;
                case 2: //Reverse
                    current += "'"; break;
            }
            array[i] = current;
        } //We now have the scramble as a String array.

        //Scrambles the cube with the scramble
        turn(array);
        //Returns the acramble for printing
        return array;
        //return null;
    }

    public static String toString(String[] scram) {
        String out = "";
        if (scram.length == 1) { return scram[0]; }
        for (int i = 0; i < scram.length-1; i++) { out += (scram[i] + ", "); }
        out += scram[scram.length-1];
        return out;
    }

}
