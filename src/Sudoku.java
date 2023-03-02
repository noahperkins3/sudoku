public class Sudoku {

    int[][] fullGrid;

    public Sudoku() {
        fullGrid = new int[9][9];
        for (int[] grid : fullGrid) {
            for (int cell : grid) {
                cell = 0;
            }
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            builder.append("\n");
            if (i % 3 == 0) {
                builder.append("\n");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    builder.append("  ");
                } else {
                    builder.append(" ");
                }
                switch (fullGrid[i][j]) {
                    case 0 -> builder.append("/");
                    case 1 -> builder.append("1");
                    case 2 -> builder.append("2");
                    case 3 -> builder.append("3");
                    case 4 -> builder.append("4");
                    case 5 -> builder.append("5");
                    case 6 -> builder.append("6");
                    case 7 -> builder.append("7");
                    case 8 -> builder.append("8");
                    case 9 -> builder.append("9");
                }
            }
        }
        return builder.toString();
    }

    // position will be #s 1-81, representing each tile
    public void addNumber(int num, int position) {
        if (isValidNum(num, position)) {
            fullGrid[position / 9][position % 9] = num;
        } else {
            System.out.println("Invalid placement!");
        }
    }

    private boolean isValidNum(int num, int position) {
        if (fullGrid[position / 9][position % 9] > 0) {
            return false;
        }
        // checks row
        for (int cell : fullGrid[position/9]) {
            if (num == cell) {
                return false;
            }
        }
        // checks column
        for (int i = 0; i < 9; i++) {
            if (fullGrid[i][position%9] == num) {
                return false;
            }
        }
        // 3x3 cell group
        int[] group = new int[9];
        // 1st, 2nd, or 3rd column slice
        int rowThird = (position / 9) / 3;
        // 1st, 2nd, or 3rd row slice
        int colThird = (position % 9) / 3;
        int index = 0;
        // offset the thirds to grab correct indices
        if (colThird == 1) {
            colThird = 3;
        } else if (colThird == 2) {
            colThird = 6;
        }
        if (rowThird == 1) {
            rowThird = 3;
        } else if (rowThird == 2) {
            rowThird = 6;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                group[index] = fullGrid[rowThird][colThird];
                index++;
            }
        }
        for (int cell : group) {
            if (num == cell) {
                return false;
            }
        }
        return true;
    }
}
