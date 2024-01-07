'''
This class automates the Java terminal usage by using each data set as input
to the make run rules
'''

from base_class import *

assignment_root_directory = str(pathlib.Path(__file__).parents[1])

class Experiment:
    def __init__(self):
        pass

    def run_shell_commands(self, subset: str) -> None:
        for ds in ['array', 'bst']:
            os.system(f"make run-{ds} IN=input/{subset} < input/{subset} > output/part_2/operations/{ds}_lookup_results_{subset.replace('.csv', '.txt')}")

    def create_subsets(self) -> None:
        pbar = tqdm(total=100)
        with open(f'{assignment_root_directory}/input/vaccinations.csv') as file:
            data_set = file.readlines()

            for n in range(991,9920, 991):
                subset = random.sample(data_set, n)

                with open(f'{assignment_root_directory}/input/subset_{n}.csv', 'w', encoding='utf-8') as new_subset:
                    new_subset.writelines(line for line in subset)
                    new_subset.write("\n")
                    pbar.update(10)
        pbar.close()
                
    def run_experiment(self) -> None:   
        pbar = tqdm(total=100)
        for n in range(991, 9920, 991):
                with open(f'{assignment_root_directory}/input/subset_{n}.csv') as subset:
                   self.run_shell_commands(os.path.basename(subset.name))
                   pbar.update(10)
        pbar.close()

        


if __name__ == '__main__':
    pass