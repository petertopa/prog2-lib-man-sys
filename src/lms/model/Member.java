/** 
 * Author: Peter Topalides s3456641
 * Member interface for abstract member class and borrower class.
 */

package lms.model;

public interface Member extends Borrower {

	public int calculateRemainingCredit();

	public HistoryRecord[] getBorrowingHistory();

	public String getFullName();

	public int getMaxCredit();

	public String getMemberId();

	public void resetMemberCredit();

	public HistoryRecord getHistoryRecord(int holdingId);

	public int calculateTotalLateFees();

}
