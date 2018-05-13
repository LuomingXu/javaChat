import javax.swing.*;

public class FormLogin extends JFrame
{
    private JTextField txtID;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPasswordField txtPwd;
    private JPanel mainPanel;
    private JButton btnRegister;
    private JButton btnRegisterConfirm;

    private User user=new User();

    public FormLogin()
    {
        InitializeComponent();

        btnLogin.addActionListener(e ->
        {
            try
            {
                user.id=Integer.parseInt(txtID.getText());
                if (user.id < 100000 || user.id > 999999)
                    throw new NumberFormatException("不是六位数字ID");

                user.pwd=String.valueOf(txtPwd.getPassword());

                if (handMsg.login_msg(user))
                    JOptionPane.showMessageDialog(null,"login success");
                else
                    JOptionPane.showMessageDialog(null,"login fail");
            }
            catch (NumberFormatException ee)
            {
                JOptionPane.showMessageDialog(null,ee.getMessage());
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

        });
        btnRegister.addActionListener(e ->
        {
            txtID.setText("");
            txtPwd.setText("");

            btnLogin.setVisible(false);
            btnRegisterConfirm.setVisible(true);
            txtPwd.setEchoChar('\0');

            JOptionPane.showMessageDialog(null,"请输入6位数字ID");
        });
        btnRegisterConfirm.addActionListener(e ->
        {
            try
            {
                //什么都没有的情况下, txtPwd.getPassword获得的是随机符号...
                if (txtPwd.getComponentCount()==0 || txtID.getText().equals(""))
                    throw new NumberFormatException("ID 密码, 不可为空");

                user.id=Integer.parseInt(txtID.getText());
                if (user.id < 100000 || user.id > 999999)
                    throw new NumberFormatException("不是六位数字ID");

                user.pwd=String.valueOf(txtPwd.getPassword());

                String reply;

                if (handMsg.id_isExist(user.id))
                {
                    throw new NumberFormatException("id 已经存在");
                }

                if ((reply = handMsg.register_msg(user)).equals("true"))
                    JOptionPane.showMessageDialog(null,"register success");
                else
                    JOptionPane.showMessageDialog(null, reply + "\nregister fail");
            }
            catch (NumberFormatException ee)
            {
                JOptionPane.showMessageDialog(null,ee.getMessage()+"\nregister fail");
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        });
        btnCancel.addActionListener(e ->
        {
            btnLogin.setVisible(true);
            btnRegisterConfirm.setVisible(false);
            txtPwd.setEchoChar('*');

            txtPwd.setText("");
            txtID.setText("");
        });
    }

    private void InitializeComponent()
    {
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        //this.setPreferredSize(new Dimension(800, 600));
        this.setLocation(200,200);
        //this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        txtPwd.setEchoChar('*');
        btnRegisterConfirm.setVisible(false);
    }
}
