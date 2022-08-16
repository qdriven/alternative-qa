package io.fluent.qboxserver.sync.excel.procesor;

import lombok.Data;

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
}
