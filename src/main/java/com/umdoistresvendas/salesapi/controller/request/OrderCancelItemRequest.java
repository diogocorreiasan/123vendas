package com.umdoistresvendas.salesapi.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelItemRequest {

    @NotBlank(message ="Numero do Pedido é obrigatório")
    private Long orderNumber;

    @Size(min = 1, message = "É necessário ao menos 1 item")
    private List<ItemCancelItemRequest> items;
}
