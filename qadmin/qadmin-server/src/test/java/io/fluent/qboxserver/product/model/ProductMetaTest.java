package io.fluent.qboxserver.product.model;

import cn.hutool.core.lang.UUID;
import com.google.gson.Gson;
import io.fluent.qboxserver.demo.model.complex.Complex;
import io.fluent.qboxserver.testcase.model.TestTask;
import org.junit.jupiter.api.Test;
import xyz.erupt.core.config.GsonFactory;
import xyz.erupt.job.model.EruptJob;

import static org.junit.jupiter.api.Assertions.*;

class ProductMetaTest {
  private final Gson gson = GsonFactory.getGson();

  @Test
  public void testJsonIssue(){
    String jsonStr="{\"testPlanId\":\"731\",\"owner\":\"xxx\",\"testCaseTab\":[{\"id\":\"552\"},{\"id\":\"553\"}],\"status\":\"yyy\",\"isValid\":true,\"id\":751}";
    TestTask ts = gson.fromJson(jsonStr, TestTask.class);
    System.out.println(ts);
    String jStr = "{\"choice\":\"7\",\"tree\":{\"id\":179,\"name\":\"Root\"},\"complexTab\":[],\"articleTab\":[{\"id\":720},{\"id\":719}],\"id\":760}";
    Complex re = gson.fromJson(jStr, Complex.class);
    System.out.println(re);
  }
  @Test
  public void generateUid(){
    for (int i = 0; i < 5; i++) {
      System.out.println( UUID.fastUUID().toString());
//      59a2bdcb-75a8-48e6-8ba0-00164df5bc42
//      ebcc53f1-5b9a-4e20-8c9f-36321fc05344
//      574e0fa1-63c3-42f8-9d73-c7ff7331d7b8
//      dbccb06d-3db4-48d8-a3b1-89769ea118e8
//      5912ed81-a6a7-4da8-8408-b434f64d9af3
    }
    String jStr="{\"name\":\"test\",\"cron\":\"test\",\"handler\":\"io.fluent.qboxserver.sync.handler.PythonScriptJobHandler\",\"status\":true}";
    EruptJob job = this.gson.fromJson(jStr, EruptJob.class);
    System.out.println(job);
  }
}