package io.fluent.qboxserver.product.model;

import javax.persistence.*;

import cn.hutool.core.lang.UUID;
import io.fluent.qboxserver.common.model.StatusMetaVo;
import lombok.Data;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

@Erupt(name = "产品元数据",
  power = @Power(importable = true, export = true),
  tree = @Tree(pid = "parent.id"))
@Entity
@Table(name = "product_meta")
@Data
public class ProductMeta extends StatusMetaVo {

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
    views = @View(title = "产品类型"),
    edit = @Edit(
      search = @Search,
      title = "获取可选类型",
      type = EditType.CHOICE,
      desc = "动态获取可选类型",
      choiceType = @ChoiceType(
        fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select id,name from master_data where category_code in ('PRODUCT','MODULE')"
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
    views = @View(show = false, title = "uid")
  )
  private String uId = UUID.fastUUID().toString();
}