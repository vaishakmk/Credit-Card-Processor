package com.cmpe202project.implementations.chainofresponsibility;

import java.util.ArrayList;

import com.cmpe202project.models.CreditCardType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class AmExCardHandlerTests {

	private AmExCardHandler amExCardHandler = new AmExCardHandler();

	@Test
	public void validateCardPositiveTestCase() {
		Assert.assertEquals(amExCardHandler.validateCard("341000000000000"), CreditCardType.AmericanExpress);
	}

	@Test
	public void validateCardNegativeTestCase() {
		Assert.assertNotEquals(amExCardHandler.validateCard("901000000000000"),CreditCardType.AmericanExpress);
	}

	@Test
	public void isValidPositiveTestCase() {
		Assert.assertTrue(amExCardHandler.isValid("341000000000000"));

	}

	@Test
	public void isValidNegativeTestCase() {
		Assert.assertFalse(amExCardHandler.isValid("991000000000000"));
	}
}