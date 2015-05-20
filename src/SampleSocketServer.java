import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class SampleSocketServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 設定port
        int port=9527;
 
        // 嘗試Listen一個連線
        try {
 
            // 初始化Server Socket 
        	ServerSocket serverSocket =new ServerSocket(port);
 
            // 輸出"伺服器已啟動"
            System.out.println("Server is start.");
 
            // 接受來自客戶端的連線
            Socket socket = serverSocket.accept();
            
            
            
            System.out.println("Accept!");
 
            Thread t = new Thread(new Listener(new ObjectInputStream(socket.getInputStream())));
            t.start();
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            Scanner sc = new Scanner(System.in);
            
            while(sc.hasNextLine()){
            	oos.writeObject(sc.nextLine());
            }
            
        } catch (IOException e) {
            // 如果失敗則顯示"Socket Error"
            System.out.println("Socket ERROR");
        } 
 
        // 顯示結束連線
        System.out.println("Socket is End");
	}

}

class Listener implements Runnable{

	ObjectInputStream ois;
	
	public Listener(ObjectInputStream ois){
		this.ois = ois;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
        	try {
				System.out.println(ois.readObject());
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
}
