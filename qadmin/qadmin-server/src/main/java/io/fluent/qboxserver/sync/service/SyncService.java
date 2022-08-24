package io.fluent.qboxserver.sync.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
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
import xyz.erupt.core.prop.EruptProp;

import javax.annotation.Resource;
import java.util.List;


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
  @Resource
  private EruptProp eruptProp;

  @Transactional
  public void syncTestCases(String fileName) {
    String excelPath = eruptProp.getUploadPath() + fileName;
    List<TestCaseExcelModel> testCases = XlsX.create().getProvider().read(
      excelPath, TestCaseExcelModel.class, null
    );

    for (TestCaseExcelModel testCase : testCases) {
      TestCase tc = BeanUtil.toBean(testCase, TestCase.class);
      ProductMeta productMeta = productMetaRepo.findProductMetaByName(testCase.getProductName());
      if (productMeta != null) {
        tc.setProductId(productMeta.getId());
        ProductMeta moduleMeta = productMetaRepo.findProductMetaByName(testCase.getModuleName());
        if (moduleMeta != null) {
          tc.setModuleId(moduleMeta.getId());
        } else {
          ProductMeta newModule = createNewModule(testCase, productMeta);
          tc.setModuleId(newModule.getId());
        }
      } else {
        ProductMeta pm = new ProductMeta();
        pm.setName(testCase.getProductName());
        pm.setDetails(testCase.getProductName());
        pm.setMetaType("产品");
        productMetaRepo.save(pm);
        tc.setProductId(pm.getId());
        ProductMeta newModule = createNewModule(testCase, pm);
        tc.setModuleId(newModule.getId());
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
  public void syncRemoteService(String fileName) {
    String excelPath = eruptProp.getUploadPath() + fileName;
    List<RemoteServiceExcelModel> services = XlsX.readObjects(excelPath, RemoteServiceExcelModel.class);
    for (RemoteServiceExcelModel service : services) {

      ProductMeta meta = productMetaRepo.findProductMetaByName(service.getProduct().toUpperCase());
      if (meta == null)
        throw new RuntimeException("product %s doesn't exist,please check it".formatted(service.getProduct()));
      RemoteService rs = remoteServiceRepo.findRemoteServiceByEndpointAndServiceMethod(
        service.getUri(), service.getMethod()
      );
      if (rs == null) rs = new RemoteService();
      rs.setName(service.getMethod());
      rs.setModuleName(service.getService());
      rs.setEndpoint(service.getUri());
      rs.setProductId(meta.getId());
      rs.setServiceName(service.getService());
      setServiceType(service, rs);

      rs.setServiceMethod(service.getMethod());
      rs.setHttpMethod(service.getHttpMethod());
      auditDataEnhancer.enhanceTimeAndUserAuditData(rs);
      //Todo: add to API history
      remoteServiceRepo.save(rs);
    }
  }

  private static void setServiceType(RemoteServiceExcelModel service, RemoteService rs) {
    if (StrUtil.isBlank(service.getType())) {
      if (service.getService().endsWith("Api")) {
        rs.setType("API");
      } else {
        rs.setType("VRPC");
      }
    } else {
      rs.setType(service.getType());
    }
  }


  //TODO: sync module name in async way
}
