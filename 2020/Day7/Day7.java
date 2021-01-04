package Day7;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import Globals.FileReader;

public class Day7 {
	static ArrayList<String> strings = FileReader.getFileContent("Day7//Bags"), bag = new ArrayList<>();
	static ArrayList<String[]> stringsArray = new ArrayList<>(), stringsArray2 = new ArrayList<>(),
			bagContained = new ArrayList<>();
	static ArrayList<DefaultMutableTreeNode> treeNodes = new ArrayList<>(), treeNodesNew = new ArrayList<>();
	static ArrayList<Integer[]> ints = new ArrayList<>();
	static HashSet<String> set = new HashSet<>();

	static JTree tree;
	static DefaultMutableTreeNode root = new DefaultMutableTreeNode();

	static DefaultMutableTreeNode node = new DefaultMutableTreeNode("shiny gold"), tempNode;
	static int nodeCount = 0;

	public static void main(String[] args) {
		String s = "";

		for (String string : strings) {
			s = string.replaceAll("(\\w*)([\\,.])", "").replaceAll("(\\d)", "").replaceAll("bags contain", "");
			stringsArray.add(s.split("(\\s{3})"));
			s = string.replaceAll("bags contain", "").replaceAll("bags", "").replaceAll("([\\,.])", " ")
					.replaceAll("bag", "").strip();
			stringsArray2.add(s.split("(\\s{2,3})"));
		}
		check("shiny gold");

		root.add(node);

		addNode(node);
		System.out.println(nodeCount);

		tree = new JTree(root);
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
		tree.setRootVisible(false);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new JScrollPane(tree));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		

	}

	private static void addNode(DefaultMutableTreeNode parentNode) {

		for (String[] strings : stringsArray2) {
			if (parentNode.getUserObject().equals(strings[0])) {
				for (int i = 1; i < strings.length; i++) {
					if (!strings[i].split(" ")[0].matches("no")) {
						for (int j = 0; j < Integer.parseInt(strings[i].split(" ")[0]); j++) {
							DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
									strings[i].split(" ")[1] + " " + strings[i].split(" ")[2]);
							parentNode.add(newNode);
							nodeCount++;
							addNode(newNode);
						}

					}
				}
			}
		}

	}

	private static void check(String search) {
		for (String[] strings2 : stringsArray) {
			for (int i = 1; i < strings2.length; i++) {
				if (strings2[i].contains(search)) {
					set.add(strings2[0]);
					check(strings2[0]);
				}
			}
		}
	}
}
