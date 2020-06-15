package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Iptal");
		UIManager.put("OptionPane.noButtonText", "Hayir");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch(str) {
		case "fill":
			msg = "Lutfen tüm alanlari doldurunuz.";
			break;
		case "error":
			msg = "Islem Basarisiz! ";
			break;
		case "success":
			msg = "Islem Basarili! ";
			break;
			default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg,"Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch(str) {
		case "sure":
			msg = "Bu islemi gerceklestirmek istediginize emin misiniz ?";
			break;
		default:
			msg= str;
			break;
		}
		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat !", JOptionPane.YES_NO_OPTION);
		if (res==0) {
			return true;
		}else {
			return false;
		}
	}
}
