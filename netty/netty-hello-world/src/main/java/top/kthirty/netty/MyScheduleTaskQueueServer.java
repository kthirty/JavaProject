package top.kthirty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class MyScheduleTaskQueueServer extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.channel().eventLoop().schedule(() -> {
            try{
                Thread.sleep(2000);
                System.out.println("多耗时业务操作");
            }catch (Exception e){
                e.printStackTrace();
            }
        },5, TimeUnit.SECONDS);
    }
}
