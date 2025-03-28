/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package jakarta.faces.view.facelets;

import java.io.IOException;
import java.net.URL;

import jakarta.el.ELContext;
import jakarta.el.ExpressionFactory;
import jakarta.el.FunctionMapper;
import jakarta.el.VariableMapper;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;

/**
 * The parent or root object in a FaceletHandler composition. The Facelet will
 * take care of populating the passed UIComponent parent in relation to the
 * create/restore lifecycle of Faces.
 * 
 * @since 2.0
 */
public abstract class FaceletContext extends ELContext
{
    // see MYFACES-4234
    public static final String FACELET_CONTEXT_KEY = "jakarta.faces.FACELET_CONTEXT".intern();

    /**
     * @return the current {@link FaceletContext} from the attributes of the current {@link FacesContext}
     * @since 5.0
     */
    public static FaceletContext getCurrentInstance()
    {
        return getCurrentInstance(FacesContext.getCurrentInstance());
    }

    /**
     * @return the current {@link FaceletContext} from the attributes of the passed {@link FacesContext}
     * @param context the current FacesContext
     * @since 5.0
     */
    public static FaceletContext getCurrentInstance(FacesContext context)
    {
        return (FaceletContext) context.getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
    }

    /**
     * Generate a unique ID for the passed String
     * 
     * @param base
     * @return a unique ID given the passed base
     */
    public abstract String generateUniqueId(String base);

    /**
     * Support method which is backed by the current VariableMapper
     * 
     * @param name
     * @return an Object specified for that name
     */
    public abstract Object getAttribute(String name);

    /**
     * The ExpressionFactory to use within the Facelet this context is executing upon.
     * 
     * @return cannot be null
     */
    public abstract ExpressionFactory getExpressionFactory();

    /**
     * The current FacesContext bound to this "request"
     * 
     * @return cannot be null
     */
    public abstract FacesContext getFacesContext();

    /**
     * Include another Facelet defined at some path, relative to the executing context, not the current Facelet (same as
     * include directive in JSP)
     * 
     * @param parent
     * @param relativePath
     * @throws IOException
     * @throws FaceletException
     * @throws FacesException
     * @throws ELException
     */
    public abstract void includeFacelet(UIComponent parent, String relativePath) throws IOException;

    /**
     * Include another Facelet defined at some path, absolute to this ClassLoader/OS
     * 
     * @param parent
     * @param absolutePath
     * @throws IOException
     * @throws FaceletException
     * @throws FacesException
     * @throws ELException
     */
    public abstract void includeFacelet(UIComponent parent, URL absolutePath) throws IOException;

    /**
     * Support method which is backed by the current VariableMapper
     * 
     * @param name
     * @param value
     */
    public abstract void setAttribute(String name, Object value);

    /**
     * Set the FunctionMapper to use in EL evaluation/creation
     * 
     * @param fnMapper
     */
    public abstract void setFunctionMapper(FunctionMapper fnMapper);

    /**
     * Set the VariableMapper to use in EL evaluation/creation
     * 
     * @param varMapper
     */
    public abstract void setVariableMapper(VariableMapper varMapper);
}
