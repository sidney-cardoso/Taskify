package br.com.sidneycardoso.taskify.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlashMessage {
    private String cssClass;
    private String message;
}
