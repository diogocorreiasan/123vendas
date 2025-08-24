package com.umdoistresvendas.salesapi.controller.request;

import com.umdoistresvendas.salesapi.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateRequest {

    @NotBlank(message ="Informe o ID do Cliente")
    private Long id;

    @NotBlank(message ="Informe o ID do Cliente")
    private String clientExternalId;

    @NotBlank(message ="Informe o Nome do Cliente")
    private String clientExternalName;

    @NotBlank(message ="Informe o Numero do Pedido")
    private Long orderNumber;

    @NotBlank(message ="Informe o parceiro")
    private String branch;

    @NotBlank(message ="Data de Criação nao ")
    private LocalDate orderDate;

    @Size(min = 1, message = "É necessário ao menos 1 item")
    private List<ItemUpdateRequest> items;

    @NotBlank(message ="Informe o Status")
    private Status status;
}
