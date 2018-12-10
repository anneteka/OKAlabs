import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Read {

	public static Long getLong() throws IOException {
		String s = getString();
		Long value = Long.valueOf(s);
		return value;
	}

	public static Long getLong(String str) throws IOException {
		System.out.print(str);
		String s = getString();
		Long value = Long.valueOf(s);
		return value;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static char getChar(String str) throws IOException {
		System.out.print(str);
		String s = getString();
		return s.charAt(0);
	}

	public static Integer getInt() throws IOException {
		boolean ch = true;
		String s = "";
		while (ch) {
			try {
				s = "";
				s = getString();
				ch = false;
			}

			catch (NumberFormatException p) {
				continue;
			} catch (Exception e) {
				continue;
			}
			for (int i = 0; i < s.length(); i++)
				if (s.charAt(i) < '0' || s.charAt(i) > '9')
					ch = true;
		}
		Integer value = Integer.valueOf(0);
		if (s.equals("NoName") != true) {
			value = Integer.valueOf(s);
		}
		return value;
	}

	public static Integer getInt(String str) {
		boolean ch = true;
		String s = "";
		while (ch) {
			try {
				System.out.print(str);
				s = "";
				s = getString();
				ch = false;
			}

			catch (NumberFormatException p) {
				continue;
			} catch (Exception e) {
				continue;
			}
			for (int i = 0; i < s.length(); i++)
				if (s.charAt(i) < '0' || s.charAt(i) > '9')
					ch = true;
		}
		Integer value = Integer.valueOf(0);
		if (s.equals("NoName") != true) {
			value = Integer.valueOf(s);
		}
		return value;
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static String getString(String str) throws IOException {
		System.out.print(str);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

}