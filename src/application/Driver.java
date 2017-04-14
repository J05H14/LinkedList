package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import linkedList.LinkedList;

public class Driver {

	public static void main(String[] args){
		File file = null;
		JFileChooser fc = new JFileChooser();
		int retVal = fc.showOpenDialog(null);

		if(retVal == JFileChooser.APPROVE_OPTION){
			file  = fc.getSelectedFile();
		}
		LinkedList<String, String, String> l1 = new LinkedList<String, String, String>(file, 2);
		
		System.out.println(l1);
		System.out.println("ADDING");
		l1.add("Dalmation", "5", "L");
//		l1.add("Dalmaton", 7, "S");
		System.out.println(l1);
		
		Integer num1 = 5;
		String num2 = "5";
		
		System.out.println(num1.equals(num2));
		
//		l1.regroup(3);
//		System.out.println(l1);
//		l1.regroup(3);
//		System.out.println(l1);
	}
}
