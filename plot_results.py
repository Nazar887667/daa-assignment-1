import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv("docs/results/results.csv")

algorithms = [
    "Merge Sort",
    "Quick Sort",
    "Deterministic Select",
    "Closest Pair"
]

input_types = [
    "Random",
    "Sorted",
    "Reverse-sorted",
    "Duplicate-heavy"
]

# ==========================================
# TIME VS N
# ==========================================

for algorithm in algorithms:

    algorithm_data = data[data["algorithm"] == algorithm]

    plt.figure(figsize=(10, 6))

    if algorithm == "Closest Pair":

        type_data = algorithm_data[
            algorithm_data["input_type"] == "Random points"
        ]

        plt.plot(
            type_data["input_size"],
            type_data["average_time_ns"],
            marker="o",
            label="Random points"
        )

    else:

        for input_type in input_types:

            type_data = algorithm_data[
                algorithm_data["input_type"] == input_type
            ]

            plt.plot(
                type_data["input_size"],
                type_data["average_time_ns"],
                marker="o",
                label=input_type
            )

    plt.xlabel("Input size (n)")
    plt.ylabel("Average execution time (ns)")
    plt.title(algorithm + " - Time vs. Input Size")
    plt.legend()
    plt.grid(True)
    plt.tight_layout()

    filename = (
        "docs/plots/"
        + algorithm.lower().replace(" ", "_")
        + "_time_vs_n.png"
    )

    plt.savefig(filename, dpi=300)
    plt.close()


# ==========================================
# RECURSION DEPTH VS N
# ==========================================

for algorithm in algorithms:

    algorithm_data = data[data["algorithm"] == algorithm]

    plt.figure(figsize=(10, 6))

    if algorithm == "Closest Pair":

        type_data = algorithm_data[
            algorithm_data["input_type"] == "Random points"
        ]

        plt.plot(
            type_data["input_size"],
            type_data["recursion_depth"],
            marker="o",
            label="Random points"
        )

    else:

        for input_type in input_types:

            type_data = algorithm_data[
                algorithm_data["input_type"] == input_type
            ]

            plt.plot(
                type_data["input_size"],
                type_data["recursion_depth"],
                marker="o",
                label=input_type
            )

    plt.xlabel("Input size (n)")
    plt.ylabel("Maximum recursion depth")
    plt.title(algorithm + " - Recursion Depth vs. Input Size")
    plt.legend()
    plt.grid(True)
    plt.tight_layout()

    filename = (
        "docs/plots/"
        + algorithm.lower().replace(" ", "_")
        + "_recursion_depth_vs_n.png"
    )

    plt.savefig(filename, dpi=300)
    plt.close()


print("All graphs created successfully.")