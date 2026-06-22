package com.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hellsino
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SqlServerEntity")
@ApiModel(value="BarcodeDetail对象", description="")
public class SqlServerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "线别")
    @TableField("[LINENO]")
    private String lineno;

}
