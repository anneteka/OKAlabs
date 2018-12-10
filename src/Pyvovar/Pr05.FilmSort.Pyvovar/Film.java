
/**
 * Клас, в якому описаний стандартний варіант сортування об'єкта, а також
 * сортування за іншими ознаками
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Film implements Comparable<Film> {

	private final String name; // назва
	private final int year; // рік випуску
	private final int length; // тривалість (в хв)
	public static final Comparator<Film> BY_NAME = new ByName(); // сортування за назвою
	public static final Comparator<Film> BY_LENGTH = new ByLength(); // сортування за тривалістю фільму (в хв)

	/**
	 * конструктор
	 * 
	 * @param name   назва
	 * @param year   рік випуски
	 * @param length тривалість
	 */
	public Film(String name, int year, int length) {
		this.name = name;
		this.year = year;
		this.length = length;
	}

	@Override
	public int compareTo(Film that) {
		if (this.year < that.year)
			return -1;
		if (this.year > that.year)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "Film: name = " + name + ", year = " + year + ", length = " + length;
	}

	/**
	 * клас, який визначає сортування фільмів за довжиною
	 *
	 */
	private static class ByLength implements Comparator<Film> {

		@Override
		public int compare(Film v, Film w) {
			if (v.length < w.length)
				return -1;
			if (v.length > w.length)
				return 1;
			return 0;
		}
	}

	/**
	 * клас, який визначає сортування фільмів за назвою
	 *
	 */
	private static class ByName implements Comparator<Film> {
		
		@Override
		public int compare(Film v, Film w) {
			return v.name.compareTo(w.name);
		}
	}

}
