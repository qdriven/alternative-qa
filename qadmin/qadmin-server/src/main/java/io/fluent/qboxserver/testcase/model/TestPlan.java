package io.fluent.qboxserver.testcase.model;

import javax.persistence.*;

import io.fluent.qboxserver.common.model.ProductModuleVO;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;

import java.util.Date;

@Erupt(name = "测试计划")
//  , linkTree = @LinkTree(field = "testTask"))
@Table(name = "test_plans")
@Entity
@SQLDelete(sql = "upate test_plans set is_valid=false where id=?")
@Where(clause = "is_valid = true")
public class TestPlan extends ProductModuleVO {

  @EruptField(
    views = @View(
      title = "测试计划名称"
    ),
    edit = @Edit(
      title = "测试计划名称",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String name;

  @EruptField(
    views = @View(
      title = "开始时间"
    ),
    edit = @Edit(
      title = "开始时间",
      type = EditType.DATE, search = @Search, notNull = true,
      dateType = @DateType
    )
  )
  private Date startDate;

  @EruptField(
    views = @View(
      title = "结束时间"
    ),
    edit = @Edit(
      title = "结束时间",
      type = EditType.DATE, search = @Search, notNull = true,
      dateType = @DateType
    )
  )
  private Date endDate;

  @EruptField(
    views = @View(
      title = "负责人"
    ),
    edit = @Edit(
      title = "负责人",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String owner;

  @EruptField(
    views = @View(
      title = "状态"
    ),
    edit = @Edit(
      title = "状态",
      type = EditType.INPUT, search = @Search, notNull = true,
      inputType = @InputType
    )
  )
  private String status;

//  @ManyToOne
//  @EruptField(
//    views = {
//      @View(title = "测试任务集合", column = "name"),
//    },
//    edit = @Edit(title = "测试任务集合", search = @Search, type = EditType.REFERENCE_TREE,
//      referenceTreeType = @ReferenceTreeType(id = "id", label = "name", pid = "parent.id"))
//  )
//  private TestTask  testTask;

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