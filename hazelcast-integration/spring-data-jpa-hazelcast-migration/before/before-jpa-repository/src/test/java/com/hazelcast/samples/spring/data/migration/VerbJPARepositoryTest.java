package com.hazelcast.samples.spring.data.migration;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

/**
 * <P>Test CRUD and query operations against a JPA database.
 * </P>
 */
@DataJpaTest
@Slf4j
public class VerbJPARepositoryTest extends AbstractVerbRepositoryTest {

	@Autowired
	private VerbJPARepository verbJPARepository;
	
	@Before
	public void setUp() {
		super.setUp(this.verbJPARepository, log);
	}
	
	@Test
	public void queryEnglish() {
		this.verbJPARepository.save(invite);
		
		Verb invite2 = this.verbJPARepository.findByEnglish("invite");
		log.info("queryEnglish(), read {}", invite2);
		
		assertThat(invite2, not(nullValue()));
		assertThat(invite2, equalTo(invite));
		
		this.verbJPARepository.deleteAll();

		Verb invite3 = this.verbJPARepository.findByEnglish("invite");

		assertThat(invite3, nullValue());
	}
}