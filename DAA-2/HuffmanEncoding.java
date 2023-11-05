import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node {
    Node left;
    Node right;
    Character value;
    int frequency;

    Node(Character value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.frequency = left.frequency + right.frequency;
    }

    boolean isLeaf() {
        return left == null && right == null;
    }
}

public class HuffmanEncoding {
    private String inputString;
    private Map<Character, String> encoding = new HashMap<>();
    private PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

    HuffmanEncoding(String inputString) {
        this.inputString = inputString;
    }

    private void calculateCharFrequency() {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : inputString.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.add(node);
        }
    }

    private void buildTree() {
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node newNode = new Node(left, right);
            queue.add(newNode);
        }
    }

    private void generateHuffmanCodes(Node node, String binaryString) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            encoding.put(node.value, binaryString);
        }

        generateHuffmanCodes(node.left, binaryString + "0");
        generateHuffmanCodes(node.right, binaryString + "1");
    }

    private void huffmanEncoding() {
        Node root = queue.poll();
        generateHuffmanCodes(root, "");
    }

    private void printEncoding() {
        System.out.println("Char | Huffman code");
        for (Map.Entry<Character, String> entry : encoding.entrySet()) {
            System.out.printf(" %-4c |%12s%n", entry.getKey(), entry.getValue());
        }
    }

    public void encode() {
        calculateCharFrequency();
        buildTree();
        huffmanEncoding();
        printEncoding();
    }

    public static void main(String[] args) {
        String inputString = "AAAAAAABBCCCCCCDDDEEEEEEEEE";
        HuffmanEncoding encoder = new HuffmanEncoding(inputString);
        encoder.encode();
    }
}
