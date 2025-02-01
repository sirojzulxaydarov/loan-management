package org.example.loanmanagement.controller;

import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.entity.User;
import org.example.loanmanagement.service.StoreService;
import org.example.loanmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService; // UserService ni qo'shing

    @GetMapping()
    public String listStores(Model model) {
        model.addAttribute("stores", storeService.getAllStore());
        return "listStore";
    }

    @GetMapping("/add")
    public String addStore(Model model) {
        model.addAttribute("store", new Store());
        model.addAttribute("users", userService.getAllUsers()); // Foydalanuvchilar ro'yxatini modelga qo'shish
        return "addStore";
    }

    @PostMapping("add")
    public String addStore(@ModelAttribute("store") Store store, Model model) {
        try {
            // Foydalanuvchini tanlash (misol uchun, birinchi foydalanuvchini tanlaymiz)
            if (store.getUser() == null || store.getUser().getId() == null) {
                throw new IllegalArgumentException("Foydalanuvchi tanlanmadi!");
            }

            // Foydalanuvchini bazadan topish
            User user = userService.getUserById(store.getUser().getId());
            if (user == null) {
                throw new IllegalArgumentException("Foydalanuvchi topilmadi!");
            }

            // Store obyektiga user ni biriktirish
            store.setUser(user);

            // Store ni saqlash
            storeService.saveStore(store);
            return "redirect:/api/stores";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("users", userService.getAllUsers()); // Foydalanuvchilar ro'yxatini qayta yuklash
            return "addStore"; // Xatolik bilan formani qayta ko'rsatish
        }
    }

    @GetMapping("/edit/{id}")
    public String editStore(@PathVariable("id") Integer id, Model model) {
        Store store = storeService.getStoreById(id)
                .orElseThrow(() -> new IllegalArgumentException("Store topilmadi!"));
        model.addAttribute("store", store);
        model.addAttribute("users", userService.getAllUsers()); // Foydalanuvchilar ro'yxatini modelga qo'shish
        return "editStore";
    }

    @PostMapping("/edit/{id}")
    public String editStore(@PathVariable("id") Integer id, @ModelAttribute("store") Store store, Model model) {
        try {
            // Foydalanuvchini tanlash (misol uchun, birinchi foydalanuvchini tanlaymiz)
            if (store.getUser() == null || store.getUser().getId() == null) {
                throw new IllegalArgumentException("Foydalanuvchi tanlanmadi!");
            }

            // Foydalanuvchini bazadan topish
            User user = userService.getUserById(store.getUser().getId());
            if (user == null) {
                throw new IllegalArgumentException("Foydalanuvchi topilmadi!");
            }

            // Store obyektiga user ni biriktirish
            store.setUser(user);

            // Store ni yangilash
            storeService.updateStore(id, store);
            return "redirect:/api/stores";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("users", userService.getAllUsers()); // Foydalanuvchilar ro'yxatini qayta yuklash
            return "editStore"; // Xatolik bilan formani qayta ko'rsatish
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStore(@PathVariable("id") Integer id) {
        storeService.deleteStoreById(id);
        return "redirect:/api/stores";
    }
}
