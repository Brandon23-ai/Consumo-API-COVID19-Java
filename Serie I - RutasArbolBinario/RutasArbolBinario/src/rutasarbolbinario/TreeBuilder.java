/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rutasarbolbinario;

import java.util.LinkedList;
import java.util.Queue;

public class TreeBuilder {
    /**
     * Construye un árbol binario a partir de un array de valores (representación nivel por nivel).
     * @param values Array de valores, donde null representa nodos vacíos
     * @return Raíz del árbol construido
     */
    public static TreeNode buildTreeFromArray(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int index = 1;
        while (!queue.isEmpty() && index < values.length) {
            TreeNode current = queue.poll();
            
            if (index < values.length && values[index] != null) {
                current.left = new TreeNode(values[index]);
                queue.offer(current.left);
            }
            index++;
            
            if (index < values.length && values[index] != null) {
                current.right = new TreeNode(values[index]);
                queue.offer(current.right);
            }
            index++;
        }
        
        return root;
    }
    
}
