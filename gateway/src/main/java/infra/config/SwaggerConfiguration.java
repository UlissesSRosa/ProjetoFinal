package infra.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
public class SwaggerConfiguration implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        SwaggerResource swaggerManagement = createSwaggerResource("management-service","/store/management/v1/api-docs");
        SwaggerResource swaggerUser = createSwaggerResource("user-service","/store/user/v1/api-docs");
        SwaggerResource swaggerOrder = createSwaggerResource("order-service","/cadastro/auth/v1/api-docs");
        return List.of(swaggerManagement,swaggerUser,swaggerOrder);
    }

    private SwaggerResource createSwaggerResource(String name, String Location){
        SwaggerResource swaggerManagement = new SwaggerResource();
        swaggerManagement.setName(name);
        swaggerManagement.setLocation(Location);
        return swaggerManagement;
    }

}
