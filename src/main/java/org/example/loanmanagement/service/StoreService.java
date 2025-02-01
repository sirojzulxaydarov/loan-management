package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.entity.User;
import org.example.loanmanagement.repository.StoreRepository;
import org.example.loanmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveStore(Store store) {
        // Store obyektidagi user maydoni null bo'lmasligini tekshirish
        if (store.getUser() == null || store.getUser().getId() == null) {
            throw new IllegalArgumentException("User maydoni to'ldirilishi shart!");
        }

        // User ni bazadan topish
        Optional<User> userOptional = userRepository.findById(store.getUser().getId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User topilmadi!");
        }

        // Store obyektiga user ni biriktirish
        store.setUser(userOptional.get());

        // Store ni saqlash
        storeRepository.save(store);
    }

    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    public void updateStore(Integer storeId, Store store) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isPresent()) {
            Store storeToUpdate = storeOptional.get();

            // Yangilanishlar
            storeToUpdate.setName(store.getName());
            storeToUpdate.setAddress(store.getAddress());
            storeToUpdate.setPhone(store.getPhone());

            // Agar user maydoni yangilansa, uni tekshirish
            if (store.getUser() != null && store.getUser().getId() != null) {
                Optional<User> userOptional = userRepository.findById(store.getUser().getId());
                if (userOptional.isEmpty()) {
                    throw new IllegalArgumentException("User topilmadi!");
                }
                storeToUpdate.setUser(userOptional.get());
            }

            storeRepository.save(storeToUpdate);
        } else {
            throw new IllegalArgumentException("Store topilmadi!");
        }
    }

    public Optional<Store> getStoreById(Integer id) {
        return storeRepository.findById(id);
    }

    public void deleteStoreById(Integer id) {
        storeRepository.deleteById(id);
    }
}
