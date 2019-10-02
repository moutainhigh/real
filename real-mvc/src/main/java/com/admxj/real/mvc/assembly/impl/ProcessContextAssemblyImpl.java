package com.admxj.real.mvc.assembly.impl;

import com.admxj.real.core.annotation.RealBean;
import com.admxj.real.mvc.assembly.ProcessContextAssembly;
import com.admxj.real.mvc.context.ProcessContext;
import com.admxj.real.mvc.exception.AssemblyException;

/**
 * @author jin.xiang
 * @version Id: ProcessContextAssemblyImpl, v 0.1 2019-10-03 01:39 jin.xiang Exp $
 */
@RealBean
public class ProcessContextAssemblyImpl<Payload> implements ProcessContextAssembly<Payload> {

    @Override
    public ProcessContext<Payload> assemble(ProcessContext<?> oldContext, Payload payload) throws AssemblyException {
        ProcessContext<Payload> processContext = new ProcessContext<>();
        processContext.setAttributes(oldContext.getAttributes());
        processContext.setRequest(oldContext.getRequest());
        processContext.setResponse(oldContext.getResponse());
        processContext.setPayload(payload);
        return processContext;
    }
}
