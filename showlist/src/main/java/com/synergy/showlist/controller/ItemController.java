package com.synergy.showlist.controller;

import com.synergy.showlist.entity.ItemEntity;
import com.synergy.showlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ItemController {

    @Autowired
    ItemService itemService;
    private List<ItemEntity> entityList = new ArrayList<>();

    @GetMapping("/items/{pageNumber}")
    public ResponseEntity<?> getItemsList(@PathVariable Integer pageNumber) {
        int dataCount = itemService.getDataCount();
        entityList.clear();
        return ResponseEntity.ok(doFilter(pageNumber, dataCount));
    }

    public Map<String, Object> doFilter(Integer pageIndex, int dataCount) {
        Page<ItemEntity> pageableItems = getPageableItems(pageIndex);
        List<ItemEntity> filteredData = pageableItems.get()
                .filter(x -> x.getItemNumber() > 2)
                .collect(Collectors.toList());
        int lastPageIndex = dataCount / itemService.pageSize;
        if (filteredData.isEmpty() && pageIndex <= lastPageIndex) {
            return doFilter(++pageIndex, dataCount);
        }
        if (entityList.size() < 10 && filteredData.size() < 10 && pageIndex <= lastPageIndex) {
            entityList.addAll(filteredData);
            return doFilter(++pageIndex, dataCount);
        }
        entityList.addAll(filteredData);
        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", pageIndex);
        map.put("itemsList", entityList);
        return map;
    }

    public Page<ItemEntity> getPageableItems(Integer pageIndex) {
        return itemService.getEntities(pageIndex);
    }

}
