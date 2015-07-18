/**
 * Author: Peter Topalides s3456641
 * Borrowing history is used when creating a member.
 * The class provides functionality for storing a history record in a
 * linked hashMap along with methods for adding to the borrowing history,
 * calculating all the late fees accosiated with a members borrowing history,
 * returning a specified holding in the history record and
 * return all the holdings in the history record.
 */

package lms.model;

import java.util.*;

public class BorrowingHistory {

	// Stores multiple instances of a history record in a linked hash map
	protected Map<Integer, HistoryRecord> bh = new LinkedHashMap<Integer, HistoryRecord>();

	// adds an instance of a history record to the bh(borrowing history) map
	public void addHistoryRecord(HistoryRecord hr) {

		// key for the map is the holding ID for a holding
		bh.put(hr.getHolding().getCode(), hr);
	}

	// iterates though a members history records and returns a integer value
	// of all the late fees a member has paid.
	public int calculateTotalLateFees() {

		int totalLateFees = 0;

		// iteration though all the members history records
		for (Map.Entry<Integer, HistoryRecord> entry : bh.entrySet()) {
			// for each history record, gets the late fee paid and
			// appends its value to totalLateFees.
			totalLateFees += entry.getValue().getLateFeePaid();
		}

		return totalLateFees;
	}

	// checks if a member is borrowing a book they have previously borrowed.
	public boolean checkHistory(Holding holding) {

		// takes the holding passed in the method and compares it to
		// the holdings in the members history records
		if (bh.containsKey(holding.getCode()))
			// if it exists in the record, returns false
			return false;
		// if it does not exist in the record, returns true.
		return true;

	}

	// takes a holding ID and returns the specific history record
	// for the holding ID provided
	public HistoryRecord getHistoryRecord(int holdingId) {
		return bh.get(holdingId);
	}

	// converts linked hash map of history records to an array
	// and returns all the history records for the member.
	public HistoryRecord[] getAllHistoryRecords() {

		// checks that the member has a history record
		if (bh.size() != 0) {
			// if history record has instances, returns an array of all history
			// records
			// using .toArray to convert from map to array.
			return bh.values().toArray(new HistoryRecord[bh.size()]);
		} else {
			// if history record is empty, returns null.
			return null;
		}
	}
}
