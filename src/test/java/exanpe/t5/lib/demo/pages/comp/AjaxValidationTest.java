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

package exanpe.t5.lib.demo.pages.comp;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;

import fr.exanpe.t5.lib.constants.AjaxValidationResult;
import fr.exanpe.t5.lib.constants.ExanpeEventConstants;

public class AjaxValidationTest
{
    @Property
    private String value;

    @OnEvent(value = ExanpeEventConstants.AJAXVALIDATION_EVENT, component = "admin")
    public AjaxValidationResult ajaxValidate(String value)
    {
        if ("admin".equalsIgnoreCase(value))
            return AjaxValidationResult.TRUE;
        else
            return AjaxValidationResult.FALSE;
    }

    @OnEvent(value = ExanpeEventConstants.AJAXVALIDATION_EVENT, component = "props")
    public AjaxValidationResult ajaxValidateProps(String value)
    {
        if ("admin".equalsIgnoreCase(value))
            return AjaxValidationResult.TRUE;
        else
            return AjaxValidationResult.FALSE;
    }

    @OnEvent(value = ExanpeEventConstants.AJAXVALIDATION_EVENT, component = "multi")
    public AjaxValidationResult ajaxValidateMulti(String value)
    {
        if (value != null && value.length() < 7)
            return AjaxValidationResult.TRUE;
        return AjaxValidationResult.FALSE;
    }
}
