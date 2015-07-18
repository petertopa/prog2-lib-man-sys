/** 
 * Author: Peter Topalides s3456641
 * Holding interface which contains all the methods 
 * of abstractHolding.
 */

package lms.model;

import lms.model.visitor.Visitable;

public interface Holding extends Visitable{

	public int getCode();

	public void setBorrowDate();

	public String getBorrowDate();

	public int getDefaultLoanFee();

	public int getMaxLoanPeriod();

	public String getTitle();
	
	public boolean isOnLoan();

	public void returnHolding();
	
	public int calculateLateFee();
}
