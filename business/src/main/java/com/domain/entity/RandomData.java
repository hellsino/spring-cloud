package com.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hellsino
 * @since 2021-12-28 15:52:22.977
 */
@Component
@ConfigurationProperties(prefix = "random.data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("apple_code")
@ApiModel(value = "AppleCode对象", description = "")
public class RandomData implements Serializable {
    public int id;
    public String username;
    public String password;
    public int code;
    public int type;
    public long time;
}
