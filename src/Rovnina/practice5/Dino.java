import java.util.Comparator;

/**
 * клас, який створює об'єкт динозавр. реалізує інтерфейс Comparable
 * 
 * @author Rovnina Tetiana
 */
public class Dino implements Comparable<Dino> {

	public static final Comparator<Dino> BY_NAME = new ByName();
	public static final Comparator<Dino> BY_Height = new ByHeight();
	public static final Comparator<Dino> BY_Country = new ByCountry();
	public static final Comparator<Dino> BY_Weight = new ByWeight();

	private final String name;
	private final int year;
	private final String country;
	private final int height;
	private final int weight;

	/**
	 * конструктор з параметрами, який створює об'єкт динозавр
	 * 
	 * @param name    назва
	 * @param year    рік відкриття
	 * @param country країна відкриття
	 * @param height  довжина
	 * @param weight  вага
	 */
	public Dino(String name, int year, String country, int height, int weight) {
		this.name = name;
		this.year = year;
		this.country = country;
		this.height = height;
		this.weight = weight;
	}

	/**
	 * метод, який порівнює динозаврів за роком їх відкриття
	 *
	 */
	@Override
	public int compareTo(Dino that) {
		if (this.year < that.year)
			return -1;
		if (this.year > that.year)
			return 1;

		return 0;
	}

	/**
	 * класс компаратор, який порівнює динозаврів за назвою
	 *
	 */
	private static class ByName implements Comparator<Dino> {
		public int compare(Dino d1, Dino d2) {
			return d1.name.compareTo(d2.name);
		}
	}

	/**
	 * класс компаратор, який порівнює динозаврів за країною, де їх було знайдено
	 *
	 */
	private static class ByCountry implements Comparator<Dino> {
		public int compare(Dino d1, Dino d2) {
			return d1.country.compareTo(d2.country);
		}
	}

	/**
	 * класс компаратор, який порівнює динозаврів за довжиною
	 *
	 */
	private static class ByHeight implements Comparator<Dino> {
		public int compare(Dino d1, Dino d2) {
			if (d1.height < d2.height)
				return -1;
			if (d1.height > d2.height)
				return 1;
			return 0;
		}
	}

	/**
	 * класс компаратор, який порівнює динозаврів за вагою
	 *
	 */
	private static class ByWeight implements Comparator<Dino> {
		public int compare(Dino d1, Dino d2) {
			if (d1.weight < d2.weight)
				return -1;
			if (d1.weight > d2.weight)
				return 1;
			return 0;
		}
	}

	@Override
	public String toString() {
		return name + ", " + year + " р., " + country + ", " + height + " м, " + weight + " кг";
	}

}
