/**
 * Author: Peter Topalides s3456641
 * Book class is a child of abstract holding and represent 1 of two possible holdings
 * in the system.
 * Provides functionality specific to a Book.
 * methods include, toString, calculating late fees and accessing visitor class.
 */

package lms.model;

import lms.model.util.DateUtil;
import lms.model.visitor.Visitor;

public class Book extends AbstractHolding {

	private static final int LOAN_FEE = 10;
	private static final int MAX_LOAN_PERIOD = 28;
	private static final String HOLDING_TYPE = "BOOK";

	private static final int latePenaltyFeeDailyRate = 2;

	// Constructor for book class
	public Book(int holdingId, String title) {
		super(holdingId, title, LOAN_FEE, MAX_LOAN_PERIOD);

	}

	// To string which overrides the abstractHolding toString
	// and includes the holding type i.e. book or video
	public String toString() {
		return (super.toString() + HOLDING_TYPE);
	}

	// returns late fees for a late holding being returned, if no late fees are
	// applicable, just returns 0.
	public int calculateLateFee() {

		// get the number of days since the holding has been borrowed
		numOfDays = DateUtil.getInstance().getElapsedDays(borrowDate);

		// if the number of days borrowd are more than the maximum allowable
		// loan period, performs calculation equivalent to (number of days late
		// * late fee of $2)
		if (numOfDays > maxLoanPeriod) {
			return (numOfDays - maxLoanPeriod) * latePenaltyFeeDailyRate;
		} else {
			return 0;
		}
	}

	// allows book class to visit the visitor class,
	// this is used for counting books in the library class.
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
