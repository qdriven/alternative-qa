package io.fluent.qboxserver.testcase.model;

import javax.persistence.*;

import io.fluent.qboxserver.product.model.ProductMeta;
import lombok.Data;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.jpa.model.MetaModelCreateVo;



@Erupt(name = "测试用例管理")
//  ,power = @Power(importable = true, export = true))
@Table(name = "test_cases")
@Entity
@Data
public class TestCase extends MetaModelCreateVo {
  @ManyToOne
  @JoinColumn(name = "product_id")
  @EruptField(
    views = @View(title = "产品名称",column = "details"),
    edit = @Edit(
      search = @Search,
      title = "产品选择",
      type = EditType.REFERENCE_TREE,
      desc = "动态获取产品",
      referenceTreeType = @ReferenceTreeType(id = "id", label = "name",
        pid = "parent.id"))
  )
  private ProductMeta product;

  @ManyToOne
  @JoinColumn(name = "module_id")
  @EruptField(
    views = @View(title = "模块名称",column = "details"),
    edit = @Edit(title = "模块选择", search = @Search, type = EditType.REFERENCE_TREE,
      referenceTreeType = @ReferenceTreeType(id = "id", label = "name",
        dependField = "product",
        dependColumn = "parent.id"
      ))
  )
  private ProductMeta module;

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

  //Test Case Status
  private String status;
}