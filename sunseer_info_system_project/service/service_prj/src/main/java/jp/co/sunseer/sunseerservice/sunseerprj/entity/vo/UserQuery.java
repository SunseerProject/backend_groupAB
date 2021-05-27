package jp.co.sunseer.sunseerservice.sunseerprj.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;


@Data
public class UserQuery {
    @ApiModelProperty(value = "案件ID、検索用")
    private String id;

    @ApiModelProperty(value = "プロジェクト名、検索用")
    private String project;

    @ApiModelProperty(value = "案件名、検索用")
    private String case_name;

    @ApiModelProperty(value = "開発担当者、検索用")
    private String development_manager;

    @ApiModelProperty(value = "開発開始日、検索用")
    private LocalDate development_start_date;

    @ApiModelProperty(value = "S-in完了日、検索用")
    private LocalDate s_in_finished_day;

    @ApiModelProperty(value = "ステータス、検索用")
    private String status;

    @ApiModelProperty(value = "開発工数、検索用")
    private Double manhours_of_development;

    @ApiModelProperty(value = "今月工数、検索用")
    private Double manhours_this_month;

    @ApiModelProperty(value = "備考欄、検索用")
    private String remarks;

    @ApiModelProperty(value = "進捗度、検索用")
    private Integer progress;

    @ApiModelProperty(value = "DEL_FLG、検索用")
    private Integer DEL_FLG;

    @ApiModelProperty(value = "登録日、検索用")
    private LocalDate registration_date;

    @ApiModelProperty(value = "更新日、検索用")
    private LocalDate update_date;

    @ApiModelProperty(value = "範囲開始日、検索用")
    private String firstDate;

    @ApiModelProperty(value = "範囲終了日、検索用")
    private String lastDate;

    @ApiModelProperty(value = "sIn範囲開始日、検索用")
    private String sInFirstDate;

    @ApiModelProperty(value = "sIn範囲終了日、検索用")
    private String sInLastDate;
}
