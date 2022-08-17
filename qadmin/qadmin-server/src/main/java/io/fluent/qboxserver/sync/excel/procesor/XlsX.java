package io.fluent.qboxserver.sync.excel.procesor;

import io.fluent.qboxserver.sync.excel.RemoteServiceExcelModel;
import lombok.Data;

import java.util.List;

@Data
public class XlsX {
  ExcelProvider provider;
  ExcelOption option;

  public XlsX(ExcelProvider provider, ExcelOption option) {
    this.provider = provider;
    this.option = option;
  }

  public static XlsX create() {
    return new XlsX(new JavaxcelProvider(), new ExcelOption());
  }

  public static <T> List<T> readObjects(String excelPath, Class<T> clazz) {
    return XlsX.create().getProvider().read(
      excelPath, clazz, null
    );
  }
}
