package com.dstch.view;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.dstch.handler.MainViewHandler;
import com.dstch.service.MainService;
import com.dstch.util.ImageUtil;

/**
 * @description 主界面窗口
 * @author wys
 * @createDate 2023年2月10日 下午10:19:59
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JRadioButton radioBtnCD = new JRadioButton();
	private JRadioButton radioBtnFI = new JRadioButton();
	private JTextField searchField = new JTextField();
	private JButton searchBtn = new JButton();
	private JPanel upPanel = new JPanel();

	private JTable mainTable = new JTable();
	private Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
	private MainService mainService = new MainService();
	private MainCDTableModel cdTableModel = new MainCDTableModel();
	private MainFITableModel fiTableModel = new MainFITableModel();

	private TrayIcon trayIcon;
	private SystemTray systemTray;

	private int selectType = TYPE_COOKEDDISHES;
	public static final int TYPE_COOKEDDISHES = 0;
	public static final int TYPE_FOODINGREDIENTS = 1;

	/**
	 * @description 拼装组件
	 * @author wys
	 * @throws SQLException
	 * @createDate 2023年2月10日 下午10:20:39
	 */
	public void init() throws SQLException {
		setTitle("饥荒烹饪助手");//设置窗口标题
		Container jfContainer = this.getContentPane();
		MainViewHandler mainViewHandler = new MainViewHandler(this);
		Font font = new Font("微软雅黑", Font.PLAIN, 20);

		ButtonGroup btnGroup = new ButtonGroup();
		radioBtnCD.setText("料理");
		radioBtnCD.setFont(font);
		radioBtnCD.addActionListener(mainViewHandler);
		btnGroup.add(radioBtnCD);

		radioBtnFI.setText("食材");
		radioBtnFI.setFont(font);
		radioBtnFI.addActionListener(mainViewHandler);
		btnGroup.add(radioBtnFI);

        radioBtnCD.setSelected(true);//默认第一个按钮选中
        selectType = MainView.TYPE_COOKEDDISHES;
        upPanel.add(radioBtnCD);
        upPanel.add(radioBtnFI);

        searchField.setColumns(10);
        searchField.setFont(font);
        upPanel.add(searchField);

        searchBtn.setText("搜索");
        searchBtn.setFont(font);
        searchBtn.addActionListener(mainViewHandler);
        searchBtn.addKeyListener(mainViewHandler);
        upPanel.add(searchBtn);
        jfContainer.add(upPanel, BorderLayout.NORTH);

        this.initTable();
        JScrollPane scrollPane = new JScrollPane(
        	mainTable,
        	ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        	ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        jfContainer.add(scrollPane);
		
        Image image = ImageUtil.getIconImage();
        if(SystemTray.isSupported()) {
        	systemTray = SystemTray.getSystemTray();
        	trayIcon = new TrayIcon(image);
			trayIcon.setImageAutoSize(true);
        	try {
				systemTray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowIconified(WindowEvent e) {
					MainView.this.dispose();
				}
			});
			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 1 ||
				   	   e.getClickCount() == 2) {
						MainView.this.setExtendedState(JFrame.NORMAL);
					}
					MainView.this.setVisible(true);
				}
			});
        }
        
        getRootPane().setDefaultButton(searchBtn);
        setIconImage(image);
        setSize(1000, 800);//设置窗口大小
		setLocationRelativeTo(null);//设置窗口居中显示
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭按钮
		setResizable(false);
        setVisible(true);
	}

	private void setTableSettings() {
		Font font = new Font("微软雅黑", Font.PLAIN, 20);
		//设置表属性
		mainTable.setRowHeight(85);
        mainTable.setForeground(Color.BLACK);// 字体颜色
        mainTable.setFont(font);// 字体样式
        mainTable.setSelectionForeground(Color.BLUE);
        mainTable.setGridColor(Color.GRAY);
        mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //设置表头
        mainTable.getTableHeader().setFont(font);// 设置表头名称字体样式
        mainTable.getTableHeader().setForeground(Color.RED);// 设置表头名称字体颜色
        mainTable.getTableHeader().setResizingAllowed(false);// 设置不允许手动改变列宽
        mainTable.getTableHeader().setReorderingAllowed(false);// 设置不允许拖动重新排序各列
        
        //设置列宽
        mainTable.getColumnModel().getColumn(0).setMinWidth(60);
        mainTable.getColumnModel().getColumn(0).setMaxWidth(60);
        mainTable.getColumnModel().getColumn(1).setMinWidth(100);
        mainTable.getColumnModel().getColumn(1).setMaxWidth(100);
        mainTable.getColumnModel().getColumn(6).setMinWidth(300);
        mainTable.getColumnModel().getColumn(6).setMaxWidth(350);
        mainTable.getColumnModel().getColumn(6).setPreferredWidth(320);
        mainTable.getColumnModel().getColumn(7).setMinWidth(150);
        mainTable.getColumnModel().getColumn(7).setMaxWidth(200);
        mainTable.getColumnModel().getColumn(7).setPreferredWidth(180);
        
        mainTable.getColumnModel().getColumn(1).setCellRenderer(new MainImageTableRenderer());
        mainTable.getColumnModel().getColumn(2).setMaxWidth(0);
        mainTable.getColumnModel().getColumn(2).setMinWidth(0);
        mainTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        //遍历表格的每一列，分别给每一列设置单元格渲染器
	    for (int i = 0; i < mainTable.getColumnCount(); i++) {
	    	if(i == 1) {
	    		continue;
	    	}
	    	TableColumn tableColumn = mainTable.getColumn(mainTable.getColumnName(i));
	        tableColumn.setCellRenderer(new MainTableCellRenderer());
	    }
	}

	private void initTable() {
		if(selectType == MainView.TYPE_FOODINGREDIENTS) {
			tableData = mainService.getFIData();
			fiTableModel.initData(tableData);
			mainTable.setModel(fiTableModel);
			RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(fiTableModel);
		    mainTable.setRowSorter(rowSorter);
		}else {
			tableData = mainService.getCDData();
			cdTableModel.initData(tableData);
			mainTable.setModel(cdTableModel);
			RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(cdTableModel);
		    mainTable.setRowSorter(rowSorter);
		}
		mainTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selectType == MainView.TYPE_COOKEDDISHES &&
					e.getButton() == MouseEvent.BUTTON1 &&
					e.getClickCount() == 2
				) {
					InfoView infoPage = new InfoView();
					String foodName = mainTable.getValueAt(mainTable.getSelectedRow(), 2).toString();
					infoPage.setFoodName(foodName);
					infoPage.init();
				}
			}
		});
		setTableSettings();
	}

	public void reloadTable () {
		if(selectType == MainView.TYPE_FOODINGREDIENTS) {
			tableData = mainService.getFIData(searchField.getText());
			fiTableModel.initData(tableData);
			mainTable.setModel(fiTableModel);
			RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(fiTableModel);
		    mainTable.setRowSorter(rowSorter);
		}else {
			tableData = mainService.getCDData(searchField.getText());
			cdTableModel.initData(tableData);
			mainTable.setModel(cdTableModel);
			RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(cdTableModel);
		    mainTable.setRowSorter(rowSorter);
		}
		setTableSettings();
	}

	public void setSelectType(int selectType) {
		this.selectType = selectType;
	}

}
