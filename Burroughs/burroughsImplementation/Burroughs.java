package burroughsImplementation;

import java.util.Calendar;

/**
 * <h1>Calculates the payment dates for salary and bonuses.</h1>
 * 
 * @author Evangelos Karvounis
 */
public class Burroughs {

	/**A final array of Strings	that contains the names of the months. It is used by the <i>monthName</i> method to get the name of the month.*/
	private final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December"};
	private Calendar temp;

	public Burroughs(){}

	/**
	 * Calculates the date that monthly bonuses are to be paid. If the 15th of a month, it is a weekend, then bonuses are paid the first Wednesday after the 15th.<br> 
	 * Sets DAY_OF_MONTH to 15 and gets the DAY_OF_WEEK. If the 15th of a month is Sunday or Saturday, then the DAY_OF_WEEK is 1 and 7 respectively.<br>
	 * If DAY_OF_MONTH is Sunday, the value 18 is returned and if it is Saturday then the value 19 is returned.<br>
	 * If DAY_OF_MONTH is a week day, then the value 15 is returned.<br>
	 * 
	 * @param calendar A Calendar object
	 * @return <b>int</b> - The day that bonus payments are to be paid. 
	 * */
	public int bonusPaymentDate(Calendar calendar){

		temp = calendar;
		temp.set(Calendar.DAY_OF_MONTH, 15);	//Sets the day to be the 15th.

		int dayOfWeek = temp.get(Calendar.DAY_OF_WEEK);	//Gets the day's weekday number. 1 is for Sunday and 7 is for Saturday.

		if(dayOfWeek == 1){

			return 18;	//If the 15th is Sunday, next Wednesday is on the 18th of the month.

		}else if(dayOfWeek == 7){

			return 19;	//If the 15th is Saturday, next Wednesday is on the 19th of the month.
		}

		return 15;	//If the 15th is weekday, 15 is returned.
	}

	/**
	 * Calculates the date that monthly salary is to be paid. Checks if the last of the month is weekend.<br> 
	 * If the day is Saturday, then the payment day is one day earlier than this day.<br>
	 * If the day is Sunday, then the payment day is two day earlier than this day. <br>
	 * 
	 * @param calendar A Calendar object
	 * @return <b>int</b> - The day that bonus payments are to be paid.
	 */
	public int salaryPaymentDate(Calendar calendar){

		temp = calendar;
		int daysInMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);	//Gets the month's number of days.

		temp.set(Calendar.DAY_OF_MONTH, daysInMonth);	//Sets the calendar to point to the last day of the month.

		int dayOfWeek = temp.get(Calendar.DAY_OF_WEEK);	//Gets the day's weekday number. 1 is for Sunday and 7 is for Saturday.

		if(dayOfWeek == 1){

			return (daysInMonth-2);	//If Sunday, return two days before the last day of the month.

		}else if(dayOfWeek == 7){

			return (daysInMonth-1);	//If Saturday, return two days before the last day of the month.
		}

		return daysInMonth;	//If weekday, return the last day of the month.
	}

	/**
	 * Gets the name of the month.
	 * 
	 * @param calendar A Calendar object
	 * @return <b>String</b> - The name of the month.
	 */
	public String monthName(Calendar calendar){

		int month = calendar.get(Calendar.MONTH);	//Gets the month value. It is a integer from [0,11].

		return MONTHS[month];
	}

}
