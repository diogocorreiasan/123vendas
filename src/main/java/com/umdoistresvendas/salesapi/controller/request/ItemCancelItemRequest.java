package com.umdoistresvendas.salesapi.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCancelItemRequest {
    @NotBlank(message ="Informe o id do Item")
    private String id;
}
