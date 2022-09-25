package com.hollly.example.tree;

import lombok.Data;

import java.util.List;

/**
 * @author hollly
 * @date 2022/8/26 18:49
 */
@Data
public class MenuVO implements TreeNode<Long>{


    private Long id;


    private Long parentId;


    private List<MenuVO> children;


    private String name;


    private String code;


    @Override
    public void setChildren(List<? extends TreeNode<Long>> childrenList) {
        children = (List<MenuVO>) childrenList;
    }


//    @Override
//    public <T extends TreeNode> void setChildren(List<T> childrenList) {
//        this.children = (List<MenuVO>) childrenList;
//    }


}
