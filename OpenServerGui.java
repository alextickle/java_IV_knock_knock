import java.io.IOException;

public class OpenServerGui {

	public static void main(String[] args) {
		ServerGui gui = new ServerGui();
		try {
			gui.start();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
