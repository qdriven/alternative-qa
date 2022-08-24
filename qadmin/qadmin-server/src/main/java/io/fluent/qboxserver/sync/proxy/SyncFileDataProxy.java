package io.fluent.qboxserver.sync.proxy;

import cn.hutool.core.bean.BeanUtil;
import io.fluent.qboxserver.sync.service.SyncService;
import xyz.erupt.core.prop.EruptProp;
import xyz.erupt.core.util.EruptSpringUtil;
import xyz.erupt.jpa.model.MetaDataProxy;
import xyz.erupt.jpa.model.MetaModel;

public class SyncFileDataProxy extends MetaDataProxy {
  private SyncService syncService;
  private EruptProp eruptProp;

  public SyncFileDataProxy() {
    syncService = EruptSpringUtil.getBean(SyncService.class);
    eruptProp = EruptSpringUtil.getBean(EruptProp.class);
  }

  @Override
  public void beforeAdd(MetaModel metaModel) {
    super.beforeAdd(metaModel);
  }

  @Override
  public void afterAdd(MetaModel metaModel) {
    System.out.println("after add something");
    super.afterAdd(metaModel);
    switch (BeanUtil.getProperty(metaModel,"usage").toString()){
      case "TestCase":syncService.syncTestCases(BeanUtil.getProperty(metaModel,"attachment"));
      case "API": syncService.syncRemoteService(BeanUtil.getProperty(metaModel,"attachment"));
      default:
        System.out.println("do nothing");
    }
  }
}
