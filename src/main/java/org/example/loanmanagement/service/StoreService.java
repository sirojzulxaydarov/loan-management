package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;


    public void saveStore(Store store) {
        storeRepository.save(store);
    }

    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    public void updateStore(Integer storeId, Store store) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isPresent()) {
            Store storeToUpdate = storeOptional.get();
            storeToUpdate.setName(store.getName());
            storeToUpdate.setAddress(store.getAddress());
            storeToUpdate.setPhone(store.getPhone());
            storeRepository.save(storeToUpdate);
        }
    }

    public Optional<Store> getStoreById(Integer id) {
        return storeRepository.findById(id);
    }

    public void deleteStoreById(Integer id) {
        storeRepository.deleteById(id);
    }
}
