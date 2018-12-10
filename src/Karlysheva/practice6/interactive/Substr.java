package practice6.interactive;

import java.util.Arrays;

public class Substr {
    public static void main(String[] args) {
        System.out.println(coutSubstr("ababab", "abab"));
        System.out.println(Arrays.toString(zFunction("ababab")));
    }

    static int coutSubstr(String str, String sbstr) {
        int counter=0;
        int[] zf = zFunction(sbstr + '#' + str);
        for (int i = sbstr.length() + 1; i< str.length() + sbstr.length(); i++){
        if (zf[i] ==sbstr.length())
            counter++;
        }
        return counter;
    }

    static int[] zFunction(String s) {
        int n = s.length();
        int[] zf = new int[n];
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            zf[i] = Math.max(0, Math.min(right-i, zf[i-left]));
            while (i + zf[i] < n && s.charAt(zf[i]) == s.charAt(i + zf[i]))
                zf[i]++;
            if (i + zf[i] > right)
                left = i;
            right = i + zf[i];
        }
        System.out.println(Arrays.toString(zf)+"\n"+s);
        return zf;
    }
}
