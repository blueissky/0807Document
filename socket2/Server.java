package socket2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Server {
	private boolean isConnect = false;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String ip;
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Queue destination;
	private MessageConsumer consumer;
	private String mes;
	private List<Customer> list=new ArrayList<Customer>();
	public static void main(String[] args) {
		new Server().createServer();
	}
	int port=6001;
	public void createServer(){
		System.out.println("准备启动服务器...");
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			System.out.println("服务器启动成功,开始监听本机"+port+"端口");
		} catch (Exception e) {
			System.out.println("端口被占用,服务器启动失败");
			return;
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String mqUrl = "tcp://192.168.10.223:61616";
					connectionFactory = new ActiveMQConnectionFactory( ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, mqUrl);  
					connection = connectionFactory.createConnection();  
					connection.start();  
					session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
					destination = session.createQueue("xhj_command");//queue通道
					consumer = session.createConsumer(destination);  
					System.out.println("MQ 连接成功");
					while (true) {  
			              TextMessage message = (TextMessage) consumer.receive();
			              if (null != message) {  
			            	  mes=message.getText();
			            	  //System.out.println("MQ receive:" + mes);
		            	   if(list.size()>0){
		          			for (int i = 0; i < list.size(); i++) {
		          				Customer customer=list.get(i);
		          				customer.send(mes);
		          			}		
				            }  
			              }  
			         }  
			      } catch (Exception e) {  
			          e.printStackTrace();  
			      } finally {  
			          try {  
			              if (null != connection)  
			                  connection.close();  
			          } catch (Throwable ignore) {  
			          }  
			      }
			}
		}).start();
		
		//服务器只负责接待,不再管理具体业务.业务交给小二去做
		while(true){
			try {
				Socket socket = server.accept();
				new Customer(socket,list);
			} catch (Exception e) {
				System.out.println("接待客户出现异常,服务器继续工作");
			}
		}
	}
}
