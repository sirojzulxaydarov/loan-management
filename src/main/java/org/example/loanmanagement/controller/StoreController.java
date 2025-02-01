package org.example.loanmanagement.controller;

import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping()
    public String listStores(Model model) {
        model.addAttribute("stores", storeService.getAllStore());
        return "listStore";
    }

    @GetMapping("/add")
    public String addStore(Model model) {
        model.addAttribute("store", new Store());
        return "addStore";
    }

    @PostMapping("add")
    public String addStore(@ModelAttribute("store") Store store) {
        storeService.saveStore(store);
        return "redirect:/api/stores";
    }

    @GetMapping("/edit/{id}")
    public String editStore(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("store", storeService.getStoreById(id));
        return "editStore";
    }

    @PostMapping("/edit/{id}")
    public String editStore(@PathVariable("id") Integer id, @ModelAttribute("store") Store store) {
        storeService.updateStore(id, store);
        return "redirect:/api/stores";
    }

    @GetMapping("/delete/{id}")
    public String deleteStore(@PathVariable("id") Integer id) {
        storeService.deleteStoreById(id);
        return "redirect:/api/stores";
    }

}
