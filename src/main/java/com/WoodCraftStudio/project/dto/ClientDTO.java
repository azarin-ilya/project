package com.WoodCraftStudio.project.dto;

import com.WoodCraftStudio.project.entities.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClientDTO {

    private Integer id;
    @NotBlank(message = "не указано имя клиента")
    private String name;
    @NotBlank(message = "не указан ИНН")
    @Pattern(regexp = "\\d{10}", message = "ИНН не соответствует допустимому формату")
    private String inn;
    @NotBlank(message = "не указан номер телефона")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"
            , message = "номер телефона не соответствует допустимому формату")
    private String phoneNumber;
    private Set<Address> addresses;

    public ClientDTO( String name) {
        this.name = name;
    }
}
