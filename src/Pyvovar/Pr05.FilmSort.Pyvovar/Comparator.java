
/**
 * інтерфейс, який містить метод для сортування об'єктів за іншою влвстивістю
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 * @param <Key>
 */
public interface Comparator<Key> {

	/**
	 * порівняння двох об'єктів
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int compare(Key v, Key w);
}
