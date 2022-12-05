package com.cmpe202project.implementations.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.cmpe202project.implementations.strategy.FileContext;
import com.cmpe202project.models.InputFileObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TextParserTests {
	private FileContext fileContext = new FileContext();

	@Test
	public void doReadFileTextTestCase() throws Exception {
		Path path = Paths.get("src/test/resources/input.txt");
		System.out.println("AbsolutePath of File "+ path.toAbsolutePath().toString());
		List<InputFileObject>
				txtFileObjects = fileContext.doReadFile(path.toAbsolutePath().toString());
		Assert.assertNull(txtFileObjects);
	}
}
