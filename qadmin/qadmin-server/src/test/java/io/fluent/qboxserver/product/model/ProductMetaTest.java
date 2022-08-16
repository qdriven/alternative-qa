package io.fluent.qboxserver.product.model;

import cn.hutool.core.lang.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMetaTest {

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
  }
}