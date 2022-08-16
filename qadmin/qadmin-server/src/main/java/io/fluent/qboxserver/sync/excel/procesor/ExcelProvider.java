package io.fluent.qboxserver.sync.excel.procesor;



import java.util.List;
import java.util.Map;

public interface ExcelProvider {
  <T> List<T> read(String path, Class<T> beanType, ExcelOption excelOption);

  List<Map<String, Object>> readForMap(String path, ExcelOption excelOption);

  <T> void  write(String path, Class<T> beanType, List<T> data, ExcelOption excelOption);

  default <T> List<T> readWithHandlers(String path, Class<T> beanType, ExcelOption excelOption, ExcelHandler... handlers) {
    throw new ExcelException("Not Implemented Yet");
  }

  default <T> void writeWithHandlers(String path, Class<T> beanType, ExcelOption excelOption, ExcelHandler... handlers) {
    throw new ExcelException("Not Implemented Yet");
  }

}
