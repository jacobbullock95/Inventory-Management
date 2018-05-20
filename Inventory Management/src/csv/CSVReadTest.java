package csv;

import static org.junit.Assert.*;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class CSVReadTest {
	
	CSVRead reader;
	

	@Test
	public void csvReadTestA() throws IOException {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("cost");
		headers.add("sellPrice");
		headers.add("reorderPoint");
		headers.add("reorderAmount");
		headers.add("temperature");
		
//		String file = "../../../assignment_files/item_properties.csv";
		String file = "C://Users//Jesse Haviland//OneDrive//University//Year 4//CAB302//Inventory Management//assignment_files/item_properties.csv";
		
		reader = new CSVRead(file, headers);
				
		List<String[]> result = reader.getResult();
		
		System.out.println(result);
		
		assertEquals(1, 1);
	}
	
	
	@Test
	public void csvReadTestB() throws IOException {
		
		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("quantity");
		
//		String file = "../../../assignment_files/item_properties.csv";
		String file = "C://Users//Jesse Haviland//OneDrive//University//Year 4//CAB302//Inventory Management//assignment_files/manifest.csv";
		
		reader = new CSVRead(file, headers);
		
		List<String[]> result = reader.getResult();
		
		System.out.println(result);
		
		assertEquals(1, 1);
	}

}
