// Alexander Alvarez
// SneakyKnights - Program 3
// COP3503, Fall 2017


import java.io.*;
import java.util.*;
import java.awt.Point;



public class SneakyKnights
{

	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)

	{			
		int strlen = coordinateStrings.size(); // O(1) in Java language
		int alpha_len, position = 0, column,row;
		char letter;
		String coordinate = null;
		String letters = null;
		String numbers = null;
		HashMap <Point, Integer> knightCords = new HashMap<Point, Integer>();

		// The minimum board size that can be passed is 1, though invalid, just in case I account for this special case by returning true

		if (boardSize  == 1)
			return true;


		for (int i = 0; i < strlen; i++)
		{	
			//Obtain all of the coordinates
			coordinate = coordinateStrings.get(i); 
		
			alpha_len = coordinate.length();

			for ( int j = 0; j < alpha_len; j++)	
			{
				letter = coordinate.charAt(j);
				// Obtain the the position at which the numerical part of the given coordinate begins. Once you find it, break out
				// As per ASCII conversions, a 48 - 57 indicate a number between 0-9 which is the starting point of -
				// the row coordinate as per instructions.
				if(letter>=48 && letter<=57)
				{			
					position = j;
					break;	
				}
			}
		
		// Store the letters and numbers in seperate strings, as I need to convert the alphabetic portion seperately	
		// Obtain only the part up to the first digit, that will be the alphabetic portion of the coordinate
		letters = coordinate.substring(0,position);
		
		// By deductive reasoning, it must follow that the rest must be numbers,if only alphabetic characters were obtained above
		numbers = coordinate.substring(position);
		
		// Parse the number string as an integer data type
		row = Integer.parseInt(numbers);
		// The string of alphabetic characters must be converted into a integer equivalent to use as a coordinate
		column = numeric_convert(letters, letters.length());
		
		// Keep track of array indexes that already have a value by changing that index to 1.
		// A 1 indicates that in that particular row or column, a piece already exist.

		// Obtain the point of  the Knight
		Point Knight = new Point(column,row);

		Integer Pos = knightCords.get(Knight);

		// If Pos is not null, this indicates that a Knight has already been seen at this position. It's not safe
		if (Pos != null)
			return false;

		// Otherwise, this is a new entry denoted by 1, it's quantity.
		else
			knightCords.put(Knight, 1);

		analyzeKnights(row,column,knightCords);

		}

		// After checking all possible ways a Knight could attack I can by process of -
		// elimination, deduce they are all safe, if it isn't true that they are no.
		return true;

	}

	public static void analyzeKnights(int row, int col, HashMap<Point,Integer> knightCords)
	{

		// Unlike Queens, each Knight can only attack in a specific pattern with 8 possible moves at at time -
		// each a certain displacement from its original position. The number added or subtracted from "row" or "col" -
		// is the difference between its original placement to the possible positions a knight could land -
		// and attack if another one is present at that spot.
		for (int i = 0; i < 8; i++)
		{
			// We are adding a Point to the Map where this Knight can attack based on the calculations -
			// described above. If j is null it's a new Point, otherwise it's not the first time and you increment.
			if(i == 1)
			{
				Integer Row = row + 1;
				Integer Col = col - 2;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}

			else if (i ==2)
			{
				Integer Row = row + 2;
				Integer Col = col - 1;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}
			
			else if (i == 3)
			{
				Integer Row = row + 1;
				Integer Col = col + 2;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}

			else if (i==4)
			{
				Integer Row = row + 2;
				Integer Col = col + 1;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}

			else if (i==5)
			{
				Integer Row = row - 1;
				Integer Col = col + 2;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}

			else if (i==6)
			{
				Integer Row = row - 2;
				Integer Col = col + 1;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}

			else if (i==7)
			{
				Integer Row = row - 2;
				Integer Col = col - 1;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}

			else
			{
				Integer Row = row - 1;
				Integer Col = col - 2;

				Point Knight = new Point(Col,Row);
				Integer j = knightCords.get(Knight);

				if (j != null)
					knightCords.put(Knight,1+i);
				else
					knightCords.put(Knight,1);
			}

		
		}

	}

	// Auxillary function to convert the base 26 representation of the letters into base 10 coodinates.
	public static int numeric_convert (String alpha_partition, int length)

	{
		int decimal_representation = 0;
		int number = 0; 
		char individual_letter;

		for (int i = 0; i < length; i++)
		{	// Obtain each individual letter
			individual_letter = alpha_partition.charAt(length - i - 1);
			
			// Convert from letter to its equivalent integer representation
			if (individual_letter >= 'a' && individual_letter <='z')
				number = (int)individual_letter - 'a' + 1;
			
			// Convert from base 26 to base 10 which ultimately yields the numeric value of the alphabetic coordinate.
			decimal_representation += Math.pow(26, i) * (number);

		}

		return decimal_representation;
	}

	public static double difficultyRating ()
	{
		return 4.0;
	}
	
	public static double hoursSpent ()
	{
		return 43;
	}


}
