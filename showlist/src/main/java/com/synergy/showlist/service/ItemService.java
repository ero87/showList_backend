package com.synergy.showlist.service;

import com.synergy.showlist.entity.ItemEntity;
import com.synergy.showlist.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    public final int pageSize = 1000;

    @Autowired
    ItemRepository itemRepository;
    public ItemEntity addItem(ItemEntity itemEntity) {
        return itemRepository.save(itemEntity);
    }

    public Page<ItemEntity> getEntities(int pageNumber) {
        return itemRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public int getDataCount() {
        return (int) itemRepository.count();
    }
}
