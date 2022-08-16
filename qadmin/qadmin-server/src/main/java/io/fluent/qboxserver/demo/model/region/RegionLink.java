package io.fluent.qboxserver.demo.model.region;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Filter;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTreeType;
import xyz.erupt.jpa.model.BaseModel;

@Erupt(name = "省市区联动")
@Entity
@Table(name = "test_regionlink")
public class RegionLink extends BaseModel {
  private static final long serialVersionUID = 1L;

  // @formatter:off
  @ManyToOne
  @EruptField(
          views = @View(title = "省份", column = "districtName"),
          edit = @Edit(title = "省份", type = EditType.REFERENCE_TREE,
                  filter = @Filter("District.hierarchy = 2"),
                  referenceTreeType = @ReferenceTreeType(label="districtName"))
  )
  @JoinColumn(name = "province_id",columnDefinition="bigint(20)")
  private District province;

  @ManyToOne
  @EruptField(
          views = @View(title = "市", column = "districtName"),
          edit = @Edit(title = "市", type = EditType.REFERENCE_TREE,
                  filter = @Filter("District.hierarchy = 3"),
                  referenceTreeType = @ReferenceTreeType(label="districtName",
                    dependField = "province", dependColumn = "pid.id")
          )
  )
  @JoinColumn(name = "city_id",columnDefinition="bigint(20)")
  private District city;

  @ManyToOne
  @EruptField(
          views = @View(title = "区", column = "districtName"),
          edit = @Edit(title = "区", type = EditType.REFERENCE_TREE,
                  filter = @Filter("District.hierarchy = 4"),
                  referenceTreeType = @ReferenceTreeType(label="districtName",
                    dependField = "city", dependColumn = "pid.id")
          )
  )
  @JoinColumn(name = "area_id",columnDefinition="bigint(20)")
  private District area;
  // @formatter:on

  public RegionLink() {
  }

}