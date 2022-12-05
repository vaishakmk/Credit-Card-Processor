package com.cmpe202project.implementations.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cmpe202project.implementations.util.Utilities;
import com.cmpe202project.models.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CsvParserTests {

	private FileContext fileContext = new FileContext();

	@Test
	public void doReadFileCSVTestCase() throws Exception {
		Path path = Paths.get("src/test/resources/SampleInput.csv");
		System.out.println("AbsolutePath of File "+ path.toAbsolutePath().toString());
		List<InputFileObject>
				csvFileObjects = fileContext.doReadFile(path.toAbsolutePath().toString());
		Assert.assertEquals(csvFileObjects, getActualList());
	}

	@Test
	public void writeFileTestCase() {
		fileContext.changeStrategy(new CsvParser());
		Path path = Paths.get("src/test/resources/SampleOutput.csv");
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

		InputFileObject inputFileObject = new InputFileObject("5410000000000000",
				Utilities.StringToDate("3/20/2030"),
				"Alice");
		actualList.add(inputFileObject);

		inputFileObject = new InputFileObject("4120000000000",
				Utilities.StringToDate("4/20/2030"),
				"Bob");
		actualList.add(inputFileObject);

		inputFileObject = new InputFileObject("341000000000000",
				Utilities.StringToDate("5/20/2030"),
				"Eve");
		actualList.add(inputFileObject);

		inputFileObject = new InputFileObject("6010000000000000",
				Utilities.StringToDate("6/20/2030"),
				"Richard");
		actualList.add(inputFileObject);

		return actualList;
	}
}