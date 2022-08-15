package io.fluent.qboxserver.api.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.ViewType;
import xyz.erupt.annotation.sub_field.sub_edit.CodeEditorType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.MetaModel;
import xyz.erupt.upms.model.base.HyperModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "api_testcases")
@Erupt(
  name = "接口测试用例", power = @Power(importable = true, export = true)
)
public class ApiTestCase extends MetaModel {
  @EruptField(
    views = @View(title = "测试用例名称"),
    edit = @Edit(title = "测试用例名称", notNull = true, search = @Search)
  )
  private String name;
  @EruptField(
    views = @View(title = "产品名称"),
    edit = @Edit(title = "产品名称", notNull = true, search = @Search)
  )
  private String productName;
  @EruptField(
    views = @View(title = "服务"),
    edit = @Edit(title = "服务", notNull = true, search = @Search)
  )
  private String service;
  @EruptField(
    views = @View(title = "地址"),
    edit = @Edit(title = "地址", notNull = true, search = @Search)
  )
  private String uri;
  @EruptField(
    views = @View(title = "方法"),
    edit = @Edit(title = "方法", notNull = true, search = @Search)
  )
  private String method = "POST";
  @EruptField(
    views = @View(title = "请求报文", type = ViewType.CODE),
    edit = @Edit(title = "请求报文", type = EditType.CODE_EDITOR, codeEditType = @CodeEditorType(language = "json"))
  )
  private String body;

  @EruptField(
    views = @View(title = "期望结果", type = ViewType.CODE),
    edit = @Edit(title = "期望结果", type = EditType.CODE_EDITOR,
      codeEditType = @CodeEditorType(language = "json"))
  )
  private String expectedResult;

  @EruptField(
    views = @View(title = "优先级"),
    edit = @Edit(title = "优先级")
  )
  private String priority;

  @EruptField(
    views = @View(title = "是否自动化"),
    edit = @Edit(title = "是否自动化")
  )
  private boolean isAutomated;
}
