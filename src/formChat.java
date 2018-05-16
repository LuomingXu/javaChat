import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class formChat extends JFrame
{
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
    String loginTime = sdf.format(d);

    private JLabel lbl=new JLabel("ID_from :");
    private JLabel lbl1=new JLabel("ID_to :");
    private JLabel lbl2=new JLabel("MSG: ");
    private JLabel lblID_from=new JLabel("");
    private JLabel lblID_to=new JLabel("");
    private JTextField txtChat1=new JTextField();
    private JTextField txtChat2=new JTextField();
    private JTextField txtChat3=new JTextField();
    private JTextField txtChat4=new JTextField();
    private JTextField txtMsg=new JTextField();
    private JButton btnCommit=new JButton("提交信息");
    private JButton btnAddIdTo=new JButton("信息接收人");
    private Font font=new Font("Console",Font.BOLD,16);
    private Font fontMsg=new Font("console", Font.PLAIN,22);

    formChat(String id_from)
    {
        System.out.println(handMsg.getMsg(123123,123456));
        lblID_from.setText(id_from);

        initFrom();
        Listener();

    }

    /**
     * 构建窗体
     */
    private void initFrom()
    {
        this.setLayout(null);
        JBounds();
        fromAdd();
        this.setSize(530,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * 设置控件
     */
    private void JBounds()
    {
        lbl.setBounds(0,0,80,20);
        lblID_from.setBounds(0,20,80,20);
        lbl1.setBounds(0,40,80,20);
        lblID_to.setBounds(0,60,80,20);

        lblID_from.setForeground(Color.RED);
        lblID_to.setForeground(Color.RED);

        txtChat1.setFont(font);
        txtChat2.setFont(font);
        txtChat3.setFont(font);
        txtChat4.setFont(font);
        txtChat1.setBounds(100,0,400,30);
        txtChat2.setBounds(100,30,400,30);
        txtChat3.setBounds(100,60,400,30);
        txtChat4.setBounds(100,90,400,30);

        lbl2.setBounds(60,132,60,20);
        txtMsg.setFont(fontMsg);
        txtMsg.setBounds(100,130,300,30);

        btnCommit.setBounds(180,180,120,40);
        btnAddIdTo.setBounds(60,180,120,40);
    }

    /**
     * 添加窗体程序里面的控件
     */
    private void fromAdd()
    {
        this.add(lbl);
        this.add(lbl1);
        this.add(lblID_from);
        this.add(lblID_to);

        this.add(txtChat1);
        this.add(txtChat2);
        this.add(txtChat3);
        this.add(txtChat4);

        this.add(lbl2);
        this.add(txtMsg);
        this.add(btnAddIdTo);
        this.add(btnCommit);
    }

    /**
     * 监听
     */
    private void Listener()
    {
        btnCommit.addActionListener(e ->
        {
            if (lblID_to.getText().equals("") || txtMsg.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "no id to OR msg is empty");
                return;
            }

            txtChat1.setText(txtChat2.getText());
            txtChat2.setText(txtChat3.getText());
            txtChat3.setText(txtChat4.getText());
            txtChat4.setText(lblID_from.getText()+": "+txtMsg.getText());

            if (!handMsg.insertMsg(Integer.parseInt(lblID_from.getText()),
                    Integer.parseInt(lblID_to.getText()),txtMsg.getText()))
            {
                JOptionPane.showMessageDialog(null, "msg commit fail");
                return;
            }

            txtMsg.setText("");

            updateMsg();

        });
        btnAddIdTo.addActionListener(e ->
        {
            String temp;
            temp=JOptionPane.showInputDialog(null,"add id to");

            System.out.println(temp+"--------"+lblID_from);

            if (temp == null || temp.equals("") || temp.equals(lblID_from.getText()))
            {
                JOptionPane.showMessageDialog(null, "this id is not legal !");
                return;
            }

            if (!handMsg.id_isExist(Integer.parseInt(temp)))
            {
                JOptionPane.showMessageDialog(null, "id is not exist");
                return;
            }
            else
                lblID_to.setText(temp);

            txtMsg.grabFocus();
        });

        txtMsg.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    btnCommit.doClick();
                }
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_J)
                {
                    updateMsg();
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                super.keyReleased(e);
            }
        });
    }

    private void updateMsg()
    {
        try
        {
            int id_from = Integer.parseInt(lblID_from.getText());
            int id_to = Integer.parseInt(lblID_to.getText());
            txtChat1.setText(id_to+": "+handMsg.getMsg(id_from,id_to));
        }
        catch(Exception e2){}
    }
}
