//
// Copyright 2011 EXANPE <exanpe@gmail.com>
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package fr.exanpe.t5.lib.base;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import fr.exanpe.t5.lib.components.Authorize;
import fr.exanpe.t5.lib.mixins.AuthorizeMixin;
import fr.exanpe.t5.lib.services.AuthorizeBusinessService;

/**
 * This class is the base one controlling authorization.<br/>
 * 
 * @see Authorize
 * @see AuthorizeMixin
 * @author jmaupoux
 */
public abstract class BaseAuthorize
{
    /**
     * Comma separated role values
     * Any of these roles are required to allow rendering
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String any;

    /**
     * Comma separated role values
     * All of these roles are required to allow rendering
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String all;

    /**
     * Comma separated role values
     * None of these roles are required to allow rendering (if one is present in the session, no
     * rendering)
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String not;

    /**
     * Class computing the authorization access
     */
    @Inject
    private AuthorizeBusinessService authorizeBusinessService;

    /**
     * Returns true if content to evaluate
     * 
     * @return true if content to evaluate
     */
    @SetupRender
    boolean init()
    {
        return applyAny() && applyAll() && applyNot();
    }

    /**
     * Returns true if content to evaluate
     * 
     * @return true if content to evaluate
     */
    private boolean applyAny()
    {
        if (StringUtils.isEmpty(any)) { return true; }

        return authorizeBusinessService.applyAny(any.split(","));
    }

    /**
     * Returns true if content to evaluate
     * 
     * @return true if content to evaluate
     */
    private boolean applyAll()
    {
        if (StringUtils.isEmpty(all)) { return true; }

        return authorizeBusinessService.applyAll(all.split(","));
    }

    /**
     * Returns true if content to evaluate
     * 
     * @return true if content to evaluate
     */
    private boolean applyNot()
    {
        if (StringUtils.isEmpty(not)) { return true; }

        return authorizeBusinessService.applyNot(not.split(","));
    }

}
