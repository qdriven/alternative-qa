package io.fluent.qboxserver.testcase.model;

import javax.persistence.*;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "测试计划")
@Table(name = "test_plans")
@Entity
public class TestPlan extends BaseModel {

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
                        title = "产品"
                ),
                edit = @Edit(
                        title = "产品",
                        type = EditType.INPUT, search = @Search, notNull = true,
                        inputType = @InputType
                )
        )
        private String product;

        @EruptField(
                views = @View(
                        title = "模块"
                ),
                edit = @Edit(
                        title = "模块",
                        type = EditType.INPUT, search = @Search, notNull = true,
                        inputType = @InputType
                )
        )
        private String module;

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

}