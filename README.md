# Colored Sliding Puzzle

## Overview
This project extends the classic [Sliding Puzzle](https://en.wikipedia.org/wiki/Sliding_puzzle) game with colored pieces,
where each color carries its own unique role:

- **Green Pieces (default)** - slide with a low cost of 1 point.
- **Red Pieces** - slide with a higher cost of 30 points, requiring careful planning.
- **Gray Pieces** - stay in place, making it a bit tricky.

The empty piece, also called the empty space, is where you slide the pieces around.

## Objective
Your goal is to reach the target pattern by sliding pieces through the empty space.
Enhance the challenge by minimizing the overall cost in your strategic moves.

## Search Algorithms
Discover efficient ways to optimally solve the Colored Sliding Puzzle, using the following search algorithms:

- **Uninformed Search Algorithms** - BFS (Breadth-First Search) and IDDFS (Iterative-Deepening Depth-First Search).
- **Informed Search Algorithms** - A*, IDA* (Iterative-Deepening A*) and DFBnB (Depth-First Branch and Bound).

Read more about these algorithms to enhance your approach in solving the puzzle.

## Input File Format
To set up the game, open the input file and follow these steps:

- **Algorithm Name (Line 1)** - choose your algorithm (BFS / IDDFS / A* / IDA* / DFBnB).
- **Dimension (Line 2)** - set puzzle dimensions in NxM form, where N is the number of rows and M is the number of columns.
- **Gray and Red Pieces (Lines 3-4)** - assign numbers to indicate gray and red pieces. Use commas to separate multiple numbers for each color, and leave each section empty if there are no corresponding pieces. Keep in mind that pieces are green by default.
- **Initial Board (Starting from Line 5)** - lay down the foundation of your puzzle with numbers representing the board, based on the defined dimensions. Make sure there is a single empty space represented by an underscore.

Please verify the accuracy of your setup, as it plays a crucial role in achieving a successful solution.

## Installation
Here is a quick guide to help you get started:

- **Clone Repository** - clone this repository to your device.
- **Open IDE** - launch your Integrated Development Environment.
- **Input File** - fill in the input file following the specified format.
- **Run Project** - execute the project using the Main class.
- **Check Results** - explore the results in the automatically generated output file.

Enjoy your solving journey!

## Project Constraints
This project is subject to the following constraints:

- **Dimensions** - use small dimensions (no more than 4x4) to ensure a reasonable search time.
- **Optimal Solution** - some initial setups lack an optimal solution, even in small dimensions. In such scenarios, all suggested algorithms will fail.

Although this code is not perfect, it's a positive step in the right direction.

## License
This puzzle operates under [MIT](https://github.com/Yahavk94/Colored-Sliding-Puzzle/blob/master/LICENSE) license.
