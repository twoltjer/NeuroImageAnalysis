package com.nia.elements.global;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class PreparedWindow extends JFrame {
	/**
	 * Serial Version Unique Identifier
	 */
	private static final long serialVersionUID = -1452834622227246316L;

	public GridBagConstraints c;
	public PreparedWindow() {
		super("NeuroImageAnalysis");
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
	
	@Override
	public Component add(Component c) {
		this.getContentPane().add(c, this.c);
		return this;
	}
}
