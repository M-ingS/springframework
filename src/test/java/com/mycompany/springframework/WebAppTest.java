package com.mycompany.springframework;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringJUnitWebConfig(locations={
	"classpath:spring/root/*.xml",
	"classpath:spring/dispatcher/*.xml"
})

@Transactional

public abstract class WebAppTest {
}