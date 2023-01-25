import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Model interface.
 *
 * The AppendUndo model consists of the input string and a stack of the strings
 * appended to the output in reverse order.
 *
 * @author Elijah Bulluck
 *
 * @mathmodel <pre>
 * {@code type AppendUndoModel is modeled by
 *   (input: string of character,
 *    output: string of string of character)}
 * </pre>
 * @initially <pre>
 * {@code default:
 *   ensures
 *     this = ("", <>)}
 * </pre>
 */
public final class AppendUndoModel1 implements AppendUndoModel {

    /**
     * Model variables.
     */
    private String input;
    private Stack<String> output = new Stack1L<>();

    /**
     * Default constructor.
     */
    public AppendUndoModel1() {
        this.input = "";
        this.output.push("");
    }

    /**
     * Reports this.input.
     *
     * @return this.input
     * @ensures <pre>
     * {@code input = this.input}
     * </pre>
     */
    @Override
    public String input() {
        return this.input;
    }

    /**
     * Sets this.input to argument.
     *
     * @param input
     *            new this.input value
     * @ensures <pre>
     * {@code this.input = input}
     * </pre>
     */
    @Override
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Reports this.output.
     *
     * @return this.output
     * @aliases reference returned by {@code output}
     * @ensures <pre>
     * {@code output = this.output}
     * </pre>
     */
    @Override
    public Stack<String> output() {
        return this.output();
    }

}
