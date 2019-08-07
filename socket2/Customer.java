package socket2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Customer extends Thread{
	private boolean isConnect = false;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String ip;
	private List<Customer>  list;
	public Customer(Socket socket, List<Customer>  list) {
		this.list=list;
		//   交易	得到远程地址信息        得到ip地址
		ip = socket.getInetAddress().getHostAddress();
		System.out.println("客户端"+ip+"连接成功");
		//当前小二负责与客户交流,负责收发数据
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("与客户端"+ip+"的连接已经建立,可以开始通讯");
		} catch (Exception e) {
			System.out.println("与客户端"+ip+"的连接失败,已经断开");
			return;
		}
		isConnect = true;
		//连接成功,进入线程工作.
		this.start();
	}
	@Override
	public void run() {
		list.add(this);
		while(isConnect){
			try {
				String s = reader.readLine();
				if(s==null||"exit".equals(s)){
					System.out.println("客户端"+ip+"主动断开连接");
					break;
				}
				analysisXML(s);
				System.out.println("客户端"+ip+"说:"+s);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("客户端"+ip+"异常断开连接");
				break;
			}
		}
		list.remove(this);
	}

	public void send(String str){
		try {
			System.out.println("发送消息:"+str);
			writer.write(str);
			writer.flush();
		} catch (Exception e) {
			System.out.println("向客户端"+ip+"发送信息失败");
			isConnect=false;
			System.out.println("客户端"+ip+"异常断开连接");
		} 
	}
	
	public static void analysisXML(String xmlDoc) {
//		String xmlDoc="<?xml version=\"1.0\" encoding=\"gb2312\"?><message System=\"ATMS\" Ver=\"1.0\"><systemtype>UTC</systemtype><messagetype>FEEDBACK</messagetype><sourceIP>192.168.10.227</sourceIP><targetIP>192.168.10.28</targetIP><user></user><password></password><feedback><type>STARTVIP</type><control lcid=\"0101\">WAIT</control></feedback></message>";
		//创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入    
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            //System.out.println(root.getName());//输出根元素的名称（测试）
            //得到根元素所有子元素的集合
            
            String messagetype=root.getChild("messagetype").getValue();
            if(!"FEEDBACK".equals(messagetype)) {
            	return;
            }
            
            String targetIP=root.getChild("targetIP").getValue();
            
            System.out.println(targetIP);
            System.out.println(messagetype);
            
              List feedback = root.getChildren("feedback");
              Element fe = (Element)feedback.get(0);
              List list = fe.getContent();
              for(int i=0;i<list.size();i++) {
            	  Element el=(Element)list.get(i);
            	  String en=el.getName();
            	  String ev=el.getValue();
            	  System.out.println(en+":"+ev);
              }
              System.out.println();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
	}
}
