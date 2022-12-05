package com.cmpe202project;

import com.cmpe202project.implementations.chainofresponsibility.AmExCardHandlerTests;
import com.cmpe202project.implementations.chainofresponsibility.DiscoverCardHandlerTests;
import com.cmpe202project.implementations.chainofresponsibility.MasterCardHandlerTests;
import com.cmpe202project.implementations.chainofresponsibility.VisaCardHandlerTests;
import com.cmpe202project.implementations.strategy.CsvParserTests;
import com.cmpe202project.implementations.strategy.JsonParserTests;
import com.cmpe202project.implementations.strategy.TextParserTests;
import com.cmpe202project.implementations.strategy.XmlParserTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
		AmExCardHandlerTests.class,
		DiscoverCardHandlerTests.class,
		MasterCardHandlerTests.class,
		VisaCardHandlerTests.class,
		CsvParserTests.class,
		JsonParserTests.class,
		XmlParserTests.class,
		TextParserTests.class
})

public class TestSuite {
}
