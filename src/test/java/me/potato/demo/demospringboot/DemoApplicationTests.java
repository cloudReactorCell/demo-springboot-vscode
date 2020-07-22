package me.potato.demo.demospringboot;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

import me.potato.demo.demospringboot.sample.Sample;
import me.potato.demo.demospringboot.sample.SampleRepository;
import me.potato.demo.demospringboot.sample.client.SampleClient;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class DemoApplicationTests {

	@Autowired
	SampleRepository sampleRepository;

	@Autowired
	SampleClient sampleClient;

	@Test
	void contextLoads() {
	}

	@Test
	void createSamples() {
		sampleRepository.deleteAll();
		sampleRepository.save(Sample.builder().data("data01").build());
		sampleRepository.save(Sample.builder().data("data02").build());
		sampleRepository.save(Sample.builder().data("data02").build());

		long count = sampleRepository.count();
		assertSame(3l, count);

	}

	@Test
	void createSamplesByNetwork() {
		sampleRepository.deleteAll();

		sampleClient.createSample(Sample.builder().data("data01").build());
		sampleClient.createSample(Sample.builder().data("data02").build());
		sampleClient.createSample(Sample.builder().data("data03").build());

		long count = sampleRepository.count();
		assertSame(3l, count);

		CollectionModel<Sample> samples = sampleClient.getSamples(Pageable.unpaged());
		assertSame(3, samples.getContent().size());

	}

}
