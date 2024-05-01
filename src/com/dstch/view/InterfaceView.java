package com.dstch.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.dstch.handler.InterfaceViewHandler;
import com.dstch.util.ImageUtil;

/**
 * @description 主类，程序进入接口
 * @author wys
 * @createDate 2023年2月10日 下午8:37:28
 */
public class InterfaceView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelImage = new JLabel();
	private JLabel jLabelTitle = new JLabel();
	private JLabel jLabelInfo = new JLabel();
	private JLabel jLabelAuthor = new JLabel();
	private JLabel jLabelVersion = new JLabel();
	private Box imageBox = new Box(BoxLayout.X_AXIS);
	private Box titleBox = new Box(BoxLayout.X_AXIS);
	private Box infoBox = new Box(BoxLayout.X_AXIS);
	private Box authorBox = new Box(BoxLayout.X_AXIS);
	private Box versionBox = new Box(BoxLayout.X_AXIS);
	private Box bottomBox = new Box(BoxLayout.Y_AXIS);
	private Box upBox = new Box(BoxLayout.Y_AXIS);

	/**
	 * @description 拼装组件
	 * @author wys
	 * @createDate 2023年2月10日 下午8:40:35
	 */
	private void init() {
		setTitle("饥荒烹饪助手");//设置窗口标题
		Container jfContainer = this.getContentPane();
		InterfaceViewHandler interfaceViewHandler = new InterfaceViewHandler(this);
		jfContainer.addMouseListener(interfaceViewHandler);

		jLabelImage.setIcon(ImageUtil.getPotImage());
		imageBox.add(Box.createHorizontalGlue());
		imageBox.add(jLabelImage);
		imageBox.add(Box.createHorizontalGlue());

		jLabelTitle.setText("饥荒烹饪助手");//设置内容
		jLabelTitle.setFont(new Font("华文新魏", Font.PLAIN, 60));//设置字体样式，null表示默认字体
		titleBox.add(Box.createHorizontalGlue());
		titleBox.add(jLabelTitle);
		titleBox.add(Box.createHorizontalGlue());

		jLabelInfo.setText("（点击鼠标开始使用）");
		jLabelInfo.setFont(new Font("黑体", Font.PLAIN, 25));
		infoBox.add(Box.createHorizontalGlue());
		infoBox.add(jLabelInfo);
		infoBox.add(Box.createHorizontalGlue());

		jLabelAuthor.setText("作者：314小作坊工作室");
		jLabelAuthor.setFont(new Font("华文楷体", Font.PLAIN, 20));
		authorBox.add(Box.createHorizontalGlue());
		authorBox.add(jLabelAuthor);
		authorBox.add(Box.createHorizontalGlue());

		jLabelVersion.setText("版本：v1.0_beta");
		jLabelVersion.setFont(new Font("华文楷体", Font.PLAIN, 20));
		versionBox.add(Box.createHorizontalGlue());
		versionBox.add(jLabelVersion);
		versionBox.add(Box.createHorizontalGlue());

		upBox.add(Box.createVerticalStrut(5));
		upBox.add(imageBox);
		upBox.add(Box.createVerticalStrut(5));
		upBox.add(titleBox);
		upBox.add(Box.createVerticalStrut(20));
		upBox.add(infoBox);
		jfContainer.add(upBox, BorderLayout.NORTH);

		bottomBox.add(authorBox);
		bottomBox.add(versionBox);
		bottomBox.add(Box.createVerticalStrut(20));
		jfContainer.add(bottomBox, BorderLayout.SOUTH);

		setIconImage(ImageUtil.getIconImage());
		setSize(600, 400);//设置窗口大小
		setLocationRelativeTo(null);//设置窗口居中显示
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭按钮
		setResizable(false);
		setVisible(true);//设置窗口可见
	}

	/**
	 * @description 程序启动
	 * @author wys
	 * @createDate 2023年2月10日 下午8:37:28
	 */
	public static void main(String[] args) {
		new InterfaceView().init();
	}

}