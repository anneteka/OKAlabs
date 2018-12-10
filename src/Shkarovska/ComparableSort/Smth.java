public class Smth implements Comparable<Smth> {

    private String str;
    private int i;

    public Smth(String str, int i){
        this.str = str;
        this.i = i;
    }
    public String getStr(){return str;}
    public int getI(){return i;}

    @Override
    public int compareTo(Smth smth) {
        return this.str.compareTo(smth.str);
    }
}
