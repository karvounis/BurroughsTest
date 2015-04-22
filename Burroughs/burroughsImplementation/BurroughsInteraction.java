package burroughsImplementation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1>Interacts with the user.</h1>
 * 
 * @author Evangelos Karvounis
 *
 */
public class BurroughsInteraction {

	public BurroughsInteraction(){}

	/**
	 * Gets the user's preferred starting date. <br>
	 * User can choose if he wants to calculate the payment dates for the next twelve months starting from today OR from a future date.
	 * @return <b>Calendar</b> User's preferred date.
	 */
	public Calendar getUserPreferredDate(){

		Calendar calendar = Calendar.getInstance();
		Scanner user = new Scanner(System.in);	//Creates a Scanner object to get information from the console.

		boolean userChoice = false;
		int choice=0, year=0, month=0, day=0;

		System.out.print("Welcome user! In order to create a .csv file that contains the payment dates for the next 12 months, you should first choose your preferred starting date.\n"
				+ "Select from the following choices: \n\t1. Calculate payment dates from today.\n\t"
				+ "2. Calculate payment dates from a date that you choose.\n\t"
				+ "3. Exit the application.\n");

		while(!userChoice){	//Loop that ensures that the user makes a valid choice.

			System.out.print("Make your choice: ");
			try{

				choice = user.nextInt();

				if(choice < 1 || choice > 3){
					System.out.println("Only integer values from 1 to 3 are acceptable. Please try again.");
					continue;
				}

				break;	//Breaks the loop
			}catch(InputMismatchException ex ){

				System.out.println("Only integer values from 1 to 3 are acceptable. Please try again.");
				user.next();
				continue;
			}
		}

		switch(choice){	//Checks the user's input.
		case 1: 
			Calendar startingDate = Calendar.getInstance();

			System.out.println("You chose today as your starting date.");
			System.out.printf("Starting Date is %d/%d/%d.\n", startingDate.get(Calendar.DATE), startingDate.get(Calendar.MONTH)+1, startingDate.get(Calendar.YEAR));
			break;
		case 2: 
			do{	//Loop that gets the user's input.
				Calendar today = Calendar.getInstance();

				System.out.print("\nEnter your preferred starting year: ");
				year = user.nextInt();

				System.out.print("\nEnter your preferred starting month using integer values from 1 to 12: ");
				month = user.nextInt();

				System.out.print("\nEnter your preferred starting day using integer values from 1 to 31: ");
				day = user.nextInt();

				if(day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH) || day <=0 || month > 12 || month<=0){	//Check if inputs are valid.
					System.out.println("It appears that you entered an invalid date. Please try again.");
					continue;
				}
				//Sets starting date
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, month-1);
				calendar.set(Calendar.DAY_OF_MONTH, day);

				if(today.after(calendar)){	//Checks if the chosen starting date is in the future. If is a past date, prompts the user to re-enter the starting date. 
					System.out.println("It appears that you entered an past date. Please try again.");
					continue;
				}
				break;

			}while(true);

			System.out.printf("Starting Date is %d/%d/%d.\n", calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
			break;
		case 3: 
			System.out.print("Thank you for using the application.");
			user.close();
			System.exit(0);	//Terminates the application
		}

		user.close();	//Closes Scanner.
		return calendar;
	}

	/**
	 * Writes the payment dates and the name of the month to a row in the "PaymentDates.csv" file.
	 * @param calendar User's preferred date.
	 */
	public void writeDatesToFile(Calendar calendar){

		Burroughs burroughsFindDates = new Burroughs();	//Instantiates the Burroughs object
		FileWriter fout;

		try {

			fout = new FileWriter("PaymentDates.csv");	//Creates a .csv file for writing.

			for(int i=0; i<12; i++){

				//Loop that writes payment dates for the next twelve months
				calendar.add(Calendar.MONTH, 1);

				fout.append(burroughsFindDates.monthName(calendar) + ", " + burroughsFindDates.salaryPaymentDate(calendar) + ", " + burroughsFindDates.bonusPaymentDate(calendar));

				if(i<=10){
					fout.append("\n");	
				}
			}

			fout.close();	//Closes FileWriter.

			System.out.print("PaymentDates.csv created.");
		} catch (IOException e) {

			e.printStackTrace();
			System.exit(1);	//Terminates the application.
		}
	}
}
