package com.admxj.real.mvc.assembly;

import com.admxj.real.mvc.context.ProcessContext;
import com.admxj.real.mvc.exception.AssemblyException;
import com.sun.prism.impl.BaseContext;

/**
 * @author jin.xiang
 * @version Id: ProcessContextAssembly, v 0.1 2019-10-03 01:31 jin.xiang Exp $
 */
public interface ProcessContextAssembly<Payload> {

//    ProcessContext<Payload> assemble(BaseContext bizContext, Payload payload) throws AssemblyException;

    ProcessContext<Payload> assemble(ProcessContext<?> oldContext, Payload payload) throws AssemblyException;

}
