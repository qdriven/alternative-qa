package io.fluent.qboxserver.demo.model;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.annotation.sub_field.sub_edit.SliderType;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Erupt(
  name = "简单的例子",
  power = @Power(importable = true, export = true)
)
@Table(name = "demo_simple")   //数据库表名
@Entity
public class Simple extends BaseModel {

  @EruptField(
    views = @View(title = "文本"),
    edit = @Edit(title = "文本", notNull = true, search = @Search)
  )
  private String input;

  @EruptField(
    views = @View(title = "数值", sortable = true),
    edit = @Edit(title = "数值", search = @Search)
  )
  private Float number;

  @EruptField(
    views = @View(title = "布尔"),
    edit = @Edit(title = "布尔", search = @Search)
  )
  private Boolean bool;


  @EruptField(
    views = @View(title = "时间"),
    edit = @Edit(title = "时间", search = @Search(vague = true))
  )
  private Date date;

  @EruptField(
    views = @View(title = "滑动条"),
    edit = @Edit(title = "滑动条", type = EditType.SLIDER, search = @Search,
      sliderType = @SliderType(max = 90, markPoints = {0, 30, 60, 90}, dots = true))
  )
  private Integer slide;

  @EruptField(
    views = @View(title = "下拉选择"),
    edit = @Edit(
      search = @Search,
      title = "下拉选择", type = EditType.CHOICE,
      choiceType = @ChoiceType(fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select id,name from e_upms_menu"
      )
    )
  )
  private Long choice;

}
