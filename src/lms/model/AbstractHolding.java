/**
 * Author: Peter Topalides s3456641
 * Abstract holding class implements holding interface. 
 * Implements methods applicable to both book and video holding sub-classes.
 */

package lms.model;

import lms.model.util.DateUtil;
import lms.model.visitor.Visitor;

public abstract class AbstractHolding implements Holding {

	// variables used thoughout the class which are
	// applicable to both book and video sub-classes

	protected int holdingId;
	protected String title;
	protected int loanFee;
	protected int maxLoanPeriod;
	protected boolean onLoan = false;
	protected int numOfDays = 0;
	protected String borrowDate;

	// creates new abstractholding instance
	public AbstractHolding(int holdingId, String title, int loanFee,
			int maxLoanPeriod) {
		this.holdingId = holdingId;
		this.title = title;
		this.loanFee = loanFee;
		this.maxLoanPeriod = maxLoanPeriod;
	}

	// overrides method within the holding interface
	// and sets the onLoan boolean to false.
	// indicating that the holding is not on hold
	@Override
	public void returnHolding() {
		onLoan = false;
	}

	// boolean check to see test if a holding
	// is on loan. This is used for other methods within the system.
	public boolean isOnLoan() {
		return onLoan;
	}

	// returns a string of holding information seperated by ":"
	// the mothod is overridden in the book and video classes.
	public String toString() {
		String returnString = holdingId + ":" + title + ":" + loanFee + ":"
				+ maxLoanPeriod + ":";
		return returnString;
	}

	// returns the holding ID of a book or video.
	@Override
	public int getCode() {
		return holdingId;
	}

	// uses DateUtil class to set the borrow date of a holding
	// and change its status to onLoan.
	@Override
	public void setBorrowDate() {
		this.borrowDate = DateUtil.getInstance().getDate();
		onLoan = true;
	}

	// returns the date the holding was borrowed.
	@Override
	public String getBorrowDate() {
		return borrowDate;
	}

	// returns the standard loan fee of a holding
	@Override
	public int getDefaultLoanFee() {
		return loanFee;
	}

	// returns the max number of days as integar
	// that the holding can be on hold before incuring
	// late fees.
	@Override
	public int getMaxLoanPeriod() {
		return maxLoanPeriod;
	}

	// returns the title of a holding i.e. it's name.
	@Override
	public String getTitle() {
		return title;
	}
	
	
	
	
	
	
	
	public abstract void accept(Visitor visitor);

}
