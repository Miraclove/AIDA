package frame.ChatFrame;

import client.ChatThread;
import client.InteractWithServer;
import config.Tools;
import config.UserInfo;
import frame.Listener.SendFriend;
import frame.MainInterface.UI_MainInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

public class ChatWithGroup extends ChatFrame {
    protected JButton GroupNameButton,minimize,closeButton,emojiButton,
            pictureButton,sendButton;
    protected JPanel mainControlPanel,functionPanel,inputPanel,sendPanel,controlPanel,sysPanel,groupMemberPanel;
    protected JScrollPane memberPanel;

    private String fName,fid,mid,mName;
    public String getGroupID(){
        return fid;
    }
    private int messageNum;
    private int height;
    private Image mHeadPic;
    private int userStatue;
    public void addMember(Image uHead,String uid, String uName, int statue){
        groupMemberPanel.setPreferredSize(new Dimension(200,(int)groupMemberPanel.getPreferredSize().getHeight()+40));
        groupMemberPanel.add(new MemberText(uHead,uName,uid,statue,this, userStatue,mid));
        memberPanel.getViewport().setViewPosition(new Point(0,groupMemberPanel.getHeight()));

    }

    private void showEmojiMenu(){
        EmojiMenu emojiMenu=new EmojiMenu(this);
    }


