package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static jpabook.jpashop.domain.item.Book.createBook;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("form" , new BookForm());

    return "items/createItemForm";
  }

  @PostMapping("/new")
  public String create(BookForm bookForm) {
    Book book = createBook(bookForm);
    itemService.saveItem(book);
    return "redirect:/";
  }

  @GetMapping("")
  public String list(Model model) {
    List<Item> items = itemService.findItems();
    model.addAttribute("items",items);
    return "items/itemList";
  }

  @GetMapping("/{id}/edit")
  public String updateItemForm(@PathVariable("id") Long itemId, Model model) {
    Book item = (Book)itemService.findItem(itemId);

    BookForm form = new BookForm();
    form.setId(item.getId());
    form.setName(item.getName());
    form.setPrice(item.getPrice());
    form.setStockQuantity(item.getStockQuantity());
    form.setAuthor(item.getAuthor());
    form.setIsbn(item.getIsbn());

    model.addAttribute("form" , form);
    return "items/updateItemForm";
  }

  @PostMapping("/{id}/edit")
  public String updateItem(@PathVariable Long id , @ModelAttribute("form") BookForm form) {
    itemService.updateItem(id, form.getName(), form.getPrice(), form.getStockQuantity());
    return "redirect:/";
  }
}
