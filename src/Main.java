import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
//            new Thread(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    try
//                    {
//                        Client client = new Client("127.0.0.1", 1133);
//                        client.send(String.format("Hello,Server!I'm %d.这周末天气如何。", client.client.getLocalPort()));
//                        client.receive();
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

        new FormLogin();

//            try
//            {
//                Client client = new Client(Client.remoteHost, 2344);
//
//                Scanner cin=new Scanner(System.in);
//                String msg="";
//                while (!msg.equals("end"))
//                {
//                    System.out.println("send msg");
//                    msg=cin.next();
//                    client.send(msg);
//                }
//
//                client.receive();
//
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
    }
}
