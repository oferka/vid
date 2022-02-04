package org.ok.vid.service_registry.mapper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class RegisteredService {

    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    @Getter
    private String name;

    @NotNull
    @Getter
    private List<@NotNull ServiceInstance> serviceInstances;
}
