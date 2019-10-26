public class VerificationOfFibs {

    public static void main(String[] args) {

        //declare answer variable
        long answer0 = 0;
        long answer1 = 0;
        long answer2 = 0;
        long testNum0 = 15;
        long testNum1 = 16;
        long testNum2 = 17;

        answer0 = FibLoop(testNum0);
        answer1 = FibLoop(testNum1);
        answer2 = FibLoop(testNum2);
        System.out.println("Fibonacci Loop: Input #s = " + testNum0 + " " + testNum1 + " " + testNum2 + " Answers = " + answer0 + " " + answer1 + " " + answer2);
        answer0 = FibRecur(testNum0);
        answer1 = FibRecur(testNum1);
        answer2 = FibRecur(testNum2);
        System.out.println("Fibonacci Recursive: Input #s = " + testNum0 + " " + testNum1 + " " + testNum2 + " Answers = " + answer0 + " " + answer1 + " " + answer2);
        answer0 = FibRecurDP(testNum0);
        answer1 = FibRecurDP(testNum1);
        answer2 = FibRecurDP(testNum2);
        System.out.println("Fibonacci Recursive DP: Input #s = " + testNum0 + " " + testNum1 + " " + testNum2 + " Answers = " + answer0 + " " + answer1 + " " + answer2);
        answer0 = FibMatrix(testNum0);
        answer1 = FibMatrix(testNum1);
        answer2 = FibMatrix(testNum2);
        System.out.println("Fibonacci Matrix: Input #s = " + testNum0 + " " + testNum1 + " " + testNum2 + " Answers = " + answer0 + " " + answer1 + " " + answer2);
    }



    //brute force version on fibonacci
    public static long FibLoop(long x){

        //if x is less than 2, return 1 as answer
        if(x < 2)
            return 1;
        else{
            //initialize variables
            long secondToLast = 1;
            long last = 1;
            long current = 0;
            //loop summing last two numbers until at x, and swapping a with b and b with c
            for(int i = 2; i <= x; i++){
                current = secondToLast + last;
                secondToLast = last;
                last = current;
            }
            //return answer
            return current;

        }
    }

    //recursive fibonacci function
    public static long FibRecur(long x){

        //if x is less than two return 1
        if(x < 2)
            return 1;
        else{
            //calls itself recursively and adds to total
            return FibRecur(x - 1) + FibRecur(x - 2);
        }
    }

    //wrapper function
    public static long FibRecurDP(long x){

        //declare result variable
        long result;
        //if x is less than 2, return 1
        if(x < 2)
            return 1;
        else{
            //initialize table
            long[] table = new long[(int) (x+1)];
            table[0] = 1;
            table[1] = 1;
            //call helper function with x and table
            result = FibRecurDPHelper(x, table);
        }
        return result;

    }

    //helper function to do actual recursion
    public static long FibRecurDPHelper(long x, long[] table){

        //declare result variable
        long result;
        //check to see if table has a value already (should have no zero's)
        if(table[(int) x] != 0){
            result = table[(int) x];
        }
        else{
            //add to table
            result = FibRecurDPHelper(x- 1, table) + FibRecurDPHelper(x - 2, table);
            table[(int)x] = result;
        }
        return result;
    }

    //wrapper function
    public static long FibMatrix(long x){

        //declare result variable
        long result;

        //initialize  matrices
        long originalMatrix[][] = {{1, 1},
                                   {1, 0}};
        long lastMatrix[][] = {{1, 0},
                               {0, 1}};

        //call helper function
        MatrixPower(originalMatrix, lastMatrix, x);

        //find and return result
        result = lastMatrix[0][0];
        return result;
    }

    //use matrix multiplication to find result function
    static void MatrixPower(long matrix[][], long resultMatrix[][], long x){

        //declare temp variable
        long temp;

        //create temp matrix
        long tempMatrix[][] = {{matrix[0][0], matrix[0][1]},
                               {matrix[1][0], matrix[1][1]}};

        //declare variables to hold values
        long topLeft, topRight, bottomLeft, bottomRight;

        for(temp = x; temp > 0; temp = temp/2){

            //check if leftmost bit is 1
            if(temp % 2 == 1){
                //multiply matrices
                topLeft = resultMatrix[0][0] * tempMatrix[0][0] + resultMatrix[0][1] * tempMatrix[1][0];
                topRight = resultMatrix[0][0] * tempMatrix[0][1] + resultMatrix[0][1] * tempMatrix[1][1];
                bottomLeft = resultMatrix[1][0] * tempMatrix[0][0] + resultMatrix[1][1] * tempMatrix[1][0];
                bottomRight = resultMatrix[1][0] * tempMatrix[0][1] + resultMatrix[1][1] * tempMatrix[1][1];
                //set values
                resultMatrix[0][0] = topLeft;
                resultMatrix[0][1] = topRight;
                resultMatrix[1][0] = bottomLeft;
                resultMatrix[1][1] = bottomRight;
            }

            //square temp matrix
            topLeft = tempMatrix[0][0] * tempMatrix[0][0] + tempMatrix[0][1] * tempMatrix[1][0];
            topRight = tempMatrix[0][0] * tempMatrix[0][1] + tempMatrix[0][1] * tempMatrix[1][1];
            bottomLeft = tempMatrix[1][0] * tempMatrix[0][0] + tempMatrix[1][1] * tempMatrix[1][0];
            bottomRight = tempMatrix[1][0] * tempMatrix[0][1] + tempMatrix[1][1] * tempMatrix[1][1];
            //set values
            tempMatrix[0][0] = topLeft;
            tempMatrix[0][1] = topRight;
            tempMatrix[1][0] = bottomLeft;
            tempMatrix[1][1] = bottomRight;
        }

    }

}

