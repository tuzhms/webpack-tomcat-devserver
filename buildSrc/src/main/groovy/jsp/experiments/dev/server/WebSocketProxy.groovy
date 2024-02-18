package jsp.experiments.dev.server


import javax.websocket.ClientEndpoint
import javax.websocket.CloseReason
import javax.websocket.ContainerProvider
import javax.websocket.OnClose
import javax.websocket.OnError
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint
import java.util.function.Consumer

@ServerEndpoint("/ws")
class WebSocketProxy implements Closeable {
    private final Set<Session> serverSessions
    private Client client

    WebSocketProxy() {
        this.serverSessions = new HashSet<>()
        this.client = new Client({ message ->
            serverSessions.basicRemote.forEach { it.sendText(message) }
        })
        ContainerProvider.webSocketContainer.connectToServer(client, new URI('ws://localhost:3000/ws'))
    }

    @OnOpen
    void onOpen(Session session) throws IOException {
        serverSessions.add(session);
    }

    @OnMessage
    void onMessage(Session session, String message) throws IOException {
        client.send(message)
    }

    @OnClose
    void onClose(Session session) throws IOException {
        serverSessions.remove(session);
    }

    @OnError
    void onError(Session session, Throwable throwable) {
        println throwable.getLocalizedMessage()
        throwable.printStackTrace()
        serverSessions.remove(session);
    }

    @Override
    void close() throws IOException {
        serverSessions.forEach {
            it.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Shutdown"))
        }
    }

    @ClientEndpoint
    static class Client implements Closeable {
        private Session clientSession;
        private final Consumer<String> onMessage;

        Client(Consumer<String> onMessage) {
            this.onMessage = onMessage;
        }

        @OnOpen
        void onOpen(Session session) {
            this.clientSession = session
        }

        @OnMessage
        void onMessage(String message, Session session) {
            onMessage.accept(message)
        }

        @OnClose
        void onClose(Session session, CloseReason closeReason) {
            println closeReason
        }

        void send(String message) {
            clientSession.basicRemote.sendText(message)
        }

        @Override
        void close() throws IOException {
            clientSession.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Shutdown"))
        }
    }
}
