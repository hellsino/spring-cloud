package com.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hellsino
 * @since 2021-05-19 11:12:23.978
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("OracleEntity")
@ApiModel(value="OracleEntity对象", description="")
public class OracleEntity implements Serializable {

    public static void main(String[] args) {
        OracleEntity oracleEntity = OracleEntity.builder().planCount("100").TagName("Java").Enabled("YES").errorLocationReason("Where").editdate(LocalDateTime.now()).position(1).build();
        System.out.println("oracleEntity = " + oracleEntity);
    }

    private static final long serialVersionUID = 1L;

    @TableField("\"Plan_Count\"")
    private String planCount;

    @TableField("\"TagName\"")
    private String TagName;

    @TableField("\"Enabled\"")
    private String Enabled;

    @TableField("\"ERROR_LOCATION_REASON\"")
    private String errorLocationReason;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("EDITDATE")
    private LocalDateTime editdate;

    @JsonInclude(JsonInclude.Include.NON_NULL)//为null的字段 不输出到前端
    @TableField(value = "POSITION", exist = false)
    private Integer position;
}
