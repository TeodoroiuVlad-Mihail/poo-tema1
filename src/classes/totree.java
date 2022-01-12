package classes;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;

/**
 * TreeNode class essentials
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class HowTo {
    /**
     * Initial call
     */
    public TreeNode sortedToABC(int[] arr) {
        if (arr.length == 0)
            return null;
        else {
            return sortedToABC(arr, 0, arr.length - 1);
        }
    }
    /**
     * Recursive solution
     */
    public TreeNode sortedToABC(int[] arr, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        TreeNode treeNode = new TreeNode(arr[mid]);
        treeNode.left = sortedToABC(arr, start, mid - 1);
        treeNode.right = sortedToABC(arr, mid + 1, end);

        return treeNode;
    }
}