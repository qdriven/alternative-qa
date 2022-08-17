package io.fluent.qboxserver.sync.excel;

import com.github.javaxcel.annotation.ExcelColumn;
import lombok.Data;

@Data
public class RemoteServiceExcelModel {
  @ExcelColumn(name = "product")
  private String product;
  @ExcelColumn(name = "service")
  private String service;
  @ExcelColumn(name = "method")
  private String method;
  @ExcelColumn(name = "httpMethod")
  private String httpMethod="POST";
  @ExcelColumn(name = "uri")
  private String uri;
}
