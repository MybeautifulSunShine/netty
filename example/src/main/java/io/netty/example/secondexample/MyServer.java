package io.netty.example.secondexample;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {

    public static void main(String[] args) throws Exception {
        /**
         *  hegaojian
         *   底层就是一个死循环 不停的去侦测底层的事件(也就是输入输出的时间不停的去处理)
         *   事件循环组
         *              NioEventLoopGroup-->异步的时间循环组
         *                              最终 -->完成变量的赋值
         *                              -->父接口EventLoopGroup循环组extend 多线程事件执行组MultithreadEventExecutorGroup
         *                    EventLoopGroup 是有多个线程,也就是
         *
         **/
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //实际的业务处理 连接
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //启动服务管道(什么都没有做，只是对启动信息进行封装)
            ServerBootstrap bootstrap = new ServerBootstrap();
            //定义好组 channel 是通道  NioServerSocketChannel反射的方式创建的
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    //请求处理器 ,我们自己定义的子处理器
                    .childHandler(new MyServerInitializer());
            //syncfuture 异步调用返回的 对象
            ChannelFuture syncfuture = bootstrap.bind(8899).sync();
            syncfuture.channel().closeFuture().sync();
        } finally {
            //关闭链接
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
