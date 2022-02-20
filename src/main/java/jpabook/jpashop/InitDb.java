package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 주문 2개
 * userA
 * userB
 */
@Component
@RequiredArgsConstructor
public class InitDb {

  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.dbInit1();
    initService.dbInit2();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {

    private final EntityManager em;
    public void dbInit1() {
      Member member = getMember("userA", "Seoul", "1", "1111");
      em.persist(member);

      Book book1 = createBook("JPA1 BOOK" , 10000, 100);
      em.persist(book1);

      Book book2 = createBook("JPA2 BOOK" , 20000, 100);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2= OrderItem.createOrderItem(book2, 20000, 2);

      Delivery delivery = createDelivery(member);
      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      em.persist(order);
    }

    private Delivery createDelivery(Member member) {
      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      return delivery;
    }

    private Book createBook(String name, int price, int count) {
      Book book1 = new Book();
      book1.setName(name);
      book1.setPrice(price);
      book1.setStockQuantity(count);
      return book1;
    }

    public void dbInit2() {
      Member member = getMember("userB" , "벌교" , "7", "02623");
      em.persist(member);

      Book book1 = createBook("SPRING1 BOOK" , 20000, 200);
      em.persist(book1);

      Book book2 = createBook("SPRING2 BOOK" , 40000, 300);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
      OrderItem orderItem2= OrderItem.createOrderItem(book2, 40000, 4);

      Delivery delivery = createDelivery(member);
      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      em.persist(order);
    }

    private Member getMember(String name, String city, String street, String zipCode) {
      Member member = new Member();
      member.setName(name);
      member.setAddress(new Address(city, street , zipCode));
      return member;
    }

  }
}
