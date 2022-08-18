package io.fluent.qboxserver.common.model;

import org.hibernate.annotations.SQLDelete;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.MetaModelVo;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class StatusMetaVo extends MetaModelVo {
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
