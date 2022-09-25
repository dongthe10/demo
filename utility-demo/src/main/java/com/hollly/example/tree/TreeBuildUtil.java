package com.hollly.example.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 构建树工具类
 *
 * 内存中根据列表构建树，有两种方式，
 * 一、第一种是深度遍历，根据当前节点，构建子节点。 {@link TreeBuildUtil}
 * 二、第二种是直接遍历，根据当前节点，找到parent节点，并把当前节点添加到parent节点的成员变量中（这个变量通常是list）。{@link cn.hutool.core.lang.tree.TreeUtil}
 *
 *
 *
 *
 * @author hollly
 * @date 2022/8/26 18:53
 */
public class TreeBuildUtil {


    /**
     * 构建树
     *
     * @param candidates 待构建树的节点列表
     * @param rootId     根节点id
     * @return 根节点的子节点列表
     */
    public static <T> List<TreeNode<T>> buildTree(List<? extends TreeNode<T>> candidates, T rootId) {
        Map<T, List<TreeNode<T>>> map = candidates.stream().collect(Collectors.groupingBy(TreeNode::getParentId));
        // 根节点的子节点列表
        List<TreeNode<T>> treeNodes = map.getOrDefault(rootId, new ArrayList<>());

        for (TreeNode<T> treeNode : treeNodes) {
            buildChildrenTree(treeNode, map);
        }

        return treeNodes;
    }


    /**
     * 构建树
     *
     * @param candidates 待构建树的节点列表
     * @param root       根节点
     * @return 根节点
     */
    public static <T> TreeNode<T> buildTree(List<? extends TreeNode<T>> candidates, TreeNode<T> root) {
        List<TreeNode<T>> treeNodes = buildTree(candidates, root.getId());
        root.setChildren(treeNodes);
        return root;
    }

    /**
     * 构建树的子节点
     *
     * @param parentNode 待构建子节点的父节点
     * @param map        <parentId, List<TreeNode>
     * @param <T>        treeNode中id的类型
     */
    private static <T> void buildChildrenTree(TreeNode<T> parentNode, Map<T, List<TreeNode<T>>> map) {
        List<TreeNode<T>> childrenTreeNodes = map.getOrDefault(parentNode.getId(), new ArrayList<>());

        parentNode.setChildren(childrenTreeNodes);
        for (TreeNode<T> childrenTreeNode : childrenTreeNodes) {
            buildChildrenTree(childrenTreeNode, map);
        }
    }
}
