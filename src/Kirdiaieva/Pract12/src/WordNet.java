public class WordNet {
    public WordNet(String synsets, String hypernyms){

    }

// множина іменників, що повертається як ітератор (без дублікатів)

    public Iterable<String> nouns(){

        return null;
    }

// чи є слово серед WordNet іменників?

    public boolean isNoun(String word){

        return false;
    }

// відстань між nounA і nounB

    public int distance(String nounA, String nounB){

        return 0;
    }

// синсет що є спільним предком nounA і nounB

// в найкоршому шляху до предка

    public String sap(String nounA, String nounB){

        return nounA;
    }

// тестування

    public static void main(String[] args){

    }
}
