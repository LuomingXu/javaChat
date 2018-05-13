import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client
{
    public static String localHost="127.0.0.1";
    public static String remoteHost="182.254.223.162";

    private static Socket client;
    private static Writer writer;

    /**
     * 构造函数
     * @param host 要连接的服务端IP地址
     * @param port 要连接的服务端对应的监听端口
     */
    public Client(String host, int port)
    {
        try
        {
            // 与服务端建立连接
            this.client = new Socket(host,port);

            if (client.isConnected())
            {
                System.out.println("Client[port:" + client.getLocalPort() + "] 与服务端建立连接...");
            }
            else
                System.out.println("connect server fail");
        }
        catch(Exception e)
        {
            System.out.println("connect server fail");
        }

    }

    /**
     * 发送消息
     * @param msg  消息
     * @throws Exception
     */
    public boolean send(String msg) throws Exception
    {
        //判断连接是否生效

        if (client == null  || client.isConnected() != true)
            throw new Exception("client == null OR client is not connected");

        //向服务器写入数据
        if(writer == null)
        {
            writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
        }

        writer.write(msg);
        writer.write("eof\n");
        writer.flush();// 写完后flush
        System.out.println("Client[port:" + client.getLocalPort() + "] 消息发送成功");

        return true;
    }

    /**
     * 在你不想继续发送信息的时候, 一定要用这个方法
     */
    public void send_end()
    {
        try
        {
            this.send("end");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 接收消息
     *
     * @throws Exception
     */
    public String receive() throws Exception
    {
        //判断连接是否生效
        if (client == null  || client.isConnected() != true)
            throw new Exception("client == null OR client is not connected");

        // 写完以后进行读操作
        Reader reader = new InputStreamReader(client.getInputStream(), "UTF-8");
        // 设置接收数据超时间为10秒
        client.setSoTimeout(10*1000);
        char[] chars = new char[64];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = reader.read(chars)) != -1)
        {
            sb.append(new String(chars, 0, len));
        }
        System.out.println("Client[port:" + client.getLocalPort() + "] 消息收到了,内容: " + sb.toString()+". remote port: "+client.getPort());

        reader.close();

        //接收消息之后关闭连接
        writer.close();
        client.close();

        //变量没有回收?手动null?static的缘故?
        writer=null;
        client=null;

        return sb.toString();
    }
}
