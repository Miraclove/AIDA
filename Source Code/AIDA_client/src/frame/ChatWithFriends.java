package frame;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatWithFriends extends ChatFrame {
    public static void main(String[] args) throws IOException {
        ChatWithFriends test=new ChatWithFriends();
    }
    protected JButton friendsNameButton,minimize,closeButton,emojiButton,
            voiceButton,pictureButton,fileButton,phoneButton,cameraButton,sendButton;
    protected JPanel mainControlPanel,chatPanel,functionPanel,inputPanel,sendPanel,controlPanel,sysPanel;
    protected JScrollPane scrollPane;
    private int height;

    private ImageIcon setIcon(String filepath,int x,int y){
        ImageIcon imageIcon = new ImageIcon(filepath);    // Icon由图片文件形成
        Image image = imageIcon.getImage();                         // 但这个图片太大不适合做Icon//    为把它缩小点，先要取出这个Icon的image ,然后缩放到合适的大小
        Image smallImage = image.getScaledInstance(x,y,Image.SCALE_FAST);//    再由修改后的Image来生成合适的Icon
        return new ImageIcon(smallImage);//   最后设置它为按钮的图片
    }

    public void updateChat(String avatarPath,String userName,String sendTime,String message,int side) throws IOException {

        height=height+60;
        chatPanel.setPreferredSize(new Dimension(500,height));
        chatPanel.add(new SingleText(avatarPath,userName,sendTime,message,side));
        JScrollBar bar=scrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum()+50);
    }

    private void showEmojiMenu(){
        EmojiMenu emojiMenu=new EmojiMenu(this);
    }

    public ChatWithFriends() throws IOException {
        init();
        this.setLayout(new BorderLayout());
        this.add(mainControlPanel,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(controlPanel,BorderLayout.SOUTH);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updateChat("res/Avatar/head-test.JPG","Mike",df.format(new Date()),"1111",0);
        this.setSize(500,520);
        setLocationRelativeTo(null);
        this.setVisible(true);


    }
    private void init(){

        //friends name button
        friendsNameButton=new JButton();
        friendsNameButton.setText("white");
        friendsNameButton.setFont(new Font("微软雅黑",Font.BOLD,16));
        friendsNameButton.setForeground(Color.WHITE);
        friendsNameButton.setFocusPainted(false);
        friendsNameButton.setContentAreaFilled(false);
        friendsNameButton.setBorderPainted(false);
        Point origin=new Point();
        friendsNameButton.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = ChatWithFriends.this.getLocation();
                ChatWithFriends.this.setLocation(
                        p.x + (e.getX() - origin.x),
                        p.y + (e.getY() - origin.y));
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        friendsNameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
                ChatWithFriends.this.dispose();
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


        voiceButton=new JButton();
        voiceButton.setMargin(new Insets(0, 0, 0, 0));
        voiceButton.setPreferredSize(new Dimension(40,40));
        voiceButton.setContentAreaFilled(false); // set don't draw message area
        voiceButton.setBorderPainted(false); // set don't draw border
        voiceButton.setFocusPainted(false);
        voiceButton.setIcon(setIcon("res/Icon/audio.png",30,30));


        pictureButton=new JButton();
        pictureButton.setMargin(new Insets(0, 0, 0, 0));
        pictureButton.setPreferredSize(new Dimension(40,40));
        pictureButton.setContentAreaFilled(false); // set don't draw message area
        pictureButton.setBorderPainted(false); // set don't draw border
        pictureButton.setFocusPainted(false);
        pictureButton.setIcon(setIcon("res/Icon/image.png",30,30));


        fileButton=new JButton();
        fileButton.setMargin(new Insets(0, 0, 0, 0));
        fileButton.setPreferredSize(new Dimension(40,40));
        fileButton.setContentAreaFilled(false); // set don't draw message area
        fileButton.setBorderPainted(false); // set don't draw border
        fileButton.setFocusPainted(false);
        fileButton.setIcon(setIcon("res/Icon/folder.png",30,30));


        phoneButton=new JButton();
        phoneButton.setMargin(new Insets(0, 0, 0, 0));
        phoneButton.setPreferredSize(new Dimension(40,40));
        phoneButton.setContentAreaFilled(false); // set don't draw message area
        phoneButton.setBorderPainted(false); // set don't draw border
        phoneButton.setFocusPainted(false);
        phoneButton.setIcon(setIcon("res/Icon/phone.png",30,30));


        cameraButton=new JButton();
        cameraButton.setMargin(new Insets(0, 0, 0, 0));
        cameraButton.setPreferredSize(new Dimension(40,40));
        cameraButton.setContentAreaFilled(false); // set don't draw message area
        cameraButton.setBorderPainted(false); // set don't draw border
        cameraButton.setFocusPainted(false);
        cameraButton.setIcon(setIcon("res/Icon/camera.png",30,30));

        sendButton=new JButton();
        sendButton.setText("发送");
        //sendButton.setPreferredSize(new Dimension(80,30));
        sendButton.setBackground(new Color(22,155,213));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("微软雅黑",Font.BOLD,16));
        sendButton.setBorderPainted(false); // set don't draw border
        sendButton.setFocusPainted(false);
        sendButton.setBounds(400,0,80,30);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date=df.format(new Date());
                try {
                    updateChat("res/Avatar/head-test.JPG","Ponny",date,input.getText(),1);
                    scrollPane.getViewport().setViewPosition(new Point(0,chatPanel.getHeight()));
                    input.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        input=new JTextArea("");
        input.setPreferredSize(new Dimension(500,300));
        input.setBackground(new Color(128,255,255));
        input.setBorder(null);


        mainControlPanel =new JPanel();
        chatPanel=new JPanel();
        functionPanel=new JPanel();
        inputPanel=new JPanel();
        sendPanel=new JPanel();
        controlPanel=new JPanel();
        sysPanel=new JPanel();


        mainControlPanel.setPreferredSize(new Dimension(500,40));

        chatPanel.setPreferredSize(new Dimension(500,300));
        functionPanel.setPreferredSize(new Dimension(500,40));
        inputPanel.setPreferredSize(new Dimension(500,100));
        sendPanel.setPreferredSize(new Dimension(500,40));

        sysPanel.setBackground(new Color(40,138,221));
        sysPanel.setLayout(new BorderLayout());
        sysPanel.add(minimize,BorderLayout.WEST);
        sysPanel.add(closeButton,BorderLayout.EAST);

        mainControlPanel.setLayout(new BorderLayout());
        mainControlPanel.setBackground(new Color(40,138,221));
        mainControlPanel.add(friendsNameButton,BorderLayout.CENTER);
        mainControlPanel.add(sysPanel,BorderLayout.EAST);

        chatPanel.setBackground(new Color(243,249,253));
        chatPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        scrollPane=new JScrollPane(chatPanel);
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
        functionPanel.add(voiceButton);
        functionPanel.add(pictureButton);
        functionPanel.add(fileButton);
        functionPanel.add(phoneButton);
        functionPanel.add(cameraButton);
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

    }

}
