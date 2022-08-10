package io.fluent.qboxserver.product.model;


import javax.persistence.*;

import io.fluent.qboxserver.demo.model.TreeView;
import lombok.Data;

import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;
import xyz.erupt.upms.model.base.HyperModel;


@Erupt(name = "产品元数据",
  power = @Power(importable = true, export = true),
  tree = @Tree(pid = "parent.id"))
@Entity
@Table(name = "product_meta")
@Data
public class ProductMeta extends HyperModel {

  @EruptField(
    views = @View(
      title = "名称"
    ),
    edit = @Edit(
      title = "名称",
      type = EditType.INPUT, search = @Search,
      notNull = true,
      inputType = @InputType
    )
  )
  private String name;

  @EruptField(
    views = @View(
      title = "详细描述"
    ),
    edit = @Edit(
      title = "详细描述",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String details;

  @EruptField(
    views = @View(title = "数据类型"),
    edit = @Edit(
      search = @Search,
      title = "获取可选类型",
      type = EditType.CHOICE,
      desc = "动态获取可选类型",
      choiceType = @ChoiceType(
        fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select id,name from master_data where category_code='PRODUCT'"
      ))
  )
  private String metaType;

  @ManyToOne
  @EruptField(
    edit = @Edit(
      title = "上级树节点",
      type = EditType.REFERENCE_TREE,
      referenceTreeType = @ReferenceTreeType(pid = "parent.id")
    )
  )
  private ProductMeta parent;

  @EruptField(
    views = @View(
      title = "是否可用"
    ),
    edit = @Edit(
      title = "是否可用",
      type = EditType.BOOLEAN, search = @Search, notNull = true,
      boolType = @BoolType
    )
  )
  private Boolean isValid = true;

}