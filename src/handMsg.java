public class handMsg
{
    private static String send_one_msg(String msg)
    {
        Client client = new  Client(Client.localHost,2344);
        try
        {
            String reply;

            client.send(msg);
            Thread.sleep(500);//发送的太快, server接收不到
            client.send_end();

            if (( reply = client.receive()).equals("true"))
                return "true";
            else
                return reply;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 向服务器发送登录判断用户密码是否正确的信息
     *
     * @param user  用户类
     * @return  用户名, 密码是否正确
     */
    public static boolean login_msg(User user)
    {
        String login_msg=String.format("login-%s-%s",user.id,user.pwd);

        return send_one_msg(login_msg).equals("true");
    }

    /**
     * 向服务器发送注册信息
     *
     * @param user  用户类
     * @return  注册是否成功, 错误时的错误信息
     */
    public static String register_msg(User user)
    {
        String register_msg=String.format("register-%s-%s",user.id,user.pwd);

        return send_one_msg(register_msg);
    }

    /**
     * 判断用户名是否存在
     * @param id 用户名
     * @return 存在true, 否则false
     */
    public static boolean id_isExist(int id)
    {
        String id_isExist_msg=String.format("id_isExist-%s",id);

        return send_one_msg(id_isExist_msg).equals("true");
    }

    /**
     * 向服务器送chat的消息
     * @param msg
     * @return
     */
    public static boolean insertMsg(int id_from, int id_to, String msg)
    {
        String insertMsg=String.format("msg-%s-%s-%s",id_from,id_to,msg);

        return send_one_msg(insertMsg).equals("true");
    }

    public static String getMsg(int id_from, int id_to)
    {
        String getMsg=String.format("getmsg-%s-%s",id_to,id_from);

        return send_one_msg(getMsg);
    }
}
