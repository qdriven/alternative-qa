package io.fluent.qboxserver.api.proxy;

import cn.hutool.core.bean.BeanUtil;
import io.fluent.qboxserver.api.repo.ApiRepo;
import org.springframework.stereotype.Component;
import xyz.erupt.jpa.model.MetaDataProxy;
import xyz.erupt.jpa.model.MetaModel;

import javax.annotation.Resource;
import java.util.Locale;


@Component
public class ApiDataProxy extends MetaDataProxy {
  @Resource
  ApiRepo apiRepo;
  @Override
  public void beforeAdd(MetaModel metaModel) {
    super.beforeAdd(metaModel);
    String moduleName = BeanUtil.getProperty(metaModel, "moduleName");
    String apiType = "vRPC";
    if (!moduleName.isEmpty() && moduleName.toLowerCase(Locale.ROOT).endsWith("api")) {
      apiType = "API";
    }

    BeanUtil.setFieldValue(metaModel, "type", apiType);

    //TODO: api client version, and update files to save.
  }
}
