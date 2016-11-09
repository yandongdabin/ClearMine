package UI;

import java.awt.BorderLayout;
import MainPro.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener{

	MainFrame()
	{
		
		File file = new File("info.dat");
		
		//���ļ�������ʱ�½�һ��
		if(!file.exists())
		{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("info.dat"));
			writer.write("1");
			writer.newLine();
			writer.write("1000");
			writer.newLine();
			writer.write("����");
			writer.newLine();
			writer.write("1000");
			writer.newLine();
			writer.write("����");
			writer.newLine();
			writer.write("1000");
			writer.newLine();
			writer.write("����");
			writer.newLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally
		{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
		row = 9;
		col = 9;
		count = 10;
		timer = new Timer(1000,(ActionListener)this);
		visited = new boolean[row][col];
		btnStatus = new int[row][col];
		BufferedReader reader=null;
		try {
			reader =new BufferedReader(new FileReader("info.dat"));
			String str = reader.readLine();
			System.out.println(str);
			int level = Integer.parseInt(str);
			if(level == 1)
			{
				row = 9;
				col = 9;
				count =10;
				thelevel = 1;
			}
			else if(level == 2)
			{
				row = 16;
				col = 16;
				count = 40;
				thelevel = 2;
			}
			else if(level == 3)
			{
				row = 16;
				col = 30;
				count = 99;
				thelevel = 3;
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			reader =new BufferedReader(new FileReader("info.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String str = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Bomb b= new Bomb(row,col,count);
		//b.generateBomb();
		//status = b.getStatus();
		
		//timer.start();
	}
	public void InitUI()
	{
		this.setTitle("ɨ��");
		
		//��Ӳ˵���
		JMenuBar bar = new JMenuBar();
		JMenu menu1 = new JMenu("��Ϸ");
		JMenu menu2 = new JMenu("����");
		JMenuItem stage1 = new JMenuItem("����");
		stage1.addActionListener(this);
		JMenuItem stage2 = new JMenuItem("�м�");
		stage2.addActionListener(this);
		JMenuItem stage3 = new JMenuItem("�߼�");
		stage3.addActionListener(this);
		JMenuItem stage4 = new JMenuItem("Ӣ�۰�");
		stage4.addActionListener(this);
		JMenuItem help = new JMenuItem("�鿴����");
		help.addActionListener(this);
		JMenuItem about = new JMenuItem("����");
		about.addActionListener(this);
		menu1.add(stage1);
		menu1.add(stage2);
		menu1.add(stage3);
		menu1.add(stage4);
		menu2.add(help);
		menu2.add(about);
		bar.add(menu1);
		bar.add(menu2);
		this.setJMenuBar(bar);
		
		//��ʼ����Ϸ����
		this.setLayout(new FlowLayout());
		this.getContentPane().setBackground(Color.BLACK);//������Ϸ�ĵ�ɫ
		changeUI();
	}
	public void changeUI()
	{
		//this.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		decoration = new JPanel();
		decoration.setLayout(new FlowLayout());
		ImageIcon icon = new ImageIcon("bomb.png");
		ImageIcon icon0 = new ImageIcon("0.png");
		ImageIcon icon1= new ImageIcon("1.png");
		ImageIcon icon2 = new ImageIcon("2.png");
		ImageIcon icon3 = new ImageIcon("3.png");
		JLabel label = new JLabel(icon);
		JLabel label_0 = new JLabel(icon0);
		JLabel label_1 = new JLabel(icon1);
		JLabel label_2 = new JLabel(icon2);
		JLabel label_3 = new JLabel(icon3);
		decoration.add(label_0);
		//decoration.add(label_1);
		decoration.add(label);
		//decoration.add(label_2);
		decoration.add(label_3);
		
		
		top = new JPanel();
		
		bottom = new JPanel();
		
		//��ʾ��ť
		btn = new myBtn[row][col];
		//System.out.println(btn.length);
		top.setLayout(new GridLayout(row,col));
		//Bomb b= new Bomb(row,col,count);
		//b.generateBomb();
		//status = b.getStatus();
		btnStatus = new int[row][col];
		visited = new boolean[row][col];
		for(int i =0;i<row;i++)
			for(int j=0;j<col;j++)
			{
				//System.out.println(i+" "+j);
				btn[i][j] = new myBtn("  ");
				btn[i][j].setMargin(new Insets(0,0,0,0));//ʹ������ȫ��ʾ����
			    btn[i][j].setForeground(Color.BLUE);
			    btn[i][j].set(i, j, 0);//�Ȱ����кż���
			    btn[i][j].addMouseListener(new btnmouseListener(this));
				top.add(btn[i][j]);
			}
		
		//��ʾ����������ʱ��
		JLabel label1 = new JLabel("����:   ");
		JLabel label2 = new JLabel("ʱ��:   ");
		minenum = new JTextField(Integer.toString(count));
		minenum.setColumns(5);
		minenum.setEditable(false);
		bottom.add(label1);
		bottom.add(minenum);
		
		time = new JTextField("0");
		time.setColumns(5);
		time.setEditable(false);
		bottom.add(label2);
		bottom.add(time);
		validate = count;
		
		mainPanel.add(decoration,BorderLayout.NORTH);
		mainPanel.add(top,BorderLayout.CENTER);
		mainPanel.add(bottom,BorderLayout.SOUTH);
		//mainPanel.setBounds(75, 50, 450, 450);*/
		this.getContentPane().add(mainPanel);
		Dimension dimFrame = this.getPreferredSize();
		//this.getContentPane().add(bottom);
		//this.setLocationRelativeTo(null);//������ʾ
		Toolkit tool=getToolkit();    //Toolkit���ǳ����࣬����ֱ���ù��췽����������������Java�ṩ��Toolkit�����getToolkit()��������������������

		//ʹ�����ھ�����ʾ
		Dimension dim=tool.getScreenSize();
		this.setLocation((dim.width-dimFrame.width)/2,(dim.height-dimFrame.height)/2);
	    //System.out.println(dim.width+" "+dim.height);
		Dimension dim1 = mainPanel.getPreferredSize();
	    this.setSize(dim1.width+25,dim1.height+75);
	    
	}
	public void gameStart()
	{
		//this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);//������ʾ
		//this.setDefaultLookAndFeelDecorated(true);
		this.setVisible(true);
	}
	public  void restartGame()
	{
		this.getContentPane().remove(mainPanel);
		//this.getContentPane().remove(bottom);
		
		this.changeUI();
		time.setText("0");
		btnmouseListener.first = true;
		timer.stop();
		this.repaint();
		this.pack();	
	}
	public void gameWin()
	{
		timer.stop();
		System.out.println("win");
		String str[] = new String[3];
		int buffer[] = new int[3];
		String name[] = new String[3];
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("info.dat"));
			reader.readLine();
			for(int i=0;i<3;i++)
			{
				str[i] = reader.readLine();
				name[i] = reader.readLine();
				System.out.println(str[i]);
			}
			for(int i=0;i<3;i++)
			{
				buffer[i] = Integer.parseInt(str[i]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				reader.close();
				//writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(buffer[thelevel-1]>Integer.parseInt(time.getText()))
		{
			buffer[thelevel-1] = Integer.parseInt(time.getText());
			String newname = JOptionPane.showInputDialog(null,"��ϲ�� ���������µļ�¼����������������");
			newname = newname.trim();
			if(newname.equals(""))newname="����";
			name[thelevel-1] = newname;
			try {
				writer = new BufferedWriter(new FileWriter("info.dat"));
				writer.write(Integer.toString(thelevel));
				writer.newLine();
				for(int i=0;i<3;i++)
				{
					writer.write(Integer.toString(buffer[i]));
					writer.newLine();
					writer.write(name[i]);
					writer.newLine();
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String str1 = "����һ��?";
			
			if(JOptionPane.OK_OPTION ==JOptionPane.showConfirmDialog(null,str1,"����",JOptionPane.OK_CANCEL_OPTION))
			{
				restartGame();
			}
			else
			{
				System.exit(0);
			}
		}
		else
		{
			String str1 ="����˳�������Ϸ����ǰ�ļ�¼��:"+buffer[thelevel-1]+"����Ŭ����������һ��?";
			if(JOptionPane.OK_OPTION ==JOptionPane.showConfirmDialog(null,str1,"����",JOptionPane.OK_CANCEL_OPTION))
			{
				restartGame();
			}
			else
			{
				System.exit(0);
			}
		}
		//JOptionPane.showMessageDialog(null,"ʤ��");
	}
	/*public static void decValidate()
	{
		validate--;
		if(validate==0)
			gameWin();
	}*/
	public void checkWin()
	{
		int co = 0;
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
			{
				if(btnStatus[i][j]==1 && status[i][j]==-1)
				{
					co++;
				}
			}
		if(co==count)
		{
			gameWin();
		}
	}
	public static void incValidate()
	{
		validate++;
	}
	public void changelevel()
	{
		String str[] = new String[3];
		String name[] = new String[3];
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("info.dat"));
			reader.readLine();
			for(int i=0;i<3;i++)
			{
				str[i] = reader.readLine();
				name[i] = reader.readLine();
				System.out.println(str[i]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				reader.close();
				//writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writer = new BufferedWriter(new FileWriter("info.dat"));
			writer.write(Integer.toString(thelevel));
			writer.newLine();
			for(int i=0;i<3;i++)
			{
				writer.write(str[i]);
				writer.newLine();
				writer.write(name[i]);
				writer.newLine();
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer)
		{
			String str = time.getText().trim();
			int t = Integer.parseInt(str);
			t +=1;
			String str1 = Integer.toString(t);
			//System.out.println(str1);
			time.setText(str1);
		}
		
		else if(e.getActionCommand() =="����")
		{
			row = 9;
			col = 9;
			count =10;
		
			//System.out.println("this");
			thelevel = 1;
			changelevel();
			this.getContentPane().remove(mainPanel);
			//this.getContentPane().remove(bottom);
			
			this.changeUI();
			time.setText("0");
			btnmouseListener.first = true;
			timer.stop();
			this.repaint();
			this.pack();	
		}
		else if(e.getActionCommand() == "�м�")
		{
			System.out.println("�м�");
			row = 16;
			col = 16;
			count = 40;
			thelevel = 2;
			changelevel();
			this.getContentPane().remove(mainPanel);
			changeUI();
			time.setText("0");
			timer.stop();
			btnmouseListener.first = true;
			this.getContentPane().repaint();
			this.pack();
		}
		else if(e.getActionCommand() =="�߼�")
		{
			row = 16;
			col = 30;
			count = 99;
			thelevel = 3;
			changelevel();
			this.getContentPane().remove(mainPanel);
			this.changeUI();
			time.setText("0");
			timer.stop();
			btnmouseListener.first = true;
			this.getContentPane().repaint();
			this.pack();
		}
		else if(e.getActionCommand() == "�鿴����")
		{
			JDialog dialog= new JDialog(this,"����");
			JTextArea area = new JTextArea(helpStr,3,100);
			dialog.add(area);
			dialog.setSize(200,200);
			area.setEditable(false);

			//�Ի�����ж���
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
		else if(e.getActionCommand() == "����")
		{
			JOptionPane.showMessageDialog(this,"ɨ��"+"    "+"���ߣ��ණ");
		}
		else if(e.getActionCommand() == "Ӣ�۰�")
		{
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader("info.dat"));
				String str[] = new String[3];
				String name[] = new String[3];
				reader.readLine();
				for(int i=0;i<3;i++)
				{
					str[i] = reader.readLine();
					name[i] = reader.readLine();
				}
				String info = "����:  "+str[0]+"  "+name[0]+"\n�м�:  "+str[1]+"  "+name[1]+"\n�߼�:  "+str[2]+"  "+name[2];
				JOptionPane.showMessageDialog(this,info,"Ӣ�۰�", 1, null);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			finally
			{
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		
	}
	public static void starttime()
	{
		timer.start();
	}
	public static void stoptime()
	{
		timer.stop();
	}
	public static int getRow()
	{
		return row;
	}
	public static int getCount()
	{
		return count;
	}
	public static int getCol()
	{
		return col;
	}
	public static myBtn[][] getBtn()
	{
		return btn;
	}
	public static boolean [][]getVisited()
	{
		return visited;
	}
	public static int[][]getBtnStatus()
	{
		return btnStatus;
	}
	public static int [][]getStatus()
	{
		return status;
	}
	private int thelevel;
	private static int count;
	private static int row;
	private static int col;//�׵�������
	private static myBtn btn[][];
	private JTextField minenum;
	private JTextField time;
	static private Timer timer;//��ʱ�� 
	private JPanel mainPanel;
	private JPanel bottom;
	private JPanel decoration;
	private JPanel top;
	public static int[][]status;
	private static int [][]btnStatus;
	private String helpStr ="1������ͨ���˵�ѡ��ѡ���Ѷ�\n2�����Բ鿴��߷�\n3����Ϸ������ο�windows\n�µ�ɨ����Ϸ";
	public static boolean visited[][];
	public static int validate;
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.InitUI();
		frame.gameStart();
		

	}

	

}
//�Զ��尴ť��
class myBtn extends JButton
{
	myBtn(String str)
	{
		super(str);
		Toolkit tool=getToolkit();    //Toolkit���ǳ����࣬����ֱ���ù��췽����������������Java�ṩ��Toolkit�����getToolkit()��������������������

		//ʹ�����ھ�����ʾ
		Dimension dim=tool.getScreenSize();
		this.setFont(new Font("����",Font.BOLD, 20*dim.height/768));
		this.setBackground(Color.pink);
		this.setForeground(Color.BLUE);  

	}
	public void set(int row,int col,int num)
	{
		this.row = row;
		this.col = col;
		this.num = num;
	}
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	public String getNum()
	{
		return Integer.toString(num);
	}
	/*public boolean equals(Object o)
	{
		myBtn btn = (myBtn)o;
		if(btn.getRow()==row && btn.getCol()==col)
			return true;
		else return false;
	}*/
	private int row;
	private int col;
	private int num; //0��ʾ�� -1��ʾ����
	
}

class btnmouseListener implements MouseListener
{

	
	btnmouseListener(MainFrame frame)
	{
		this.frame = frame;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//��ԭ����ڵڶ��Ҽ�ԭ��
		if(e.getButton()==e.BUTTON1)
		{
			if(e.getClickCount()==1)
			{
				
				myBtn btn  = (myBtn)e.getSource();
				myBtn [][] btns = MainFrame.getBtn();
				if(first)
				{
					MainFrame.starttime();
					Bomb b= new Bomb(MainFrame.getRow(),MainFrame.getCol(),MainFrame.getCount(),btn.getRow(),btn.getCol());
					System.out.println("row= "+btn.getRow()+" col = "+btn.getCol());
					b.generateBomb();
					MainFrame.status = b.getStatus();
					for(int i=0;i<btns.length;i++)
						for(int j=0;j<btns[i].length;j++)
						{
							btns[i][j].set(i, j, MainFrame.status[i][j]);
						}
					first = false;
				}
				
				
				LinkedList<myBtn> queue = new LinkedList<myBtn>();
				//btn.setForeground(Color.green);
				
				//�ڰ�ť����ǵ�����²�������
				int btnStatus[][]=MainFrame.getBtnStatus();
				if(btnStatus[btn.getRow()][btn.getCol()]!=0)
					return;
				
				boolean visited[][]=MainFrame.getVisited();
				//��Ϸ���������������㵽��
				if(Integer.parseInt(btn.getNum())==-1)
				{
					System.out.println("Game Over");
					if(JOptionPane.OK_OPTION ==JOptionPane.showConfirmDialog(null,"��Ϸʧ�ܣ�������","����",JOptionPane.OK_CANCEL_OPTION))
					{
						frame.restartGame();
					}
					else
					{
						System.exit(0);
					}
					
				}
				//���Ϊ��ʱ�Ĺ�ȱ���
				if(Integer.parseInt(btn.getNum())==0)
				{
					btn.setEnabled(false);
					btn.setBackground(Color.white);
					
					
					int firD = MainFrame.getRow();
					int secD = MainFrame.getCol();
					int row = btn.getRow();
					int col = btn.getCol();
					visited[row][col] = true;
					//System.out.println("row = "+row+" "+col);
					int temp[][] = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
					for(int i=0;i<temp.length;i++)
					{
						int pa1 = row+temp[i][0];
						int pa2 = col+temp[i][1];
						
						
						if(pa1>=0 && pa1<firD && pa2>=0 && pa2<secD)
						{
							//btns[pa1][pa2].setBackground(Color.magenta);
							//System.out.println("pa1 = "+pa1+" "+pa2);
							btns[pa1][pa2].setEnabled(false);
							if(!visited[pa1][pa2])
							{
								
								if(Integer.parseInt(btns[pa1][pa2].getNum())==0)
								{
									btns[pa1][pa2].setBackground(Color.white);
									queue.add(btns[pa1][pa2]);
								}
								else
								{
									btns[pa1][pa2].setBackground(Color.orange);
									btns[pa1][pa2].setText(btns[pa1][pa2].getNum());
								}
								visited[pa1][pa2] = true;
							}
						}
					}
		
					
				}
				while(queue.size()>0)
				{
					myBtn btn1 = queue.poll();
					int row = btn1.getRow();
					int col = btn1.getCol();
					int firD = MainFrame.getRow();
					int secD = MainFrame.getCol();
					int temp[][] = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
					//System.out.println("temp length ="+temp.length);
					for(int i=0;i<temp.length;i++)
					{
						int pa1 = row+temp[i][0];
						int pa2 = col+temp[i][1];
						if(pa1>=0 && pa1<firD && pa2>=0 && pa2<secD)
						{
							if(!visited[pa1][pa2])
							{
								btns[pa1][pa2].setEnabled(false);
								
								if(Integer.parseInt(btns[pa1][pa2].getNum())==0 )
								{
									btns[pa1][pa2].setBackground(Color.white);
									queue.add(btns[pa1][pa2]);
								}
								else
								{
									btns[pa1][pa2].setBackground(Color.orange);
									btns[pa1][pa2].setText(btns[pa1][pa2].getNum());
								}
								visited[pa1][pa2] = true;
							}
						}
					}
			
					
				}
				if(Integer.parseInt(btn.getNum())==0)return;
				if(!visited[btn.getRow()][btn.getCol()])
				{
					
					btn.setText(btn.getNum());
					btn.setBackground(Color.orange);
					//if(btn.)
					visited[btn.getRow()][btn.getCol()] = true;
					btn.setEnabled(false);
				}
				
			
			}
			else if(e.getClickCount()==2)
			{
				boolean [][]visited = MainFrame.getVisited();
				myBtn btn = (myBtn)e.getSource();
				if(visited[btn.getRow()][btn.getCol()])
				{
					//�ж���Ϸ�Ƿ�����ĵ�һ����
					int row = btn.getRow();
					int col = btn.getCol();
					int firD = MainFrame.getRow();
					int secD = MainFrame.getCol();
					int count = 0;
					int bombCount = Integer.parseInt(btn.getNum());
					int right =0;
					int btnStatus[][]=MainFrame.getBtnStatus();
					int status[][]=MainFrame.getStatus();
					myBtn [][]btns = MainFrame.getBtn();
					int temp[][] = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
					for(int i=0;i<temp.length;i++)
						{
							int pa1=row+temp[i][0];
							int pa2=col+temp[i][1];
							if((pa1>=0 && pa1<firD) &&(pa2>=0 && pa2<secD))
							{
								if(btnStatus[pa1][pa2]==1)
									count++;
								if(btnStatus[pa1][pa2]==1 && status[pa1][pa2]==-1)
									right++;
							}
						}
				
					//�������Ϊ������Χ�׺�Ӧ
					if(count == status[row][col])
					{
						LinkedList<myBtn> queue = new LinkedList<myBtn>();
						if(right==count)
						{
							for(int i=0;i<temp.length;i++)
							{
								int pa1=row+temp[i][0];
								int pa2=col+temp[i][1];
								if((pa1>=0 && pa1<firD) &&(pa2>=0 && pa2<secD))
								{
									if(!visited[pa1][pa2] && btnStatus[pa1][pa2]!=1)//δ�����ʹ���û�б��Ϊ��
									{
										btns[pa1][pa2].setEnabled(false);
										visited[pa1][pa2] = true;
										if(Integer.parseInt(btns[pa1][pa2].getNum())==0)
										{
											btns[pa1][pa2].setBackground(Color.white);
											queue.add(btns[pa1][pa2]);
										}
										else
										{
											if(btnStatus[pa1][pa2]!=1)
											{
												btns[pa1][pa2].setBackground(Color.orange);
												btns[pa1][pa2].setText(btns[pa1][pa2].getNum());
											}
										}
										
									}
								}
							}
							
							while(queue.size()>0)
							{
								myBtn btn1 = queue.poll();
								int row1 = btn1.getRow();
								int col1 = btn1.getCol();
								//System.out.println("temp length ="+temp.length);
								for(int i=0;i<temp.length;i++)
								{
									int pa1 = row1+temp[i][0];
									int pa2 = col1+temp[i][1];
									if(pa1>=0 && pa1<firD && pa2>=0 && pa2<secD)
									{
										if(!visited[pa1][pa2] && btnStatus[pa1][pa2]!=1)
										{
											
											btns[pa1][pa2].setEnabled(false);
											if(Integer.parseInt(btns[pa1][pa2].getNum())==0 )
											{
												btns[pa1][pa2].setBackground(Color.white);
												queue.add(btns[pa1][pa2]);
											}
											else
											{
												btns[pa1][pa2].setBackground(Color.orange);
												btns[pa1][pa2].setText(btns[pa1][pa2].getNum());
											}
											visited[pa1][pa2] = true;
										}
									}
								}
							}//end-while
						}
						else
						{
							System.out.println("Game Over");
							if(JOptionPane.OK_OPTION ==JOptionPane.showConfirmDialog(null,"��Ϸʧ�ܣ�������","����",JOptionPane.OK_CANCEL_OPTION))
							{
								frame.restartGame();
							}
							else
							{
								System.exit(0);
							}
						}
					}
				}
				
				
			}
		}
		else if(e.getButton()==e.BUTTON3) //�Ҽ�
		{
			myBtn btn= (myBtn)e.getSource();
			int[][] btnStatus = MainFrame.getBtnStatus();
			boolean visited[][] = MainFrame.getVisited();
			int row = btn.getRow();
			int col = btn.getCol();
			if(visited[row][col])return;
			if(btnStatus[row][col]==0)
			{
				btnStatus[row][col] = 1;
				btn.setText("Y");
				frame.checkWin();
			}
			else if(btnStatus[row][col]==1)
			{
				btnStatus[row][col] = 2;
				btn.setText("?");
			}
			else if(btnStatus[row][col]==2)
			{
				btnStatus[row][col] = 0;
				btn.setText(" ");
			}
			//if()
			//int no = Integer.parseInt(btn.getNum());
			//String str="image/"+1+"t.png"; 
			//System.out.println(str);
			//ImageIcon icon = new ImageIcon(str);
			
			//ImageIcon icon = new ImageIcon("flag.gif");
			//btn.setIcon(icon);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static boolean first = true;
	ImageIcon []numIcon = new ImageIcon[9];
	private MainFrame frame;
	
	
}
