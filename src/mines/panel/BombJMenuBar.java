package mines.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mines.dialog.AboutSweeping;
import mines.dialog.HeroDialog;
import mines.dialog.UserDefinedJDialog;

import mines.tools.StaticTool;

import mines.main.MainFrame;

public class BombJMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JMenu menuGame = new JMenu("��Ϸ(G)");

	JMenu menuHelp = new JMenu("����(H)");

	JMenuItem menuItemStart = new JMenuItem("����");

	JMenuItem menuItemC = new JMenuItem("����");

	JMenuItem menuItemZ = new JMenuItem("�м�");

	JMenuItem menuItemG = new JMenuItem("�߼�");

	JMenu menuHero = new JMenu("Ӣ�۰�");
	JMenuItem menuHeroC = new JMenuItem("����Ӣ�۰�");
	JMenuItem menuHeroZ = new JMenuItem("�м�Ӣ�۰�");
	JMenuItem menuHeroG = new JMenuItem("�߼�Ӣ�۰�");
	JMenuItem menuItemCustom = new JMenuItem("�Զ���");
	JMenuItem menuItemExit = new JMenuItem("�˳�");

	JMenuItem menuItemAbout = new JMenuItem("����ɨ��");
	JMenuItem menuItemHole = new JMenuItem("���Ž���");

	MainFrame mainFrame;

	public BombJMenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {
		menuGame.setMnemonic('G');// ���ÿ�ݼ� alt+ G �Ż���Ч��
		menuHelp.setMnemonic('H');
		// �˵�����뵽�˵�
		menuGame.add(menuItemStart);

		menuItemStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.reStartGame();
			}
		});

		menuGame.addSeparator();// �˵�����ָ���

		menuGame.add(menuItemC);
		menuItemC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.allrow = 9;
				StaticTool.allcol = 9;
				StaticTool.allcount = 10;
				mainFrame.reStartGame();
			}
		});

		menuGame.add(menuItemZ);
		menuItemZ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.allrow = 16;
				StaticTool.allcol = 16;
				StaticTool.allcount = 40;
				mainFrame.reStartGame();
			}
		});

		menuGame.add(menuItemG);
		menuItemG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.allrow = 16;
				StaticTool.allcol = 30;
				StaticTool.allcount = 99;
				mainFrame.reStartGame();
			}
		});
		menuGame.addSeparator();// �˵�����ָ���
		menuGame.add(menuItemCustom);
		menuItemCustom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UserDefinedJDialog(mainFrame);

			}
		});

		menuGame.addSeparator();// �˵�����ָ���
		menuGame.add(menuHero);
		menuHero.add(menuHeroC);
		menuHeroC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new HeroDialog(1, mainFrame);

			}
		});
		menuHero.add(menuHeroZ);
		menuHeroZ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new HeroDialog(2, mainFrame);

			}
		});
		menuHero.add(menuHeroG);
		menuHeroG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new HeroDialog(3, mainFrame);

			}
		});

		menuGame.addSeparator();// �˵�����ָ���
		menuGame.add(menuItemExit);
		menuItemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);

			}
		});

		menuHelp.add(menuItemAbout);
		menuItemAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutSweeping(mainFrame);

			}
		});
		menuHelp.add(menuItemHole);
		menuItemHole.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StaticTool.isHole = true;

			}
		});
		// �˵����뵽�˵���
		this.add(menuGame);
		this.add(menuHelp);

	}

}
