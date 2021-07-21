package com.ecommerce.securedControllers;

import com.ecommerce.database.Item;
import com.ecommerce.database.ItemRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminHomeController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/admin_home")
	public ModelAndView greeting() {
        ArrayList<Long> idsForDisplay = new ArrayList<Long>();
        ArrayList<String> itemsForDisplay = new ArrayList<String>();
        ArrayList<Long> yourIdsForDisplay = new ArrayList<Long>();
        ArrayList<String> yourItemsForDisplay = new ArrayList<String>();

        // get all the items, for the items list
        Iterable<Item> items = itemRepository.getShopItems();
        for (Item i : items) {
            if (itemsForDisplay.indexOf(i.getName()) == -1) {
                itemsForDisplay.add(i.getName());
                idsForDisplay.add(i.getId());
            }
        }

        // get the items of one particular user
        Iterable<Item> yourItems = itemRepository.getUsersItems("user", "user,user");
        for (Item ii : yourItems) {
            System.out.println(ii);
            if (ii.getInWhoseCart() != "shop") {
                yourItemsForDisplay.add(ii.getName());
                yourIdsForDisplay.add(ii.getId());
            }
        }


		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_home");
        modelAndView.addObject("items", itemsForDisplay);
        modelAndView.addObject("ids", idsForDisplay);

        modelAndView.addObject("yourItems", yourItemsForDisplay);
        modelAndView.addObject("yourIds", yourIdsForDisplay);       
        return modelAndView;
        
	}

    @PostMapping("/admin_home")
    public ModelAndView deleteFile(@RequestParam("id") Long id
    , @RequestParam String removeFrom) {

        // This endpoint is used to remove items from the shop,
        // as well as from the user's cart.
        // Therefore, we need to specify where we are removing the item from

        if (removeFrom.equals("shop")) {
            itemRepository.deleteById(id);
        } else {
              itemRepository.setWhoseCart("shop", id);
        }


        ArrayList<Long> idsForDisplay = new ArrayList<Long>();
        ArrayList<String> itemsForDisplay = new ArrayList<String>();
        ArrayList<Long> yourIdsForDisplay = new ArrayList<Long>();
        ArrayList<String> yourItemsForDisplay = new ArrayList<String>();

        // get all the items, for the items list
        Iterable<Item> items = itemRepository.getShopItems();
        for (Item i : items) {
            if (itemsForDisplay.indexOf(i.getName()) == -1) {
                itemsForDisplay.add(i.getName());
                idsForDisplay.add(i.getId());
            }
        }

        // get the items of one particular user
        Iterable<Item> yourItems = itemRepository.getUsersItems("user", "user,user");
        for (Item ii : yourItems) {
            yourItemsForDisplay.add(ii.getName());
            yourIdsForDisplay.add(ii.getId());
        }


		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_home");
        modelAndView.addObject("items", itemsForDisplay);
        modelAndView.addObject("ids", idsForDisplay);

        modelAndView.addObject("yourItems", yourItemsForDisplay);
        modelAndView.addObject("yourIds", yourIdsForDisplay);       
        return modelAndView;
    }
}
