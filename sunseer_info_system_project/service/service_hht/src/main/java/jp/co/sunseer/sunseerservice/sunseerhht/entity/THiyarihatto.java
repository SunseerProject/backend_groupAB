package jp.co.sunseer.sunseerservice.sunseerhht.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @author GroupA
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="THiyarihatto对象", description="")
public class THiyarihatto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主キー媒体ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "対象システム")
    private String targetSystem;

    @ApiModelProperty(value = "事故発生区分")
    private String accidentClassification;

    @ApiModelProperty(value = "レベル")
    private Integer level;

    @ApiModelProperty(value = "発生日時")
    private LocalDate date;

    @ApiModelProperty(value = "事象内容")
    private String eventContent;

    @ApiModelProperty(value = "根本原因")
    private String rootCause;

    @ApiModelProperty(value = "一次対応")
    private String primarySupport;

    @ApiModelProperty(value = "恒久対応")
    private String permanentSupport;

    @ApiModelProperty(value = "対策期限")
    private LocalDate countermeasureDeadline;

    @ApiModelProperty(value = "ステータス")
    private String status;

    @ApiModelProperty(value = "担当")
    private String staff;

    @ApiModelProperty(value = "備考")
    private String remarks;

    @ApiModelProperty(value = "DEL_FLG")
    @TableLogic
    private Integer delFlg;

    @ApiModelProperty(value = "登録日")
    private LocalDate registrationDate;

    @ApiModelProperty(value = "更新日")
    private LocalDate updateDate;
}
