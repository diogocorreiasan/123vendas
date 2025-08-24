package com.umdoistresvendas.salesapi.controller.request;

import com.umdoistresvendas.salesapi.domain.Status;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequest {

    @NotBlank(message ="Informe o ID do Item")
    private String id;

    @NotBlank(message ="Informe o ID Externo do Item")
    private String productExternalId;

    @NotBlank(message ="Informe o Nome do Item")
    private String productExternalName;

    @NotBlank(message ="Informe o Status do Item")
    private Status status;

    @NotBlank(message ="Informe a quantidade")
    @Min(value = 1, message = "Quantidade deve ser no mínimo 1")
    @Max(value = 20, message = "Quantidade não pode ultrapassar 20")
    private Integer amount;

    @NotBlank(message ="Informe o Valor do Item")
    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "Valor mínimo é 0.01")
    private BigDecimal price;
}
