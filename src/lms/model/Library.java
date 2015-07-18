/**
 * Author: Peter Topalides s3456641
 * Library class holds and instance of a member and a
 * library collection.
 * This class is primaraly used to call functions member and library
 * collection classes.
 * Library provides functionality for most methods in the system
 */

package lms.model;

import lms.model.exception.InsufficientCreditException;
import lms.model.exception.MultipleBorrowingException;
import lms.model.exception.OverdrawnCreditException;

public class Library {

	// Initialse an instance of both member and library collection
	private Member member;
	private LibraryCollection libraryCollection;

	// empty constructor
	public Library() {
	}

	// takes an instance of memeber and adds it to the library
	public void addMember(Member member) {

		this.member = member;
	}

	// takes an instance of a library collection and add it to the library
	public void addCollection(LibraryCollection collection) {

		this.libraryCollection = collection;

	}

	// calls member class to calculate total late fees
	public int calculateTotalLateFees() {
		return member.calculateTotalLateFees();
	}

	// calls member class to calculate remaining credit
	public int calculateRemainingCredit() {
		return member.calculateRemainingCredit();
	}

	// calls member class to reset member credit
	public void resetMemberCredit() {
		member.resetMemberCredit();
	}

	// calls library collection class to get borrowed holdings
	// which is returned as a Holding array.
	public Holding[] getBorrowedHoldings() {
		return libraryCollection.getBorrowedHoldings();
	}

	// calls member class to get a specific history record using
	// holding ID to search.
	public HistoryRecord getHistoryRecord(int holdingId) {
		return member.getHistoryRecord(holdingId);
	}

	// calls member class to return an array of history records for a member
	public HistoryRecord[] getBorrowingHistory() {
		return member.getBorrowingHistory();
	}

	// calls library collection to find a holding and then calls
	// member to return the holding.
	public void returnHolding(int holdingId) throws OverdrawnCreditException {

		// gets the specified holding from library collection using holding ID
		// and stores the holding in h.
		Holding h = libraryCollection.getHolding(holdingId);
		// calls the member class to return the holding and passes the holding
		// to
		// the method.
		member.returnHolding(h);

	}

	// calls library collection to find a holding and then calls
	// member class to borrow the holding.
	public void borrowHolding(int holdingId)
			throws InsufficientCreditException, MultipleBorrowingException {

		// gets the specified holding from library collection using holding ID
		// and stores the holding in h.
		Holding h = libraryCollection.getHolding(holdingId);

		// calls the member class to borrow the holding and passes the holding
		// to
		// the method.
		member.borrowHolding(h);
	}

	// calls library collection to count all the video holdings in the library
	public int countVideos() {
		return libraryCollection.countVideos();
	}

	// calls library collection to count all the book
	// holdings in the library
	public int countBooks() {
		return libraryCollection.countBooks();
	}

	// calls library collection to to return a holding array with all the
	// holdings
	// in the library.
	public Holding[] getAllHoldings() {
		return libraryCollection.getAllHoldings();
	}

	// calls library collection to to get a specific holding using holding ID
	public Holding getHolding(int holdingId) {
		return libraryCollection.getHolding(holdingId);
	}

	// calls library collection to add a holding to it passing a holding to the
	// method
	public boolean addHolding(Holding holding) {
		return libraryCollection.addHolding(holding);
	}

	// returns the instance of library collection
	public LibraryCollection getCollection() {
		return libraryCollection;
	}

	// returns the instance of member
	public Member getMember() {
		return member;

	}

	// calls the library collection to remove a holding from its collection
	// using
	// a holding ID.
	public boolean removeHolding(int holdingId) {
		return libraryCollection.removeHolding(holdingId);
	}
	
	public String getCollectionName(){
		return libraryCollection.getCollectionName();
	}
	
	public String getCollectionCode(){
		return libraryCollection.getCollectionCode();
	}

}
