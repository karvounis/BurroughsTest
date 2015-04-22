package burroughsImplementation;

import java.util.Calendar;

/**
 * <h1>Main class. Coordinates the application steps.</h1>
 * 
 * <p>My assumptions are:
 * 		1.The user chooses his preferred starting date. 
 * 		2.The program creates a file that contains the next twelve months' names, the salary payment and the bonus payment dates.
 * 		3.The next twelve months are the months after the desired date. That means, that if the user chooses 1/2/2017 as starting date, then the first month 
 * 		  in the output file is March/2017 (the 3rd month). 
 *</p>
 * <p>Choices:
 * 		1.Decided to implement the application using instances of the Calendar class.
 * 		2.Main class instantiates a BurroughsInteraction object that is used to get information from the user and
 * 		  that object instantiates a Burroughs object that is used to calculate the salary and bonus payment dates.
 * </p>
 * @author <a href="mailto:karvounis.b@gmail.com">Evangelos Karvounis</a>
 *
 */
public class BurroughsMain {

	public static void main(String[] args) {

		BurroughsInteraction burroughsApp = new BurroughsInteraction();	//Instantiates a BurroughsInteraction object.

		Calendar chosenStartingDay = burroughsApp.getUserPreferredDate();	//Gets the starting day.

		burroughsApp.writeDatesToFile(chosenStartingDay);	//Writes the .csv file.

		System.out.println("\nThank you for using the application.");
	}

}
