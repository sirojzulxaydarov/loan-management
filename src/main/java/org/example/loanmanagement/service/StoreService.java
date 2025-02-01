package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    private List<Store> getAllStore(){
        return storeRepository.findAll();
    }

    public Store getStoreById(Integer id) {
        return storeRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Store with id " + id + " not found")
        );
    }

    public void deleteStoreById(Integer id) {
        storeRepository.deleteById(id);
    }
}
