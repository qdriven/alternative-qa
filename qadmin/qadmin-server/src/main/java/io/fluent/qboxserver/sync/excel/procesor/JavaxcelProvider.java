package io.fluent.qboxserver.sync.excel.procesor;

import com.github.javaxcel.Javaxcel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


/**
 * javaxcel provider based on: https://github.com/javaxcel/javaxcel-core
 * image: https://poi.apache.org/components/spreadsheet/images/ss-features.png
 */
public class JavaxcelProvider implements ExcelProvider {
  Javaxcel instance = Javaxcel.newInstance();

  @Override
  public <T> List<T> read(String path, Class<T> beanType, ExcelOption excelOption) {

    try {
      try (Workbook wb = WorkbookFactory.create(new File(path))) {
        if (beanType.getSimpleName().equalsIgnoreCase("Map")) {
           throw new RuntimeException("Map class is not supported,please use readAsMap method");
        } else {
          return this.instance.reader(wb, beanType).read();
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public  List<Map<String,Object>> readForMap(String path, ExcelOption excelOption) {
    try {
      try (Workbook wb = WorkbookFactory.create(new File(path))) {
           return this.instance.reader(wb).read();
        }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public <T> void write(String path, Class<T> beanType, List<T> data, ExcelOption excelOption) {
    try {
      try (OutputStream out = new FileOutputStream(path)) {
        Workbook wb = new SXSSFWorkbook();
        this.instance.writer(wb,beanType).write(out,data);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
