package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.specification.ItemSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {
    private final ItemRepository itemRepository;

    @Override
    public List<Item> search(SearchParams searchParams, Pageable pageable) {
        List<Specification<Item>> specifications = new ArrayList<>();

        // (!) Order is important

        Optional.ofNullable(searchParams.getCategoryName())
                .filter(value -> !value.isBlank())
                .ifPresent(value -> {
                    specifications.add(ItemSpecification.searchByCategoryName(value));
                });

        Optional.ofNullable(searchParams.getIds())
                .filter(ids -> !ids.isEmpty())
                .ifPresent(ids -> {
                    specifications.add(ItemSpecification.searchByIds(ids));
                });

        Optional.ofNullable(searchParams.getSearch())
                .filter(value -> !value.isBlank())
                .ifPresent(value -> {
                    specifications.add(ItemSpecification.searchByName(value));
                });


        if (specifications.isEmpty()) {
            return itemRepository.findAll(pageable).getContent();
        }

        Specification<Item> combinedSpecification = specifications.get(0);

        IntStream
                .range(1, specifications.size())
                .forEach(index -> {
                    Specification.where(combinedSpecification).or(specifications.get(index));
                });

        return itemRepository.findAll(combinedSpecification, pageable).getContent();
    }
}
