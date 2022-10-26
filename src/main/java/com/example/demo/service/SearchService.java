package com.example.demo.service;

import com.example.demo.domain.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {
    List<Item> search(SearchParams searchParams, Pageable pageable);
}
