package org.openxdata.server.admin.client.view.factory;

import org.openxdata.server.admin.client.permissions.UIViewLabels;
import org.openxdata.server.admin.client.view.DatasetView;
import org.openxdata.server.admin.client.view.contextmenu.OpenXDataContextMenu;
import org.openxdata.server.admin.client.view.treeview.DatasetTreeView;
import org.openxdata.server.admin.client.view.treeview.listeners.ContextMenuInitListener;
import org.openxdata.server.admin.client.view.widget.OpenXDataNotificationBar;
import org.openxdata.server.admin.client.view.widget.OpenXDataStackPanel;
import org.openxdata.server.admin.client.view.widget.OpenXDataToolBar;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Default implementation of the {@link OpenXDataWidgetFactory}.
 * 
 * <p>
 * The <code>Composites</code> returned from this factory are too abstract to be
 * implemented AS IS. In some cases, you might be required to cast to known
 * <tt>types</tt> before implementing custom behavior.
 * </p>
 * 
 * @version 1.0
 * 
 * 
 */
@SuppressWarnings("deprecation")
public class OpenXDataViewFactory implements OpenXDataWidgetFactory {

    /** Vertical Panel to align <tt>Widgets.</tt> */
    private VerticalPanel verticalPanel;
    /** HorizontalSplitPanel to align main widgets. */
    private HorizontalSplitPanel horizontalSplitClient;
    /** Widget for Mapping Permissions to Roles */
    private static OpenXDataWidgetGinInjector injector;

    /** Constructs an instance of this <tt>class.</tt> */
    public OpenXDataViewFactory() {
    }

    @Override
    public DatasetTreeView getReportsTreeView() {
        return injector.getReportsTreeView();
    }

    @Override
    public DatasetView getReportView() {
        return injector.getReportView();

    }

    @Override
    public OpenXDataToolBar getOpenXDataToolBar() {
        return injector.getOpenXDataToolBar();
    }

    @Override
    public VerticalPanel getVerticalPanel() {
        if (verticalPanel == null) {
            verticalPanel = new VerticalPanel();

            verticalPanel.setWidth("100%");

            // Notification Bar
            verticalPanel.add(getNotificationLabel());

            // Tool Bar
            verticalPanel.add(getOpenXDataToolBar());

        }

        return verticalPanel;
    }

    @Override
    public OpenXDataNotificationBar getNotificationLabel() {
        return injector.getNotificationBar();
    }

    @Override
    public HorizontalSplitPanel getHorizontalSplitPanel() {
        if (horizontalSplitClient == null) {
            horizontalSplitClient = new HorizontalSplitPanel();

            horizontalSplitClient.setSplitPosition("20%");
//			horizontalSplitClient.setRightWidget(getStudyView());
        }

        return horizontalSplitClient;
    }

    @Override
    public OpenXDataStackPanel getOpenXdataStackPanel() {
        return injector.getOpenXdataStackPanel();

    }

    @Override
    public PopupPanel getContextMenu(
            ContextMenuInitListener contextMenuListener,
            UIViewLabels contextMenuLabels, String treeViewName) {
        return new OpenXDataContextMenu().createContextMenu(
                contextMenuListener, contextMenuLabels, treeViewName);
    }

    @Override
    public EventBus getEventBus() {
        return injector.getEventBus();
    }

    @Override
    public void setInjector(OpenXDataWidgetGinInjector injector) {
        OpenXDataViewFactory.injector = injector;
    }
}
