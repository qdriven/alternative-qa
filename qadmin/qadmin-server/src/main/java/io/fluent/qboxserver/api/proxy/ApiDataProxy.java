package io.fluent.qboxserver.api.proxy;

import cn.hutool.core.bean.BeanUtil;
import xyz.erupt.jpa.model.MetaDataProxy;
import xyz.erupt.jpa.model.MetaModel;

import java.util.Locale;


public class ApiDataProxy extends MetaDataProxy {

  @Override
  public void beforeAdd(MetaModel metaModel) {
    super.beforeAdd(metaModel);
    String moduleName = BeanUtil.getProperty(metaModel, "moduleName");
    String apiType = "vRPC";
    if (!moduleName.isEmpty() && moduleName.toLowerCase(Locale.ROOT).endsWith("api")) {
      apiType = "API";
    }

    BeanUtil.setFieldValue(metaModel, "type", apiType);
  }
}
