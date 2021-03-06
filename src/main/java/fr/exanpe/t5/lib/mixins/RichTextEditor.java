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

/**
 * 
 */
package fr.exanpe.t5.lib.mixins;

import java.awt.TextArea;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import fr.exanpe.t5.lib.services.ExanpeComponentService;

/**
 * A mixin used to give {@link TextArea} rich text editing features.
 * Replaces the standard HTML textarea and it allows for the rich formatting of text content,
 * including common structural treatments like lists, formatting treatments like bold and italic
 * text, ...
 * JavaScript : This component is bound to a class Exanpe.RichTextEditor.<br/>
 * CSS : This component is bound to a class exanpe-rte.<br/>
 * 
 * @since 1.2
 * @author lguerin
 */
@Import(library =
{ "${exanpe.yui2-base}/yahoo-dom-event/yahoo-dom-event.js", "${exanpe.yui2-base}/element/element-min.js",
        "${exanpe.yui2-base}/container/container_core-min.js", "${exanpe.yui2-base}/menu/menu-min.js", "${exanpe.yui2-base}/button/button-min.js",
        "${exanpe.yui2-base}/editor/editor-min.js", "${exanpe.yui2-base}/json/json-min.js", "${exanpe.asset-base}/js/exanpe-t5-lib.js" }, stylesheet =
{ "${exanpe.asset-base}/css/editor.css", "${exanpe.asset-base}/css/exanpe-t5-lib-core.css", "${exanpe.asset-base}/css/exanpe-t5-lib-skin.css" })
public class RichTextEditor
{
    /**
     * Rich Text Editor title
     */
    @Parameter(defaultPrefix = BindingConstants.MESSAGE)
    private String title;

    /**
     * Indicating if the the toolbar should have a collapse button or not.
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "true")
    private Boolean collapse;

    /**
     * Width of the textarea
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "500")
    private Integer width;

    /**
     * Height of the textarea
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "300")
    private Integer height;

    /**
     * A comma-separated list of button names to be retained.<br />
     * The names are case-insensitive.
     * <p>
     * Available names are : <br />
     * bold, italic, underline, justifyleft, justifycenter, justifyright, createlink, undo, redo,
     * insertunorderedlist, insertorderedlist, heading, forecolor, backcolor and separator.
     * </p>
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL, allowNull = false)
    private String include;

    /**
     * A comma-separated list of button names to be removed from the default editor toolbar.<br />
     * The names are case-insensitive.
     * <p>
     * Available names are : <br />
     * bold, italic, underline, justifyleft, justifycenter, justifyright, createlink, undo, redo,
     * insertunorderedlist, insertorderedlist, heading, forecolor, backcolor.
     * </p>
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL, allowNull = false)
    private String exclude;

    /**
     * Should we focus the textarea when the content is ready
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "true")
    private Boolean autofocus;

    /**
     * Used to override the default message catalog.<br />
     * <p>
     * Available label keys are : <br />
     * bold, italic, underline, justifyleft, justifycenter, justifyright, createlink, undo, redo,
     * insertunorderedlist, insertorderedlist, heading, none, h1, h2, h3, h4, h5, h6, forecolor,
     * backcolor.<br />
     * </p>
     */
    @Parameter("componentResources.messages")
    private Messages messages;

    /**
     * Root CSS class of the mixin
     */
    private static final String ROOT_CSS_CLASS = "exanpe-rte";

    /**
     * YUI CSS class (sam skin)
     */
    private static final String YUI_CSS_CLASS = "yui-skin-sam";

    @InjectContainer
    private ClientElement container;

    @Environmental
    private JavaScriptSupport javascriptSupport;

    @Inject
    private ExanpeComponentService exanpeService;

    @Inject
    private ComponentResources resources;

    @BeginRender
    void begin(MarkupWriter writer)
    {
        Element e = writer.element("span");
        exanpeService.reorderCSSClassDeclaration(e, ROOT_CSS_CLASS);
        Element e2 = writer.element("span");
        e2.addClassName(YUI_CSS_CLASS);
    }

    @AfterRender
    void end(MarkupWriter writer)
    {
        JSONObject data = buildJSONData();
        javascriptSupport.addInitializerCall("richTextEditorBuilder", data);
        writer.end();
        writer.end();
    }

    private JSONObject buildJSONData()
    {
        JSONObject data = new JSONObject();
        data.accumulate("id", container.getClientId());
        data.accumulate("title", title);
        data.accumulate("width", width);
        data.accumulate("height", height);
        data.accumulate("autofocus", autofocus);
        data.accumulate("collapse", collapse);

        // Messages used by mixin
        JSONObject rteMessages = new JSONObject();
        String rteButtons = exanpeService.getEscaladeMessage(resources, "exanpe-rte-toolbar-buttons");
        String[] buttons = rteButtons.split(",");
        for (int i = 0; i < buttons.length; i++)
        {
            String key = buttons[i];
            String label = messages.contains(key) ? messages.get(key) : exanpeService.getEscaladeMessage(resources, key);
            rteMessages.accumulate(key, label);
        }
        data.accumulate("messages", rteMessages.toString());

        // Buttons to be include only
        JSONArray includeButtons = new JSONArray();
        if (include != null)
        {
            String[] includeList = include.split(",");
            for (int i = 0; i < includeList.length; i++)
            {
                includeButtons.put(StringUtils.strip(includeList[i]).toLowerCase());
            }
        }
        data.accumulate("include", includeButtons.toString());

        // Exlude following buttons
        JSONArray excludeButtons = new JSONArray();
        if (exclude != null)
        {
            String[] excludeList = exclude.split(",");
            for (int i = 0; i < excludeList.length; i++)
            {
                excludeButtons.put(StringUtils.strip(excludeList[i]).toLowerCase());
            }
        }
        data.accumulate("exclude", excludeButtons.toString());

        return data;
    }
}
