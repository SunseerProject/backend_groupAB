package jp.co.sunseer.sunseerservice.sunseerhht.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class THiyarihattoQuery {

    @ApiModelProperty(value = "対象システム、検索用")
    private String target_system;

    @ApiModelProperty(value = "事故発生区分、検索用")
    private String accident_classification;

    @ApiModelProperty(value = "レベル、検索用")
    private Integer level;

    @ApiModelProperty(value = "発生日時、検索用")
    private LocalDate date;

    @ApiModelProperty(value = "発生日時(スタート)、検索用")
    private LocalDate dateStart;

    @ApiModelProperty(value = "発生日時(エンド)、検索用")
    private LocalDate dateEnd;

    @ApiModelProperty(value = "対策期限、検索用")
    private LocalDate countermeasure_deadline;

    @ApiModelProperty(value = "対策期限(スタート)、検索用")
    private LocalDate countermeasure_deadlineStart;

    @ApiModelProperty(value = "対策期限(エンド)、検索用")
    private LocalDate countermeasure_deadlineEnd;

    @ApiModelProperty(value = "ステータス、検索用")
    private String status;

    @ApiModelProperty(value = "担当、検索用")
    private String staff;

    @ApiModelProperty(value = "登録日、検索用")
    private LocalDate registrationDate;

    @ApiModelProperty(value = "更新日、検索用")
    private LocalDate updateDate;

    public String getTargetSystem() {
        return target_system;
    }

    public String getAccidentClassification() {
        return accident_classification;
    }

    public Integer getLevel() {
        return level;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public LocalDate getCountermeasure_deadline() {
        return countermeasure_deadline;
    }

    public LocalDate getCountermeasure_deadlineStart() {
        return countermeasure_deadlineStart;
    }

    public LocalDate getCountermeasure_deadlineEnd() {
        return countermeasure_deadlineEnd;
    }

    public String getStatus() {
        return status;
    }

    public String getStaff() {
        return staff;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }
}
