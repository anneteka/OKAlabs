package Practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Написати клас (за власним бажанням), що буде реалізовувати інтерфейс
 * Comparable та надавати різні реалізації Comparator (для різних варіантів
 * сортування).
 * 
 * Написати клас тестувальник, що зчитує тестові дані з файлу і демонструє різні
 * варіанти сортування.
 * 
 * Дослідити різні алгоритми сортування та вказати які з них стійкі (стабільні).
 * 
 * Стійкість (англ. stability) - стійке сортування не змінює взаємного
 * розташування елементів з однаковими ключами.
 * 
 * @author Богдана
 *
 */

public class MyComparator implements Comparable<MyComparator> {
	String name;
	int age;
	double grade;

	MyComparator(String name, int age, double grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;

	}

	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public double getGrade() {
		return grade;
	}
	public String toString() {
		return name + " (" + age + ") - " + grade;
	}

	// sortin by age
	@Override
	public int compareTo(MyComparator st1) {
		return this.age - st1.age;

	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("1.txt"));
		int n = sc.nextInt();
		MyComparator[] comparator = new MyComparator[n];
		for (int i = 0; i < n; i++) {
			String name = sc.next();
			// System.out.println(name);
			int age = sc.nextInt();
			// System.out.println(age);
			String gradesTR = sc.next();
			double grade = Double.parseDouble(gradesTR);

			comparator[i] = new MyComparator(name, age, grade);
		}
		//вивід стедентів у файлі
		for (MyComparator c : comparator)
			System.out.println(c);
		System.out.println();
		//використання МерджСорт
		MergeSort.sort(comparator);
		for (MyComparator c : comparator)
			System.out.println(c);
		//Використання СелекшнСорт
		MergeGradeSortMyComparator grade = new MergeGradeSortMyComparator();
		SelectionSort.sort(comparator, grade);
		System.out.println();
		for (MyComparator c : comparator)
			System.out.println(c);
		//Використання ФьорстСорт 
		System.out.println();
		MergeNameSortComparator name=new MergeNameSortComparator();
		FirstSort.sort(comparator, name);
		for (MyComparator c : comparator)
			System.out.println(c);
		
	}

}
/**
 * реалізація компаратора , що сортує за оцінкою
 * @author Богдана
 *
 */
class MergeGradeSortMyComparator implements Comparator<MyComparator> {

	@Override
	public int compare(MyComparator std1, MyComparator std2) {
		if (std1.getGrade() < std2.getGrade())
			return -1;
		if (std1.getGrade() > std2.getGrade())
			return 1;
		return 0;
	}
}

/**
 * реалізація компаратора , що сортує за ім
 * @author Богдана
 *
 */
class MergeNameSortComparator implements Comparator<MyComparator> {
	public int compare(MyComparator m1, MyComparator m2) {
		String name1 = m1.getName().toLowerCase();
        String name2 = m2.getName().toLowerCase();
		return name1.compareTo(name2);
	}
}
