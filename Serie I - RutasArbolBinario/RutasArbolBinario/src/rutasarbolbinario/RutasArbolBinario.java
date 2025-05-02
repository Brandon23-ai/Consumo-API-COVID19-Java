/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rutasarbolbinario;

import java.util.List;

/**
 *
 * @author btmor
 */
public class RutasArbolBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                // Construir el árbol del ejemplo
        Integer[] treeValues = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        int target = 22;
        
        TreeNode root = TreeBuilder.buildTreeFromArray(treeValues);
        BinaryTreePathFinder pathFinder = new BinaryTreePathFinder(root);
        List<List<Integer>> result = pathFinder.findPathsWithSum(target);
        
        System.out.println("Rutas que suman " + target + ": " + result);
    }
    
}
