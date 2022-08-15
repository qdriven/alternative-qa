package io.fluent.qboxserver.product.model;

import javax.persistence.*;

import lombok.Data;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;
import xyz.erupt.upms.model.base.HyperModel;


@Erupt(name = "主数据记录", power = @Power(importable = true, export = true))
@Table(name = "master_data")
@Entity
@Data
public class MasterData extends HyperModel {

  @EruptField(
    views = @View(
      title = "种类"
    ),
    edit = @Edit(
      title = "种类",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String category;


  @EruptField(
    views = @View(title = "种类代码"),
    edit = @Edit(
      search = @Search,
      title = "获取可选类型",
      type = EditType.CHOICE,
      desc = "动态获取可选类型",
      choiceType = @ChoiceType(
        fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select distinct category_code from master_data where is_valid =true"
      ))
  )
  private String categoryCode;

  @EruptField(
    views = @View(
      title = "名称"
    ),
    edit = @Edit(
      title = "名称",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String name;

  @EruptField(
    views = @View(
      title = "是否有效"
    ),
    edit = @Edit(
      title = "是否有效",
      type = EditType.BOOLEAN, search = @Search, notNull = true,
      boolType = @BoolType
    )
  )
  private Boolean isValid;

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
  private String detail;

}