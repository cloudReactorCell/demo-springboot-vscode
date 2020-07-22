package me.potato.demo.demospringboot.sample;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface SampleRepository extends PagingAndSortingRepository<Sample, Long> {
    
}