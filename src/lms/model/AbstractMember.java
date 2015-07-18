/**
 * Author: Peter Topalides s3456641
 * AbstractMember implements the Member interface.
 * contains methods which members would typically need.
 * methods include calculating late fees for a late returned holding
 * borrowing a holding and various getters.
 * Also contains and instance of a members Borrowing History
 * and a list of their loans currently on hold.
 */

package lms.model;

import java.util.ArrayList;
import java.util.List;

import lms.model.exception.InsufficientCreditException;
import lms.model.exception.MultipleBorrowingException;

public abstract class AbstractMember implements Member {

	protected String memberId;
	protected String memberName;
	protected int maxCredit;
	protected int currentCredit;

	// holds an instance of a members borrowing history
	protected BorrowingHistory history;

	// initiate an array list of all the current holdings a member has on loan.
	protected List<Holding> currentLoans = new ArrayList<Holding>();

	// Constructor creates a borrowing history for a member and sets
	// its current credit to the max credit of the member type.
	// Constructor is overridden in member subclasses (premium and standard
	// member)
	// to obtain details specific to their type.
	public AbstractMember(String memberId, String memberName, int maxCredit) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.maxCredit = maxCredit;
		currentCredit = maxCredit;
		history = new BorrowingHistory();
	}

	// calls method in the members borrowing history
	// to calculate their late fees.
	public int calculateTotalLateFees() {
		return history.calculateTotalLateFees();
	}

	// Method used to borrow a holding.
	// Checks that the member has enough credit to borrow the holding.
	// Check that the member is not trying to borrow a holding that they have
	// previously borrowed,
	// throws exceptions accordingly.
	@Override
	public void borrowHolding(Holding holding)
			throws InsufficientCreditException, MultipleBorrowingException {

		// Gets the standard loan fee for the holding
		int cost = holding.getDefaultLoanFee();

		// Checks that the member has enough credit to borrow the holding.
		if (currentCredit - cost < 0) {
			// if they do not have enough credit, exception is thown
			throw new InsufficientCreditException("Not enough Credit!");
		} else {
			// if the member has enough credit to borrow the holding
			// the system checks that the member is not trying to borrow a
			// holding that they have previously borrowed,
			if (history.checkHistory(holding)) {
				// if true, holdings borrowdate is set.
				holding.setBorrowDate();
				// holding is added to the members current loans
				currentLoans.add(holding);
				// cost of the holding is deducted from the members current
				// credit
				currentCredit -= cost;
			} else {
				// if the member is trying to borrow a previously borrowed
				// holding
				// exception is thrown accordingly.
				throw new MultipleBorrowingException(
						"This is a message to let you know about multiple borrowing!");
			}
		}
	}

	// returns a string of information about the member, all separated by ":"
	public String toString() {
		String returnString = memberId + ":" + memberName + ":" + currentCredit
				+ ":";
		return returnString;
	}

	// returns members current credit
	public int calculateRemainingCredit() {
		return currentCredit;
	}

	// returns all the history records of the member in an array.
	public HistoryRecord[] getBorrowingHistory() {
		return history.getAllHistoryRecords();
	}

	// returns members name
	public String getFullName() {
		return memberName;
	}

	// resets members credit to max credit.
	public void resetMemberCredit() {
		currentCredit = maxCredit;
	}

	// returns the max credit for member type
	public int getMaxCredit() {
		return maxCredit;
	}

	// retuns members ID
	public String getMemberId() {
		return memberId;
	}

	// returns a history record for a specified holding
	// uses the holding ID to search for the record.
	public HistoryRecord getHistoryRecord(int holdingId) {
		return history.getHistoryRecord(holdingId);
	}
}
