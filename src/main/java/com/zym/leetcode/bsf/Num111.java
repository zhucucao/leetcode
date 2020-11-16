package com.zym.leetcode.bsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Num111 {

    public static void main(String[] args) {


        Integer minLen = Integer.MAX_VALUE;
        Integer length = 0;
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("minLen", minLen);

        int x = hashMap.get("minLen").equals(minLen) ? 0 : hashMap.get("minLen");
        System.out.println(x);
    }

    public void minDepthBackTrack(TreeNode root, HashMap<String, Integer> hashMap, int length) {

        if (root == null) {
            return;
        }
        Integer minLen = hashMap.get("minLen");
        length++;
        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left == null && right == null) {
            if (minLen > length) {
                minLen = length;
                hashMap.put("minLen", minLen);
            }
            return;
        }
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        if (left != null) {
            nodeList.add(left);
        }
        if (right != null) {
            nodeList.add(right);
        }
        for (TreeNode treeNode : nodeList) {
            minDepthBackTrack(treeNode, hashMap, length);
        }
    }


//    public int minDepth(TreeNode root) {
//
//        if(root == null) {
//            return 0;
//        }
//
//        if(root.left == null && root.right == null) {
//            return 1;
//        }
//        int minLen = Integer.MAX_VALUE;
//        if(root.left != null) {
//            minLen = Math.min(minDepth(root.left),minLen);
//        }
//
//        if(root.right != null) {
//            minLen = Math.min(minDepth(root.right),minLen);
//        }
//
//        return minLen + 1;
//    }


/**     BFS
 *     https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/java-di-gui-he-fei-di-gui-liang-chong-fang-shi-de-/
 */

    /**
     * 相当于一层一层的再遍历  每遍历一层 累计+1 如果在同层中找到 叶子节点 则同层的兄弟层不需要再遍历
     * <p>
     *         1
     *    2         3
     *  4
     * 例如这样一个二叉树  初始化 1 根结点入队列
     * 队列不为空 level++
     * 左右节点均不为空则 2 ，3 节点入队
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        // 层数
        int level = 0;

        while (!queue.isEmpty()) {
            level++;
            int levelCount = queue.size();
            for (int i = 0; i < levelCount; i++) {

                TreeNode poll = queue.poll();
                if (poll.left == null && poll.right == null) {
                    return level;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }

            }

        }
        return -1;
    }
}

