package me.potato.demo.demospringboot.sample.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import me.potato.demo.demospringboot.sample.Sample;

@FeignClient(name="samples", url="http://localhost:8080", path="samples")
public interface SampleClient {
    
    @GetMapping
    CollectionModel<Sample> getSamples(Pageable pageable);

    @PostMapping
    void createSample(@RequestBody Sample sample);
} 