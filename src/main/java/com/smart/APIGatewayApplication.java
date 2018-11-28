package com.smart;

import com.smart.getway.filter.ThrowExceptionFilter;
import com.smart.sso.client.ClientFilter;
import com.smart.sso.client.PermissionFilter;
import com.smart.sso.client.SmartContainer;
import com.smart.sso.client.SsoFilter;
import com.smart.sso.common.config.ConfigUtils;
import com.smart.sso.rpc.AuthenticationRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;


@EnableZuulProxy
@SpringCloudApplication
@ServletComponentScan
@EnableFeignClients
@EnableDiscoveryClient
public class APIGatewayApplication extends SpringBootServletInitializer {
    private static final Log logger = LogFactory.getLog(APIGatewayApplication.class);

    public static final String SPRING_CONFIG_LOCATION = "spring.config.location";


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APIGatewayApplication.class);
    }


//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/baidu")
//                        .uri("http://baidu.com:80/")
//                )
//               .route("websocket_route", r -> r.path("/apitopic1/**")
//                .uri("ws://127.0.0.1:6605"))
//                .route(r -> r.path("/userapi3/**")
//                        .filters(f -> f.addResponseHeader("X-AnotherHeader", "testapi3"))
//
//                        .uri("lb://user-service/")
//                )
//                .build();
//    }

    public static void main(String[] args) {
        logger.error(" Start APIGatewayApplication Done");
        SpringApplication.run(APIGatewayApplication.class, args);
        logger.error(" End APIGatewayApplication Done");
    }

    @Bean
    public ThrowExceptionFilter throwExceptionFilter() {
        return new ThrowExceptionFilter();
    }

    /**
     * 自定义配置加载，方法定义为static的，保证优先加载
     * @return
     */
    @Bean
    public static ConfigUtils properties() {
        final ConfigUtils ppc = new ConfigUtils();
        ppc.setIgnoreResourceNotFound(true);
        final List<Resource> resourceLst = new ArrayList<Resource>();
        if(System.getProperty(SPRING_CONFIG_LOCATION) != null){
            String configFilePath = System.getProperty(SPRING_CONFIG_LOCATION);
            String[] configFiles = configFilePath.split(",|;");

            FileSystemResource res =null;
            for (String configFile : configFiles) {
                if (configFile.startsWith("file:")){
                    resourceLst.add(new FileSystemResource(configFile));
                }else {
                    resourceLst.add( new ClassPathResource(configFile));
                }
            }
        }else {
            resourceLst.add(new ClassPathResource("config.properties"));
//            resourceLst.add(new ClassPathResource("config/kafka.properties"));
        }
        ppc.setLocations(resourceLst.toArray(new Resource[]{}));
        return ppc;
    }


    @Bean
    public SmartContainer smartContainer(AuthenticationRpcService authenticationRpcService){
        SmartContainer smartContainer = new SmartContainer();
        smartContainer.setIsServer(true);
        List<ClientFilter> filters = new ArrayList<ClientFilter>();
        filters.add(new SsoFilter());
        PermissionFilter permissionFilter = new PermissionFilter();
        permissionFilter.setSsoAppCode("smart-sso-server");
        filters.add(permissionFilter);
        smartContainer.setFilters(filters);
        smartContainer.setAuthenticationRpcService(authenticationRpcService);
        return smartContainer;
    }

}

