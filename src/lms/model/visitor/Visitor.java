package lms.model.visitor;

import lms.model.Video;
import lms.model.Book;

public interface Visitor {
	
	public void visit(Video video);
	public void visit(Book book);
	

}
