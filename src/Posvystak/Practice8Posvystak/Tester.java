import java.util.Scanner;

public class Tester {

    static String i;
    static String s1;
    static String s2;
    static Scanner scanner = new Scanner(System.in);
    static ST<String,String> st = new ST();
    static BST<String,String> bst = new BST();

    public static void main(String[] args) {
        while (true) {
            System.out.println("ST - 1");
            System.out.println("BST - 2");
            i = scanner.nextLine();
            switch (i) {
                case "1":
                    st();
                    break;
                case "2":
                    bst();
                    break;
            }
        }
    }

    static void st() {
        while (true) {
            System.out.println("Put - 1");
            System.out.println("Get - 2");
            System.out.println("Delete - 3");
            System.out.println("Min - 4");
            System.out.println("Max - 5");
            System.out.println("Delete Min - 6");
            System.out.println("Delete Max - 7");
            System.out.println("Ceiling - 8");
            System.out.println("Floor - 9");
            System.out.println("Rank - 10");
            System.out.println("Print All - 11");
            System.out.println("Press 0 to exit");
            i = scanner.nextLine();
            if (i.equals("0")) break;
            try {
                switch (i) {
                    case "1":
                        System.out.println("Key: ");
                        s1 = scanner.nextLine();
                        System.out.println("Value: ");
                        s2 = scanner.nextLine();
                        st.put(s1,s2);
                        break;
                    case "2":
                        System.out.println("Key: ");
                        s1 = scanner.nextLine();
                        System.out.println(st.get(s1));
                        break;
                    case "3":
                        System.out.println("Key: ");
                        s1 = scanner.nextLine();
                        st.delete(s1);
                        for (String key: st) if (st.get(key)!=null) System.out.println("("+key+", "+st.get(key)+") ");
                        break;
                    case "4":
                        System.out.println(st.min());
                        break;
                    case "5":
                        System.out.println(st.max());
                        break;
                    case "6":
                        st.deleteMin();
                        for (String key: st) if (st.get(key)!=null) System.out.println("("+key+", "+st.get(key)+") ");
                        break;
                    case "7":
                        st.deleteMax();
                        for (String key: st) if (st.get(key)!=null) System.out.println("("+key+", "+st.get(key)+") ");
                        break;
                    case "8":
                        System.out.println("Key: ");
                        s1 = scanner.nextLine();
                        System.out.println(st.ceiling(s1));
                        break;
                    case "9":
                        System.out.println("Key: ");
                        s1 = scanner.nextLine();
                        System.out.println(st.floor(s1));
                        break;
                    case "10":
                        System.out.println("Key: ");
                        s1 = scanner.nextLine();
                        System.out.println(st.rank(s1));
                        break;
                    case "11":
                        for (String key: st) if (st.get(key)!=null) System.out.println("("+key+", "+st.get(key)+") ");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static void bst() {
        while (true) {
            System.out.println("Put - 1");
            System.out.println("Get - 2");
            System.out.println("Delete - 3");
            System.out.println("Min - 4");
            System.out.println("Max - 5");
            System.out.println("Ceiling - 6");
            System.out.println("Floor - 7");
            System.out.println("Rank - 8");
            System.out.println("Print All - 9");
            System.out.println("Press 0 to exit");
            i = scanner.nextLine();
            if (i.equals("0")) break;
            switch (i) {
                case "1":
                    System.out.println("Key: ");
                    s1 = scanner.nextLine();
                    System.out.println("Value: ");
                    s2 = scanner.nextLine();
                    bst.put(s1,s2);
                    break;
                case "2":
                    System.out.println("Key: ");
                    s1 = scanner.nextLine();
                    System.out.println(bst.get(s1));
                    break;
                case "3":
                    System.out.println("Key: ");
                    s1 = scanner.nextLine();
                    bst.delete(s1);
                    for (String key: bst) if (bst.get(key)!=null) System.out.println("("+key+", "+bst.get(key)+") ");
                    break;
                case "4":
                    System.out.println(bst.min());
                    break;
                case "5":
                    System.out.println(bst.max());
                    break;
                case "6":
                    System.out.println("Key: ");
                    s1 = scanner.nextLine();
                    System.out.println(bst.ceiling(s1));
                    break;
                case "7":
                    System.out.println("Key: ");
                    s1 = scanner.nextLine();
                    System.out.println(bst.floor(s1));
                    break;
                case "8":
                    System.out.println("Key: ");
                    s1 = scanner.nextLine();
                    System.out.println(bst.rank(s1));
                    break;
                case "9":
                    for (String key: bst) if (bst.get(key)!=null) System.out.println("("+key+", "+bst.get(key)+") ");
                    break;
            }
        }
    }

}
