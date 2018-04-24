package com.example.face;

import com.example.face.config.AopConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.context.annotation.*;

//移除@SpringBootApplication 用@Configuration和@EnableAutoConfiguration代替
//默认情况下，我们会用@SpringBootApplication注解来自动获取应用的配置信息,但同时，使用这个注解后，会触发自动配置( auto-configuration)
//和组件扫描(component scanning)这跟使用 @Configuration、@EnableAutoConfiguration 和 @ComponentScan 三个注解的作用是一样的。
//这样的话也就会有三方面的影响：
//1、会导致项目启动时间变长。当启动一个大的应用程序,或将做大量的集成测试启动应用程序时，影响会特别明显。
//2、会加载一些不需要的多余的实例（beans）。
//3、会增加 CPU 消耗。
//针对以上两个情况，我们可以移除 @SpringBootApplication 和 @ComponentScan 两个注解来禁用组件自动扫描，然后在我们需要的 bean 上进行显式配置
//而@SpringBootApplication 注解的作用跟 @EnableAutoConfiguration 注解的作用是相当的，那就意味着它也能带来上述的三个问题。
//要避免这些问题，我们就要知道我们需要的组件列表是哪些，可以用 -Ddebug 的方式来帮助我们明确地定位：
//更新项目配置，显式地引入这些组件，引入之后，再运行一下应用确保没有错误发生：
@ComponentScan(basePackages = {"com.example.face.*"})
//可以删掉我们不需要的组件信息，来提高应用的性能
@Import({
		DispatcherServletAutoConfiguration.class,
		EmbeddedServletContainerAutoConfiguration.class,
		ErrorMvcAutoConfiguration.class,
		HttpEncodingAutoConfiguration.class,
		//将java实体对象转换成HTTP的数据输出流
		//Spring Boot底层通过HttpMessageConverters依靠Jackson库将Java实体类输出为JSON格式。
		//当有多个转换器可用时，根据消息对象类型和需要的内容类型选择最适合的转换器使用。
		HttpMessageConvertersAutoConfiguration.class,
		JacksonAutoConfiguration.class,
//		JmxAutoConfiguration.class,
		MultipartAutoConfiguration.class,
		ServerPropertiesAutoConfiguration.class,
		PropertyPlaceholderAutoConfiguration.class,
		ThymeleafAutoConfiguration.class,
		WebMvcAutoConfiguration.class,
//		WebSocketAutoConfiguration.class,
		AopConfig.class,
})
public class SpringbootFaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFaceApplication.class, args);
	}

}
