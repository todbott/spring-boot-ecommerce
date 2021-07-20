package com.ecommerce.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {


  @Autowired
  private ItemRepository itemRepository;

  // ---- the two endpoints for the shop; you can see all items and search for items
  @RequestMapping(path="/shop")
  public @ResponseBody Iterable<Item> getAllItems() {
    return itemRepository.getShopItems();
  }

  @RequestMapping(path="/shop/search")
  public @ResponseBody Iterable<Item> getFilteredItems(@RequestParam String searchString) {
    return itemRepository.getFilteredItems(searchString);
  } 





  // --- these are all the endpoints used by the user of the app
  // -- add item to cart, remove item from cart, buy item(s), get past orders
  @RequestMapping(path="/addItemToCart") 
  public @ResponseBody String addItemToCart (@RequestParam Long itemId
  , @RequestParam String username) {
    itemRepository.blankWhoseCart(itemId);
    itemRepository.setWhoseCart(username, itemId);
    return "Added to cart";
  }
  
  @RequestMapping(path="/deleteItemFromCart")
  public @ResponseBody String deleteItemFromCart (@RequestParam Long itemId) {
    itemRepository.blankWhoseCart(itemId);
    itemRepository.setWhoseCart("shop", itemId);
    return "Deleted from cart";
  }

  @RequestMapping(path="/getItemsFromCart")
  public @ResponseBody Iterable<Item> getItemsFromCart (@RequestParam String username) {
    String usernameusername = username + "," + username;
    return itemRepository.getUsersItems(username, usernameusername);
  }

  @RequestMapping(path="/buyItem")
  public @ResponseBody String itemBought(@RequestParam Long itemId 
  , @RequestParam String username) {
    username = "bought by:" + username;
    itemRepository.blankWhoseCart(itemId);
    itemRepository.itemBought(username, itemId);
    return "Marked as bought";
  }

  @RequestMapping(path="/getOrders")
  public @ResponseBody Iterable<Item> getOrders(@RequestParam String username) {
    String usernameusername = "bought by:" + username + "," + username;
    return itemRepository.getOrders(usernameusername);
  }
}