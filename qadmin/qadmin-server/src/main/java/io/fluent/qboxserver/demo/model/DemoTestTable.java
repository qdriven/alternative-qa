package io.fluent.qboxserver.demo.model;

import javax.persistence.*;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.jpa.model.MetaModelVo;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "demo")
@Table(name = "demo_test_table")
@Entity
public class DemoTestTable extends MetaModelVo {

        @EruptField(
                views = @View(
                        title = "名字"
                ),
                edit = @Edit(
                        title = "名字",
                        type = EditType.INPUT, search = @Search, notNull = true,
                        inputType = @InputType
                )
        )
        private String name;

        @EruptField(
                views = @View(
                        title = "密码"
                ),
                edit = @Edit(
                        title = "密码",
                        type = EditType.INPUT, search = @Search, notNull = true,
                        inputType = @InputType(type = "password")
                )
        )
        private String pwd;

}