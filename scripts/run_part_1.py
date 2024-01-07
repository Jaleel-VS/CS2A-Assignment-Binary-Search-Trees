'''
For automating part 1 of the assignment by redirecting each test 
set to the Java classes
'''

from base_class import *
from generate_random_input_data import run as generate_new_test_data

root_directory = str(pathlib.Path(__file__).parents[1])



def clean_up_data(data: str) -> None:
    """Removes the lines that contain the word 'operation'
    from part 2 of the assignment

    Args:
        data (str): string to be processed
    """    
    os.system(f'grep -v "operation" {data} > tmpfile && mv tmpfile {data}')

def run_shell_command():
    """Run part 1 of the experiment and export the results"""   

    for i in tqdm(range(1, 4)):
        for data_structure in ['array', 'bst']:
            os.system(f'make run-{data_structure} IN=input/vaccinations.csv < input/test_set_{i}.txt > output/part_1/{data_structure}_test_set_{i}.txt')
            clean_up_data(f'output/part_1/{data_structure}_test_set_{i}.txt')
        print(f'Data set {i} successfully processed')

            

if '__main__' == __name__:
    generate_new_test_data()
    run_shell_command()
