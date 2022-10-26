package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;

@DataJpaTest
@Import(SearchServiceImpl.class)
class SearchServiceImplTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SearchServiceImpl searchService;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("Поиск без учета категорий")
    @Sql("classpath:test.sql")
    @Test
    void testSearchWithoutCategories() {
        SearchParams params = SearchParams.builder()
                .ids(Set.of(1L, 2L))
                .search("B")
                .build();

        List<Item> result = searchService.search(params, PageRequest.of(0, 10));

        Assertions.assertThat(result).hasSize(2);

        // For debug
        result.forEach(item -> {
            System.out.println(item.getId() + " : " + item.getName());
        });
    }

    @DisplayName("Поиск c учетом категорий")
    @Sql("classpath:test.sql")
    @Test
    void testSearchWithCategories() {
        SearchParams params = SearchParams.builder()
                .search("B")
                .categoryName("Light")
                .build();

        List<Item> result = searchService.search(params, PageRequest.of(0, 10));

        Assertions.assertThat(result).hasSize(2);

        // For debug
        result.forEach(item -> {
            System.out.println(item.getId() + " : " + item.getName());
        });
    }
}