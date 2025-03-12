package org.example.loanmanagement.controller;

import org.example.loanmanagement.dto.StoreDto;
import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.service.StoreService;
import org.example.loanmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreDto storeDto) {
        Store store = storeService.createStore(storeDto);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<StoreDto> getStoreByUser(@PathVariable Integer userId) {
        StoreDto storeDto=storeService.getStoreById(userId);
        return ResponseEntity.ok(storeDto);

    }


}
