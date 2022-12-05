package com.cmpe202project.implementations.chainofresponsibility;

import com.cmpe202project.implementations.chainofresponsibility.MasterCardHandler;
import com.cmpe202project.models.CreditCardType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.jupiter.api.Assertions.*;
@RunWith(JUnit4.class)
public class MasterCardHandlerTests {

	private MasterCardHandler masterCardHandler = new MasterCardHandler();

	@Test
	public void validateCardPositiveTestCase() {
		Assert.assertEquals(masterCardHandler.validateCard("5410000000000000"), CreditCardType.MasterCard);
	}

	@Test
	public void validateCardNegativeTestCase() {
		Assert.assertNotEquals(masterCardHandler.validateCard("5410000000000"),CreditCardType.MasterCard);
	}

	@Test
	public void isValidPositiveTestCase() {
		Assert.assertTrue(masterCardHandler.isValid("5410000000000000"));

	}

	@Test
	public void isValidNegativeTestCase() {
		Assert.assertFalse(masterCardHandler.isValid("98900000000000000"));
	}
}