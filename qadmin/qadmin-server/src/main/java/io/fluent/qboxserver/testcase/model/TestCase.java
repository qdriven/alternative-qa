package io.fluent.qboxserver.testcase.model;

import javax.persistence.*;

import io.fluent.qboxserver.product.model.ProductMeta;
import lombok.Data;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;
import xyz.erupt.upms.model.base.HyperModel;


@Erupt(name = "测试用例管理")
@Table(name = "test_cases")
@Entity
@Data
public class TestCase extends HyperModel {

  @EruptField(
    views = @View(title = "产品名称"),
    edit = @Edit(
      search = @Search,
      title = "产品选择",
      type = EditType.CHOICE,
      desc = "动态获取产品",
      choiceType = @ChoiceType(
          fetchHandler = SqlChoiceFetchHandler.class,
          fetchHandlerParams = "select id,name from product_meta where is_valid=true and parent_id is null"
        ))
  )
  private Long productId;

  @EruptField(
    views = @View(title = "模块名称"),
    edit = @Edit(title = "模块选择", search = @Search, type = EditType.CHOICE,
      choiceType = @ChoiceType(
        fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select id,name from product_meta where parent_id in (select id from product_meta where parent_id is null and is_valid = true)"
      )
  ))
  private Long moduleId;

   @EruptField(
    views = @View(
      title = "功能"
    ),
    edit = @Edit(
      title = "功能",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String feature;

  @EruptField(
    views = @View(
      title = "测试用例概要"
    ),
    edit = @Edit(
      title = "测试用例概要",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String summary;


  @EruptField(
    views = @View(
      title = "优先级"
    ),
    edit = @Edit(
      title = "优先级",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String priority;
  @Lob
  @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
  @EruptField(
    views = @View(
      title = "前置条件"
    ),
    edit = @Edit(
      title = "前置条件",
      type = EditType.HTML_EDITOR, notNull = true,
      htmlEditorType = @HtmlEditorType(HtmlEditorType.Type.UEDITOR)
    )
  )
  private String precondition;
  @Lob
  @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
  @EruptField(
    views = @View(
      title = "测试步骤"
    ),
    edit = @Edit(
      title = "测试步骤",
      type = EditType.HTML_EDITOR, notNull = true,
      htmlEditorType = @HtmlEditorType(HtmlEditorType.Type.UEDITOR)
    )
  )
  private String steps;
  @Lob
  @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
  @EruptField(
    views = @View(
      title = "预期结果"
    ),
    edit = @Edit(
      title = "预期结果",
      type = EditType.HTML_EDITOR, notNull = true,
      htmlEditorType = @HtmlEditorType(HtmlEditorType.Type.UEDITOR)
    )
  )
  private String expectedResult;

//  //Test Case Status
//  private String status;
}