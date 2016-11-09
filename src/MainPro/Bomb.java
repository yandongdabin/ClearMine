package MainPro;

import javax.swing.JOptionPane;

public class Bomb {

	public Bomb(int row,int col,int count,int firD,int secD)//使得首次点击不出现游戏失败的情况
	{
		this.row = row;
		this.col = col;
		this.count = count;
		this.firD = firD;
		this.secD = secD;
		b= new int[row][col];
		a= new int[row][col];
	}
	public void set(int row,int col,int count)
	{
		this.row = row;
		this.col = col;
		this.count = count;
		a = new int[count][count];
	}
	public int [][] getStatus()
	{
		return b;
	}
	public void generateBomb()
	{
		//布雷
		for(int i=0;i<count;i++)
		{
			int r = (int)((Math.random()*100)%(row));
			int c = (int)((Math.random()*100)%(col));
			//System.out.println(r+" "+c);
			if(a[r][c]==0 &&(r!=firD && c!=secD))
			{
				System.out.println(r+" "+c);
				a[r][c] = -1;//表示访问过
				b[r][c] = -1;//代表有雷
			}
			else i--;
		}
		//初始化参数
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
			{
				if(a[i][j]!=-1)
				{
					int c = 0;
					int temp[][] = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
					for(int k=0;k<temp.length;k++)
					{
						int pa1 = i+temp[k][0];
						int pa2 = j+temp[k][1];
						if(pa1>=0 && pa1<row && pa2>=0 && pa2<col)
						{
							if(a[pa1][pa2]==-1)c++;
						}
					}
					b[i][j] = c;
				}
			}
	}
	
	private int a[][];
	private int b[][];
	private int count,row,col,firD,secD;
	private boolean visited[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bomb b= new Bomb(9,9,10,1,1);
		b.generateBomb();
		int [][]a = b.getStatus();
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
				System.out.print(a[i][j]+"  ");
			System.out.println();
		}
		//JOptionPane.showConfirmDialog(null,"您已顺利完成游戏，再来一次？","提醒",JOptionPane.OK_CANCEL_OPTION);
		JOptionPane.showInputDialog(null,"请输入您的姓名");
	}

}
