import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SudokuGenerator {
    public SudokuGenerator() {
        generate();
    }

    private void generate() {
        while (true) {
            SudokuGrid sudokuGrid = new SudokuGrid();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Press [Enter] to generate board | ");
            System.out.print("Type any character to quit");
            String in = scanner.nextLine();
            if(!in.isEmpty())
                break;
            System.out.println("Please wait...");
            System.out.println();
            reduce(sudokuGrid);
            if (sudokuGrid.collapsed()) {
                System.out.println(sudokuGrid);
            }
        }
    }

    public void reduce(SudokuGrid sudokuGrid) {
        // pick a box, b, with the least number of possibilities remaining,
        // randomly pick one of the possibilities, p, in that box
        int[] b = sudokuGrid.getLPBox();
        while (b != null) {
            int size = sudokuGrid.retrieve(b[0], b[1]).size();
            b = sudokuGrid.getLPBox();
            if (b != null) {
                int x = ThreadLocalRandom.current().nextInt(size);
                List<Integer> box = sudokuGrid.retrieve(b[0], b[1]);
                int value = box.get(x);
                if (sudokuGrid.next(sudokuGrid.valid(b[0], b[1], value))) {
                    sudokuGrid.set(b[0], b[1], value);
                }
            }
        }
    }
}