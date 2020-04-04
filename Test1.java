给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

以数组形式返回答案。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。2 <= nums.length <= 500
0 <= nums[i] <= 100

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] bucket=new int[101];
        for(int i:nums){
            bucket[i]++;
        }
        int[] res=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int count=0;
            for(int j=0;j<nums[i];j++){
                count+=bucket[j];
            }
            res[i]=count;
        }
        return res;
    }
}

给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
class Solution {
    public int numberOfSteps (int num) {
        int count=0;
        while(num>0){
            if((num&1)==1){
                num^=1;
            }else{
                num>>=1;
            }
            count++;
        }
        return count;
    }
}

给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    List<String> res=new ArrayList<>();
    int[] dx={1,-1,0,0};
    int[] dy={0,0,1,-1};
    private int row;
    private int col;
    public List<String> findWords(char[][] board, String[] words) {
        row=board.length;
        col=board[0].length;
        Trie trie=new Trie();
        for(String s:words){
            trie.insert(s);
        }
        Node root=trie.root;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int index=board[i][j]-'a';
                if(root.next[index]!=null){
                    backTrack(board,i,j,root,"",new boolean[row][col]);
                }
                //backTrack(board,i,j,root,"",new boolean[row][col]);
            }
        }
        return res;
    }
    private void backTrack(char[][] board,int i,int j,Node root,String s,boolean[][] isVisited){
        if(i<0||j<0||i>=row||j>=col||isVisited[i][j]){
            return;
        }
        root=root.next[board[i][j]-'a'];
        if(root==null){
            return;
        }
        isVisited[i][j]=true;
        s+=board[i][j];
        
        if(root.isEnd){
            root.isEnd=false;
            res.add(s);
        }
        
        for(int k=0;k<4;k++){
            int x=i+dx[k];
            int y=j+dy[k];
            backTrack(board,x,y,root,s,isVisited);
        }
        s=s.substring(0,s.length()-1);
        isVisited[i][j]=false;
    }

}
class Node{
    Node[] next;
    boolean isEnd;
    public Node(){
        next=new Node[26];
    }
}
class Trie{
    Node root;
    public Trie(){
        root=new Node();
    }
    public void insert(String word){
        Node cur=root;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(cur.next[index]==null){
                cur.next[index]=new Node();
            }
            cur=cur.next[index];
        }
        cur.isEnd=true;
    }
}


在二维网格 grid 上，有 4 种类型的方格：

1 表示起始方格。且只有一个起始方格。
2 表示结束方格，且只有一个结束方格。
0 表示我们可以走过的空方格。
-1 表示我们无法跨越的障碍。
返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    private int res;
    private int row;
    private int col;
    int[] dx={1,-1,0,0};
    int[] dy={0,0,1,-1};
    public int uniquePathsIII(int[][] grid) {
        row=grid.length;
        col=grid[0].length;
        int target=1;
        int x=0;
        int y=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==0){
                    target++;
                }
                if(grid[i][j]==1){
                    x=i;
                    y=j;
                }
            }
        }
        back(grid,x,y,target,new boolean[row][col]);
        return res;
    }
    private void back(int[][] grid,int i,int j,int target,boolean[][] isVisited){
        if(i<0||j<0||i>=row||j>=col||grid[i][j]==-1||isVisited[i][j]){
            return;
        }
        if(grid[i][j]==2){
            if(target==0)
               res++;
            return;
        }

        target-=1;
        isVisited[i][j]=true;
        for(int k=0;k<4;k++){
            int x=i+dx[k];
            int y=j+dy[k];
            back(grid,x,y,target,isVisited);
        }
        target+=1;
        isVisited[i][j]=false;
        
    }
}