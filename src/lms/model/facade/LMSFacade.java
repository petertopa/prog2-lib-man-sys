package lms.model.facade;

import lms.model.HistoryRecord;
import lms.model.Holding;
import lms.model.Library;
import lms.model.LibraryCollection;
import lms.model.Member;
import lms.model.exception.InsufficientCreditException;
import lms.model.exception.MultipleBorrowingException;
import lms.model.exception.OverdrawnCreditException;
import lms.model.util.DateUtil;

/**
 * @author Mikhail Perepletchikov
 */
public class LMSFacade implements LMSModel {

	private Library library;

	public LMSFacade() {
		library = new Library();
	}

	public void setDate(String currentDate) {
		DateUtil.getInstance().setDate(currentDate);
	}

	@Override
	public void addMember(Member member) {
		library.addMember(member);

	}

	@Override
	public void addCollection(LibraryCollection collection) {
		library.addCollection(collection);

	}

	@Override
	public Member getMember() {
		return library.getMember();
	}

	@Override
	public LibraryCollection getCollection() {
		return library.getCollection();
	}

	@Override
	public boolean addHolding(Holding holding) {
		return library.addHolding(holding);
	}

	@Override
	public boolean removeHolding(int holdingId) {
		return library.removeHolding(holdingId);
	}

	@Override
	public Holding getHolding(int holdingId) {
		return library.getHolding(holdingId);
	}

	@Override
	public Holding[] getAllHoldings() {
		return library.getAllHoldings();
	}

	@Override
	public int countBooks() {
		try {
			return library.countBooks();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public int countVideos() {
		try {
			return library.countVideos();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public void borrowHolding(int holdingId)
			throws InsufficientCreditException, MultipleBorrowingException {
		library.borrowHolding(holdingId);

	}

	@Override
	public void returnHolding(int holdingId) throws OverdrawnCreditException {
		library.returnHolding(holdingId);

	}

	@Override
	public HistoryRecord[] getBorrowingHistory() {
		return library.getBorrowingHistory();
	}

	@Override
	public HistoryRecord getHistoryRecord(int holdingId) {
		return library.getHistoryRecord(holdingId);
	}

	@Override
	public Holding[] getBorrowedHoldings() {
		return library.getBorrowedHoldings();
	}

	@Override
	public void resetMemberCredit() {
		library.resetMemberCredit();

	}

	@Override
	public int calculateRemainingCredit() {
		return library.calculateRemainingCredit();
	}

	@Override
	public int calculateTotalLateFees() {
		return library.calculateTotalLateFees();
	}

	public String getCollectionName() {
		return library.getCollectionName();
	}

	public String getCollectionCode() {
		return library.getCollectionCode();
	}
}