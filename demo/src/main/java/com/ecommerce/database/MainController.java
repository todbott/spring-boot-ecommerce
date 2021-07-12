package com.ecommerce.database;

import java.util.List;
import java.util.UUID;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired 
  private UserRepository userRepository;

  @Autowired
  private ItemRepository itemRepository;

  // ---- the two endpoints for the shop; you can see all items and search for items
  @GetMapping(path="/shop")
  public @ResponseBody Iterable<Item> getAllItems() {
    // This returns a JSON or XML with the items
    return itemRepository.findAll();
  }

  @PostMapping(path="/shop/search")
  public @ResponseBody Iterable<Item> getFilteredItems(@RequestParam String searchString) {
    // This returns a JSON or XML with the items
    return itemRepository.getFilteredItems(searchString);
  } 

  // ---- for debugging and testing purposes
  @GetMapping(path="/listAll")
  public @ResponseBody Iterable<AppUser> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }


  // --- for adding users; not to be in the final app
  @PostMapping(path="/addOne") // Map ONLY POST Requests 
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String password
      , @RequestParam String role) {
    AppUser n = new AppUser();
    n.setName(name);
    n.setPassword(password);
    n.setRole(role);
    userRepository.save(n);
    return "Saved";
  }

  // ---- for debugging and testing purposes
  @PostMapping(path="/deleteAll") 
  public @ResponseBody String deleteAllUsers () {
    userRepository.deleteAll();
    return "All users deleted";
  }



  // --- admins can add and delete items from the shop with these 2 endpoints
  @PostMapping(path="/addItemToShop") 
  public @ResponseBody String addItem (@RequestParam String name
  , @RequestParam String description
  , @RequestParam String price
  , @RequestParam String picture) {

    Item i = new Item();
    i.setName(name);
    i.setDescription(description);
    i.setPrice(price);
    i.setPicture(picture);
    i.setInWhoseCart("shop");
    itemRepository.save(i);
    return "Saved";
  }

  @PostMapping(path="/deleteItemFromShop")
  public @ResponseBody String deleteItem (@RequestParam Long id) {
    itemRepository.deleteById(id);
    return "Deleted";
  }



  // --- users can add and delete items from their cart with these 2 endpoints
  @PostMapping(path="/addItemToCart") 
  public @ResponseBody String addItemToCart (@RequestParam Long itemId
  , @RequestParam String username) {
    itemRepository.setWhoseCart(username, itemId);
    return "Added to cart";
  }
  
  @PostMapping(path="/deleteItemFromCart")
  public @ResponseBody String deleteItemFromCart (@RequestParam Long itemId) {
    itemRepository.setWhoseCart("shop", itemId);
    return "Deleted from cart";
  }
}