package com.kelvin.webtest.holder;

import jersey.repackaged.com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

/**
 * @author Kelvin范显
 * @createDate 2018年01月23日
 */
@Component
@Data
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> map = Maps.newHashMap();
}
