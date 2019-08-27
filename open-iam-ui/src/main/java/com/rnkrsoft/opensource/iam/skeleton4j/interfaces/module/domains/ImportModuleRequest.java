package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.domains;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@Data
public class ImportModuleRequest implements Serializable{
    @ApidocElement(value = "流水号", maxLen = 36, unique = true)
    Integer serialNo;
    @ApidocElement(value = "应用", maxLen = 36, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = ApplicationService.class, value = "fetchApp=fetch", cascadeEvent = WebTriggerEvent.INIT, resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE))
    })
    Integer appId;
}
