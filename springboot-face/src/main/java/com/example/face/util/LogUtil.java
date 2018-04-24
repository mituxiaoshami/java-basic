package com.example.face.util;

import com.example.face.common.WebLogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: sea
 * @Description: 日志工具
 * @Date: 11:01 2018/4/20
 */
public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);


    /**
     * 打印所有的url、方法名称、bean名称、方法参数
     */
    public static void logAll() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        ServletContext servletContext = request.getServletContext();

        if (servletContext == null) {
            logger.error("servletContext must not be null ");
        }

        // 获取spring web 应用的上下文
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        //获取所有的RequestMapping
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(context,
                HandlerMapping.class, true, false);

        for (HandlerMapping handlerMapping : allRequestMappings.values())
        {
            //本项目只需要RequestMappingHandlerMapping中的URL映射
            if (handlerMapping instanceof RequestMappingHandlerMapping)
            {
                RequestMappingHandlerMapping requestMapping = (RequestMappingHandlerMapping) handlerMapping;

                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> entry: handlerMethods.entrySet())
                {
                    // 获取映射信息
                    RequestMappingInfo info = entry.getKey();

                    HandlerMethod method = entry.getValue();

                    RequestMethodsRequestCondition condition = info.getMethodsCondition();

                    // 获取请求方式
                    String requestType = condition.getMethods().toString();
                    logger.info(requestType);

                    // 获取HandlerMapping中的url
                    PatternsRequestCondition patternsCondition = info.getPatternsCondition();
                    String requestUrl = patternsCondition.getPatterns().toString();
                    logger.info(requestUrl);

                    // 获取controller方法名称
                    String controllerName = method.getBeanType().toString();
                    logger.info(controllerName);

                    // 获取对应请求方法名
                    String requestMethodName = method.getMethod().getName();
                    logger.info(requestMethodName);

                    // 获取所有参数类型
                    Class<?>[] methodParamTypes = method.getMethod().getParameterTypes();
                    for (Class<?> methodParamType : methodParamTypes) {
                        logger.info(methodParamType.getSimpleName());
                    }

                    // 获取所有参数
                    MethodParameter[] parameters = method.getMethodParameters();
                    for (MethodParameter paramter: parameters) {
                        if (!StringUtils.isEmpty(paramter.getParameterName())) {
                            logger.info(paramter.getParameterName());
                        }
                    }
                }

            }
        }


        logger.info("日志结束");

    }

}
