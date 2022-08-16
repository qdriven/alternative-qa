package io.fluent.qboxserver.demo.model.region;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.View;

/**
 * @description test_district
 * @author WS
 * @date 2021-04-28
 */
@Erupt(name = "地区表")
@Entity
@Table(name = "test_district")
public class District implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键自增
   */
  @Id
  /*@wjw_comment: 自己控制主键生成
  @GeneratedValue(generator = "generator")
  @GenericGenerator(name = "generator",
                    strategy = "native")
  */
  @Column(name = "id")
  @EruptField
  private Long id;

  /**
   * 父类id
   */
  @ManyToOne
  @JoinColumn(name = "pid")
  private District pid;

  /**
   * 城市的名字
   */
  @EruptField(views = @View(title = "名称"))
  @Column(name = "district_name")
  private String districtName;

  /**
   * 城市的类型，0是国，1是省，2是市，3是区
   */
  @Column(name = "type")
  private Integer type;

  /**
   * 地区所处的层级
   */
  @EruptField(views = @View(title = "层级"))
  @Column(name = "hierarchy")
  private Integer hierarchy;

  /**
   * 层级序列
   */
  @Column(name = "district_sqe")
  private String districtSqe;

  public District() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public District getPid() {
    return pid;
  }

  public void setPid(District pid) {
    this.pid = pid;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getHierarchy() {
    return hierarchy;
  }

  public void setHierarchy(Integer hierarchy) {
    this.hierarchy = hierarchy;
  }

  public String getDistrictSqe() {
    return districtSqe;
  }

  public void setDistrictSqe(String districtSqe) {
    this.districtSqe = districtSqe;
  }

  @Override
  public String toString() {
    // @formatter:off
    return "TestDistrict [id=" + id + ", pid=" + pid + ", districtName=" + districtName
           + ", type=" + type + ", hierarchy=" + hierarchy + ", districtSqe=" + districtSqe + "]";
    // @formatter:on
  }

}