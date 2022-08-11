package io.fluent.qboxserver.api.model;

import io.fluent.qboxserver.api.proxy.ApiDataProxy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.PreDataProxy;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.MetaModel;
import xyz.erupt.upms.model.base.HyperModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "apis")
@Erupt(
  name = "接口列表", power = @Power(importable = true, export = true)
)
@PreDataProxy(value = ApiDataProxy.class)
public class Api extends MetaModel {
  @EruptField(
    views = @View(title = "接口名称"),
    edit = @Edit(title = "接口名称")
  )
  private String name;

  @EruptField(
    views = @View(title = "产品"),
    edit = @Edit(title = "产品", notNull = true, search = @Search)
  )
  private String productName;

  @EruptField(
    views = @View(title = "模块"),
    edit = @Edit(title = "模块", notNull = true, search = @Search)
  )
  private String moduleName;
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
  private String method;

  @EruptField(
    views = @View(title = "报文"),
    edit = @Edit(title = "报文")
  )
  private String body;

  @EruptField(
    views = @View(title = "接口类型"),
    edit = @Edit(title = "接口类型")
  )
  private String type ="API";

  @EruptField(
    views = @View(title = "api生命周期状态"),
    edit = @Edit(title = "api生命周期状态")
    //default is true, and only updated by import
  )
  private String status ="NEW";

}