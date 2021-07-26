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
        ArrayList<String> picturesForDisplay = new ArrayList<String>();
        ArrayList<String> quantitiesForDisplay = new ArrayList<String>();

        ArrayList<Long> yourIdsForDisplay = new ArrayList<Long>();
        ArrayList<String> yourItemsForDisplay = new ArrayList<String>();
        ArrayList<String> yourQuantitiesForDisplay = new ArrayList<String>();

        // get all the items, for the items list
        Iterable<Item> items = itemRepository.getShopItems();
        for (Item i : items) {
            if (itemsForDisplay.indexOf(i.getName()) == -1) {
                itemsForDisplay.add(i.getName());
                idsForDisplay.add(i.getId());
                picturesForDisplay.add(i.getPicture());
                quantitiesForDisplay.add(itemRepository.countShopItems(i.getName()).toString());
            }
        }

        // get the items of one particular user
        Iterable<Item> yourItems = itemRepository.getUsersItems("user");
        for (Item ii : yourItems) {
            if (ii.getInWhoseCart() != "shop") {
                if (yourItemsForDisplay.indexOf(ii.getName()) == -1) {
                    yourItemsForDisplay.add(ii.getName());
                    yourIdsForDisplay.add(ii.getId());
                    yourQuantitiesForDisplay.add(itemRepository.countUsersItems("user", ii.getName()).toString());
                }
            }
        }


		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_home");
        modelAndView.addObject("items", itemsForDisplay);
        modelAndView.addObject("ids", idsForDisplay);
        modelAndView.addObject("pictures", picturesForDisplay);
        modelAndView.addObject("quantities", quantitiesForDisplay);

        modelAndView.addObject("yourItems", yourItemsForDisplay);
        modelAndView.addObject("yourIds", yourIdsForDisplay);       
        modelAndView.addObject("yourQuantities", yourQuantitiesForDisplay);  

        return modelAndView;
        
	}

    @PostMapping("/admin_home")
    public String deleteFile(@RequestParam("itemName") String itemName
    , @RequestParam String removeFrom
    , @RequestParam Integer quantity
    , @RequestParam String Authorization) {

        // This endpoint is used to remove items from the shop,
        // as well as from the user's cart.
        // Therefore, we need to specify where we are removing the item from

        if (removeFrom.equals("shop")) {
            itemRepository.deleteByName(itemName);
        } else {
              itemRepository.setWhoseCart("user", "shop", itemName, quantity);
        }

        return "redirect:/admin_home?Authorization=" + Authorization;
    }
}
