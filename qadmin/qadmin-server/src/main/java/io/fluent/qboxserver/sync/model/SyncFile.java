package io.fluent.qboxserver.sync.model;

import io.fluent.qboxserver.common.handler.SqlTagFetchHandler;
import io.fluent.qboxserver.sync.proxy.SyncFileDataProxy;
import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.PreDataProxy;
import xyz.erupt.annotation.fun.TagsFetchHandler;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.core.annotation.EruptDataProcessor;
import xyz.erupt.jpa.model.MetaModelVo;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

import javax.persistence.Entity;
import javax.persistence.Table;

@Erupt(name = "测试相关文件上传同步")
@Table(name = "qa_files")
@Entity
@Data
@PreDataProxy(value= SyncFileDataProxy.class)
public class SyncFile extends MetaModelVo {

  @EruptField(
    views = @View(title = "用途"),
    edit = @Edit(
      search = @Search,
      title = "获取可选类型",
      type = EditType.CHOICE,
      desc = "动态获取可选类型",
      choiceType = @ChoiceType(
        fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select distinct code from master_data where category_code='UPLOAD_FILE_USAGE' and is_valid=true"
      ))
  )
  private String usage;

  @EruptField(
    views = @View(title = "文件上传"),
    edit = @Edit(title = "文件上传", type = EditType.ATTACHMENT,
      attachmentType = @AttachmentType(fileTypes = {"xls", "xlsx", "csv"}))
  )
  private String attachment;

  @EruptField(
    views = @View(
      title = "用途描述"
    ),
    edit = @Edit(
      title = "用途描述",
      type = EditType.TEXTAREA, notNull = true,
      inputType = @InputType
    )
  )
  private String comments;
}