package com.rnkrsoft.opensource.iam.gateway.channel.domains;

import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class GetAlreadyLoginChannelsResponse extends AbstractResponse{
    @ApidocElement("记录")
    final List<GetAlreadyLoginChannelsRecord> records = new ArrayList();
}
