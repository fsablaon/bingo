package org.fs.bingo;

public class BingoTestUtil {

	/**
	 * Create a 3x3 matrix of unmarked nodes
	 * @return matrix of unmarked nodes
	 */
	public static Node[][] createMatrix(){
		Node[][] matrix = new Node[3][3];

		Node[] row0 = new Node[3];
		row0[0] = createNode(1);
		row0[1] = createNode(2);
		row0[2] = createNode(3);

		Node[] row1 = new Node[3];
		row1[0] = createNode(4);
		row1[1] = createNode(5);
		row1[2] = createNode(6);

		Node[] row2 = new Node[3];
		row2[0] = createNode(7);
		row2[1] = createNode(8);
		row2[2] = createNode(9);

		matrix[0] = row0;
		matrix[1] = row1;
		matrix[2] = row2;

		return matrix;
	}

	public static Node createNode(int value){
		return new Node(value);
	}
}
