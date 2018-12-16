public class FormulaList {
    private int cycleMaxLenght=0;
    private long firstNumber;
    private long secondNumber;
    private String answer;

    public FormulaList(long firstNumber, long secondNumber){
        this.answer = firstNumber + " " + secondNumber + " ";
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;

        if (firstNumber > secondNumber){
            long temp = secondNumber;
            this.secondNumber = firstNumber;
            this.firstNumber = temp;
        }
    }

    public int findMaxLength(){
        for (long j=firstNumber;j<=secondNumber;j++) {

            long n = j;
            int cycleLength = 1;

            while (n !=1) {
                if ((n % 2) == 0) {
                    n /= 2;
                    cycleLength++;
                }
                else{
                    n = n*3 + 1;
                    cycleLength++;
                }
            }
            if (cycleMaxLenght<cycleLength)
                cycleMaxLenght = cycleLength;
        }
//
//        answer +=cycleMaxLenght;

//        return answer;
 return cycleMaxLenght;
    }

    public String toString(){
        return answer;
    }
}
