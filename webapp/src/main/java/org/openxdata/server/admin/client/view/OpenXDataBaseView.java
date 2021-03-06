package org.openxdata.server.admin.client.view;

import org.openxdata.server.admin.client.OpenXDataAppMessages;
import org.openxdata.server.admin.client.internationalization.OpenXdataConstants;
import org.openxdata.server.admin.client.presenter.WidgetDisplay;
import org.openxdata.server.admin.client.view.factory.OpenXDataWidgetFactory;
import org.openxdata.server.admin.client.view.listeners.OpenXDataViewApplicationEventListener;
import org.openxdata.server.admin.client.view.treeview.OpenXDataBaseTreeView;
import org.openxdata.server.admin.client.view.widget.OpenXDataFlexTable;
import org.openxdata.server.admin.client.view.widget.OpenXDataStackPanel;
import org.openxdata.server.admin.client.view.widget.OpenXDataToolBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

/**
 * <tt>Base class</tt> for all Views that are <tt>Composites</tt> and want to be
 * notified of selections on the {@link OpenXDataBaseTreeView} intances.
 * 
 * 
 */
public abstract class OpenXDataBaseView extends Composite {

	protected static OpenXdataConstants constants = GWT
			.create(OpenXdataConstants.class);
	
	protected OpenXDataAppMessages appMessages = GWT.create(OpenXDataAppMessages.class);

	/** Widget for organizing display in tabular format. */
	protected FlexTable table;

	/** Widget for organizing other tabs. */
	protected DecoratedTabPanel tabs;

	/** Item that has been selected on the <tt>Tree View.</tt> */
	protected Object selectedItem;

	/**
	 * A handle to the <tt>StackPanel</tt> for use on <tt>AppEventListener</tt>
	 * members.
	 */
	protected OpenXDataStackPanel openxdataStackPanel;

	/** Handle to <tt>Widget Factory.</tt> */
	protected OpenXDataWidgetFactory widgetFactory;
	protected EventBus eventBus;

	/**
	 * Sole constructor. (For invocation by subclass constructors, typically
	 * implicit.)
	 * <p>
	 * Defines a contract with a sub class to construct an instance of this
	 * <tt>Object</tt> with a given <tt>ItemChangeListener.</tt>
	 * </p>
	 * 
	 * @param itemChangeListener
	 *            <tt>ItemChangeListener</tt> for the sub class.
	 * @param openXDataViewFactory
	 */
	protected OpenXDataBaseView(OpenXDataWidgetFactory openXDataViewFactory) {
		eventBus = openXDataViewFactory.getEventBus();
		this.widgetFactory = openXDataViewFactory;
		initializeView();
	}

	/**
	 * Initializes the control.
	 */
	private void initializeView() {

		// Initialize this widget's Main Views.
		tabs = new DecoratedTabPanel();
		// TabLayoutPanel p = new TabLayoutPanel(1.5, Unit.EM);
		table = new OpenXDataFlexTable();
	}

	/**
	 * Registers this class with the relevant <tt>Event Dispatchers.</tt>
	 * <p>
	 * <tt>Event Dispatcher is {@link OpenXDataToolBar}.
	 * </p>
	 */
	protected void registerWithEventDispatchers() {
		if (this instanceof OpenXDataViewApplicationEventListener) {
			widgetFactory.getOpenXDataToolBar()
					.registerApplicationEventListener(
							(OpenXDataViewApplicationEventListener) this);
		} 
	}

    public WidgetDisplay getDisplay() {
        final OpenXDataBaseView thisView = this;

        return new WidgetDisplay() {

            @Override
            public Widget asWidget() {
                return thisView;
            }
        };
    }

}
