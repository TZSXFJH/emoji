package com.ustclab.emoji.common.model.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "表情实体类")
public class Emoji {

  @Schema(description = "表情id")
  private Long emojiId;
  @Schema(description = "发送者id")
  private Long senderId;
  @Schema(description = "表情码")
  private String emojiCode;
  @Schema(description = "发送时间")
  private Timestamp timestamp;

}
