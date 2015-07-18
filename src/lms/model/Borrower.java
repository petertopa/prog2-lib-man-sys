/**
 * Author: Peter Topalides s3456641
 * Borrower interface works with member interface to call methods
 * specific to a member borrowing a holding.
 * Calls borrow and return holdings.
 */

package lms.model;

import lms.model.exception.InsufficientCreditException;
import lms.model.exception.MultipleBorrowingException;
import lms.model.exception.OverdrawnCreditException;

public interface Borrower{
	
	public void borrowHolding(Holding holding)
			throws InsufficientCreditException, MultipleBorrowingException;

	public void returnHolding(Holding holding) throws OverdrawnCreditException;

}
