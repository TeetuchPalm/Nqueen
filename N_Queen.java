/*
 6313131 Teetuch Siribunpitug
 6313176 Nattapong Jitrangsi
 6313172 Chavakan Yimmark
 */



import java.util.*;

public class N_Queen {
    public static class Pair{
        public int i, j;
        public Pair(int i, int j){
            this.i = i; this.j = j;
        }
        public int getI() {return i;}
        public int getJ() {return j;}
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 0;
        boolean mainProgram = true;
        while(mainProgram) {
            boolean con = true;
            while (con) {
                try {
                    System.out.print("Enter N (at least 4 | -1 for EXIT): ");
                    System.out.println();
                    String in = scan.nextLine();
                    n = Integer.parseInt(in);
                    if((n < 4) && (n!=-1)){System.out.println("N must be at least 4!");}
                    else{con = false;}
                } catch (Exception e) {
                    System.out.println("The input is not an integer!");
                }
            }
            if(n == -1){mainProgram = false;}
            else nQueenSolve(n);
        }
        System.out.println("Bye!");
    }

    public static void nQueenSolve(int n){
        int mode = 0;
        ArrayDeque <Pair> s = new ArrayDeque<Pair>();
        Scanner scan = new Scanner(System.in);
        char[][] board = new char[n][n];
        ////////////////////////
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                board[i][j]='-';
        ////////////////////////
        printBoard(board, n);
        //Check Manually put
        boolean loop = true;
        while(loop) {
            System.out.println("Manually place 1st Queen (Y/N)");
            String in = scan.nextLine();
            if(in.equalsIgnoreCase("y")){mode = 0; loop = false;}
            else if (in.equalsIgnoreCase("n")){mode = 1; loop = false;}
            else{System.out.println("Error input!");}
        }
        //If |Y| Select row & column
        int row = -1, col = -1;
        int i  = 0, j = 0;
        if(mode == 0){
            boolean con = true;
            while (con) {
                try {
                    System.out.print("Enter ROW of 1st Queen (1 - n): ");
                    System.out.println();
                    String in = scan.nextLine();
                    row = Integer.parseInt(in) -1 ;
                    if(row<0 || row > n-1){System.out.println("Out of Bound!");}
                    else{con = false;}
                } catch (Exception e) {
                    System.out.println("The input is not an integer!");
                }
            }
            con = true;
            while (con) {
                try {
                    System.out.print("Enter COLUMN of 1st Queen (1 - n): ");
                    System.out.println("");
                    String in = scan.nextLine();
                    col = Integer.parseInt(in) - 1;
                    if(col<0 || col > n-1){System.out.println("Out of Bound!");}
                    else{con = false;}
                } catch (Exception e) {
                    System.out.println("The input is not an integer!");
                }
            }
            //add 1st queen && add qPlaced
            board[row][col] = 'Q';
            printBoard(board, n);
            s.push(new Pair(row, col));
            i = row+1;
            if(i == n) i = 0;
        }
        if(mode == 1){
            board[i][j] = 'Q';
            s.push(new Pair(i, j));
            i++;
        }
        //Main Backtracking step using deque as stack
        boolean noSolFlag = false;

        while(true){
            boolean validRow = false;
            while(j < n){
                if(noSolFlag){break;}
                if(collide(i, j, s) == 0) {
                    board[i][j] = 'Q';
                    s.push(new Pair(i, j));
                    validRow = true;
                    i++;
                    j = 0;
                    if(i == n){i = 0;}
//Print board when ADD
//                    System.out.println("ADD");
//                    printStack(s);
//                    printBoard(board, n);
////////////////////
                    break;
                }
                else j++;

            }

            if(!validRow){
                if((s.isEmpty()) || (mode == 0 && s.size() == 1)){
                    System.out.println("No Solution !!");
                    break;
                }
                else{
                    Pair temp = s.pop();
                    i = temp.getI();
                    j = temp.getJ();
                    board[i][j] = '-';
                    j++;
                    if(j == n){noSolFlag = true;}
                    else {noSolFlag = false;}

//Print board when RMV
//                    System.out.println("RMV");
//                    printStack(s);
//                    printBoard(board, n);
////////////////////////////////
                }
            }

            if(s.size() == n){
                System.out.println("Solution");
                printBoard(board, n);
                break;
            }
        }
        System.out.println("");
    }

    public static void printBoard(char board[][], int n){
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++) {
                if(i+j == 0){ System.out.print("    ");}
                else if(i == 0){ System.out.format(" %2d ",j);}
                else if(j == 0){ System.out.format(" %2d ",i);}
                else{System.out.print("  " + board[i-1][j-1] + " ");}
            }
            System.out.println();
        }
    }



    public static int collide(int i, int j, /*int n, char[][] board*/ ArrayDeque<Pair> s){
        ArrayDeque<Pair> copy = s.clone();
        while(!copy.isEmpty()){
            Pair t = copy.poll();
            if((i == t.getI()) || (j == t.getJ())){return 1;}
            else if(i+j == t.getI()+t.getJ()){return 1;}
            else if(i-j == t.getI()-t.getJ()){return 1;}
        }
        return 0;
    }

    //Method for showing stack
//    public static void printStack(ArrayDeque<Pair> s){
//        ArrayDeque<Pair> copy = s.clone();
//        System.out.print("Stack: ");
//        while(!copy.isEmpty()){
//            Pair t = copy.pollLast();
//            System.out.format("(%2d , %2d)  ", t.getI()+1, t.getJ()+1);
//        }
//        System.out.println("");
//    }

}
