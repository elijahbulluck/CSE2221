import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
    }

    private static Integer replaceMiddle(Queue<Integer> q, Integer num) {
        int rotate = q.length() / 2;
        q.rotate(rotate);
        Integer middle = q.front();
        q.replaceFront(num);
        q.rotate(-rotate);
        return middle;
    }

    private static Map<String, Integer> wordCount(Queue<String> q) {
        Map<String, Integer> count = new Map1L<String, Integer>();
        Queue<String> temp = q.newInstance();
        for (String str : q) {
            if (!count.hasKey(str)) {
                count.add(str, 0);
            }
        }
        temp.transferFrom(q);
        while (temp.length() > 0) {
            String s = temp.dequeue();
            if (count.hasKey(s)) {
                Map.Pair<String, Integer> k = count.remove(s);
                count.add(s, k.value() + 1);
            }
        }
        return count;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Queue<String> s = new Queue1L<>();
        s.enqueue("word");
        s.enqueue("word");
        s.enqueue("head");
        s.enqueue("word");
        s.enqueue("once");
        s.enqueue("jump");
        s.enqueue("jump");
        s.enqueue("head");
        Map<String, Integer> m = wordCount(s);
        out.print(m);
        in.close();
        out.close();

    }

}
