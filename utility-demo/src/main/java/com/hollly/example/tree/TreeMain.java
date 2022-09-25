package com.hollly.example.tree;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hollly
 * @date 2022/8/26 18:45
 */
public class TreeMain {

    public static void main(String[] args) {
        // 实体转换
        MenuVO menuVO = new MenuVO();
        menuVO.setId(1L);
        menuVO.setParentId(0L);
        menuVO.setName("menuVO name");
        menuVO.setCode("menuVO code");


        MenuVO menuVO1 = new MenuVO();
        menuVO1.setId(2L);
        menuVO1.setParentId(0L);
        menuVO1.setName("menuVO name 1");
        menuVO1.setCode("menuVO code 1");


        MenuVO menuVO2 = new MenuVO();
        menuVO2.setId(3L);
        menuVO2.setParentId(0L);
        menuVO2.setName("menuVO name 2");
        menuVO2.setCode("menuVO code 2");


        MenuVO menuVO3 = new MenuVO();
        menuVO3.setId(4L);
        menuVO3.setParentId(1L);
        menuVO3.setName("menuVO name 3");
        menuVO3.setCode("menuVO code 3");


        MenuVO menuVO4 = new MenuVO();
        menuVO4.setId(5L);
        menuVO4.setParentId(1L);
        menuVO4.setName("menuVO name 4");
        menuVO4.setCode("menuVO code 4");

        MenuVO menuVO5 = new MenuVO();
        menuVO5.setId(6L);
        menuVO5.setParentId(2L);
        menuVO5.setName("menuVO name 5");
        menuVO5.setCode("menuVO code 5");

        MenuVO menuVO6 = new MenuVO();
        menuVO6.setId(7L);
        menuVO6.setParentId(4L);
        menuVO6.setName("menuVO name 6");
        menuVO6.setCode("menuVO code 6");


        ArrayList<MenuVO> objects = Lists.newArrayList();
        List<? extends TreeNode<Long>> childrenList = objects;
        menuVO6.setChildren(objects);


//        List<TreeNode<Long>> menuVOS = Lists.newArrayList(menuVO, menuVO1, menuVO2, menuVO3, menuVO4, menuVO5, menuVO6);
        List<MenuVO> menuVOS2 = Lists.newArrayList(menuVO, menuVO1, menuVO2, menuVO3, menuVO4, menuVO5, menuVO6);
        MenuVO root = new MenuVO();
        root.setId(0L);

        TreeBuildUtil.buildTree(menuVOS2, root);

//        List<TreeNode<Long>> treeNodes2 = TreeBuildUtil.buildTree(menuVOS2, root);

        // 结果树
        String s = JSONObject.toJSONString(root.getChildren());


        List<MenuVO> aaa = new ArrayList<>();
        List<? extends TreeNode<Long>> t = aaa;
        List<? super TreeNode<Long>> t2 = null;
        Object object = t2.get(0);
//        List<? extends MenuVO> ttt = aaa;


        System.out.println(s);


//        List<Integer> a = null;
//        List<Number> n = (List<Number>)a;
        // 使用工具类构建整个树状结构



    }

}
