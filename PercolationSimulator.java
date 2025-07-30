
public class PercolationSimulator {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: java PercolationSimulator (grid size) (number of trials) [-show]");
            return;
        }

        boolean show = false;

        if (args.length == 3) {
            if (!args[2].equals("-show")) {
                System.out.println("Usage: java PercolationSimulator (grid size) (number of trials) [-show]");
                return;
            }

            show = true;
        }

        int grid = 0;
        int trials = 0;

        try {
            grid = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        if (grid < 2) {
            System.out.println("Invalid grid size");
            return;
        }

        if (trials <= 0) {
            System.out.println("Invalid number of trials");
            return;
        }

        double sumP = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < trials; i++) {
            MonteCarloSimulation MCS = new MonteCarloSimulation(grid);

            if (show) {
                MCS.enableDisplay();
            }

            sumP += MCS.connectivityThreshold();
        }

        long endTime = System.nanoTime();

        System.out.println("Trial executed " + trials + " times, with a " + grid + "x" + grid + " grid");
        System.out.println("Connectivity threshold = " + sumP / trials);
        System.out.println("Elapsed time = " + (endTime - startTime) / 1000000000.0 + " seconds");
    }
}
