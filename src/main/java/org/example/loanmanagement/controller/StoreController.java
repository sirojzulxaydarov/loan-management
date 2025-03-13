package org.example.loanmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.loanmanagement.dto.StoreDto;
import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.service.StoreService;
import org.example.loanmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Store> createStore(@RequestBody StoreDto storeDto) {
        Store store = storeService.createStore(storeDto);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StoreDto> getStoreByUser(@PathVariable Integer userId) {
        StoreDto storeDto=storeService.getStoreById(userId);
        return ResponseEntity.ok(storeDto);

    }


}
