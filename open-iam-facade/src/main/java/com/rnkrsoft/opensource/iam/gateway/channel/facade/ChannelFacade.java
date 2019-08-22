package com.rnkrsoft.opensource.iam.gateway.channel.facade;

import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetChannelDetailRequest;
import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetChannelDetailResponse;
import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetAlreadyLoginChannelsRequest;
import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetAlreadyLoginChannelsResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by woate on 2019/7/14.
 */
@ApidocService(value = "渠道服务",channel = "iam")
public interface ChannelFacade {
    @ApidocInterface(value = "获取渠道详细信息", name = "401",version = "1")
    GetChannelDetailResponse getChannelDetail(GetChannelDetailRequest request);

    @ApidocInterface(value = "获取已经登陆的渠道", name = "402", version = "1")
    GetAlreadyLoginChannelsResponse getAlreadyLoginChannels(GetAlreadyLoginChannelsRequest request);
}
