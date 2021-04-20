package top.kthirty.web;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.kthirty.api.R;

import java.lang.annotation.Annotation;

@ControllerAdvice
public class RespReultHandler implements ResponseBodyAdvice<Object> {
    private static final Class<? extends Annotation> IGNORE_ANNOTATION_TYPE = IgnoreRespResult.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(),IGNORE_ANNOTATION_TYPE)
                && !methodParameter.hasMethodAnnotation(IGNORE_ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 防止重复包裹的问题出现
        if (o instanceof R) {
            return o;
        }
        return R.success(o);
    }
}
