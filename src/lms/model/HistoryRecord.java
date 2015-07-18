/**
 * Author: Peter Topalides s3456641
 * This class is used to hold history records of
 * holding that a member has loaned.
 * It stores the holding itself along with 
 * the total fee paid included late fees.
 * Functionality for getting the fee paid, getting the
 * late fee paid and getting the holding have been included in the
 * methods.
 */

package lms.model;

public class HistoryRecord {

	protected Holding holding;
	protected int feePayed;

	// Constructor for the class, contains the holding that was
	// loaned along with the fee payed.
	public HistoryRecord(int feePayed, Holding holding) {
		this.feePayed = feePayed;
		this.holding = holding;
	}

	// returns only the late fees paid for the holding
	public int getLateFeePaid() {
		return feePayed - holding.getDefaultLoanFee();
	}

	// returns the holding that was loaned
	public Holding getHolding() {
		return holding;
	}

	// returns the total fee payed for the holding loan,
	// including the late fees
	public int getFeePaid() {
		return feePayed;
	}

	// to string method for returning the holding ID and fee paid.
	public String toString() {
		String returnString = holding.getCode() + ":" + feePayed;
		return returnString;
	}
}
