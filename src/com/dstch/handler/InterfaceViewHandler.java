package com.dstch.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import com.dstch.view.InterfaceView;
import com.dstch.view.MainView;

public class InterfaceViewHandler extends MouseAdapter{

	private InterfaceView interfaceView;
	public InterfaceViewHandler(InterfaceView interfaceView) {
		this.interfaceView = interfaceView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 ||
		   e.getButton() == MouseEvent.BUTTON2 ||
		   e.getButton() == MouseEvent.BUTTON3) {
			try {
				new MainView().init();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			interfaceView.dispose();
		}
	}

}
