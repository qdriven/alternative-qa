package io.fluent.qboxserver.sync.excel.procesor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import io.fluent.qboxserver.sync.excel.RemoteServiceExcelModel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class XlsXTest {

  @Test
  void readObjects() {
//    String path = FileUtil.getParent(new File("ccf_api_list.xlsx"))
    String loaderPath = ClassLoaderUtil.getClassLoader().getResource(".").getPath();
    String excelPath = loaderPath+"ccf_api_list.xlsx";
    List<RemoteServiceExcelModel> result = XlsX.readObjects(excelPath, RemoteServiceExcelModel.class);
    System.out.println(result);
  }
}