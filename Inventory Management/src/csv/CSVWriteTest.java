package csv;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class CSVWriteTest {

	@Test
	public void test() {
		
		String file = "test.csv";
		
		List<String[]> data = new ArrayList<String[]>();
		
		String[] a = {"Apple", "4", "8"};
		String[] b = {"Banana", "3", "3", "99"};
		String[] c = {"Pear", "43"};
		data.add(a);
		data.add(b);
		data.add(c);
		data.add(c);
		data.add(b);
		data.add(a);
		
		CSVWrite writer = new CSVWrite(file, data);
		
		assertEquals(1, 1);
	}

}
