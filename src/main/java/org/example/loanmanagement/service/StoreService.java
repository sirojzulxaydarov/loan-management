package org.example.loanmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.loanmanagement.dto.StoreDto;
import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.entity.User;
import org.example.loanmanagement.repository.StoreRepository;
import org.example.loanmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public Store createStore(StoreDto storeDto) {

        User user = userRepository.findById(storeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Store store = Store.builder()
                .name(storeDto.getName())
                .address(storeDto.getAddress())
                .phone(storeDto.getPhone())
                .user(user)
                .build();
        return storeRepository.save(store);
    }

    public StoreDto getStoreById(Integer userId) {

        Optional<Store> store = storeRepository.findByUserId(userId);
        if (store.isPresent()) {
            StoreDto storeDto = new StoreDto();
            storeDto.setName(store.get().getName());
            storeDto.setAddress(store.get().getAddress());
            storeDto.setPhone(store.get().getPhone());
            storeDto.setUserId(store.get().getUser().getId());
            return storeDto;
        } else throw new RuntimeException("Store not found");


    }

}
