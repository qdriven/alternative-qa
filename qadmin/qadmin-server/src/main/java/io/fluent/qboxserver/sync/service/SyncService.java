package io.fluent.qboxserver.sync.service;

import cn.hutool.core.bean.BeanUtil;
import io.fluent.qboxserver.product.model.ProductMeta;
import io.fluent.qboxserver.product.repo.ProductMetaRepo;
import io.fluent.qboxserver.sync.excel.TestCaseExcelModel;
import io.fluent.qboxserver.sync.excel.procesor.XlsX;
import io.fluent.qboxserver.testcase.model.TestCase;
import io.fluent.qboxserver.testcase.repo.TestCaseRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SyncService {
  @Resource
  private TestCaseRepo testCaseRepo;
  @Resource
  private ProductMetaRepo productMetaRepo;

  @Transactional
  public void syncTestCases(String excelPath){
    List<TestCaseExcelModel> testCases = XlsX.create().getProvider().read(
      excelPath, TestCaseExcelModel.class,null
    );

    for (TestCaseExcelModel testCase : testCases) {
      TestCase tc = BeanUtil.toBean(testCase,TestCase.class);
      ProductMeta productMeta = productMetaRepo.findProductMetaByName(testCase.getProductName());
      if(productMeta!=null){
        tc.setProduct(productMeta);
        ProductMeta moduleMeta = productMetaRepo.findProductMetaByName(testCase.getModuleName());
        if(moduleMeta!=null){
          tc.setModule(moduleMeta);
        }else{
          ProductMeta newModule = createNewModule(testCase, productMeta);
          tc.setModule(newModule);
        }
      }else{
        ProductMeta pm = new ProductMeta();
        pm.setName(testCase.getProductName());
        pm.setDetails(testCase.getProductName());
        pm.setMetaType("产品");
        productMetaRepo.save(pm);
        tc.setProduct(pm);
        ProductMeta newModule = createNewModule(testCase, pm);
        tc.setModule(newModule);
      }
      testCaseRepo.save(tc);
    }
  }

  private ProductMeta createNewModule(TestCaseExcelModel testCase, ProductMeta pm) {
    ProductMeta newModule = new ProductMeta();
    newModule.setParent(pm);
    newModule.setName(testCase.getModuleName());
    newModule.setDetails(testCase.getModuleName());
    newModule.setMetaType("模块");
    productMetaRepo.save(newModule);
    return newModule;
  }

}
