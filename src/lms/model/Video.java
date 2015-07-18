/**
 * Author: Peter Topalides s3456641
 * Video class is a child of abstract holding and represent 1 of two possible holdings
 * in the system.
 * Provides functionality specific to a Video.
 * methods include, toString, calculating late fees and accessing visitor class.
 */

package lms.model;

import lms.model.util.DateUtil;
import lms.model.visitor.Visitor;

public class Video extends AbstractHolding {

	private static final int MAX_LOAN_PERIOD = 7;
	private static final String HOLDING_TYPE = "VIDEO";

	//toString to return holding type to super toString
	public String toString() {
		return (super.toString() + HOLDING_TYPE);
	}


	//Constructor for video class to pass the max loan period to super constructor
	public Video(int holdingId, String title, int LOAN_FEE) {
		super(holdingId, title, LOAN_FEE, MAX_LOAN_PERIOD);
		
	}

	// returns late fees for a late holding being returned, if no late fees are
	// applicable, just returns 0.
	public int calculateLateFee() {
		
		// get the number of days since the holding has been borrowed
		numOfDays = DateUtil.getInstance().getElapsedDays(borrowDate);

		//calculates the daily late fee and stores it in rate.
		int rate = (getDefaultLoanFee() / 2);

		// if the number of days borrowed are more than the maximum allowable
		// loan period, performs calculation equivalent to (number of days late
		// * late fee 'rate')
		if (numOfDays > maxLoanPeriod) {
			return (numOfDays - maxLoanPeriod) * rate;
		} else {
			return 0;
		}
	}
	
	// allows book class to visit the visitor class,
	// this is used for counting books in the library class.
	public void accept(Visitor visitor){
		visitor.visit(this);
	}
	

}
