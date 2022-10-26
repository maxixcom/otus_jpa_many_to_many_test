package com.example.demo.service;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder(toBuilder = true)
@Getter
public class SearchParams {
    private final Set<Long> ids;
    private final String search;
    private final String categoryName;
}
