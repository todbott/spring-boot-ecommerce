package com.ecommerce.securedControllers;

import java.io.IOException;
import java.util.Base64;

import com.ecommerce.database.Item;
import com.ecommerce.database.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    @Autowired
    private ItemRepository itemRepository;
    
    @GetMapping("/upload")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file
    , @RequestParam("itemName") String name
    , @RequestParam("description") String description
    , @RequestParam("price") String price
    , @RequestParam("token") String token
    , RedirectAttributes attributes) throws IOException {


        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("upload");
            return "redirect:/admin_home?Authorization=" + token;
        }

        byte[] fileContent = file.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        // put the base64 string into the database with everything else
        Item ii = new Item();
        ii.setName(name);
        ii.setDescription(description);
        ii.setPrice(price);
        ii.setPicture(encodedString);
        ii.setInWhoseCart("shop");
        itemRepository.save(ii);
        
       
        return "redirect:/admin_home?Authorization=" + token;

    }

}
