package com.dstch.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import com.dstch.view.MainView;

public class MainViewHandler extends KeyAdapter implements ActionListener{

	private MainView mainView;
	public MainViewHandler(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton jButton = (JButton)e.getSource();
			String text = jButton.getText();
			if("搜索".equals(text)) {
				mainView.reloadTable();
			}
		}else if(e.getSource() instanceof JRadioButton) {
			JRadioButton jRadioButton = (JRadioButton)e.getSource();
			String text = jRadioButton.getText();
			if("料理".equals(text)) {
				mainView.setSelectType(MainView.TYPE_COOKEDDISHES);
				mainView.reloadTable();
			}else if("食材".equals(text)) {
				mainView.setSelectType(MainView.TYPE_FOODINGREDIENTS);
				mainView.reloadTable();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainView.reloadTable();
		}
	}

}
