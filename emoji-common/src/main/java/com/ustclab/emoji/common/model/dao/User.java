package com.ustclab.emoji.common.model.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "用户实体类")
public class User {

  private Long userId;
  private String password;
  @Schema(description = "角色")
  private Long role;
  private String email;
  private String userName;
  @Schema(description = "头像地址")
  private String avatar;
  private Timestamp lastLoginTime;
  @Schema(description = "1为")
  private Boolean status;
  private Timestamp createTime;
  private Timestamp updateTime;
}
