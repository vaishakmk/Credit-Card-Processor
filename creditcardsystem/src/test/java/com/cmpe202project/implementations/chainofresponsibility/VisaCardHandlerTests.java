package com.cmpe202project.implementations.chainofresponsibility;

import com.cmpe202project.models.CreditCardType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.jupiter.api.Assertions.*;
@RunWith(JUnit4.class)
public class VisaCardHandlerTests {

	private VisaCardHandler visaCardHandler = new VisaCardHandler();

	@Test
	public void validateCardPositiveTestCase() {
		Assert.assertEquals(visaCardHandler.validateCard("4120000000000"), CreditCardType.Visa);
	}

	@Test
	public void validateCardNegativeTestCase() {
		Assert.assertEquals(visaCardHandler.validateCard("9710000000000"), CreditCardType.Invalid);
	}

	@Test
	public void isValidPositiveTestCase() {
		Assert.assertTrue(visaCardHandler.isValid("4120000000000"));

	}

	@Test
	public void isValidNegativeTestCase() {
		Assert.assertFalse(visaCardHandler.isValid("98900000000000000"));
	}

}