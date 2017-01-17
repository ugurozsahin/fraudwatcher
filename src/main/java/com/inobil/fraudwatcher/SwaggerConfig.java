package com.inobil.fraudwatcher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.inobil.fraudwatcher.rest.TransactionController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter { 
	
    @Bean
    public Docket apiAllEndPoints() { 
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("All")
        		.select()
        		.apis(RequestHandlerSelectors.basePackage(TransactionController.class.getPackage().getName()))
        		.build()
        		.apiInfo(apiInfo());
    }
    
    @Bean
    public Docket apiTransaction() { 
    	List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
    	responseMessageList.add(new ResponseMessageBuilder().code(500).message("500").responseModel(new ModelRef("Error")).build());
    	responseMessageList.add(new ResponseMessageBuilder().code(403).message("Forbidden!").build());
    	
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("Transactions")
        		.select()
        		.paths(PathSelectors.regex(".*transaction.*"))
        		//.apis(RequestHandlerSelectors.basePackage(TransactionController.class.getPackage().getName()))
        		//.apis(RequestHandlerSelectors.any())
        		//.paths(PathSelectors.any())       
        		.build()
        		//.useDefaultResponseMessages(false)
        		.apiInfo(apiInfo());
        //.globalResponseMessage(RequestMethod.GET, responseMessageList);
    }
    
    @Bean
    public Docket apiApplication() { 
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("Applications")
        		.select()
        		.paths(PathSelectors.regex(".*application*"))       
        		.build()
        		.apiInfo(apiInfo());
    }
    
    @Bean
    public Docket apiApplicationRule() { 
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("Application Rules")
        		.select()
        		.paths(PathSelectors.regex(".*applicationrule.*"))       
        		.build()
        		.apiInfo(apiInfo());
    } 
    
    @Bean
    public Docket apiDetailEndPoints() { 
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("Details")
        		.select()
        		.apis(RequestHandlerSelectors.any())
        		.paths(PathSelectors.any())      
        		.build()
        		.apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("FraudWatcher Rest API")
                .description("Real-time Fraud Detection In Online Transactions <a href=\"/h2-console\">DB Management</a>")
                .termsOfServiceUrl("/")
                .contact(new Contact("Inobil", "http://www.inobil.com", "support@inobil.com"))
                .license("GNU GENERAL PUBLIC LICENSE")
                .licenseUrl("/")
                .version("1.0")
                .build();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
          .addResourceLocations("classpath:/META-INF/resources/");
     
        registry.addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}