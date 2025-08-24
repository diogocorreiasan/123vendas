package com.umdoistresvendas.salesapi.controller.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequest {

    @NotBlank(message ="Informe o ID Externo do Item")
    private String productExternalId;

    @NotBlank(message ="Informe o Nome do Item")
    private String productExternalName;

    @NotBlank(message ="Informe a quantidade")
    @Min(value = 1, message = "Quantidade deve ser no mínimo 1")
    @Max(value = 20, message = "Quantidade não pode ultrapassar 20")
    private Integer amount;

    @NotBlank(message ="Informe o Valor do Item")
    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "Valor mínimo é 0.01")
    private BigDecimal price;
}
