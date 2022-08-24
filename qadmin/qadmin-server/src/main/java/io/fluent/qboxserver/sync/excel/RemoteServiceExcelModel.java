package io.fluent.qboxserver.sync.excel;

import com.github.javaxcel.annotation.ExcelColumn;
import com.github.javaxcel.annotation.ExcelIgnore;
import lombok.Data;

@Data
public class RemoteServiceExcelModel {
  @ExcelColumn(name = "product")
  private String product;
  @ExcelColumn(name = "service")
  private String service;
  @ExcelColumn(name = "fullService")
  private String fullService;
  @ExcelColumn(name = "method")
  private String method;
  @ExcelIgnore
  private String httpMethod="POST";
  @ExcelColumn(name = "uri")
  private String uri;
  @ExcelColumn(name = "type")
  private String type;
}
