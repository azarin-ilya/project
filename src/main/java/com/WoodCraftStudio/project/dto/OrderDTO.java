package com.WoodCraftStudio.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDTO {

    private LocalDate creationDate;
    @NotBlank(message = "не указано наименование товара")
    private String productName;
    @NotBlank(message = "не указана цена")
    private String price;

    public OrderDTO(String productName) {
        this.productName = productName;
    }
}
