package io.fluent.qboxserver.sync.service;

import cn.hutool.core.bean.BeanUtil;
import io.fluent.qboxserver.api.model.RemoteService;
import io.fluent.qboxserver.api.repo.RemoteServiceRepo;
import io.fluent.qboxserver.common.data.AuditDataEnhancer;
import io.fluent.qboxserver.product.model.ProductMeta;
import io.fluent.qboxserver.product.repo.ProductMetaRepo;
import io.fluent.qboxserver.sync.excel.RemoteServiceExcelModel;
import io.fluent.qboxserver.sync.excel.TestCaseExcelModel;
import io.fluent.qboxserver.sync.excel.procesor.XlsX;
import io.fluent.qboxserver.testcase.model.TestCase;
import io.fluent.qboxserver.testcase.repo.TestCaseRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@Service
public class SyncService {
  @Resource
  private TestCaseRepo testCaseRepo;
  @Resource
  private ProductMetaRepo productMetaRepo;

  @Resource
  private AuditDataEnhancer auditDataEnhancer;

  @Resource
  private RemoteServiceRepo remoteServiceRepo;

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


  @Transactional
  public void syncRemoteService(String excelPath){
    List<RemoteServiceExcelModel> services = XlsX.readObjects(excelPath,RemoteServiceExcelModel.class);
    for (RemoteServiceExcelModel service : services) {
      var rs = new RemoteService();
      ProductMeta meta = productMetaRepo.findProductMetaByName(service.getProduct().toUpperCase());
      if(meta==null) throw new RuntimeException("product %s doesn't exist,please check it".formatted(service.getProduct()));
      rs.setName(service.getMethod());
      rs.setModuleName(service.getService());
      rs.setEndpoint(service.getUri());
      rs.setProductId(meta.getId());
      rs.setServiceName(service.getService());
      String apiType = "vRPC";
      if (!service.getService().isEmpty() && service.getService().toLowerCase(Locale.ROOT).endsWith("api")) {
          apiType = "API";
      }
      rs.setType(apiType);
      rs.setServiceMethod(service.getMethod());
      rs.setHttpMethod(service.getHttpMethod());
      auditDataEnhancer.enhanceTimeAndUserAuditData(rs);
      remoteServiceRepo.save(rs);
    }
  }
}
