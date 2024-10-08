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
package org.apache.myfaces.el.resolver.implicitobject;

import jakarta.el.ELContext;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

/**
 * Implementors of this class encapsulate the information needed to resolve the implicit object.
 * 
 * @author Stan Silvert
 */
public abstract class ImplicitObject
{

    public abstract Object getValue(ELContext context);

    /**
     * Returns an interned String representing the name of the implicit object.
     */
    public abstract String getName();

    /**
     * Returns the most general type allowed for a future call to setValue()
     */
    public abstract Class<?> getType();

    // get the FacesContext from the ELContext
    protected FacesContext facesContext(ELContext context)
    {
        return (FacesContext) context.getContext(FacesContext.class);
    }

    protected ExternalContext externalContext(ELContext context)
    {
        return facesContext(context).getExternalContext();
    }

}
