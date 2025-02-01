package org.example.loanmanagement.controller;

import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping()
    public String listStores(Model model) {
        List<Store> stores=storeService.getAllStore();
        model.addAttribute("stores", stores);
        return "storeList";
    }
}
