package socket2;

import java.io.UnsupportedEncodingException;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Mq{
	
	public static void main(String[] args) {
		try {
			new Mq().ss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ss() throws Exception{
		String mqUrl = "tcp://192.168.10.223:61616";
		  //创建一个链接工厂
		ActiveMQConnectionFactory connectionFactory =  new ActiveMQConnectionFactory( ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, mqUrl);  
         //从工厂中创建一个链接
        Connection connection = connectionFactory.createConnection();
        //启动链接,不启动不影响消息的发送，但影响消息的接收
        connection.start();
        //创建一个事物session
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //获取消息发送的目的地，指消息发往那个地方
        //Queue queue = session.createQueue(Common.HMD);
//        Topic queue = session.createTopic(Common.HMD);
        Queue queue = session.createQueue("xhj_command");
        //获取消息发送的生产者
        MessageProducer producer = session.createProducer(queue);
        String xml=xmlMessage();
        xml=this.changeCharset(xml, "UTF-8", "GBK");
        TextMessage msg = session.createTextMessage(xml);
        producer.send(msg);
        session.commit();
        //close connection
        producer.close();
        session.close();
        connection.close();
	}
	public String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        if(str != null) {
            //用源字符编码解码字符串
            byte[] bs = str.getBytes(oldCharset);
            return new String(bs, newCharset);
        }
        return null;
    }
	public String xmlMessage() {
		StringBuilder xml=new StringBuilder();
		//订阅取消消息
//		xml.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
//		xml.append("<message System=\"ATMS\" Ver=\"1.0\">");
//		xml.append("<systemtype>UTC</systemtype>");
//		xml.append("<messagetype>SUBSCRIPTION</messagetype>");
//		xml.append("<sourceIP>192.168.10.28</sourceIP>");
//		xml.append("<targetIP>192.168.10.227</targetIP>");
//		xml.append("<user></user>");
//		xml.append("<password></password>");
//		xml.append("<subscription>");
//		xml.append("<type>ALARMEND</type>");
//		xml.append("<lcid>0101</lcid>");
//		xml.append("<pam></pam>");
//		xml.append("</subscription>");
//		xml.append("</message>");
		//特勤控制
		xml=new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		xml.append("<message System=\"ATMS\" Ver=\"1.0\">");
		xml.append("<systemtype>UTC</systemtype>");
		xml.append("<messagetype>CONTROL</messagetype>");
		xml.append("<sourceIP>192.168.10.28</sourceIP>");
		xml.append("<targetIP>192.168.10.227</targetIP>");
		xml.append("<user></user>");
		xml.append("<password></password>");
		xml.append("<control>");
		xml.append("<type>STOPVIP</type>");
		xml.append("<cross>");
		xml.append("<lcid>0101</lcid>");
		xml.append("<stageid>2</stageid>");
		xml.append("</cross>");
		xml.append("</control>");
		xml.append("</message>");        
		
		return xml.toString();
	}
}


//STARTVIP：启动特勤    2秒延迟
//STOPVIP：停止特勤      4秒延迟
//STARTRED：启动全红  当前周期结束后全红，人行灯变红   STOPVIP也起作用
//STOPRED：停止全红     4秒延迟
//STARTFLASH：启动黄闪    周期结束后信号灯全黄闪，人行灯不亮
//STOPFLASH：停止黄闪   

