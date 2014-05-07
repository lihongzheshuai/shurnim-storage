/**
 * 
 */
package com.coderli.shurnim.storage.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.coderli.shurnim.storage.DefaultShurnimStorageImpl;
import com.coderli.shurnim.storage.ShurnimStorage;
import com.coderli.shurnim.storage.plugin.model.Plugin;

import javax.swing.JScrollPane;
import javax.swing.tree.TreeNode;

import java.awt.SystemColor;

/**
 * @author OneCoder
 * @date 2014年5月7日 下午8:26:11
 * @website http://www.coderli.com
 */
public class ShurnimUI {

	private JFrame frmShurnim;
	private ShurnimStorage shurnim = new DefaultShurnimStorageImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShurnimUI window = new ShurnimUI();
					window.frmShurnim.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShurnimUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShurnim = new JFrame();
		frmShurnim.setTitle("Shurnim云存储工具");
		frmShurnim.setBounds(100, 100, 450, 300);
		frmShurnim.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane pluginListPanel = new JScrollPane();
		pluginListPanel.setBackground(new Color(238, 238, 238));
		pluginListPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		frmShurnim.getContentPane().add(pluginListPanel, BorderLayout.WEST);

		// 动态获取树节点
		DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("平台列表");
		createTreeNode(topNode);
		JTree pluginTree = new JTree(topNode);
		pluginTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component node = e.getComponent();
			}
		});
		pluginTree.setBackground(new Color(238, 238, 238));
		pluginListPanel.setViewportView(pluginTree);

		JPanel menuPanel = new JPanel();
		frmShurnim.getContentPane().add(menuPanel, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		menuPanel.add(menuBar);

	}

	/**
	 * 根据支持的插件列表构造树节点
	 * 
	 * @param topNode
	 * @author OneCoder
	 * @date 2014年5月7日 下午10:16:08
	 */
	private void createTreeNode(DefaultMutableTreeNode topNode) {
		topNode.setAllowsChildren(true);
		List<Plugin> plugins = shurnim.getSupportedPlugins();
		if (plugins != null) {
			for (Plugin plugin : plugins) {
				String pluginName = plugin.getName();
				DefaultMutableTreeNode pluginNode = new DefaultMutableTreeNode(
						pluginName);
				topNode.add(pluginNode);
			}
		}

	}

}
