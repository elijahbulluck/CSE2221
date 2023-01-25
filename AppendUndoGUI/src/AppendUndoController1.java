/**
 * Controller interface.
 *
 * @author Elijah Bulluck
 *
 * @mathmodel <pre>
 * {@code type AppendUndoController is modeled by
 *   (model: AppendUndoModel,
 *    view: AppendUndoView)}
 * </pre>
 * @initially <pre>
 * {@code (AppendUndoModel model, AppendUndoView view):
 *   ensures
 *     this.model = model  and
 *     this.view = view}
 * </pre>
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        String output = model.output().top();
        /*
         * Update view to reflect changes in model
         */
        if (model.output().length() > 1) {
            view.updateUndoAllowed(true);
        } else {
            view.updateUndoAllowed(false);
        }

        view.updateInputDisplay(input);
        view.updateOutputDisplay(output);
    }

    /**
     * Processes event to reset model.
     *
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.input = ""  and
     * this.model.output = <>  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processResetEvent() {
        this.model.setInput("");
        this.model.output().clear();
        this.model.output().push("");
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to append to output.
     *
     * @param input
     *            string to be appended
     *
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.input = ""  and
     * this.model.output =  <input> * #this.model.output  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processAppendEvent(String input) {
        String top = this.model.output().top();
        this.model.output().push(top + input);
        this.model.setInput("");
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to undo last append to output.
     *
     * @updates {@code this.model, this.view}
     * @requires <pre>
     * {@code this.model.output /= <>}
     * </pre>
     * @ensures <pre>
     * {@code #this.model.output = <this.model.input> * this.model.output  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processUndoEvent() {
        this.model.output().pop();
        updateViewToMatchModel(this.model, this.view);
    }

}
