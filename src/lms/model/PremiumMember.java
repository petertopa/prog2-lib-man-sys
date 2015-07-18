/**
 * Author: Peter Topalides s3456641
 * Premium member class extends abstract member class
 * This class implements functionality for returning member type
 * to abstract member toSting, return holding method specific to
 * premium members.
 */

package lms.model;

public class PremiumMember extends AbstractMember {

	// Declares constant for members maximum credit
	private static final int MAX_CREDIT = 45;

	// Used to return member type in return string toString
	private static final String MEMBER_TYPE = "PREMIUM";

	// constructor used to pass max credit to super constructor
	public PremiumMember(String premiumMemberId, String premiumMemberName) {
		super(premiumMemberId, premiumMemberName, MAX_CREDIT);
	}

	// toString to return member type to super toString.
	public String toString() {
		return (super.toString() + MEMBER_TYPE);
	}

	// return holding method specific to premium member
	// allows returning of a holding regardless of members
	// current credit.
	@Override
	public void returnHolding(Holding holding) {

		// calls holding to return holding
		holding.returnHolding();

		// creates a new history record passing values for the fees paid
		// and holding.
		HistoryRecord hr = new HistoryRecord(holding.getDefaultLoanFee()
				+ holding.calculateLateFee(), holding);

		// adds the history record to borrowing history map
		history.addHistoryRecord(hr);

		// removes the holding being returns from the abstract members current
		// loans array
		currentLoans.remove(holding);

		// deducts any applicable late fees from members current credit
		currentCredit -= holding.calculateLateFee();

	}

}
