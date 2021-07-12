package com.ecommerce.securedControllers;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import com.ecommerce.database.Item;
import com.ecommerce.database.ItemRepository;
import com.google.api.client.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.io.FileUtils;

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
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("upload");
            return modelAndView;
        }

        // normalize the file path
        byte[] fileContent = file.getBytes();
        //byte[] fileContent = FileUtils.readFileToByteArray(new File(fileName));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        // put the base64 string into the database with everything else
        Item i = new Item();
        i.setName("hey");
        i.setDescription("from a test");
        i.setPrice("50.00");
        i.setPicture(encodedString);
        i.setInWhoseCart("shop");
        itemRepository.save(i);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_home");
        return modelAndView;

    }

}
