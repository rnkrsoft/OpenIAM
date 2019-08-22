package com.rnkrsoft.opensource.iam.gateway.facades;

import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetAlreadyLoginChannelsRequest;
import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetAlreadyLoginChannelsResponse;
import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetChannelDetailRequest;
import com.rnkrsoft.opensource.iam.gateway.channel.domains.GetChannelDetailResponse;
import com.rnkrsoft.opensource.iam.gateway.channel.facade.ChannelFacade;
import org.springframework.stereotype.Service;

/**
 * Created by woate on 2019/7/14.
 */
@Service
public class ChannelFacadeImpl implements ChannelFacade {
    @Override
    public GetChannelDetailResponse getChannelDetail(GetChannelDetailRequest request) {
        return null;
    }

    @Override
    public GetAlreadyLoginChannelsResponse getAlreadyLoginChannels(GetAlreadyLoginChannelsRequest request) {
        return null;
    }
}
