package Model;

public class Floyd {

	int[][] length = null;// 任意两点之间路径长度
	int[][][] route = null;// 任意两点之间的路径
	
	public Floyd(int[][] R) {
		int row = R.length;
		int [][] site = new int [row][row];// 定义任意两点之间经过的点
		int [] singleroute = new int [row];// 记录一条路径
		this.length = R;
		route = new int [row][row][];
		for (int i = 0;i<row;i++) {// 初始化为任意两点之间没有路径
			for(int j = 0;j<row;j++) {
				site[i][j] = -1;
			}
		}
		for(int i = 0;i<row;i++) {// 假设任意两点之间的没有路径
			singleroute[i] = -1;
		}
		for(int i=0;i<row;++i) {
			for(int j=0;j<row;++j) {
				for(int k=0;k<row;++k) {
					if(length[j][k]>length[j][i]+length[i][k]) {// 如果存在更短路径则取更短路径
						length[j][k] = length[j][i]+length[i][k];
						site[j][k] = i;// 把经过的点加入
					}
				}
			}
		}
		for(int i = 0;i<row;i++) {// 求出所有的路径
			int[] point = new int [1];
			for(int j=0;j<row;j++) {
				point[0] = 0;
				singleroute[point[0]++] = i;
				
				Route(site,i,j,singleroute,point);
				route[i][j] = new int [point[0]];
				for(int num=0;num<point[0];num++) {
					route[i][j][num] = singleroute[num];//保存中转节点信息
				}
				
			}
		}
	}
	
	// 输出i到j的路径的实际代码，point[]记录一条路径的长度
	private void Route(int [][] site,int i,int j,int[] singleroute,int[] point) {
		if(i==j) {
			return;
		}
		if(site[i][j]==-1)
			singleroute[point[0]++] = j;
		else {
			Route(site,i,site[i][j],singleroute,point);
			Route(site,site[i][j],j,singleroute,point);
		}
	}
	
	public int[] getRoute(int begin,int stop) {
		return route[begin][stop];
	}
}
