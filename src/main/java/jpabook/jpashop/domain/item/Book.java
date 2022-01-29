package jpabook.jpashop.domain.item;

import jpabook.jpashop.controller.BookForm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item {

  private String author;
  private String isbn;

  public Book() {}

  public static Book createBook(BookForm bookForm) {
    Book book = new Book();
    book.setName(bookForm.getName());
    book.setPrice(bookForm.getPrice());
    book.setStockQuantity(bookForm.getStockQuantity());
    book.setAuthor(bookForm.getAuthor());
    book.setIsbn(bookForm.getIsbn());
    return book;
  }

}
