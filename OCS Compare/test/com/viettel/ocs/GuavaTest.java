package com.viettel.ocs;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

public class GuavaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Set which stores some Singer
		Set<String> singers = new HashSet<String>();
		singers.add("Amitabh Bacchan");
		singers.add("Shan");
		singers.add("Medona");

		// Another Set which stores Actors
		Set<String> actors = new HashSet<String>();
		actors.add("Amitabh Bacchan");
		actors.add("tom cruise");
		actors.add("SRK");

		// Calculating Intersection of two Set in Java
		Set<String> intersection = Sets.intersection(actors, singers);
		System.out.printf("Intersection of two Set %s and %s in Java is %s %n", singers.toString(), actors.toString(),
				intersection.toString());
		System.err.println("Number of elements common in two Set : " + intersection.size());

		// Calculating Union of two Set in Java
		Set<String> union = Sets.union(actors, singers);
		System.out.printf("Union of two Set %s and %s in Java is %s %n", singers.toString(), actors.toString(),
				union.toString());
		System.out.println("total number of element in union of two Set is : " + union.size());
		
		Set<String> difference = Sets.difference(actors, singers);
		System.out.printf("Difference of two Set %s and %s in Java is %s %n", singers.toString(), actors.toString(),
				difference.toString());
		System.out.println("total number of element in difference of two Set is : " + difference.size());
		
	}

}
