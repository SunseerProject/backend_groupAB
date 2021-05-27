package jp.co.sunseer.sunseerservice.sunseerprj.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author GroupB
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TProject对象", description="")
public class TProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "案件ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "プロジェクト名")
    private String project;

    @ApiModelProperty(value = "案件名")
    private String caseName;

    @ApiModelProperty(value = "開発担当者名")
    private String developmentManager;

    @ApiModelProperty(value = "開発開始日")
    private LocalDate developmentStartDate;

    @ApiModelProperty(value = "S-in完了日")
    private LocalDate sInFinishedDay;

    @ApiModelProperty(value = "ステータス")
    private String status;

    @ApiModelProperty(value = "開発工数")
    private Double manhoursOfDevelopment;

    @ApiModelProperty(value = "今月工数")
    private Double manhoursThisMonth;

    @ApiModelProperty(value = "備考欄")
    private String remarks;

    @ApiModelProperty(value = "進捗度")
    private Integer progress;

    @ApiModelProperty(value = "DEL_FLG")
    @TableField("DEL_FLG")
    private Integer delFlg;

    @ApiModelProperty(value = "登録日")
    private LocalDate registrationDate;

    @ApiModelProperty(value = "更新日")
    private LocalDate updateDate;


}
