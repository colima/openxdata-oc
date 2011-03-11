package org.openxdata.server.admin.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import java.util.List;
import org.openxdata.server.admin.client.view.event.EditableEvent;
import org.openxdata.server.admin.client.view.event.EventRegistration;
import org.openxdata.server.admin.client.view.event.ItemSelectedEvent;
import org.openxdata.server.admin.model.TaskDef;
import org.openxdata.server.admin.model.TaskParam;

/**
 *
 * @author kay
 */
public class ParameterPresenter implements IPresenter<ParameterPresenter.Display> {

    public interface Display extends WidgetDisplay {

        public void addParameter(TaskParam param);

        public HasClickHandlers btnAdd();

        public <H extends EventHandler> void addHandler(H handler, Type<H> type);
    }
    private Display display;
    private EventBus eventBus;
    private TaskDef taskDef;

    @Inject
    public ParameterPresenter(Display display, EventBus eventBus) {
        this.display = display;
        this.eventBus = eventBus;
        bindUI();
        bindHandlers();
    }

    private void bindHandlers() {
        ItemSelectedEvent.addHandler(eventBus, new ItemSelectedEvent.Handler<TaskDef>() {

            @Override
            public void onSelected(Composite sender, TaskDef item) {
                setTaskDef(taskDef);
            }
        }).forClass(TaskDef.class);
    }

    private void bindUI() {
        display.btnAdd().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                addParam();
            }
        });
        display.addHandler(new EditableEvent.HandlerAdaptor<TaskParam>() {

            @Override
            public void onDeleted(TaskParam item) {
                removeParam(item);
            }

            @Override
            public void onChange(TaskParam item) {
                fireChange(item);
            }
        }, EventRegistration.getType(EditableEvent.class, TaskParam.class));
    }

    private void addParam() {
        TaskParam param = new TaskParam(taskDef, "", "");
        taskDef.addParam(param);
        display.addParameter(param);
        eventBus.fireEvent(new EditableEvent<TaskDef>(taskDef));
    }

    private void removeParam(TaskParam param) {
        if (param.getTaskDef() != taskDef) return;
        taskDef.deleteParam(param);
        eventBus.fireEvent(new EditableEvent<TaskDef>(taskDef));
    }

    private void fireChange(TaskParam item) {
        if (item.getTaskDef() != taskDef) return;
        taskDef.setDirty(true);
        eventBus.fireEvent(new EditableEvent<TaskDef>(taskDef));
    }

    @Override
    public Display getDisplay() {
        return display;
    }

    public void setTaskDef(TaskDef taskDef) {
        List<TaskParam> parameters = taskDef.getParameters();
        for (TaskParam taskParam : parameters) {
            display.addParameter(taskParam);
        }
        this.taskDef = taskDef;
    }
}
