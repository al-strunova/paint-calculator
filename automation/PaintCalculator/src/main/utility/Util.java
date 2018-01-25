package main.utility;

import java.util.Random;

public class Util {
	
    /*************************************************************************
     * Function description: 
     * Create random number		
     *************************************************************************/	
	public static int randomGenerator(int minimum, int maximum) {
		Random rn = new Random();
		int range = maximum - minimum + 1;
		int randomNum =  rn.nextInt(range) + minimum;
		return randomNum;
	}
	
}
