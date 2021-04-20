package top.kthirty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 收到消息处理
     * @param ctx 通道上下文
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端收到客户端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 消息处理结束方法，相应给客户端
     * @param ctx 通道上下文
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //发送消息给客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已收到消息", CharsetUtil.UTF_8));
    }

    /**
     * 事件出发
     * @param ctx 上下文
     * @param evt 事件对象
     * @throws Exception 异常抛出
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event=(IdleStateEvent) evt;
            System.out.println(event.state());
        }
        super.userEventTriggered(ctx, evt);
    }

    /**
     * 出现异常后的处理
     * @param ctx 上下文
     * @param cause 错误信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 回收通道上下文
        ctx.close();
    }
}
