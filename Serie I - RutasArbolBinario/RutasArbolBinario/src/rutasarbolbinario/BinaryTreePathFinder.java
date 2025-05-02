/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rutasarbolbinario;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class BinaryTreePathFinder {
    private TreeNode root;

    public BinaryTreePathFinder(TreeNode root) {
        this.root = root;
    }

    /**
     * Encuentra todas las rutas de raíz a hoja que suman el targetSum.
     *
     * @param targetSum Valor objetivo que debe sumar la ruta
     * @return Lista de rutas que cumplen con la suma
     */
    public List<List<Integer>> findPathsWithSum(int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        dfs(root, targetSum, new LinkedList<>(), result);
        return result;
    }

    /**
     * Recorrido en profundidad (DFS) para encontrar rutas válidas.
     *
     * @param node Nodo actual
     * @param remainingSum Suma restante para alcanzar el objetivo
     * @param currentPath Ruta actual desde la raíz
     * @param result Lista para almacenar rutas válidas
     */
    private void dfs(TreeNode node, int remainingSum,
            LinkedList<Integer> currentPath,
            List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        currentPath.add(node.val);
        remainingSum -= node.val;

        // Si es hoja y la suma coincide
        if (node.left == null && node.right == null && remainingSum == 0) {
            result.add(new ArrayList<>(currentPath));
        }

        // Recorrer subárboles
        dfs(node.left, remainingSum, currentPath, result);
        dfs(node.right, remainingSum, currentPath, result);

        // Backtracking
        currentPath.removeLast();
    }

}
