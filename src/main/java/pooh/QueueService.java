package pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String theme = req.getTheme();
        if (queue.putIfAbsent(theme, new ConcurrentLinkedQueue<>()) == null) {
            queue.get(theme).add(req.text());
            return null;
        } else {
            return new Resp(queue.get(theme).poll(), 200);
        }
    }
}