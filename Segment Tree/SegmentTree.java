public class SegmentTree {
    int size;
    int[] tree;
    int[] arr;
    SegmentTree(int[] inp){
        arr=inp;
        size=arr.length;
        tree=new int[4*size];
        build(0,0,size-1);
    }
    void build(int node, int start, int end){
        if(start==end){
            tree[node]=arr[start];
        }
        else{
            int mid=(start+end)/2;
            build(2*node+1,start,mid);
            build(2*node+2,mid+1,end);
            tree[node]=tree[2*node+1]+tree[2*node+2]; 
        }
    }
    int query(int node, int l, int r){
        return querycal(node,0,size-1,l,r);
    }
    int querycal(int node, int start, int end, int l, int r){
        if(r<start || l>end){
            return 0;
        }
        if(l<=start && r>=end){
            return tree[node];
        }
        int mid=(start+end)/2;
        return querycal(2*node+1, start, mid, l, r)+querycal(2*node+2, mid+1, end, l, r);
    }
    void update(int node, int ind, int val){
        updateutil(node,0,size-1,ind,val);
    }
    void updateutil(int node, int start, int end, int ind, int val){
        if(start==end){
            tree[node]=val;
        }
        int mid=(start+end)/2;
        if(ind<=mid){
            updateutil(2*node+1, start, mid, ind, val);
        }
        else{
            updateutil(2*node+2, mid+1, end, ind, val);
        }
        tree[node]=tree[2*node+1]+tree[2*node+2];
    }
}

