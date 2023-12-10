import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		// Load input
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		String steps = input[0];
		
		ArrayList<Node> nodes = new ArrayList<>();
		
		for(int i = 2; i < input.length; i++) {
			nodes.add(new Node(input[i].split("=")[0].trim()));
		}
		
		for(int i = 2; i < input.length; i++) {
			Node currentNode = getNode(input[i].split("=")[0].trim(), nodes);
			String[] lrNodes = input[i].substring(6).replaceAll("[()]| ", "").split(",");
			currentNode.setLeft(getNode(lrNodes[0], nodes));
			currentNode.setRight(getNode(lrNodes[1], nodes));
		}
		
		// Part 1
		
//		Node currentNode = getNode("AAA", nodes);
//		int numSteps = 0;
//		
//		while(!currentNode.getName().equals("ZZZ")) {
//			switch(steps.charAt(numSteps % steps.length())) {
//			case 'L':
//				currentNode = currentNode.getLeft();
//				break;
//			case 'R':
//				currentNode = currentNode.getRight();
//				break;
//			}
//			numSteps++;
//		}
//		
//		System.out.println("Part 1 - " + numSteps);
		
		// Part 2
		
		ArrayList<Node> startingNodes = new ArrayList<Node>();
		for(Node node: nodes) if(node.getName().charAt(2) == 'A') startingNodes.add(node);
		Node[] curNodes = startingNodes.toArray(new Node[0]);
		
		long numStepsPt2 = 0;
		
//		while(!nodesEndWithZs(curNodes)) {
//			int step = (int) (numStepsPt2 % steps.length());
//			switch(steps.charAt(step)) {
//			case 'L':
//				for(int i = 0; i < curNodes.length; i++)
//					curNodes[i] = curNodes[i].getLeft();
//				break;
//			case 'R':
//				for(int i = 0; i < curNodes.length; i++)
//					curNodes[i] = curNodes[i].getRight();
//				break;
//			}
//			numStepsPt2++;
//		}
		
		// Attempt 2
		
		int[] nodePeriods = new int[curNodes.length];
		
		for(int i = 0; i < curNodes.length; i++) {
			nodePeriods[i] = getPeriod(curNodes[i], nodes, steps) + 1;
		}
		
		System.out.println(Arrays.toString(nodePeriods));
		
		int result = nodePeriods[0];
		
		for(int i = 1; i < nodePeriods.length; i++) {
			result = (int) LCM(result, nodePeriods[i]);
		}
		
		System.out.println(result);
		
	}

	public static long LCM(long a, long b) {
        long largerValue = a;
        long smallerValue = b;
        if (b > a) {
            largerValue = b;/*  w ww.j a  v a  2  s. c  o  m*/
            smallerValue = a;
        }
        for (int i = 1; i <= largerValue; i++) {
            if ((largerValue * i) % smallerValue == 0) {
                return largerValue * i;
            }
        }
        return largerValue * smallerValue;
    }

	private static int getPeriod(Node startNode, ArrayList<Node> nodes, String steps) {
		int numSteps = 0;

		Node currentNode = startNode;

		while (currentNode.getName().charAt(2) != 'Z') {
			switch (steps.charAt(numSteps % steps.length())) {
			case 'L':
				currentNode = currentNode.getLeft();
				break;
			case 'R':
				currentNode = currentNode.getRight();
				break;
			}
			numSteps++;
		}

		System.out.println(currentNode);
		return numSteps;

	}

	private static boolean nodesEndWithZs(Node[] nodes) {
		boolean toRet = true;

		for (Node node : nodes) {
			toRet &= node.getName().charAt(2) == 'Z';
		}

		return toRet;
	}

	public static Node getNode(String name, ArrayList<Node> nodes) {
		for (Node node : nodes) {
			if (node.getName().equals(name)) {
				return node;
			}
		}
		return null;
	}

	static class Node {
		Node left, right;
		String name;

		public Node(String name) {
			this.name = name;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			String toRet = "";
			toRet += name + ":[";
			if (left != null) {
				toRet += left.getName();
			}
			toRet += ",";
			if (right != null) {
				toRet += right.getName();
			}
			toRet += "]";
			return toRet;
		}

	}

}
