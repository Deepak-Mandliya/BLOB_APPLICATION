package com.cg.rewards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RewardsServiceTest {

	private final RewardsService service = new RewardsService(null);

	@Test
	void testCalculatePoints() {
		assertEquals(90, service.calculatePoints(120));
		assertEquals(50, service.calculatePoints(100));
		assertEquals(10, service.calculatePoints(60));
		assertEquals(0, service.calculatePoints(50));
	}
}
