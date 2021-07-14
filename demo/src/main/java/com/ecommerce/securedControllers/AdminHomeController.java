package com.ecommerce.securedControllers;

import com.ecommerce.database.Item;
import com.ecommerce.database.ItemRepository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

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
        Collection<Item> items = itemRepository.findAll();
        for (Item i : items) {
            if (itemsForDisplay.indexOf(i.getName()) == -1) {
                itemsForDisplay.add(i.getName());
                idsForDisplay.add(i.getId());
            }
        }

        // get the items of one particular user
        Iterable<Item> yourItems = itemRepository.getUsersItems("user");
        for (Item i : yourItems) {
            yourItemsForDisplay.add(i.getName());
            yourIdsForDisplay.add(i.getId());
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
    public ModelAndView deleteFile(@RequestParam("id") Long id) {


        itemRepository.deleteById(id);

        ArrayList<Long> idsForDisplay = new ArrayList<Long>();
        ArrayList<String> itemsForDisplay = new ArrayList<String>();

        Collection<Item> items = itemRepository.findAll();
        for (Item i : items) {
            itemsForDisplay.add(i.getName());
            idsForDisplay.add(i.getId());
        }


		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_home");
        modelAndView.addObject("items", itemsForDisplay);
        modelAndView.addObject("ids", idsForDisplay);
        return modelAndView;
        //return "redirect:/admin_home";

    }
}
