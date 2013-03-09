package stcs.logic;

public class RectangleMatrixCalculator {

	private RectangleMatrixCalculator() {
	}

	public static double[][] getOriginalMatrixApproximation(double[] rowsSums,
			double[] columnsSums, double delta) {
		int height = rowsSums.length;
		int width = columnsSums.length;

		double[][] output = new double[height][width];
		double initialValue = 0;

		// initializing with example initial value
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				initialValue = (rowsSums[i] + columnsSums[j])
						/ (width + height);
				// System.out.println("INITIAL_VAL=" + initialValue);
				output[i][j] = initialValue;
			}
		}

		// computations start
		boolean computationsEnd = false;
		boolean noUpdate = true;
		double diff = 0;
		double sum = 0;

		double min = 0;
		double max = 1;

		// int iters = 0;

		while (!computationsEnd) {

			// ++iters;

			noUpdate = true;

			for (int i = 0; i < height; ++i) { // for all rows

				diff = 0;
				sum = 0;

				for (int j = 0; j < width; ++j) {
					sum += output[i][j];
				}

				diff = (rowsSums[i] - sum) / height;

				if (Math.abs(diff) <= delta) {
					continue;
				} else {
					noUpdate = false;
				}

				for (int j = 0; j < width; ++j) {
					output[i][j] += diff;

				}
			}

			for (int i = 0; i < width; ++i) { // for all columns

				diff = 0;
				sum = 0;

				for (int j = 0; j < height; ++j) {
					sum += output[j][i];
				}

				diff = (columnsSums[i] - sum) / width;

				if (Math.abs(diff) <= delta) {
					continue;
				} else {
					noUpdate = false;
				}

				for (int j = 0; j < height; ++j) {
					output[j][i] += diff;
				}
			}

			if (noUpdate) {
				computationsEnd = true;
			}

			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					if (output[i][j] < min)
						output[i][j] = min;
					if (output[i][j] > max)
						output[i][j] = max;
				}
			}

		}

		System.out.println("\nOBRAZ PRZYBLI¯ONY:");
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				System.out.printf("%f%2s", output[i][j], "");
			}
			System.out.println();
		}
		System.out.println("##############################");

		return output;
	}
}
