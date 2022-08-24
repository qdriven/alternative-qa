package io.fluent.qboxserver.sync.handler;

import org.springframework.stereotype.Service;
import xyz.erupt.core.annotation.EruptHandlerNaming;
import xyz.erupt.job.handler.EruptJobHandler;

@Service
@EruptHandlerNaming(value = "Python 后台脚本")
//TODO: call back integrate with FEISHU
public class PythonScriptJobHandler implements EruptJobHandler {
  @Override
  public String exec(String code, String param) {
    System.out.println("start job");
    System.out.println("start execute python script");
    return null;
  }

  @Override
  public void success(String result, String param) {
    EruptJobHandler.super.success(result, param);
  }

  @Override
  public void error(Throwable throwable, String param) {
    EruptJobHandler.super.error(throwable, param);
  }
}
