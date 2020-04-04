给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
class Solution {
    public int trap(int[] height) {
        int len=height.length;
        int[] maxLeft=new int[len];
        int[] maxRight=new int[len];
        for(int i=1;i<len-1;i++){
            maxLeft[i]=Math.max(maxLeft[i-1],height[i-1]);
        }
        for(int i=len-2;i>0;i--){
            maxRight[i]=Math.max(maxRight[i+1],height[i+1]);
        }
        int res=0;
        for(int i=1;i<len-1;i++){
            int tmp=Math.min(maxLeft[i],maxRight[i]);
            if(tmp>height[i]){
                res+=tmp-height[i];
            }
        }
        return res;
    }
}

class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack=new Stack<>();
        int i=0;
        int res=0;
        while(i<height.length){
            while(!stack.isEmpty()&&height[stack.peek()]<height[i]){
                int tmp=stack.pop();
                if(stack.isEmpty()) break;
                int len=(i-stack.peek()-1);
                int hi=Math.min(height[i],height[stack.peek()])-height[tmp];
                res+=(hi)*len;
        }
        stack.push(i++);
    }
    return res;
}
}

