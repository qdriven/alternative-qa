package io.fluent.qboxserver.demo.model;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.core.exception.EruptApiErrorTip;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Map;

@Erupt(
  name = "服务层逻辑扩展 @DataProxy （扩展CURD）",
  desc = "提供增、删、改、查、导入、导出、数据初始化等事件触发逻辑接口，相当于传统开发中的 service 层\n" +
    "可以实现如：缓存写入，数据校验，RPC调用，动态赋值等功能 ！）",
  dataProxy = CurdExt.class
)
@Entity
@Table(name = "demo_crud")
@Data
public class CurdExt extends BaseModel implements DataProxy<CurdExt> {

  @EruptField(
    views = @View(title = "名称"),
    edit = @Edit(title = "名称", notNull = true, search = @Search(vague = true))
  )
  private String name;


  @Override
  public void beforeAdd(CurdExt curdExtension) {
    //字段校验
    if ("张三".equals(curdExtension.getName())) {
      throw new EruptApiErrorTip("名称禁止为张三！");
    }
  }

  @Override
  public void afterAdd(CurdExt curdExtension) {
    System.out.println("After add " + curdExtension);
  }

  @Override
  public void beforeUpdate(CurdExt curdExtension) {
    //动态写数据
    curdExtension.setName(curdExtension.getName() + "xxx");
  }

  @Override
  public void afterUpdate(CurdExt curdExtension) {
    System.out.println("After Update " + curdExtension);
  }

  @Override
  public void beforeDelete(CurdExt curdExtension) {
    System.out.println("Before Delete " + curdExtension);
  }

  @Override
  public void afterDelete(CurdExt curdExtension) {
    System.out.println("After Delete " + curdExtension);
  }

  @Override
  public void afterFetch(Collection<Map<String, Object>> list) {
    System.out.println("After Fetch " + list);
  }

  @Override
  public void addBehavior(CurdExt curdExtension) {
    System.out.println("After Behavior " + curdExtension);
  }

  @Override
  public void editBehavior(CurdExt curdExtension) {
    System.out.println("Edit Behavior " + curdExtension);
  }
}
