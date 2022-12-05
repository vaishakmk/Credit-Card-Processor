package com.cmpe202project.implementations.chainofresponsibility;

import com.cmpe202project.models.CreditCardType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DiscoverCardHandlerTests {
	private DiscoverCardHandler discoverCardHandler = new DiscoverCardHandler();

	@Test
	public void validateCardPositiveTestCase() {
		Assert.assertEquals(discoverCardHandler.validateCard("6011000000000000"), CreditCardType.Discover);
	}

	@Test
	public void validateCardNegativeTestCase() {
		Assert.assertNull(discoverCardHandler.validateCard("901000000000000"));
	}

	@Test
	public void isValidPositiveTestCase() {
		Assert.assertTrue(discoverCardHandler.isValid("6011000000000000"));

	}

	@Test
	public void isValidNegativeTestCase() {
		Assert.assertFalse(discoverCardHandler.isValid("991000000000000"));
	}
}