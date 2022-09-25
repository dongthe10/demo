package com.hollly.example.tree;

import java.util.List;

/**
 *
 *
 *
 * @author hollly
 * @date 2022/8/26 18:45
 */
public interface TreeNode<T> {


    T getId();

    T getParentId();


//    <T extends TreeNode> void setChildren(List<T> childrenList);
    void setChildren(List<? extends TreeNode<T>> childrenList);

//    List<TreeNode<T>> getChildren();

}
