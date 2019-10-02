package com.admxj.real.mvc.context;

import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jin.xiang
 * @version Id: ProcessContext, v 0.1 2019-10-03 01:26 jin.xiang Exp $
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessContext<Payload> {
    private HttpRequest       request;
    private HttpResponse      response;
    private ContextAttributes attributes;
    private Payload           payload;

}
