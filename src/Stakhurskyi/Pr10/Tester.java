package Stakhurskyi.Pr10;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Scanner;


public class Tester {



	private File file = new File("Dictionary01.txt");

	private Scanner sc;

	private SearchDictionary search;

	private ArrayList<String> res;



	public static void main(String[] args) throws IOException {

		Tester tester = new Tester();

		tester.readDictionary();

		tester.seachWord();

	}



	/**

	 * ���������� � ����� ��� �� ����� � �������

	 * 

	 * @throws IOException

	 */

	private void readDictionary() throws IOException {

		sc = new Scanner(file);

		String str;

		search = new SearchDictionary();

		while (sc.hasNext()) {

			str = sc.next();

			search.addWord(str);

		}

	}



	/**

	 * ����������� ������ �� ����������� �� ��������� �� ����� ��������� ������

	 */

	private void seachWord() {

		System.out.println("����� � �����: " + file.getName());

		sc = new Scanner(System.in);

		String wordToSeach;

		while (true) {

			System.out.print("������ �����: ");

			wordToSeach = sc.next();

			res = (ArrayList<String>) search.query(wordToSeach);

			if(res.isEmpty())

				System.out.println("������ ����� � �������� ����");

			else

				System.out.println(res.toString());

		}

	}



}