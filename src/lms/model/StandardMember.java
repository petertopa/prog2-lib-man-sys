/**
 * Author: Peter Topalides s3456641
 * Standard member class extends abstract member class
 * This class implements functionality for returning member type
 * to abstract member toSting, return holding method specific to
 * standard members.
 */

package lms.model;

import lms.model.exception.OverdrawnCreditException;

public class StandardMember extends AbstractMember {

	// Declares constant for members maximum credit
	private static final int MAX_CREDIT = 30;

	// Used to return member type in return string toString
	private static final String MEMBER_TYPE = "STANDARD";

	// constructor used to pass max credit to super constructor
	public StandardMember(String standardMemberId, String standardMemberName) {
		super(standardMemberId, standardMemberName, MAX_CREDIT);
	}

	// toString to return member type to super toString.
	public String toString() {
		return (super.toString() + MEMBER_TYPE);
	}

	// return holding method specific to standard members
	// will not allow returning of a holding if members current credit will
	// drop below 0.
	@Override
	public void returnHolding(Holding holding) throws OverdrawnCreditException {

		// takes the late fees for returning the book and stores the value in
		// fees
		int fees = holding.calculateLateFee();

		// checks if current member credit will drop below 0 after the return
		if (currentCredit - fees < 0) {
			// if ture, throws exception
			throw new OverdrawnCreditException(
					"Can not return, credit can not be below 0!");
		} else {
			// if members credit will not drop below 0, returns holding
			holding.returnHolding();

			// creates a new history record passing values for the fees paid
			// and holding.
			HistoryRecord hr = new HistoryRecord(holding.getDefaultLoanFee()
					+ holding.calculateLateFee(), holding);

			// adds the history record to borrowing history map
			history.addHistoryRecord(hr);

			// removes the holding being returns from the abstract members
			// current
			// loans array
			currentLoans.remove(holding);

			// deducts any applicable late fees from members current credit
			currentCredit -= holding.calculateLateFee();
		}
	}

}
