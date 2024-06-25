/*
package com.bbva.eipi.batch;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.StepRunner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

// Test for batch process EIPIJ005-01-ES


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/batch/beans/EIPIJ005-01-ES-beans.xml","classpath:/META-INF/spring/jobs-EIPIJ005-01-ES-runner-context.xml", "classpath:/META-INF/spring/batch/jobs/jobs-EIPIJ005-01-ES-context.xml" })
public class EIPIJ00501ESFunctionalTest implements ApplicationContextAware {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private StepRunner stepRunner;

	private ApplicationContext context;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobRepository jobRepository;

	@Before
	public void setUp() {
		jdbcTemplate.update("create table TESTS (ID integer, NAME varchar(40))");
		stepRunner = new StepRunner(jobLauncher, jobRepository);
	}

	@After
	public void tearDown() {
		this.jdbcTemplate.update("drop table TESTS");
	}

	@Test
	public void testTasklet() {
		Step step = (Step) context.getBean("s2");
		assertEquals(BatchStatus.COMPLETED, stepRunner.launchStep(step).getStatus());
		assertEquals(2, jdbcTemplate.queryForObject("SELECT ID from TESTS where NAME = 'SampleTasklet2'", Integer.class).intValue());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

}
*/
