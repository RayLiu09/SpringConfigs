package com.example.demo.services.impl;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Service
public class WebSocketServiceImpl {
    // 静态变量用来存放所有在线连接数, 应该把它设计成线程安全的。
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServiceImpl对象。
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<WebSocketServiceImpl>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("有新的连接加入.");
        } catch (Exception e) {
            System.out.println("发送消息出错");
        }
    }

    /**
     * 连接关闭调用的方法
     *
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param msg
     */
    @OnMessage
    public void onMessage(String msg) {
        System.out.println("来自客户端的消息:" + msg);
        //群发消息
        for (WebSocketServiceImpl item : webSocketSet) {
            try {
                item.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    private void sendMessage(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    private static synchronized int getOnlineCount() {
        return WebSocketServiceImpl.onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServiceImpl.onlineCount++;
    }
    
    private static synchronized void subOnlineCount() {
        WebSocketServiceImpl.onlineCount--;
    }
}
