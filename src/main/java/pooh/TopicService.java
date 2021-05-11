package pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {

    private final ConcurrentHashMap<Integer, ConcurrentHashMap<String,
            ConcurrentLinkedQueue<String>>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String theme = req.getTheme();
        int id = req.getId();
        if (queue.get(id).putIfAbsent(theme, new ConcurrentLinkedQueue<>()) == null) {
            queue.get(id).get(theme).add(req.text());
            return null;
        } else {
            return new Resp(queue.get(id).get(theme).poll(), 200);
        }
    }
}
