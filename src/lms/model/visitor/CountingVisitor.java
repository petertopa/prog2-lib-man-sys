package lms.model.visitor;

import lms.model.Video;
import lms.model.Book;

public class CountingVisitor implements Visitor{
	
	private int bookCount, videoCount;
	
	public CountingVisitor(){
		bookCount = videoCount = 0;
	}

	@Override
	public void visit(Book book) {
		bookCount ++;
		
	}

	@Override
	public void visit(Video video) {
		videoCount++;
		
	}
	
	public int getBookCount(){
		return bookCount;
	}
	
	public int getVideoCount(){
		return videoCount;
	}


}
