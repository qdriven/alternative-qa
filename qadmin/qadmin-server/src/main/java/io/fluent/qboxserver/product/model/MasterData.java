package io.fluent.qboxserver.product.model;

import javax.persistence.*;

import io.fluent.qboxserver.common.handler.SqlTagFetchHandler;
import io.fluent.qboxserver.common.model.StatusMetaVo;
import lombok.Data;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;


@Erupt(name ="系统配置数据", power = @Power(importable = true, export = true))
@Table(name = "master_data")
@Entity
@Data
public class MasterData extends StatusMetaVo {

  @EruptField(
    views = @View(
      title = "代号code"
    ),
    edit = @Edit(
      title = "代号code",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String code;


  @EruptField(
    views = @View(title = "类别code"),
    edit = @Edit(
      search = @Search,
      title = "获取可选种类",
      type = EditType.TAGS,
      desc = "动态获取可选种类",
      tagsType = @TagsType(
        fetchHandler = SqlTagFetchHandler.class,
        fetchHandlerParams = "select distinct category_code from master_data where is_valid=true"
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