public class User
{
    public User(){}
    public User(int id, String pwd)
    {
        this.id=id;
        this.pwd=pwd;
    }

    public int id=-1;
    public String pwd=null;
    public String pwd_hash=null;
    public String create_time=null;
    public int[] friends=null;
    public enum relation{friend, black_list}
}
