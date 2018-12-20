package Stakhurskyi.Pr4;



import java.io.File;

import java.io.IOException;

import java.util.HashMap;

import java.util.Hashtable;

import java.util.Iterator;

import java.util.List;




public class Tester {



	public static void main(String[] args) throws IOException {

//		Brute brute1 = new Brute(new File("src\\PatternRecognition\\input8.txt"));

//		brute1.findLine();

//		System.out.println(brute1.toString());



		Fast fast1 = new Fast(new File("src\\PatternRecognition\\rs1423.txt"));

		fast1.findLine();

		System.out.println(fast1.toString());

		

	}



	



}