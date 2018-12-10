import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 
 * @author Maria Skyrta. E-olymp Evolution. Find the closest common relative for
 *         two bacteria. Algorithm: divide numbers by two till they are equal,
 *         closest common relative found.
 *
 */
public class Evolution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s = br.readLine();
			int n = Integer.parseInt(s);
			if (n > 1) {
				s = br.readLine();
				BigInteger l = new BigInteger(s);
				s = br.readLine();
				BigInteger m = new BigInteger(s);
				while (!l.equals(m)) {
					if (l.compareTo(m) > 0)
						l = l.divide(new BigInteger("2"));
					else
						m = m.divide(new BigInteger("2"));

				}
				System.out.println(m);
			}
		}
	}

}
