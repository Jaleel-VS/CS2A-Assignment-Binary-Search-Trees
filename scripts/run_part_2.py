from experiment import Experiment
import summarise
from data_analysis import DataAnalysis

if __name__ == '__main__':
    new_experiment = Experiment()
    print("Creating subsets...")
    new_experiment.create_subsets()
    print("Automating Java operations...")
    new_experiment.run_experiment()
    print("Summarising results...")
    summarise.create_summary()
    print("Analyzing data...")
    da = DataAnalysis()
    da.run_analysis()
    print("Results sucessfully exported")
