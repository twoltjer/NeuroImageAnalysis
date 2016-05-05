package com.nia.elements.instance.panels;

import javax.swing.JPanel;

import com.nia.elements.instance.panels.rightpanels.ImportRightSubPanel;
import com.nia.elements.instance.panels.rightpanels.RightSubPanel;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 8847187226380955681L;
	public final int PANEL_VIEW_IMPORT = 0;
	public final int PANEL_VIEW_EXPORT = 1;
	public final int PANEL_VIEW_CHOOSE_DIR = 2;
	public final int PANEL_VIEW_OPTIONS = 3;
	public final int PANEL_VIEW_COLORS = 4;
	public final int PANEL_VIEW_SCAN = 5;
	private int panelView;
	private RightSubPanel panelShowing;
	

	public RightPanel() {
		super();
		setPanelView(this.PANEL_VIEW_CHOOSE_DIR);
	}
	
	public void setPanelView(int panelView) {
		this.panelView = panelView;
		if(panelView == PANEL_VIEW_IMPORT) {
			this.remove(panelShowing);
			this.panelShowing = new ImportRightSubPanel();
			this.add(this.panelShowing);
			this.panelShowing.display();
		}
	}
	
	public int getPanelView() {
		return this.panelView;
	}
}