    public ChatWithGroup(String mid,String mName,String fid,String fName,Image mHeadPic){
        this.mid=mid;
        this.mName=mName;
        this.fid=fid;
        this.fName=fName;
        this.mHeadPic=mHeadPic;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
        this.setLayout(new BorderLayout());
        this.add(mainControlPanel,BorderLayout.NORTH);
        this.add(memberPanel,BorderLayout.EAST);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(controlPanel,BorderLayout.SOUTH);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //addMessage("res/Icon/11111.png","mike","1111","1111",0);
        //addMember("111","111",1);
        this.setSize(700,520);
        setLocationRelativeTo(null);

        this.setVisible(true);


    }
    private void init(){

        //friends name button
        GroupNameButton =new JButton();
        GroupNameButton.setText(fName);
        GroupNameButton.setFont(new Font("微软雅黑",Font.BOLD,16));
        GroupNameButton.setForeground(Color.WHITE);
        GroupNameButton.setFocusPainted(false);
        GroupNameButton.setContentAreaFilled(false);
        GroupNameButton.setBorderPainted(false);
        Point origin=new Point();
        GroupNameButton.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = ChatWithGroup.this.getLocation();
                ChatWithGroup.this.setLocation(
                        p.x + (e.getX() - origin.x),
                        p.y + (e.getY() - origin.y));
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        GroupNameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //minimize button mini frame
        minimize=new JButton();
        minimize.setPreferredSize(new Dimension(40,40));
        minimize.setMargin(new Insets(0, 0, 0, 0));
        minimize.setIcon(setIcon("res/Icon/mini.png",30,30));
        minimize.setRolloverIcon(setIcon("res/Icon/mini_selected.png",30,30));
        minimize.setPressedIcon(setIcon("res/Icon/mini_selected.png",30,30));
        minimize.setBorderPainted(false);
        minimize.setContentAreaFilled(false);
        minimize.setFocusPainted(false);
        minimize.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
        //close button  close the frame
        closeButton = new JButton();
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setPreferredSize(new Dimension(40,40));
        closeButton.setContentAreaFilled(false); // set don't draw message area
        closeButton.setBorderPainted(false); // set don't draw border
        closeButton.setFocusPainted(false);
        closeButton.setIcon(setIcon("res/Icon/close.png",30,30));
        closeButton.setRolloverIcon(setIcon("res/Icon/close_selected.png",30,30));
        closeButton.setPressedIcon(setIcon("res/Icon/close_selected.png",30,30));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_MainInterface.withGroup.remove(fid);
                ChatWithGroup.this.dispose();
            }
        });



        emojiButton=new JButton();
        emojiButton.setMargin(new Insets(0, 0, 0, 0));
        emojiButton.setPreferredSize(new Dimension(40,40));
        emojiButton.setContentAreaFilled(false); // set don't draw message area
        emojiButton.setBorderPainted(false); // set don't draw border
        emojiButton.setFocusPainted(false);
        emojiButton.setIcon(setIcon("res/Icon/smile.png",30,30));
        emojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmojiMenu();
            }
        });


        pictureButton=new JButton();
        pictureButton.setMargin(new Insets(0, 0, 0, 0));
        pictureButton.setPreferredSize(new Dimension(40,40));
        pictureButton.setContentAreaFilled(false); // set don't draw message area
        pictureButton.setBorderPainted(false); // set don't draw border
        pictureButton.setFocusPainted(false);
        pictureButton.setIcon(setIcon("res/Icon/image.png",30,30));
        pictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int returnVal=chooser.showOpenDialog(ChatWithGroup.this);
                chooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.getName().endsWith(".jpg")||f.getName().endsWith(".png")||f.isDirectory()){
                            return true;
                        }else {
                            return false;
                        }
                    }
                    @Override
                    public String getDescription() {
                        return "图片(.jpg,.png)";
                    }
                });
                System.out.println("returnVal="+returnVal);
                String filepath;
                if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型
                    filepath = chooser.getSelectedFile().getPath();      //获取绝对路径
                    System.out.println(filepath);
                    System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());  //输出相对路径
                    String message= "#/Image"+ Tools.getImageBinary(filepath);
                    ChatWithGroup.this.addMessage(mHeadPic,mName, new Date().toString(),message, 1);
                    ChatThread.getDataStream().send(message, fid, true);
                }
            }
        });

        input=new JTextArea("");
        input.setPreferredSize(new Dimension(700,300));
        input.setBackground(new Color(128,255,255));
        input.setBorder(null);

        sendButton=new JButton();
        sendButton.setText("发送");
        //sendButton.setPreferredSize(new Dimension(80,30));
        sendButton.setBackground(new Color(22,155,213));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("微软雅黑",Font.BOLD,16));
        sendButton.setBorderPainted(false); // set don't draw border
        sendButton.setFocusPainted(false);
        sendButton.setBounds(600,0,80,30);
        SendFriend sendFriend=new SendFriend(mHeadPic,mName,fid,true,input,this);
        sendButton.addActionListener(sendFriend);



        mainControlPanel =new JPanel();
        chatPanel=new JPanel();
        functionPanel=new JPanel();
        inputPanel=new JPanel();
        sendPanel=new JPanel();
        controlPanel=new JPanel();
        sysPanel=new JPanel();


        mainControlPanel.setPreferredSize(new Dimension(700,40));
        chatPanel.setPreferredSize(new Dimension(500,300));
        functionPanel.setPreferredSize(new Dimension(700,40));
        inputPanel.setPreferredSize(new Dimension(700,100));
        sendPanel.setPreferredSize(new Dimension(700,40));

        sysPanel.setBackground(new Color(40,138,221));
        sysPanel.setLayout(new BorderLayout());
        sysPanel.add(minimize,BorderLayout.WEST);
        sysPanel.add(closeButton,BorderLayout.EAST);

        mainControlPanel.setLayout(new BorderLayout());
        mainControlPanel.setBackground(new Color(40,138,221));
        mainControlPanel.add(GroupNameButton,BorderLayout.CENTER);
        mainControlPanel.add(sysPanel,BorderLayout.EAST);

        chatPanel.setBackground(new Color(243,249,253));
        chatPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        scrollPane =new JScrollPane(chatPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(243,249,253)));
        height=0;
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED));
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        chatPanel.setPreferredSize(new Dimension(500,height));


        functionPanel.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));
        functionPanel.setBackground(Color.BLUE);
        functionPanel.add(emojiButton);
        functionPanel.add(pictureButton);
        functionPanel.add(sendButton);
        functionPanel.setBackground(new Color(128,255,255));

        inputPanel.add(input);
        inputPanel.setBackground(new Color(128,255,255));

        sendPanel.setLayout(null);
        sendPanel.add(sendButton);
        sendPanel.setBackground(new Color(128,255,255));

        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(functionPanel,BorderLayout.NORTH);
        controlPanel.add(inputPanel,BorderLayout.CENTER);
        controlPanel.add(sendPanel,BorderLayout.SOUTH);

        groupMemberPanel=new JPanel();
        memberPanel= new JScrollPane(groupMemberPanel);
        groupMemberPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        memberPanel.setPreferredSize(new Dimension(200,300));
        memberPanel.setBackground(new Color(201,251,254));
        groupMemberPanel.setBackground(new Color(201,251,254));
        memberPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(243,249,253)));
        memberPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        memberPanel.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED));
        memberPanel.getVerticalScrollBar().setUI(new ScrollBarUI());
        memberPanel.getVerticalScrollBar().setUnitIncrement(15);
        groupMemberPanel.setPreferredSize(new Dimension(200,0));
        //新线程拉取信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                Vector<UserInfo.FriendsOrGroups> members = InteractWithServer.getGroupMembers(fid);
                for (int i =0;i<members.size();i++){
                    System.out.println(members.get(i).getId());
                    if (members.get(i).getId().equals(mid)){
                        userStatue=Integer.parseInt(members.get(i).getStatus());
                        System.out.println(userStatue);
                        break;
                    }
                }
                for (int i =0;i<members.size();i++){
                    System.out.println("Group Member load:"+members.get(i).getId());
                    addMember(Tools.base64StringToImage(members.get(i).getAvatar()), members.get(i).getId(), members.get(i).getName(), Integer.parseInt(members.get(i).getStatus()));
                }
                Vector<String> record = InteractWithServer.getChatRecord(mid, fid, true);
                System.out.println("获得消息数量："+record.size());
                for (int i = 0; i < record.size(); i++) {
//                 res[0] 消息发送时间 res[1] fromId res[2] toId res[3] message
                    String res[] = record.get(i).split("```", 4);
                    Image image;
                    System.out.println("历史消息:"+res[3]);
                    for (int j = 0; j <members.size();j++){
                        if(members.get(j).getId().equals(res[1])){
                            if (members.get(j).getId().equals(mid)){
                                addMessage(mHeadPic,mName,res[0],res[3],1);
                            }else {
                                image=Tools.base64StringToImage(members.get(j).getAvatar());
                                addMessage(image,members.get(j).getName(),res[0],res[3],0);
                            }
                            break;
                        }
                    }
                    // 聊天面板显示用户昵称
                }
                scrollPane.getViewport().setViewPosition(new Point(0,chatPanel.getHeight()));
            }
        }).start();
    }
    private ImageIcon setIcon(String filepath,int x,int y){
        ImageIcon imageIcon = new ImageIcon(filepath);    // Icon由图片文件形成
        Image image = imageIcon.getImage();                         // 但这个图片太大不适合做Icon//    为把它缩小点，先要取出这个Icon的image ,然后缩放到合适的大小
        Image smallImage = image.getScaledInstance(x,y,Image.SCALE_FAST);//    再由修改后的Image来生成合适的Icon
        return new ImageIcon(smallImage);//   最后设置它为按钮的图片
    }

    public void updateFrame(){
        groupMemberPanel.removeAll();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Vector<UserInfo.FriendsOrGroups> members = InteractWithServer.getGroupMembers(fid);
                for (int i =0;i<members.size();i++){
                    System.out.println("Group Member load:"+members.get(i).getId());
                    addMember(Tools.base64StringToImage(members.get(i).getAvatar()),members.get(i).getId(),members.get(i).getName(),Integer.parseInt(members.get(i).getStatus()));
                }
            }
        }).start();
        groupMemberPanel.validate();
        groupMemberPanel.repaint();
    }
}
