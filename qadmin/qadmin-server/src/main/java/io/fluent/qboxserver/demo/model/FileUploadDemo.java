package io.fluent.qboxserver.demo.model;

import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.AttachmentType;
import xyz.erupt.jpa.model.MetaModelVo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Erupt(name = "demo_file_upload")
@Table(name = "demo_file_upload")
@Entity
@Data
public class FileUploadDemo extends MetaModelVo {

  @EruptField(
    views = @View(title = "文件上传"),
    edit = @Edit(title = "文件上传", type = EditType.ATTACHMENT,
      attachmentType = @AttachmentType)
  )
  private String attachment;
}
