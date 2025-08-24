package com.umdoistresvendas.salesapi.controller.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    @NotBlank(message ="Informe o ID do Cliente")
    private String clientExternalId;

    @NotBlank(message ="Informe o Nome do Cliente")
    private String clientExternalName;

    @NotBlank(message ="Informe o parceiro")
    private String branch;

    @Size(min = 1, message = "É necessário ao menos 1 item")
    private List<ItemCreateRequest> items;
}
