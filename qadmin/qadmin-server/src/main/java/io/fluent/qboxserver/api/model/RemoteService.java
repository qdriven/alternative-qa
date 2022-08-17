package io.fluent.qboxserver.api.model;

import io.fluent.qboxserver.common.handler.SqlTagFetchHandler;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.PreDataProxy;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.ViewType;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.jpa.model.MetaModel;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

import javax.persistence.Entity;
import javax.persistence.Table;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "remote_services")
@Erupt(
  name = "远程服务清单"
)
@Data
@SQLDelete(sql = "upate remote_services set is_valid=false where id=?")
@Where(clause = "is_valid = true")
public class RemoteService extends MetaModel {
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
      title = "产品"
    ),
    edit = @Edit(
      title = "产品",
      search = @Search,
      type = EditType.CHOICE,
      desc = "获取产品",
      choiceType = @ChoiceType(
        fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select id,name,details from product_meta where is_valid =true and parent_id is NULL"
      ))
  )
  private Long productId;


  @EruptField(
    views = @View(title = "模块名"),
    edit = @Edit(
      search = @Search,
      title = "获取可选模块",
      type = EditType.TAGS,
      desc = "动态获取可选模块",
      tagsType = @TagsType(
        fetchHandler = SqlTagFetchHandler.class,
        fetchHandlerParams = "select distinct module_name from remote_services where is_valid=true"
      ))
  )
  private String moduleName;

  @EruptField(
    views = @View(title = "服务"),
    edit = @Edit(title = "服务", notNull = true, search = @Search)
  )
  private String serviceName;

  @EruptField(
    views = @View(title = "地址"),
    edit = @Edit(title = "地址", notNull = true, search = @Search)
  )
  private String endpoint;

  @EruptField(
    views = @View(title = "方法"),
    edit = @Edit(title = "方法", notNull = true, search = @Search)
  )
  private String serviceMethod;

  @EruptField(
    views = @View(title = "http请求方法"),
    edit = @Edit(title = "http请求方法", notNull = true, search = @Search)
  )
  private String httpMethod;

  @EruptField(
    views = @View(title = "请求报文", type = ViewType.CODE),
    edit = @Edit(title = "请求报文", type = EditType.CODE_EDITOR, codeEditType = @CodeEditorType(language = "json"))
  )
  private String body;

  @EruptField(
    views = @View(title = "接口类型"),
    edit = @Edit(title = "接口类型")
  )
  private String type = "API";

  @EruptField(
    views = @View(title = "服务使用状态"),
    edit = @Edit(title = "服务使用状态")
    //default is true, and only updated by import
  )
  private String status = "NEW";

  @EruptField(
    views = @View(title = "是否有效"),
    edit = @Edit(title = "是否有效")
    //default is true, and only updated by import
  )
  private boolean isValid = true;
}