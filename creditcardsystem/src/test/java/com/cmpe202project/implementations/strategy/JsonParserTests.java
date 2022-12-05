package com.cmpe202project.implementations.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cmpe202project.implementations.util.Utilities;
import com.cmpe202project.models.*;
import com.cmpe202project.implementations.strategy.*;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class JsonParserTests {

	private FileContext fileContext = new FileContext();

	@Test
	public void doReadFileJSONTestCase() throws Exception {
		Path path = Paths.get("src/test/resources/input_file.json");
		System.out.println("AbsolutePath of File "+ path.toAbsolutePath().toString());
		List<InputFileObject>
				jsonFileObjects = fileContext.doReadFile(path.toAbsolutePath().toString());
		Assert.assertEquals(jsonFileObjects, getActualList());
	}

	@Test
	public void writeFileTestCase() {
		fileContext.changeStrategy(new JsonParser());
		Path path = Paths.get("src/test/resources/SampleOutput.json");
		System.out.println("AbsolutePath of File "+ path.toAbsolutePath().toString());
		List<CreditCard> list = new ArrayList<>();
		CreditCard cc = new MasterCC("5400000000000000", new Date(), "Name");
		list.add(cc);
		cc = new VisaCC("4120000000000", new Date(), "Name");
		list.add(cc);
		cc = new DiscoverCC("6011000000000000", new Date(), "Name");
		list.add(cc);
		cc = new AmExCC("341000000000000", new Date(), "Name");
		list.add(cc);
		cc = new InvalidCC("00000", new Date(), "");
		list.add(cc);
		fileContext.doWriteFile(path.toAbsolutePath().toString(), list);
	}

	private List<InputFileObject> getActualList() throws Exception {
		List<InputFileObject> actualList = new ArrayList<>();

		InputFileObject inputFileObject = new InputFileObject("5567894523129089",
				Utilities.StringToDate("08/26"),
				"John Doe");
		actualList.add(inputFileObject);

		return actualList;
	}
}