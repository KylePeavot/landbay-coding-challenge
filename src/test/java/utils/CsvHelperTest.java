package test.java.utils;

import main.java.utils.CsvHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class CsvHelperTest {

    @Test
    public void testCsvHelperReturnsNullWithWrongFilename() {
        assertNull(CsvHelper.getAllLinesFromCsvWithHeaders("badFileName"));
    }


}