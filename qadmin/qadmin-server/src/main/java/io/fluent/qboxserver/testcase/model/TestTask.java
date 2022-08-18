package io.fluent.qboxserver.testcase.model;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;
import xyz.erupt.upms.model.base.HyperModel;

import java.util.ArrayList;
import java.util.List;


@Erupt(name = "测试任务集")
@Table(name = "test_tasks")
@Entity
@SQLDelete(sql = "upate test_tasks set is_valid=false where id=?")
public class TestTask extends HyperModel {

  @EruptField(
    views = @View(
      title = "测试迭代"
    ),
    edit = @Edit(
      title = "测试迭代",
      type = EditType.CHOICE, search = @Search, notNull = true,
      choiceType = @ChoiceType(
        fetchHandler = SqlChoiceFetchHandler.class,
        fetchHandlerParams = "select id,name from test_plans where is_valid=true"
      )
    )
  )
  private Long testPlanId;


  @EruptField(
    views = @View(
      title = "负责人"
    ),
    edit = @Edit(
      title = "负责人",
      type = EditType.CHOICE, search = @Search, notNull = true,
      choiceType = @ChoiceType(vl = {@VL(value = "xxx", label = "xxx"), @VL(value = "yyy", label = "yyy")})
    )
  )
  private String owner;

  @ManyToMany
  @JoinTable(
    name = "test_task_cases",
    joinColumns = @JoinColumn(name = "test_task_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "test_case_id", referencedColumnName = "id"))
  @EruptField(
    views = @View(title = "测试用例"),
    edit = @Edit(title = "关联测试用例", type = EditType.TAB_TABLE_REFER)
  )
  private List<TestCase> testCaseTab = new ArrayList<>();

  @EruptField(
    views = @View(
      title = "测试结果"
    ),
    edit = @Edit(
      title = "测试结果",
      type = EditType.CHOICE, search = @Search, notNull = true,
      choiceType = @ChoiceType(vl = {@VL(value = "xxx", label = "xxx"), @VL(value = "yyy", label = "yyy")})
    )
  )
  private String status;


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