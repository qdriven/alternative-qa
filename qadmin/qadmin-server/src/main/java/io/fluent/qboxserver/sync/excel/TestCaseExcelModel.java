package io.fluent.qboxserver.sync.excel;

import com.github.javaxcel.annotation.ExcelColumn;
import lombok.Data;

@Data
public class TestCaseExcelModel {
  private String productName;
  private String moduleName;
  private String feature;
  private String summary;
  private String priority;
  private String precondition;
  private String steps;
  private String expectedResult;
}
