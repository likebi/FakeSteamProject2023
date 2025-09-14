package mines.listenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mines.tools.StaticTool;

import mines.main.MainFrame;

import mines.dialog.UserDefinedJDialog;

public class UserDefinedListener implements ActionListener {
	UserDefinedJDialog userDefinedJDialog;

	MainFrame mainFrame;

	public UserDefinedListener(UserDefinedJDialog userDefinedJDialog,
			MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.userDefinedJDialog = userDefinedJDialog;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == userDefinedJDialog.getButtonCancer()) {
			userDefinedJDialog.dispose();
			mainFrame.reStartGame();
		} else if (e.getSource() == userDefinedJDialog.getButtonSure()) {
			String highT = userDefinedJDialog.getjTextFieldHigh().getText();
			Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
			Matcher matcher = pattern.matcher(highT);
			int row = 0;
			if (!matcher.matches()) {
				userDefinedJDialog.getjLabelMessage()
						.setText("�����������ΧӦ��9-30֮��");
				return;
			} else {
				row = Integer.parseInt(highT);
				if (row < 9 || row > 30) {
					userDefinedJDialog.getjLabelMessage().setText(
							"�����������ΧӦ��9-30֮��");
					return;
				}

			}
			String colT = userDefinedJDialog.getjTextFieldWide().getText();
			int col = 0;
			try {
				col = Integer.parseInt(colT);
				if (col < 9 || col > 30) {
					userDefinedJDialog.getjLabelMessage().setText(
							"�����������ΧӦ��9-30֮��");
					return;
				}
			} catch (Exception e2) {
				userDefinedJDialog.getjLabelMessage().setText(
						"����Ӧ��Ϊ�����ҷ�ΧӦ��9-30֮��");
				return;
			}

			String mineT = userDefinedJDialog.getjTextFieldBomb().getText();
			int mine = 0;
			try {
				mine = Integer.parseInt(mineT);
				if (mine < 10) {
					mine = 10;
				} else {
					mine = Math.min(mine, StaticTool.allrow * StaticTool.allcol
							* 4 / 5);
				}
			} catch (Exception e3) {
				userDefinedJDialog.getjLabelMessage().setText("����Ӧ��Ϊ����");
				return;
			}
			userDefinedJDialog.dispose();
			StaticTool.allrow = row;
			StaticTool.allcol = col;
			StaticTool.allcount = mine;

			mainFrame.reStartGame();
		}

	}

}
