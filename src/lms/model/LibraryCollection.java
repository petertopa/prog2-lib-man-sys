/**
 * Author: Peter Topalides s3456641
 * Library collecting holds a linked hash map of holdings
 * and implements methods specific to a typical library collecting.
 * Functionality includes: removing/adding holdings to the collection,
 * return specific holdings, returning all holdings in the collection,
 * counting the holdings individually as either books or videos
 * and returning an array of all the holding currently on loan.
 */

package lms.model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import lms.model.visitor.CountingVisitor;

public class LibraryCollection {

	private String collectionCode = "";
	private String collectionName = "";

	// Linked hash map to store all the holdings
	private Map<Integer, Holding> holdings = new LinkedHashMap<Integer, Holding>();

	// array list to store all the holding currently on loan.
	List<Holding> onLoanList = new ArrayList<Holding>();

	// Constructor to creat collection, takes collection code and name.
	public LibraryCollection(String collectionCode, String collectionName) {
		this.collectionCode = collectionCode;
		this.collectionName = collectionName;
	}

	// to string to return the collection code and collection name along with
	// the holding ID's off all the holdings in the collection.
	public String toString() {

		// creates a string with collection code and name.
		String returnString = collectionCode + ":" + collectionName;

		// used to check if the loop has iterated to append comma separation
		// properly.
		boolean first = true;

		// empty string code, used to append the holding ID's
		String stringCode = "";

		// checks that the library collection has holdings
		if (!holdings.isEmpty()) {
			// if the holding collection is not empty, iterates through the map
			// to get the holding ID
			for (Map.Entry<Integer, Holding> entry : holdings.entrySet()) {

				// stores the holding ID in code and gets the Holding ID
				// using getCode method
				int code = entry.getValue().getCode();

				// if the loop has iterated only once, stingCode takes the
				// value of code(Holding ID)
				if (first) {
					stringCode = "" + code;
					// indicates that the loop has run more than once.
					first = false;
				} else {
					// for every other loop iteration the holding ID is
					// appending to
					// sting code using comma separation.
					stringCode += "," + code;
				}
			}
			// if the holdings map had holdings, returns original return string
			// with
			// the string code of holding ID's
			return returnString + ":" + stringCode;
		} else {
			// if the holdings map was empty, just returns the original return
			// string.
			return returnString;
		}
	}

	// removes holding from holdings map
	public boolean removeHolding(int holdingId) {

		// checks that holding to be removied is not currently on loan
		if (holdings.get(holdingId).isOnLoan()) {
			// if on loan, does not remove holding
			return false;
		} else {
			// if it is not on loan, removes holding from holdings map.
			holdings.remove(holdingId);
			return true;
		}
	}

	// adds holding to holdings map
	public boolean addHolding(Holding holding) {

		// checks that the map does not already contain the same holding
		if (holdings.containsKey(holding.getCode()))
			// if same holding is alrady in the map, returns false
			return false;
		// if it is not already in the map, add the holding to the map and
		// returns true.
		holdings.put(holding.getCode(), holding);
		return true;

	}

	// returns a holding using specified holding ID
	public Holding getHolding(int holdingId) {
		// uses .get to get the holding from the map.
		return holdings.get(holdingId);
	}

	// returns an array of all the holdings in the holdings map.
	public Holding[] getAllHoldings() {

		// checks that the map is not empty
		if (holdings.size() != 0) {
			// if the map has holdings in it, uses .toArray to convert map to
			// array.
			return holdings.values().toArray(new Holding[holdings.size()]);
		} else {
			// if map is empty, returns null
			return null;
		}

	}

	// calls visitor class to count videos
	public int countVideos() {

		// creates instance of videoVistor to count videos.
		CountingVisitor videoVisitor = new CountingVisitor();

		// uses loop to iterate though the holdings map and call the accept
		// method on the video visitor
		for (Holding holding : holdings.values())
			holding.accept(videoVisitor);
		// returns the count.
		return videoVisitor.getVideoCount();
	}

	// calls visitor class to count books
	public int countBooks() {

		// creates instance of bookVistor to count books.
		CountingVisitor bookVisitor = new CountingVisitor();

		// uses loop to iterate though the holdings map and call the accept
		// method on the book visitor
		for (Holding holding : holdings.values())
			holding.accept(bookVisitor);
		return bookVisitor.getBookCount();
	}

	// reutns an array of Holdings that are currently on loan
	public Holding[] getBorrowedHoldings() {

		// interates though the holdings map and check if it is on loan
		for (Map.Entry<Integer, Holding> entry : holdings.entrySet()) {
			if (entry.getValue().isOnLoan()) {
				// if the holding is on loan, stores the holding in the
				// onLoanList (arrayList)
				onLoanList.add(entry.getValue());
			}
		}

		// if the onLoanList has holdings in it, returns the on loan list is an
		// array.
		if (onLoanList.size() != 0) {
			// convert array list to array using .toArray.
			return onLoanList.toArray(new Holding[onLoanList.size()]);
		}

		return null;
	}
	
	public String getCollectionName(){
		return collectionName;
	}
	
	public String getCollectionCode(){
		return collectionCode;
	}

}
